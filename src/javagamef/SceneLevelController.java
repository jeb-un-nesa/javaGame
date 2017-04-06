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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jamal
 */
public class SceneLevelController implements Initializable {

    @FXML
    private Button level1;
    @FXML
    private Button btn_level2;
    @FXML
    private Button btn_level3;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handle_action_level1(ActionEvent event) throws Exception {
        Stage currentstage = (Stage) level1.getScene().getWindow();
        Level11 c = new Level11();
        c.start(currentstage);
    }

    @FXML
    private void handle_acction_level2(ActionEvent event) throws Exception {
        Stage currentstage = (Stage) btn_level2.getScene().getWindow();
        level2 c = new level2();
        c.start(currentstage);
            
    }

    @FXML
    private void handle_action_level3(ActionEvent event) throws Exception {
        Stage currentstage = (Stage) btn_level3.getScene().getWindow();
        Level3 c = new Level3();
        c.start(currentstage);
    }

    @FXML
    private void handle_acction_back(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/javagamef/FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            currentstage.setTitle("TIKTIK");
            currentstage.setScene(scene);
            currentstage.show();
    }
    
}
