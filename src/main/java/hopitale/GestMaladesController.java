
package hopitale;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.stage.Modality;


public class GestMaladesController implements Initializable {


    @FXML
    private TextField MaladeNom;
    @FXML
    private TextField MaladeCin;
    @FXML
    private TextField MaladeAdresse;
    @FXML
    private TextField MaladeTele;
    @FXML
    private DatePicker NaissanceMalade;
    @FXML
    private Button btnAnnulerMalade;
    @FXML
    private Button btnAjouterMallade;
    @FXML
    private TextField NumeroDossier;
    private Label LblMsgSuccees;
    @FXML
    private TableView<Malades> tabMalades;
    
    @FXML
    private TableColumn<Malades, Integer> colNumDossier;
        
    @FXML
    private TableColumn<Malades, String> colCin;
    @FXML
    private TableColumn<Malades, String> colNom;
    @FXML
    private TableColumn<Malades, String> colTele;
    @FXML
    private TableColumn<Malades, String> colAdresse;
    @FXML
    private TableColumn<Malades, String> colMedecin;
    @FXML
    private TableColumn<Malades, String> colStatus;
   
    @FXML
    private RadioButton rbM;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private RadioButton rbF;
    @FXML
    private ChoiceBox<String> MedecinChoice;
    @FXML
    private ChoiceBox<String> tatusChoice;
    @FXML
    private TextField tfRechercherMal;
   
    @FXML
    private Button bSupprimer;
    @FXML
    private Button bModifier;
    @FXML
    private Line tfSearchButtom;
    @FXML
    private Button bRechercherMal;
    @FXML
    private MenuItem toMed;
    @FXML
    private MenuItem toInf;
    @FXML
    private MenuItem toMal;
    @FXML
    private MenuItem toCat;
    @FXML
    private MenuItem toCat1;
    @FXML
    private MenuItem vis;
    @FXML
    private MenuItem dec;
    
        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String requete = "select * from malades;";
        ShowMalades(requete);
        //throw new UnsupportedOperationException("Not supported yet.");
        //choiceBox Medcin
        MedcinCoices();
        
        // status
        ObservableList<String> statusList = FXCollections.observableArrayList("en cours","sain","mort");
        tatusChoice.setItems(statusList);
        
         //delete btn
        ImageView delImg = new ImageView(new Image("images/delete.png"));
        delImg.setFitHeight(15); delImg.setFitWidth(15);
        bSupprimer.setGraphic(delImg);
        bSupprimer.setText("");
        //search btn
        ImageView searchImg = new ImageView(new Image("images/loupe.png"));
        searchImg.setFitHeight(20); searchImg.setFitWidth(20);
        bRechercherMal.setGraphic(searchImg);
        bRechercherMal.setText("");
    }
    
    
    public void MedcinCoices(){
        ObservableList<String> MedcinList = FXCollections.observableArrayList();
        Connection con = getConnection();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Medcin;");
            while(rs.next()){
                  MedcinList.addAll(rs.getString("nom")+" "+rs.getString("prenom"));
            }
        } catch(SQLException ex){
            System.out.println(">>> SQL: "+ex.getMessage());
        }
        MedecinChoice.setItems(MedcinList);
    }
    
     //    Etablir la connection 
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
   
    //  voir les data 
    public ObservableList<Malades> getMaladesList(String Query){
        ObservableList<Malades> MalList = FXCollections.observableArrayList();
        Connection cnt = getConnection();
	String requete = Query ;
        Statement stmt ;  
        ResultSet reslt ; 
        try{
        stmt = cnt.createStatement();
        reslt = stmt.executeQuery(requete);
        Malades malades ; 
        while(reslt.next()){
            malades = new Malades(reslt.getInt("NumDossier") ,
                                   reslt.getString("cin") ,
                                   reslt.getString("nom") ,
                                   reslt.getString("sexe"),
                                   reslt.getString("naissance") ,
                                   reslt.getString("tele"),
                                   reslt.getString("adresse"),
                                   reslt.getString("status"),
                                   reslt.getString("medecin") 
                                ); 
             
            MalList.add(malades) ;

         }
        }catch(Exception exp){
        System.out.println("----------- Erreur : " + exp.getMessage());
        }
       return MalList ; 
    } 
    
    //    affiche categorie 
public void ShowMalades(String requete){
    ObservableList<Malades> MaladesInfo = getMaladesList(requete) ;
    colNumDossier.setCellValueFactory(new PropertyValueFactory<Malades, Integer>("NumDossier"));
    colCin.setCellValueFactory(new PropertyValueFactory<Malades, String>("cin"));
    colNom.setCellValueFactory(new PropertyValueFactory<Malades , String>("nom"));   
    colTele.setCellValueFactory(new PropertyValueFactory<Malades , String>("tele")); 
    colAdresse.setCellValueFactory(new PropertyValueFactory<Malades , String>("adresse")); 
    colMedecin.setCellValueFactory(new PropertyValueFactory<Malades , String>("medecin")); 
    colStatus.setCellValueFactory(new PropertyValueFactory<Malades , String>("status"));   
    
    tabMalades.setItems(MaladesInfo);
}
    
//  gestion Evenement  
    @FXML
    private void InsertRow(ActionEvent event) {

    // Etablir la connection 
     String requete = "select * from malades" ;
    try{
        Connection cnt = getConnection();
        Statement stmt ; 
        if(NumeroDossier.getText() != null){
        RadioButton selSexe = (RadioButton) sexe.getSelectedToggle();

        String query = "insert into Malades values("
                + NumeroDossier.getText()+ ","                
                + "'" + MaladeCin.getText() + "',"
                + "'" + MaladeNom.getText() + "',"
                + "'" + selSexe.getText() + "',"
                + "'" + NaissanceMalade.getValue() + "',"
                + "'" + MaladeTele.getText() + "',"
                + "'" + MaladeAdresse.getText() + "',"
                + "'" + tatusChoice.getValue() + "' ," 
                + "'" + MedecinChoice.getValue() + "'"
                + ");";

        stmt = cnt.createStatement();
        stmt.executeUpdate(query);
    // add rows to table
        ShowMalades(requete);
        }
        }catch(Exception exc){
      
            System.out.println(".................... Insertion Error " + exc.getMessage());
            ShowMalades(requete);
}
    }
    

    
    
//    mofier 
      @FXML
    void ModifierRow(ActionEvent event) {
    
    
//    corp 
   String requete = "SELECT * FROM Malades" ;
           
    try{
      if(NumeroDossier.getText() != null){
        RadioButton selSexe = (RadioButton) sexe.getSelectedToggle();
        String query = "UPDATE malades SET "
                    +"cin = '" + MaladeCin.getText() + "', "
                    + "nom = '" + MaladeNom.getText() + "', "
                    + "sexe = '" + selSexe.getText() + "', "
                    + "naissance = '" + NaissanceMalade.getValue() + "', "
                    + "tele = '" + MaladeTele.getText() + "', "
                    + "adresse = '" + MaladeAdresse.getText() + "', "
                    + "medecin = '" + MedecinChoice.getValue() + "', "
                    + "status = '" + tatusChoice.getValue() + "' " 
                + " WHERE NumDossier = " + NumeroDossier.getText() + " ;";
        Connection cnt = getConnection();
        Statement stmt ; 
        stmt = cnt.createStatement();
        stmt.executeUpdate(query);
        ShowMalades(requete);
       }
    }catch(Exception exc){       
        System.out.println(">>> SQL:"+exc.getMessage());
       ShowMalades(requete);
    }
}
     
// vider les textFiel
    @FXML
 public void viderInfo(Event e){
         
    String requete = "SELECT * FROM malades" ;
try{
        NumeroDossier.setText("");
        MaladeCin.setText("");
        MaladeNom.setText("");
        NaissanceMalade.setValue(null);
        MaladeAdresse.setText("");
        MaladeTele.setText("");
        MaladeTele.setText("");
        MedecinChoice.setValue("");
        tatusChoice.setValue("");
        LblMsgSuccees.setText("");
        
       ShowMalades(requete);
    }catch(Exception ep){       
        System.out.println(" ............ Cherche : " + ep.getMessage());
       ShowMalades(requete);
}
} 
 
// supprimer une row
      @FXML
     public void suppRow(Event e){
           String requete = "SELECT * FROM malades" ;
           
    
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirm.setContentText("Voulez vous supprimer "+
                                    tabMalades.getSelectionModel().getSelectedItem().getNom());
        alertConfirm.initModality(Modality.APPLICATION_MODAL);
        Connection cnt = getConnection();
        String Myquery = "DELETE FROM malades WHERE NumDossier = " + NumeroDossier.getText() + ";";
        
        alertConfirm.showAndWait().ifPresent(reponse -> {      
        try{
            Statement stmt ; 
            stmt = cnt.createStatement();
            stmt.executeUpdate(Myquery);
            ShowMalades(requete);
       
        }catch(Exception ecp){       
            ecp.printStackTrace();
            
        } 
    });
        ShowMalades(requete);
}

    @FXML
    private void setButtomColored(KeyEvent event) {
        if(tfRechercherMal.getText().isEmpty()){
            tfSearchButtom.setStyle("-fx-stroke: #9a9a9a");
        }else{
            tfSearchButtom.setStyle("-fx-stroke:  #00ace6");
        }
    }

    @FXML
    private void RechercherMedcin(ActionEvent event) {
        try{
            String requete = "SELECT * FROM Malades WHERE LOWER(nom)='"+tfRechercherMal.getText()+"' ;" ;
            ShowMalades(requete); 
         }catch(Exception ex){
             System.out.println(" ............ Cherche : " + ex.getMessage());
         }
    }

    @FXML
    private void getSeletedValues(MouseEvent event) {
        if(event.getClickCount() > 1 ){
                Malades mal = tabMalades.getSelectionModel().getSelectedItem();

                Malades malades = tabMalades.getSelectionModel().getSelectedItem();
                NumeroDossier.setText(String.valueOf(malades.getNumDossier()));
                MaladeNom.setText(malades.getNom());
                MaladeCin.setText(malades.getCin());
                MaladeAdresse.setText(malades.getAdresse());               
                NaissanceMalade.setValue(LocalDate.parse(mal.getNaissance(), DateTimeFormatter.ISO_DATE));
                if(mal.getSexe().equals("Male")){
                    rbM.setSelected(true);
                }else {
                    rbF.setSelected(true);
                }
                MaladeTele.setText(malades.getTele());
                tatusChoice.setValue(mal.getStatus());
                MedecinChoice.setValue(mal.getMedecin());
     }
    }

  @FXML
    private void toMed(ActionEvent event) {
        Main.setRoot("AdminGestMedecins");
    }

    @FXML
    private void toInf(ActionEvent event) {
                Main.setRoot("AdminGestInfirmier");

    }

    @FXML
    private void toMal(ActionEvent event) {
                        Main.setRoot("GestMaladies");

    }

    @FXML
    private void toCat(ActionEvent event) {
                        Main.setRoot("AdminGestCategorie");

    }

    @FXML
    private void toVis(ActionEvent event) {
                        Main.setRoot("visualisation");

    }

    @FXML
    private void toDec(ActionEvent event) {
                        Main.setRoot("Authentification");

    }
}