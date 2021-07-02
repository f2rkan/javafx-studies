package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.JavaMySQL.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;

public class SampleController {
	
	//kurucusu icinde direkt baglant� kurmak, initialize() ile ugrasman� keser; internet baglant�s� bunlardan biridir
	public SampleController() {
		//class ilk cal�st�r�ld�g�nda buraya d��er
		//olusturdugun baglant�ya ana baglant�y� ata
		baglanti = VeritabaniUtil.Baglan();
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_login;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_sifre;

    @FXML
    private Label lbl_sonuc;
    
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
    void btn_ekle_Click(ActionEvent event) {
    	sql = "insert into login(kul_ad, sifre, yetki) values(?, ?, ?)";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(2, VeritabaniUtil.MD5Sifrele(txt_sifre.getText().trim()));
			sorguIfadesi.setString(1, txt_kul.getText().trim());
			sorguIfadesi.setString(3, "0");
			//executeUpdate(); silme g�ncelleme ve ekleme i�lemlerini ger�ekle�tirir
			sorguIfadesi.executeUpdate();
			lbl_sonuc.setText("kullan�c� ekleme i�lemi gerceklesti");
		} catch (Exception e) {
			lbl_sonuc.setText(e.getMessage().toString());
		}
    }

    @FXML
    void btn_guncelle_Click(ActionEvent event) {
    	sql = "update login set sifre = ? where kul_ad = ?";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, VeritabaniUtil.MD5Sifrele(txt_sifre.getText().trim()));
			sorguIfadesi.setString(2, txt_kul.getText().trim());
			//executeUpdate(); silme g�ncelleme ve ekleme i�lemlerini ger�ekle�tirir
			sorguIfadesi.executeUpdate();
			lbl_sonuc.setText("sifre guncelleme gerceklesti");
		} catch (Exception e) {
			lbl_sonuc.setText(e.getMessage().toString());
		}
    }

    @FXML
    void btn_login_Click(ActionEvent event) {
    	//once sql ifadeni olu�tur
    	sql = "select * from login where kul_ad = ? and sifre = ?";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			//trim, fazla bosluk ifadelerine kars�n kullan�l�r
			sorguIfadesi.setString(1, txt_kul.getText().trim());
			sorguIfadesi.setString(2, txt_sifre.getText().trim());
			
			//sorgu ifadesi bitti, art�k bunlar� result set'e ata
			//executeQuery(), sorguyu cal�st�r demek
			ResultSet getirilen = sorguIfadesi.executeQuery();
			//herhangi bir ifade geliyor mu gelmiyor mu kontrol�n� if else icinde "getirilen" result set'i ile yap�l�r
			if(!getirilen.next()) {
				lbl_sonuc.setText("sistem: kullanici adi ya da sifre hatali");
			}
			else {
				//getString'e kac�nc� id'de oldugu da yaz�labilir
				//tabloda 1. s�tundaki de�eri getirir
				getirilen.getString(1);
				System.out.println("kID: " + getirilen.getString("kID"));
				System.out.println("kullanici adi: " + getirilen.getString("kul_ad"));
				System.out.println("sifre: " + VeritabaniUtil.MD5Sifrele(getirilen.getString("sifre")));
				System.out.println("yetki: " + getirilen.getString("yetki"));
				
				//string harici bir yap�yla getirmek istiyorsak:
				/*getirilen.getInt("kID");
				getirilen.getDouble("sutun adi");
				getirilen.getDate("tarih")
				*/
			}
		} catch (Exception e) {
			lbl_sonuc.setText(e.getMessage().toString());
		}
    }

    @FXML
    void btn_sil_Click(ActionEvent event) {
    	sql = "delete from login where kul_ad = ? and sifre = ?";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(2, VeritabaniUtil.MD5Sifrele(txt_sifre.getText().trim()));
			sorguIfadesi.setString(1, txt_kul.getText().trim());
			//executeUpdate(); silme g�ncelleme ve ekleme i�lemlerini ger�ekle�tirir
			sorguIfadesi.executeUpdate();
			lbl_sonuc.setText("kullan�c� silme i�lemi gerceklesti");
		} catch (Exception e) {
			lbl_sonuc.setText(e.getMessage().toString());
		}
    }	

    @FXML
    void initialize() {
      
    }
}
