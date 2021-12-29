package hopitale;

import hopitale.Categorie;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
//import javafx.scene.control.Cel.PropertyValueFactory;


public class AdminGestCategorieController implements Initializable  {

    @FXML
    private Button bAjouter;

    @FXML
    private Button bAnnuler;


    @FXML
    private Button bModifier;


    @FXML
    private Button bRechercherCat;


    @FXML
    private Button bSupprimer;

    @FXML
    private TextField chefCat;

    @FXML
    private TableColumn<Categorie, String> colDesignation;

    @FXML
    private TableColumn<Categorie, Integer> colId;

    @FXML
    private TableColumn<Categorie, String> colNomChef;

    @FXML
    private TableColumn<Categorie, Integer> colNombreChambre;

    @FXML
    private TextField designationCat;


    @FXML
    private TextField numCat;

    @FXML
    private TextField rechercherCat;

    @FXML
    private TableView<Categorie> tabCat;
    @FXML
    private TextField IdCat;
    
    @FXML 
    private Label LblMessageError ;    
    
    @FXML 
    private Label LblMessageSuccees ;
    
    private Label LblMsgRch ;

    @FXML
    private Line tfSearchButtom;
     
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        String requete ="SELECT * FROM categorie" ;
        ShowCateg(requete);
         //delete btn
        ImageView delImg = new ImageView(new Image("images/delete.png"));
        delImg.setFitHeight(15); delImg.setFitWidth(15);
        bSupprimer.setGraphic(delImg);
        bSupprimer.setText("");
        //search btn
        ImageView searchImg = new ImageView(new Image("images/loupe.png"));
        searchImg.setFitHeight(20); searchImg.setFitWidth(20);
        bRechercherCat.setGraphic(searchImg);
        bRechercherCat.setText("");
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
    public ObservableList<Categorie> getCategorieList(String Query){
        ObservableList<Categorie> CateList = FXCollections.observableArrayList();
        Connection cnt = getConnection();
	String requete = Query ;
        Statement stmt ;  
        ResultSet reslt ; 
        try{
        stmt = cnt.createStatement();
        reslt = stmt.executeQuery(requete);
        Categorie categorie ; 
         while(reslt.next()){
             categorie = new Categorie(reslt.getInt("Id_Categorie") , reslt.getString("Designation") , reslt.getInt("Nombre_Chembre") , reslt.getString("Nom_Chef")); 
             CateList.add(categorie) ;
         }
        }catch(Exception exp){
        System.out.println("----------- Errer : " + exp.getMessage());
        }
       return CateList ; 
    } 
    
//    affiche categorie 
    public void ShowCateg(String requete){
    ObservableList<Categorie> DataList = getCategorieList(requete) ;
    colId.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("Id_Categorie"));
    colDesignation.setCellValueFactory(new PropertyValueFactory<Categorie , String>("Designation"));
    colNombreChambre.setCellValueFactory(new PropertyValueFactory<Categorie , Integer>("Nombre_Chembre"));
    colNomChef.setCellValueFactory(new PropertyValueFactory<Categorie , String>("Nom_Chef"));    
    tabCat.setItems(DataList);
            }
    
//    gestion d'evenements
    @FXML
    public void InsertLigne(Event e){
//    Etablir la connection 
 String requete = "SELECT * FROM Categorie" ;
try{
    Connection cnt = getConnection();
    Statement stmt ; 
    String query = "insert into Categorie values( "
            + IdCat.getText()+ ","
            + "'"+designationCat.getText()+"',"
            + "" + numCat.getText() + "," 
            + "'" + chefCat.getText() + "');";
    stmt = cnt.createStatement();
    stmt.executeUpdate(query);
//    message de succees 
    LblMessageSuccees.setText(" L'insertion bien Enregistrer ");
//    remplir la table
    ShowCateg(requete);
    }catch(Exception exc){
        LblMessageError.setText(" L'insertion mal passer ");
       
       System.out.println(exc);
       ShowCateg(requete);
}
}
    @FXML
     public void AnnulerData(Event e){
         
    String requete = "SELECT * FROM Categorie" ;
try{
        IdCat.setText("");
        designationCat.setText("");
        numCat.setText("");
        chefCat.setText("");
        LblMessageSuccees.setText("");
        ShowCateg(requete);
    }catch(Exception exc){       
       exc.printStackTrace();
       ShowCateg(requete);
}
}
/* gestion de clique sur la table 
      public void ClickTable(Event e){
try{
        IdCat.setText("");
        designationCat.setText("");
        numCat.setText("");
        chefCat.setText("");
        ShowCateg();
    }catch(Exception exc){       
       exc.printStackTrace();
       ShowCateg();
}
}
  */
//     recherche par la designation : 
    @FXML
     public void Chercher(Event e){
         try{
            String requete = "SELECT * FROM Categorie WHERE LOWER(Designation)='"+rechercherCat.getText()+"';" ;
            ShowCateg(requete); 
            rechercherCat.setText("");
         }catch(Exception ex){
             System.out.println(" >>>> Erreur : " + ex.getMessage());
             LblMsgRch.setText("No result...");
             LblMsgRch.setLayoutX(100);
             LblMsgRch.setLayoutX(10);
             ShowCateg("SELECT * FROM Categorie");
         }
     }
     
//     charger les donnees au inputs 
    @FXML
     public void TrasferTableData(MouseEvent event){
         if(event.getClickCount() > 1 ){
     Categorie categorie = tabCat.getSelectionModel().getSelectedItem();
      IdCat.setText(Integer.toString(categorie.getId_Categorie()));
      designationCat.setText(categorie.getDesignation());
      numCat.setText(Integer.toString(categorie.getNombre_Chembre()));
      chefCat.setText(categorie.getNom_Chef());
//         System.out.println("id" + categorie.getId_Categorie() +"desi" + categorie.getDesignation());
     }
     }
     @FXML
     public void supprimerData(Event e){
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirm.setContentText("Voulez vous supprimer cette categorie");
        alertConfirm.initModality(Modality.APPLICATION_MODAL);
        String requete = "SELECT * FROM categorie" ;
        alertConfirm.showAndWait().ifPresent(reponse -> {
        if(reponse == ButtonType.OK){   
            try{
                if(IdCat.getText() != null){
                    String query = "DELETE FROM categorie WHERE Id_Categorie = " + IdCat.getText() + " ;";
                    Connection cnt = getConnection();
                    Statement stmt ; 
                    stmt = cnt.createStatement();
                    stmt.executeUpdate(query);
                    ShowCateg(requete);
                    LblMessageSuccees.setText("  Ligne bien supprimer ");
                  LblMessageError.setText("");
                 }
            }catch(Exception exc){       
                exc.printStackTrace();  
            }
        }
        });
        ShowCateg(requete);
}
// ---------- Updating data  -------
      @FXML
     public void updateData(Event e){
           String requete = "SELECT * FROM categorie" ;
           
    try{
      if(IdCat.getText() != null){
        String query = "UPDATE categorie SET Designation = '"
                + designationCat.getText() + "' , Nombre_Chembre ='"
                + numCat.getText() + "' , Nom_Chef = '"
                + chefCat.getText() + "'"
                +" WHERE Id_Categorie = '" + IdCat.getText() + "';";
        Connection cnt = getConnection();
        Statement stmt ; 
        stmt = cnt.createStatement();
        stmt.executeUpdate(query);
        ShowCateg(requete);
        LblMessageSuccees.setText("L'enregisterement est modifier");
       }
    }catch(Exception exc){       
       exc.printStackTrace();
       ShowCateg(requete);
    }
}
     
    @FXML
    private void setButtomColored(KeyEvent event) {
        if(rechercherCat.getText().isEmpty()){
            tfSearchButtom.setStyle("-fx-stroke: #9a9a9a");
        }else{
            tfSearchButtom.setStyle("-fx-stroke:  #00ace6");
        }
    }


 

}
