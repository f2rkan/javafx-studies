package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FormUygulama1Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_anamenu;

    @FXML
    private Button btn_iletisim;

    @FXML
    private Button btn_uyeler;

    @FXML
    private Button btn_ayarlar;

    @FXML
    private Button btn_cikis;

    @FXML
    void btn_anamenu_Click(ActionEvent event) {

    }

    @FXML
    void btn_ayarlar_Click(ActionEvent event) {

    }

    @FXML
    void btn_cikis_Click(ActionEvent event) {

    }

    @FXML
    void btn_iletisim_Click(ActionEvent event) {

    }

    @FXML
    void btn_uyeler_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btn_anamenu != null : "fx:id=\"btn_anamenu\" was not injected: check your FXML file 'FormUygulama1.fxml'.";
        assert btn_iletisim != null : "fx:id=\"btn_iletisim\" was not injected: check your FXML file 'FormUygulama1.fxml'.";
        assert btn_uyeler != null : "fx:id=\"btn_uyeler\" was not injected: check your FXML file 'FormUygulama1.fxml'.";
        assert btn_ayarlar != null : "fx:id=\"btn_ayarlar\" was not injected: check your FXML file 'FormUygulama1.fxml'.";
        assert btn_cikis != null : "fx:id=\"btn_cikis\" was not injected: check your FXML file 'FormUygulama1.fxml'.";

    }
}
