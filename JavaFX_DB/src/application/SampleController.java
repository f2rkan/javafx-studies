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
	
	//kurucusu icinde direkt baglantý kurmak, initialize() ile ugrasmaný keser; internet baglantýsý bunlardan biridir
	public SampleController() {
		//class ilk calýstýrýldýgýnda buraya düþer
		//olusturdugun baglantýya ana baglantýyý ata
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
    //bir sorgu ifadesi olusturman lazým
    //parametre gondericez sorguya
    PreparedStatement sorguIfadesi = null;
    //varolan sonuclarý getirmek icin ResultSet kullanýrsýn
    //c#'taki dataset'le ayný isi yapar
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
			//executeUpdate(); silme güncelleme ve ekleme iþlemlerini gerçekleþtirir
			sorguIfadesi.executeUpdate();
			lbl_sonuc.setText("kullanýcý ekleme iþlemi gerceklesti");
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
			//executeUpdate(); silme güncelleme ve ekleme iþlemlerini gerçekleþtirir
			sorguIfadesi.executeUpdate();
			lbl_sonuc.setText("sifre guncelleme gerceklesti");
		} catch (Exception e) {
			lbl_sonuc.setText(e.getMessage().toString());
		}
    }

    @FXML
    void btn_login_Click(ActionEvent event) {
    	//once sql ifadeni oluþtur
    	sql = "select * from login where kul_ad = ? and sifre = ?";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			//trim, fazla bosluk ifadelerine karsýn kullanýlýr
			sorguIfadesi.setString(1, txt_kul.getText().trim());
			sorguIfadesi.setString(2, txt_sifre.getText().trim());
			
			//sorgu ifadesi bitti, artýk bunlarý result set'e ata
			//executeQuery(), sorguyu calýstýr demek
			ResultSet getirilen = sorguIfadesi.executeQuery();
			//herhangi bir ifade geliyor mu gelmiyor mu kontrolünü if else icinde "getirilen" result set'i ile yapýlýr
			if(!getirilen.next()) {
				lbl_sonuc.setText("sistem: kullanici adi ya da sifre hatali");
			}
			else {
				//getString'e kacýncý id'de oldugu da yazýlabilir
				//tabloda 1. sütundaki deðeri getirir
				getirilen.getString(1);
				System.out.println("kID: " + getirilen.getString("kID"));
				System.out.println("kullanici adi: " + getirilen.getString("kul_ad"));
				System.out.println("sifre: " + VeritabaniUtil.MD5Sifrele(getirilen.getString("sifre")));
				System.out.println("yetki: " + getirilen.getString("yetki"));
				
				//string harici bir yapýyla getirmek istiyorsak:
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
			//executeUpdate(); silme güncelleme ve ekleme iþlemlerini gerçekleþtirir
			sorguIfadesi.executeUpdate();
			lbl_sonuc.setText("kullanýcý silme iþlemi gerceklesti");
		} catch (Exception e) {
			lbl_sonuc.setText(e.getMessage().toString());
		}
    }	

    @FXML
    void initialize() {
      
    }
}
