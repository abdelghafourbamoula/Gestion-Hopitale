/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hopitale;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author abdelghafour
 */
public class AdminGestCatController implements Initializable {


    @FXML
    private TextField nomCat;
    @FXML
    private TextField designationCat;
    @FXML
    private TextField numCat;
    @FXML
    private TextField chefCat;
    @FXML
    private Button bAnnuler;
    @FXML
    private Button bAjouter;
    @FXML
    private TextField rechercherCat;
    @FXML
    private Button bRechercherCat;
    @FXML
    private Button bSupprimer;
    @FXML
    private Button bStart;
    @FXML
    private Button bEnd;
    @FXML
    private Button bPrivious;
    @FXML
    private Button bNext;
    @FXML
    private TableView<?> tabCat;
    @FXML
    private Button bModifier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
