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

	//kurucusu icinde direkt baglantý kurmak, initialize() ile ugrasmaný keser; internet baglantýsý bunlardan biridir
		public TabloDBController() {
			//class ilk calýstýrýldýgýnda buraya düþer
			//olusturdugun baglantýya ana baglantýyý ata
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
    //bir sorgu ifadesi olusturman lazým
    //parametre gondericez sorguya
    PreparedStatement sorguIfadesi = null;
    //varolan sonuclarý getirmek icin ResultSet kullanýrsýn
    //c#'taki dataset'le ayný isi yapar
    ResultSet getirilen = null;
    //bir de sql olusturuyoruz
    String sql;
    @FXML
    void btn_refresh_Click(ActionEvent event) {
    	DegerleriGetir(tableview_kayitlar);
    }
    //benim buraya gönderdiðim table view yapýsýný doldurup yeniden bana return etsin
    public void DegerleriGetir(TableView tablo) {
    	sql = "select * from login";
    	//tableview icerisine observablelist þeklinde atama yaparsýn
    	ObservableList<Kayitlar_login> kayitlar_liste = FXCollections.observableArrayList();
    	//kayitlar_liste'ye teker teker alacagýz, bir sorun olmazsa bunu satýr satýr okuyup yazacak
    	try {
    		sorguIfadesi = baglanti.prepareStatement(sql);
    		ResultSet getirilen = sorguIfadesi.executeQuery();
    		//gelen sorgularý teker teker almak icin while döngüsü kullanýyoruz
    		while(getirilen.next()) {
    			kayitlar_liste.add(new Kayitlar_login(getirilen.getInt("kID"), getirilen.getString("kul_ad"), getirilen.getString("sifre"), getirilen.getInt("yetki")));
    		}
    		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    		col_kul.setCellValueFactory(new PropertyValueFactory<>("sutun_kulad"));
    		col_sifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
    		col_yetki.setCellValueFactory(new PropertyValueFactory<>("yetki"));
    		//setItems diyerek oluþturmuþ oldugumuz observablelist'i ya da diziyi ya da listeyi parametre olarak gönderiyoruz; bu sayede bütün deðerlerin gelmesini saðlýyorduk
    		tableview_kayitlar.setItems(kayitlar_liste);
    		
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    @FXML
    void tableview_MouseClick(MouseEvent event) {
    	Kayitlar_login kayit = new Kayitlar_login();
    	//secili olan kaydý satýr olarak nesneye atmak
    	//get(tableview_kayitlar.getSelectionModel().getSelectedIndex(): hangi indisteki degeri sectiysen o degeri getirir
    	//tipimiz de Kayitlar_login
    	kayit = (Kayitlar_login) tableview_kayitlar.getItems().get(tableview_kayitlar.getSelectionModel().getSelectedIndex());
    	//setter iþlemleri
    	txt_kul.setText(kayit.getSutun_kulad());
    	txt_sifre.setText(kayit.getSifre());
    	lbl_id.setText(String.valueOf(kayit.getId()));
    	if(kayit.getYetki() == 0) {
    		lbl_yetki.setText("normal kullanýcý");
    	}
    	else if(kayit.getYetki() == 1) {
    		lbl_yetki.setText("yönetici");
    	}
    }

    @FXML
    void initialize() {
        DegerleriGetir(tableview_kayitlar);
    }
}
