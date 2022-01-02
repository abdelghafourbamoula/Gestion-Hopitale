
package hopitale;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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
import javafx.stage.Stage;


public class AdminGestInfirmierController implements Initializable{
    @FXML
    private Button bAnnuler;

    @FXML
    private Button bEnregistrer;

    @FXML
    private Button bModifier;

    @FXML
    private Button bRechercherMed;

    @FXML
    private Button bSupprimer;

    @FXML
    private RadioButton rbF;

    @FXML
    private RadioButton rbM;

    @FXML
    private ToggleGroup sexe;

    @FXML
    private TextField tfAdresse;

    @FXML
    private TextField tfCin;

    @FXML
    private TextField tfEmail;

    @FXML
    private DatePicker tfNais;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfPrenom;

    @FXML
    private TextField tfRechercherMed;

    @FXML
    private Line tfSearchButtom;

    @FXML
    private TextField tfSpecialite;

    @FXML
    private TextField tfTele;
    
    @FXML
    private TableView<Medcin> tableMedcin;
    @FXML
    private TableColumn<Medcin, String> colCin;
    @FXML
    private TableColumn<Medcin, String> colNom;
    @FXML
    private TableColumn<Medcin, String> colPrenom;
    @FXML
    private TableColumn<Medcin, String> colNaisance;
    @FXML
    private TableColumn<Medcin, String> colSpecialite;
    @FXML
    private TableColumn<Medcin, String> colAdresse;
    @FXML
    private TableColumn<Medcin, String> colEmail;
    @FXML
    private TableColumn<Medcin, String> colTele;
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Aficher les medcins exists par la methode 
        showMedcins("");
        //delete btn
        ImageView delImg = new ImageView(new Image("images/delete.png"));
        delImg.setFitHeight(15); delImg.setFitWidth(15);
        bSupprimer.setGraphic(delImg);
        bSupprimer.setText("");
        //search btn
        ImageView searchImg = new ImageView(new Image("images/loupe.png"));
        searchImg.setFitHeight(20); searchImg.setFitWidth(20);
        bRechercherMed.setGraphic(searchImg);
        bRechercherMed.setText("");
    }    

    @FXML
    private void EnregistrerMedcin(ActionEvent event) {
        if(event.getSource() == bEnregistrer){
            RadioButton selSexe = (RadioButton) sexe.getSelectedToggle();
            Connection con = getConnection();

            String query = "INSERT INTO Infirmier VALUES ("+
                                "\""+tfCin.getText()+"\", "+
                                "\""+tfNom.getText()+"\", "+
                                "\""+tfPrenom.getText()+"\", "+
                                "\""+selSexe.getText()+"\", "+
                                "\""+tfNais.getValue()+"\", "+
                                "\""+tfSpecialite.getText()+"\", "+
                                "\""+tfTele.getText()+"\", "+
                                "\""+tfEmail.getText()+"\", "+
                                "\""+tfAdresse.getText()+"\", "+
                                "\""+tfPassword.getText()+"\""+
                            ");";
            System.out.println(query);
            try{
                Statement st = con.createStatement();
                st.executeUpdate(query);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        showMedcins("");        
    }

    @FXML
    private void AnnulerFentre(ActionEvent event) {
        tfCin.setText(null);
        tfNom.setText(null);
        tfPrenom.setText(null);
//        tfNais.setValue(LocalDate.parse("", DateTimeFormatter.ISO_DATE));
        tfSpecialite.setText(null);
        tfTele.setText(null);
        tfEmail.setText(null);
        tfAdresse.setText(null);
        tfPassword.setText(null);
                    
    }

    @FXML
    private void RechercherMedcin(ActionEvent event) {
        if(tfRechercherMed.getText() != ""){
            showMedcins("WHERE LOWER(nom) = '"+tfRechercherMed.getText()+"'");
        } else {
            showMedcins("");
        }
    }

    @FXML
    private void SupprimerMedcin(ActionEvent event) {
        Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
        alertConfirm.setContentText("Voulez vous supprimer "+
                                    tableMedcin.getSelectionModel().getSelectedItem().getNom()+
                                    " "+tableMedcin.getSelectionModel().getSelectedItem().getPrenom());
        alertConfirm.initModality(Modality.APPLICATION_MODAL);
        Connection con = getConnection();
        String query = "DELETE FROM Infirmier Where cin='"+tableMedcin.getSelectionModel().getSelectedItem().getCin()+"';";
        alertConfirm.showAndWait().ifPresent(reponse -> {
            if(reponse == ButtonType.OK){   
                try{
                    Statement st = con.createStatement();
                    st.executeUpdate(query);
                } catch(Exception ex){
                    System.out.println(">>> SQL Exception :"+ex.getMessage());
                }
            }
        });
       
        showMedcins("");
    }

    @FXML
    private void ModifierMedcin(ActionEvent event) {
        Connection con = getConnection();
        RadioButton selSexe = (RadioButton) sexe.getSelectedToggle();
        String query =  "UPDATE Infirmier SET "+
                        "cin='"+tfCin.getText()+"', "+
                        "nom='"+tfNom.getText()+"', "+
                        "prenom='"+tfPrenom.getText()+"', "+
                        "sexe='"+selSexe.getText()+"', "+
                        "naissance='"+tfNais.getValue()+"', "+
                        "specialite='"+tfSpecialite.getText()+"', "+
                        "tele='"+tfTele.getText()+"', "+
                        "email='"+tfEmail.getText()+"', "+
                        "adresse='"+tfAdresse.getText()+"', "+
                        "password='"+tfPassword.getText()+"' "+
                        " WHERE cin='"+tfCin.getText()+"' ;";
        try{
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch(Exception ex){
            System.out.println(">>> SQL Exception :"+ex.getMessage());
        }
        showMedcins("");
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
    

    public void showMedcins(String condition){
        //criee une Fx array list pour les medcins
        ObservableList<Medcin> listMedcin = FXCollections.observableArrayList();
        //etablire la connexion et load data
        Connection con = getConnection();
        String query = "SELECT * FROM Infirmier "+condition+" ;";
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            Medcin medcin;
            while(rs.next()){
                 medcin = new Medcin(rs.getString("cin"),
                                    rs.getString("nom"),
                                    rs.getString("prenom"),
                                    rs.getString("sexe"),
                                    rs.getString("naissance"),
                                    rs.getString("specialite"),
                                    rs.getString("tele"),
                                    rs.getString("email"),
                                    rs.getString("adresse"),
                                    rs.getString("password")                        
                                );
                listMedcin.add(medcin);
            }
            
        } catch(SQLException ex){
            System.out.println(">>> SQL Exception :"+ex.getMessage());
        }
        
        //determiner les valeurs des colones
        colCin.setCellValueFactory(new PropertyValueFactory<Medcin, String>("cin"));
        colNom.setCellValueFactory(new PropertyValueFactory<Medcin, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Medcin, String>("prenom"));      
        colNaisance.setCellValueFactory(new PropertyValueFactory<Medcin, String>("naissance"));
        colSpecialite.setCellValueFactory(new PropertyValueFactory<Medcin, String>("specialite"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Medcin, String>("adresse"));
        colTele.setCellValueFactory(new PropertyValueFactory<Medcin, String>("tele"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Medcin, String>("email"));
        
        // ajouter la liste comme item de la table
        tableMedcin.setItems(listMedcin);
    }
    
    @FXML
    private void getSeletedValues(MouseEvent event) {
        if(event.getClickCount() > 1 && tableMedcin.getSelectionModel().getSelectedItem() != null){
            Medcin selectedMedcin = tableMedcin.getSelectionModel().getSelectedItem();
            tfCin.setText(selectedMedcin.getCin());
            tfNom.setText(selectedMedcin.getNom());
            if(selectedMedcin.getSexe().equals("Male")){
                rbM.setSelected(true);
            }else {
                rbF.setSelected(true);
            }
            tfNais.setValue(LocalDate.parse(selectedMedcin.getNaissance(), DateTimeFormatter.ISO_DATE));
            tfPrenom.setText(selectedMedcin.getPrenom());
            tfSpecialite.setText(selectedMedcin.getSpecialite());
            tfTele.setText(selectedMedcin.getTele());
            tfEmail.setText(selectedMedcin.getEmail());
            tfAdresse.setText(selectedMedcin.getAdresse());
            tfPassword.setText(selectedMedcin.getPassword());
            
           
        }
    }

    @FXML
    private void setButtomColored(KeyEvent event) {
        if(tfRechercherMed.getText().isEmpty()){
            tfSearchButtom.setStyle("-fx-stroke: #9a9a9a");
        }else{
            tfSearchButtom.setStyle("-fx-stroke:  #00ace6");
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
