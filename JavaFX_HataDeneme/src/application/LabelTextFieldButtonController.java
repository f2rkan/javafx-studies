package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LabelTextFieldButtonController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private TextField txt_Hiz1;

    @FXML
    private TextField txt_Hiz2;

    @FXML
    private TextField text_Hiz3;

    @FXML
    private Button btn_hesapla;

    @FXML
    private Label lbl_sonuc;

    @FXML
    private Button btn_temizle;

    @FXML
    void btn_hesapla_click(ActionEvent event) {
    	
    	double hiz1, hiz2, sure;
    	hiz1 = Double.parseDouble(txt_Hiz1.getText());
    	hiz2 = Double.parseDouble(txt_Hiz2.getText());
    	sure = Double.parseDouble(text_Hiz3.getText());
    	
    	double sonuc = (hiz1 - hiz2) * sure;
    	if (sonuc <= 0) {
			sonuc = Math.abs(sonuc);
		}
    	lbl_sonuc.setText(String.valueOf(sonuc) + " km");
    	//Double.ToString(sonuc);
    }

    @FXML
    void btn_temizle_click(ActionEvent event) {
    	lbl_sonuc.setText(null);
    	txt_Hiz1.setText(null);
    	txt_Hiz2.clear();
    	text_Hiz3.clear();
    }

    @FXML
    void initialize() {
        assert anchor1 != null : "fx:id=\"anchor1\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txt_Hiz1 != null : "fx:id=\"txt_Hiz1\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txt_Hiz2 != null : "fx:id=\"txt_Hiz2\" was not injected: check your FXML file 'Sample.fxml'.";
        assert text_Hiz3 != null : "fx:id=\"text_Hiz3\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btn_hesapla != null : "fx:id=\"btn_hesapla\" was not injected: check your FXML file 'Sample.fxml'.";
        assert lbl_sonuc != null : "fx:id=\"lbl_sonuc\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btn_temizle != null : "fx:id=\"btn_temizle\" was not injected: check your FXML file 'Sample.fxml'.";

    }
}
