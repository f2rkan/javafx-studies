package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.JavaMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.*;

public class TabloDBController {

	//kurucusu icinde direkt baglant� kurmak, initialize() ile ugrasman� keser; internet baglant�s� bunlardan biridir
		public TabloDBController() {
			//class ilk cal�st�r�ld�g�nda buraya d��er
			//olusturdugun baglant�ya ana baglant�y� ata
			baglanti = VeritabaniUtil.Baglan();
		}
		
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Kayitlar_login> tableview_kayitlar;

    @FXML
    private TableColumn<Kayitlar_login, Integer> col_id;

    @FXML
    private TableColumn<Kayitlar_login, String> col_kul;

    @FXML
    private TableColumn<Kayitlar_login, String> col_sifre;

    @FXML
    private TableColumn<Kayitlar_login, String> col_yetki;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_sifre;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_yetki;

    @FXML
    private Button btn_refresh;

    Connection baglanti = null;
    //bir sorgu ifadesi olusturman laz�m
    //parametre gondericez sorguya
    PreparedStatement sorguIfadesi = null;
    //varolan sonuclar� getirmek icin ResultSet kullan�rs�n
    //c#'taki dataset'le ayn� isi yapar
    ResultSet getirilen = null;
    //bir de sql olusturuyoruz
    String sql;
    @FXML
    void btn_refresh_Click(ActionEvent event) {
    	DegerleriGetir(tableview_kayitlar);
    }
    //benim buraya g�nderdi�im table view yap�s�n� doldurup yeniden bana return etsin
    public void DegerleriGetir(TableView tablo) {
    	sql = "select * from login";
    	//tableview icerisine observablelist �eklinde atama yapars�n
    	ObservableList<Kayitlar_login> kayitlar_liste = FXCollections.observableArrayList();
    	//kayitlar_liste'ye teker teker alacag�z, bir sorun olmazsa bunu sat�r sat�r okuyup yazacak
    	try {
    		sorguIfadesi = baglanti.prepareStatement(sql);
    		ResultSet getirilen = sorguIfadesi.executeQuery();
    		//gelen sorgular� teker teker almak icin while d�ng�s� kullan�yoruz
    		while(getirilen.next()) {
    			kayitlar_liste.add(new Kayitlar_login(getirilen.getInt("kID"), getirilen.getString("kul_ad"), getirilen.getString("sifre"), getirilen.getInt("yetki")));
    		}
    		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    		col_kul.setCellValueFactory(new PropertyValueFactory<>("sutun_kulad"));
    		col_sifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
    		col_yetki.setCellValueFactory(new PropertyValueFactory<>("yetki"));
    		//setItems diyerek olu�turmu� oldugumuz observablelist'i ya da diziyi ya da listeyi parametre olarak g�nderiyoruz; bu sayede b�t�n de�erlerin gelmesini sa�l�yorduk
    		tableview_kayitlar.setItems(kayitlar_liste);
    		
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    @FXML
    void tableview_MouseClick(MouseEvent event) {
    	Kayitlar_login kayit = new Kayitlar_login();
    	//secili olan kayd� sat�r olarak nesneye atmak
    	//get(tableview_kayitlar.getSelectionModel().getSelectedIndex(): hangi indisteki degeri sectiysen o degeri getirir
    	//tipimiz de Kayitlar_login
    	kayit = (Kayitlar_login) tableview_kayitlar.getItems().get(tableview_kayitlar.getSelectionModel().getSelectedIndex());
    	//setter i�lemleri
    	txt_kul.setText(kayit.getSutun_kulad());
    	txt_sifre.setText(kayit.getSifre());
    	lbl_id.setText(String.valueOf(kayit.getId()));
    	if(kayit.getYetki() == 0) {
    		lbl_yetki.setText("normal kullan�c�");
    	}
    	else if(kayit.getYetki() == 1) {
    		lbl_yetki.setText("y�netici");
    	}
    }

    @FXML
    void initialize() {
        DegerleriGetir(tableview_kayitlar);
    }
}
