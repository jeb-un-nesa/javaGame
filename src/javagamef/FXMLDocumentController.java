/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagamef;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;



/**
 *
 * @author jamal
 */
public class FXMLDocumentController implements Initializable {
    
  
    
    @FXML
    private Button btn_Start;
    @FXML
    private Button btn_Level;
    @FXML
    private Button btn_Exit;
    private Stage Stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handle_Action_Start(ActionEvent event) throws Exception {
        Stage currentstage = (Stage) btn_Start.getScene().getWindow();
        Level1 c = new Level1();
        c.start(currentstage);
    }

    @FXML
    private void handle_Action_Level(ActionEvent event) throws IOException {
         Stage currentstage = (Stage) btn_Level.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/javagamef/SceneLevel.fxml"));
            Scene scene = new Scene(root);
            currentstage.setTitle("TIKTIK");
            currentstage.setScene(scene);
            currentstage.show();
    }

    @FXML
    private void handle_Action_Exit(ActionEvent event) {
         Stage stage = (Stage) btn_Exit.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    
}
