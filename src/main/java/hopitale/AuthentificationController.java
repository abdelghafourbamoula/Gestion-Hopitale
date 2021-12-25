
package hopitale;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.stage.Stage;



public class AuthentificationController implements Initializable {

    @FXML
    private Button bQuitter;
    @FXML
    private TextField adminIdentif;
    @FXML
    private PasswordField adminPassword;
    @FXML
    private Button bConnAdmin;
    @FXML
    private Label lbMsg;
    @FXML
    private Line adminIdentifButtom;
    @FXML
    private Line adminPasswordButtom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // quitter btn image
        ImageView quitImg = new ImageView(new Image("images/logout.png"));
        quitImg.setFitHeight(20); quitImg.setFitWidth(20);
        bQuitter.setGraphic(quitImg);
        bQuitter.setText("");
        //progress
        ProgressIndicator progInd = new ProgressIndicator(0);
        progInd.setLayoutX(50); progInd.setLayoutY(50);
        progInd.setProgress(20);
        
    }   
    
    @FXML
    private void Quitter(ActionEvent event) {
        Alert alertExit = new Alert(AlertType.CONFIRMATION);
        alertExit.setContentText("Vous etes en trains de quitter cette Application");
        alertExit.showAndWait().ifPresent(reponse -> {
            if (reponse == ButtonType.OK){
                Stage stage = (Stage) bQuitter.getScene().getWindow();
                stage.close();
            }
        });
    }

    @FXML
    private void AdminConnection(ActionEvent event) {
        if( adminIdentif.getText().equals("Admin001") && adminPassword.getText().equals("admin")){
            try{
                Main.setRoot("AdminGestMedecins");
       
            }
            catch(IOException e){
                System.out.println(".........."+e);
            }
            
        } else{
            lbMsg.setText("Identifient / Mot de passe Incorrect !");
            lbMsg.setStyle("-fx-background-color: #fad4d4; -fx-padding: 5");
        }
    }

    @FXML
    private void adminIdentifTyped(KeyEvent event) {
        setButtomColored(adminIdentif, adminIdentifButtom);
    }

    @FXML
    private void adminPsswordTyped(KeyEvent event) {
        setButtomColored(adminPassword, adminPasswordButtom);
    }
    
    public void setButtomColored(TextField tf, Line line) {
     if(tf.getText().isEmpty()){
         line.setStyle("-fx-stroke: #000000");
     }else{
         line.setStyle("-fx-stroke: #2d9ed7");
     }
    }

}
