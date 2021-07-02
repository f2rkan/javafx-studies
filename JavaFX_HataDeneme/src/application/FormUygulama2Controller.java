package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FormUygulama2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_formVeri;

    public void VeriAl(String icerik) {
    	lbl_formVeri.setText(icerik);
    }
    
    @FXML
    void initialize() {
        assert lbl_formVeri != null : "fx:id=\"lbl_formVeri\" was not injected: check your FXML file 'FormUygulama2.fxml'.";

    }
}
