
package hopitale;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    private static Scene scene;
    
    @Override
    public void start(Stage primaryStage) {
        
        try{
            scene = new Scene(loadFXML("Authentification"));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hopitale");
            primaryStage.show();
        } catch(IOException e){
            System.out.println("............ Main: "+e);
        }
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        return FXMLLoader.load(Main.class.getResource(fxml+".fxml"));
    }
    
    static void setRoot(String fxml) {
        try{
            scene.setRoot(loadFXML(fxml));
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
