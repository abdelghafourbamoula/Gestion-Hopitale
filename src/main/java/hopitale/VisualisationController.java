
package hopitale;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author abdelghafour
 */
public class VisualisationController implements Initializable {

    @FXML
    private Label NbrMedecins;
    @FXML
    private Label NbrMedecinsMale;
    @FXML
    private Label NbrMedecinsFemale;
    @FXML
    private BarChart<String, Number> MedecinChart;
    @FXML
    private Label NbrInfermiers;
    @FXML
    private Label NbrInfermiersMale;
    @FXML
    private Label NbrInfermiersFemale;
    @FXML
    private BarChart<String, Number> InfermiersChart;
    @FXML
    private Label NbrMalades;
    @FXML
    private Label NbrMaladesMale;
    @FXML
    private Label NbrMaladesFemale;
    @FXML
    private BarChart<?, ?> MaladesChart;
    @FXML
    private PieChart MaladesPie;
    @FXML
    private NumberAxis nombre_des_medecins;
    @FXML
    private CategoryAxis medecin_specialites;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // nombre des medcins
       String nbrMed = countElements("SELECT COUNT(*) FROM Medcin ;", "COUNT(*)");
       NbrMedecins.setText(nbrMed);
       
       String nbrMedM = countElements("SELECT COUNT(*) FROM Medcin WHERE LOWER(sexe)='male' ;", "COUNT(*)");
       NbrMedecinsMale.setText(nbrMedM);
       
       String nbrMedF = countElements("SELECT COUNT(*) FROM Medcin WHERE LOWER(sexe)='female' ;", "COUNT(*)");
       NbrMedecinsFemale.setText(nbrMedF);
       
       //Medcin chart
       String medcinChartquery = "SELECT specialite, COUNT(cin) as nbrMed FROM Medcin GROUP BY specialite;";
       traceChart(MedecinChart, medcinChartquery, "nombre des medcins", "specialite", "nbrMed");
        
       
       // nombre des Infermiers
       String nbrInf = countElements("SELECT COUNT(*) FROM Infirmier ;", "COUNT(*)");
       NbrInfermiers.setText(nbrInf);
       
       String nbrInfM = countElements("SELECT COUNT(*) FROM Infirmier WHERE LOWER(sexe)='male' ;", "COUNT(*)");
       NbrInfermiersMale.setText(nbrInfM);
       
       String nbrInfF = countElements("SELECT COUNT(*) FROM Infirmier WHERE LOWER(sexe)='female' ;", "COUNT(*)");
       NbrInfermiersFemale.setText(nbrInfF);
       
       //Infermier Chart
       String InferChartquery = "SELECT specialite AS diplome, COUNT(cin) AS nbrInfer FROM Infirmier GROUP BY specialite;";
       traceChart(InfermiersChart, InferChartquery, "nombre des infirmieres", "diplome", "nbrInfer");
        
    }    
    
        public Connection getConnection(){
        Connection con = null;
        try{
            String url = "jdbc:sqlite:/home/abdelghafour/NetBeansProjects/Gestion-Hopitale/Hopitale.db";
            con = DriverManager.getConnection(url);
        }
        catch(SQLException ex){
            System.out.println(">>> SQL Exception :"+ex.getMessage());
        }
        
        return con;
    }
        
    public String countElements(String countQuery, String get){
        Connection con =  getConnection();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(countQuery);
            return rs.getString(get);
        }
        catch(SQLException e){
            System.out.println(">>>SQL "+e.getMessage());
            return null;
        }
    }
    
    public void traceChart(BarChart barchart,String query, String name, String key, String value){
        Connection con = getConnection();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(name);
            
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(key), rs.getInt(value)));
            }
            
            barchart.getData().add(series);
        }
        catch(SQLException ex){
            System.out.println(">>> SQL:"+ex.getMessage());
        }

    }
    
}
