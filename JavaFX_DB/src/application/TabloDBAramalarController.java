package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import java.sql.*;
import com.JavaMySQL.Util.VeritabaniUtil;

public class TabloDBAramalarController {

	//constructor ile veritabanýna baglantý olusturma
	public TabloDBAramalarController() {
		baglanti = VeritabaniUtil.Baglan();
	}
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_arama;

    @FXML
    private ComboBox<String> combo_user;

    @FXML
    private DatePicker dateBaslangic;

    @FXML
    private DatePicker dateBitis;

    @FXML
    private Button btn_arama;

    @FXML
    private TableView<Kayitlar_islem> tableview_Aramalar;

    @FXML
    private TableColumn<Kayitlar_islem, Integer> column_ID;

    @FXML
    private TableColumn<Kayitlar_islem, String> column_kul_ad;

    @FXML
    private TableColumn<Kayitlar_islem, String> column_aciklama;

    @FXML
    private TableColumn<Kayitlar_islem, Double> column_tutar;

    @FXML
    private TableColumn<Kayitlar_islem, LocalDate> column_tarih;
    
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
    void btn_arama_Click(ActionEvent event) {
    	sql = "select * from islemler where islemTarih > '"+dateBaslangic.getValue()+"' and islemTarih < '"+dateBitis.getValue()+"'";
    	DegerleriGetir(tableview_Aramalar, sql);
    	
    	
    	sql = "select * from islemler where islemTarih >? and islemTarih <? ";
    	
    	
    	try {
    		sorguIfadesi = baglanti.prepareStatement(sql);
        	sorguIfadesi.setDate(1, Date.valueOf(dateBaslangic.getValue()));
        	DegerleriGetir2(tableview_Aramalar, sorguIfadesi);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    }

    @FXML
    void combo_user_OnAction(ActionEvent event) {

    	sql = "select * from islemler where user = 'furkan'";
    	DegerleriGetir(tableview_Aramalar, sql);
    	
    }

    @FXML
    void txt_arama_Action(ActionEvent event) {
    	
    }

    @FXML
    void txt_arama_KeyPressed(KeyEvent event) {
    	if(txt_arama.getText().equals("")) {
    		sql = "select * from islemler";
    	}
    	else {
    		sql = "select * from islemler where islemAciklama like '%"+txt_arama.getText()+"%' or user like '%"+txt_arama.getText()+"%'";
    	}
    	
    	DegerleriGetir(tableview_Aramalar, sql);
    }
    
    public void DegerleriGetir(TableView tablo, String sql) {
    	//sql = "select * from islemler";
    	//tableview icerisine observablelist þeklinde atama yaparsýn
    	ObservableList<Kayitlar_islem> kayitlar_liste = FXCollections.observableArrayList();
    	//kayitlar_liste'ye teker teker alacagýz, bir sorun olmazsa bunu satýr satýr okuyup yazacak
    	try {
    		sorguIfadesi = baglanti.prepareStatement(sql);
    		ResultSet getirilen = sorguIfadesi.executeQuery();
    		//gelen sorgularý teker teker almak icin while döngüsü kullanýyoruz
    		while(getirilen.next()) {
    			kayitlar_liste.add(new Kayitlar_islem(getirilen.getInt("islemID"), getirilen.getString("user"), getirilen.getString("islemAciklama"), getirilen.getDouble("islemTutar"), getirilen.getDate("islemTarih")));
    		}
    		column_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
    		column_kul_ad.setCellValueFactory(new PropertyValueFactory<>("kul_ad"));
    		column_aciklama.setCellValueFactory(new PropertyValueFactory<>("islemAciklama"));
    		column_tarih.setCellValueFactory(new PropertyValueFactory<>("islem_Tarih"));
    		column_tutar.setCellValueFactory(new PropertyValueFactory<>("ucret"));
    		
    		//setItems diyerek oluþturmuþ oldugumuz observablelist'i ya da diziyi ya da listeyi parametre olarak gönderiyoruz; bu sayede bütün deðerlerin gelmesini saðlýyorduk
    		tableview_Aramalar.setItems(kayitlar_liste);
    		
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    
    public void DegerleriGetir2(TableView tablo, PreparedStatement sorgu) {
    	//sql = "select * from islemler";
    	//tableview icerisine observablelist þeklinde atama yaparsýn
    	ObservableList<Kayitlar_islem> kayitlar_liste = FXCollections.observableArrayList();
    	//kayitlar_liste'ye teker teker alacagýz, bir sorun olmazsa bunu satýr satýr okuyup yazacak
    	try {
    		//sorguIfadesi = baglanti.prepareStatement(sql);
    		ResultSet getirilen = sorgu.executeQuery();
    		//gelen sorgularý teker teker almak icin while döngüsü kullanýyoruz
    		while(getirilen.next()) {
    			kayitlar_liste.add(new Kayitlar_islem(getirilen.getInt("islemID"), getirilen.getString("user"), getirilen.getString("islemAciklama"), getirilen.getDouble("islemTutar"), getirilen.getDate("islemTarih")));
    		}
    		column_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
    		column_kul_ad.setCellValueFactory(new PropertyValueFactory<>("kul_ad"));
    		column_aciklama.setCellValueFactory(new PropertyValueFactory<>("islemAciklama"));
    		column_tarih.setCellValueFactory(new PropertyValueFactory<>("islem_Tarih"));
    		column_tutar.setCellValueFactory(new PropertyValueFactory<>("ucret"));
    		
    		//setItems diyerek oluþturmuþ oldugumuz observablelist'i ya da diziyi ya da listeyi parametre olarak gönderiyoruz; bu sayede bütün deðerlerin gelmesini saðlýyorduk
    		tableview_Aramalar.setItems(kayitlar_liste);
    		
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    

    @FXML
    void initialize() {
    	sql = "select * from islemler";
        DegerleriGetir(tableview_Aramalar, sql);
        
        dateBaslangic.setValue(LocalDate.now());
        dateBitis.setValue(LocalDate.now());
    }
}
