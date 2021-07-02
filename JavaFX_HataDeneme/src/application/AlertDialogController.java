package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class AlertDialogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txt_pass;

    @FXML
    private TextField txt_kul;

    @FXML
    private Button btn_alertInput;

    @FXML
    private Button btn_combo;

    @FXML
    private Button btn_giris;

    @FXML
    private Button btn_alertBilgi;

    @FXML
    private Button btn_alertHata;

    @FXML
    private Button btn_alertSoru;

    @FXML
    private Button btn_cikis;

    @FXML
    void btn_alertBilgi_Click(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Otomasyon Denemesi");
    	alert.setHeaderText("Bilgi Mesajý....");
    	alert.setContentText("Bu bir bilgi mesajýdýr; merhaba");
    	alert.showAndWait();
    }

    @FXML
    void btn_alertHata_Click(ActionEvent event) {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Otomasyon Denemesi ERROR");
    	alert.setHeaderText("ERROR Mesajý....");
    	alert.setContentText("Bu bir ERROR mesajýdýr; merhaba");
    	alert.showAndWait();
    }

    @FXML
    void btn_alertInput_Click(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog("textInputDialodg");
    	dialog.setTitle("deneme input dialog");
    	dialog.setHeaderText("textInput Header");
    	dialog.setContentText("telefona gelen onay kodunu gir");
    	
    	Optional<String> sonuc = dialog.showAndWait();
    	if(sonuc.isPresent()) {
    		System.out.println("Girilen metin " + sonuc.get());
    	}
    }

    @FXML
    void btn_alertSoru_Click(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("setTitle kýsmý");
    	alert.setHeaderText("onay kutusu");
    	alert.setContentText("silmek istedigine emin misin?");
    	
    	ButtonType btn1 = new ButtonType("evet");
    	ButtonType btn2 = new ButtonType("hayýr");
    	ButtonType btn3 = new ButtonType("iptal", ButtonData.CANCEL_CLOSE);
    	ButtonType btn4 = new ButtonType("tamam", ButtonData.OK_DONE);
    	
    	alert.getButtonTypes().setAll(btn1, btn2, btn3);
   
    	Optional<ButtonType> sonuc = alert.showAndWait();
    	
    	if(sonuc.get() == btn1) {
    		System.out.println("evet butonuna basýldý");
    	}
    	else if(sonuc.get() == btn2) {
    		System.out.println("hayýr butonuna basýldý");
    	}
    	else if(sonuc.get() == btn3) {
    		System.out.println("iptal butonuna basýldý");
    	}
    }
    public void LoginKontrol(String kul, String sifre) {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("setTitle kýsmý");
    	alert.setHeaderText("hata mesajý kutusu");
    		
    	if(!kul.isEmpty() && !sifre.isEmpty()) {
    		if(kul.contains("@furkan.edu.tr") && kul.length() > 13) {
    			if(sifre.contains("=") || sifre.contains("'") || sifre.contains("\\x75") || sifre.contains("\\x25")) {
    				alert.setContentText("sql injection tespit edildi");
    				System.exit(0);
    			}
    			else {
    				if(sifre.length() < 8) {
    					alert.setContentText("sifre 8 karakterden kucuk olamaz");
    				}
    				else {
    					alert.setContentText("giris basarili");
    				}
    			}
    		}
    		else {
    			alert.setContentText("furkan uzantýlý mail adresiyle girilmelidir.");
    		}
    	}
    	else {
			alert.setContentText("kullanici adi ve sifre bos gecilemez");
		}
    	
    	alert.showAndWait();
    }

    @FXML
    void btn_cikis_Click(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void btn_combo_Click(ActionEvent event) {
    	List<String> sec = new ArrayList<>();
    	sec.add("1. Seçenek");
    	sec.add("2. Seçenek");
    	sec.add("3. Seçenek");
    	
    	ChoiceDialog<String> dialog = new ChoiceDialog<String>("2. Seçenek", sec);
    	Optional<String> sonuc = dialog.showAndWait();
    	if(sonuc.isPresent()) {
    		System.out.println("Secilen" + sonuc.get());
    	}
    }

    @FXML
    void btn_giris_Click(ActionEvent event) {
    		LoginKontrol(txt_kul.getText(), txt_pass.getText());
    }

    @FXML
    void initialize() {
        
    }
}
