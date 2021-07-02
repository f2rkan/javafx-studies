package application;

import java.net.URL;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FormUygulama_Ana_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor_tum;

    @FXML
    private AnchorPane anchor_sol;

    @FXML
    private AnchorPane anchor_sag;

    @FXML
    private Button btn_ic;

    @FXML
    private Button btn_dis;

    @FXML
    private Button btn_menu;

    @FXML
    private Button btn_trans_dis;

    @FXML
    void btn_dis_Click(ActionEvent event) {
    	//stage olarak dis form olustur
    	try {
    		Stage stage1 = new Stage();
			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("FormUygulama2.fxml"));
			Scene scene = new Scene(pane1);
			stage1.setScene(scene);
			stage1.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	
    	//dis forma veri aktarmak
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("FormUygulama2.fxml"));
    		AnchorPane pane2 = (AnchorPane) loader.load();
    		FormUygulama2Controller nesne = loader.getController();
    		
    		Scene scene2 = new Scene(pane2);
    		nesne.VeriAl(" Merhaba ");
    		
    		Stage stage2 = new Stage();
    		stage2.setScene(scene2);
    		stage2.getIcons().add(new Image("file:yatay_logo.png"));
    		//stage2.getIcons().add(new Image(getClass().getResourceAsStream("file://Users//furkan//eclipse-workspace//JavaFX_HataDeneme//src//application//yatay_logo.png")));
    		stage2.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    

    @FXML
    void btn_ic_Click(ActionEvent event) {

    	try {
			AnchorPane panel = (AnchorPane) FXMLLoader.load(getClass().getResource("FormUygulama2.fxml"));
			anchor_sag.getChildren().setAll(panel);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

    @FXML
    void btn_menu_Click(ActionEvent event) {

    	try {
			AnchorPane panel = (AnchorPane) FXMLLoader.load(getClass().getResource("FormUygulama1.fxml"));
			anchor_sol.getChildren().setAll(panel);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

    @FXML
    void btn_trans_dis_Click(ActionEvent event) {

    	Stage stage1 = new Stage();
    	try {
    		
    		AnchorPane panel = (AnchorPane) FXMLLoader.load(getClass().getResource("FormUygulama2.fxml"));
			Scene scene1 = new Scene(panel);
			stage1.setScene(scene1);
			//formun transparanlýgýný ayarlama
			stage1.setOpacity(0.8);
			//forma baþlýk atama
			stage1.setTitle("deneme title");
			//formun ekran merkezinde çalýþtýrýlmasý
			stage1.centerOnScreen();
			//form gösterim biçimleri
			stage1.initStyle(StageStyle.DECORATED); //varsayýlan -- css'e bagýmlý
			stage1.initStyle(StageStyle.UNDECORATED); //menü tuþlarý olmadan gösterim
			stage1.initStyle(StageStyle.TRANSPARENT); //tamamen transparan
			stage1.initStyle(StageStyle.UNIFIED); //varsayýlan
			stage1.initStyle(StageStyle.UTILITY); //sadece kapama butonu gorunur
			
			stage1.getIcons().add(new Image("file:yatay_logo.png"));
			//stage1.getIcons().add(new Image(getClass().getResourceAsStream("file:/Users/furkan/eclipse-workspace/JavaFX_HataDenem/src/application/yatay_logo.png")));
			stage1.show();
			
			//full screen - full ekran göstermek
			//stage1.setFullScreen(true);
    	} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        assert anchor_tum != null : "fx:id=\"anchor_tum\" was not injected: check your FXML file 'FormUygulama_Ana.fxml'.";
        assert anchor_sol != null : "fx:id=\"anchor_sol\" was not injected: check your FXML file 'FormUygulama_Ana.fxml'.";
        assert anchor_sag != null : "fx:id=\"anchor_sag\" was not injected: check your FXML file 'FormUygulama_Ana.fxml'.";
        assert btn_ic != null : "fx:id=\"btn_ic\" was not injected: check your FXML file 'FormUygulama_Ana.fxml'.";
        assert btn_dis != null : "fx:id=\"btn_dis\" was not injected: check your FXML file 'FormUygulama_Ana.fxml'.";
        assert btn_menu != null : "fx:id=\"btn_menu\" was not injected: check your FXML file 'FormUygulama_Ana.fxml'.";
        assert btn_trans_dis != null : "fx:id=\"btn_trans_dis\" was not injected: check your FXML file 'FormUygulama_Ana.fxml'.";

    }
}
