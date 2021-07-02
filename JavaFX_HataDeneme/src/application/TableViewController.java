package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.TableViewController.Kayitlar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Kayitlar> kayitlar_table;

    @FXML
    private TableColumn<Kayitlar, Integer> id;

    @FXML
    private TableColumn<Kayitlar, String> kul_adi;

    @FXML
    private TableColumn<Kayitlar, String> sifre;	

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_sifre;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Label lbl_deger;

    @FXML
    private Button btn_goster;
    
    ObservableList<Kayitlar> veriler;
    @FXML
    void btn_ekle_click(ActionEvent event) {
    	veriler = FXCollections.observableArrayList();
    	veriler.add(new Kayitlar(3, txt_kul.getText(), txt_sifre.getText()));
    	
    	//yeni kayýt ekleme
    	kayitlar_table.getItems().addAll(veriler);
    }

    @FXML
    void btn_goster_click(ActionEvent event) {
    	//secili kaydý aldýrmak icin olusturduk
    	Kayitlar kayit = new Kayitlar();
    	if(kayitlar_table.getSelectionModel().getSelectedIndex() != -1) {
    		kayit = (Kayitlar) kayitlar_table.getItems().get(kayitlar_table.getSelectionModel().getSelectedIndex());
    		lbl_deger.setText("Deger: " + kayit.getId() + "; Kullanici adi: " + kayit.getKul_ad() + "; Sifre: " + kayit.getSifre());
    	}
    	else {
    		lbl_deger.setText("herhangi bir kayit secilmedi");
    	}
    }

    @FXML
    void btn_guncelle_click(ActionEvent event) {
    	//secili kaydý aldýrmak icin olusturduk
    	//secili kaydý aldýrmak icin kayýtlar tipinde bir nesne olustur
    	Kayitlar kayit = new Kayitlar();
    	if(kayitlar_table.getSelectionModel().getSelectedIndex() != -1) {
    		kayit = (Kayitlar) kayitlar_table.getItems().get(kayitlar_table.getSelectionModel().getSelectedIndex());
    		//secili olan degerin id'sini al
    		int idm = kayit.getId(); 
    		//txt_kul.setText(kayit.getKul_ad());
    		//txt_sifre.setText(kayit.getSifre());
    		
    		//yeni kayýtlar olusturarak indise gore deger atama
    		Kayitlar kk = new Kayitlar(idm, txt_kul.getText(), txt_sifre.getText());
    		//bu kayýtlarý belli bir sýraya gore ataman lazým
    		int sira = kayitlar_table.getSelectionModel().getSelectedIndex();
    		kayitlar_table.getItems().set(sira, kk);
    		
    	}
    	else {
    		lbl_deger.setText("herhangi bir kayit secilmedi");
    	}
    	
    }

    @FXML
    void btn_sil_click(ActionEvent event) {
    	//c#'taki data set'e denk geliyor
    	ObservableList<Kayitlar> secilenKayit, tumKayitlar;
    	tumKayitlar = kayitlar_table.getItems();
    	//sadece secileni bulmak istiyorsan:
    	//tek deger icin Item da diyebilirsin 1'den cok deger icin Items demen gerekir
    	secilenKayit = kayitlar_table.getSelectionModel().getSelectedItems(); 
    	
    	//remove kýsmýný silmek icin kullandýk add de diyebilirdik bu da kayýt eklerdi
    	secilenKayit.forEach(tumKayitlar::remove);
    }

    @FXML
    void initialize() {
        btn_ekle.setTooltip(new Tooltip("Kaydetmeyi saðlar"));
        
        //tooltip nesnesi olusturma
        Tooltip tip = new Tooltip();
        //tooltip geri plan - arka plana renk atama
        tip.setStyle("-fx-background-color: yellow;");
        //tooltip'e text atama
        tip.setText("Silme iþlemi yapar");
        //resim ekleme
        //Image img = new Image(getClass().getResourceAsStream("C:\Users\furkan\eclipse-workspace\JavaFX_HataDeneme\src\application\yatay_logo.png"));
        //olusturmus oldugun resmi setGraphic'e at:
        //tip.setGraphic(new ImageView(img));
        //bunlarý nerede kullanacagýný belirt:
        //btn_sil.setTooltip(tip);
        
        //TableView'e veri gömme
        veriler = FXCollections.observableArrayList();
        veriler.add(new Kayitlar(1, "admin", "12345"));
        veriler.add(new Kayitlar(2, "admin1", "12345"));
        veriler.add(new Kayitlar(3, "admin3", "12345"));
        
        //ilk TableColumn ismi
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        kul_adi.setCellValueFactory(new PropertyValueFactory<>("kul_ad"));
        sifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
        
        kayitlar_table.setItems(veriler);
        //kul_adi.setVisible(false);
    }
    public static class Kayitlar{
    	private int id;
    	public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getKul_ad() {
			return kul_ad;
		}
		public void setKul_ad(String kul_ad) {
			this.kul_ad = kul_ad;
		}
		public String getSifre() {
			return sifre;
		}
		public void setSifre(String sifre) {
			this.sifre = sifre;
		}
		private String kul_ad;
    	private String sifre;
    	
    	//setter getter olusturma:
    	//önce constructor olusturursun
    	//parametresiz constructor
    	Kayitlar() {
			this.id = 0;
		}
    	//constructor'ý override et:
    	//3 tane degeri teker teker girilerek yapýlan iþlemi saðla:
    	Kayitlar(int id, String kul_ad, String sifre){
    		//fonksiyonda parametre olarak gelen id, class icindeki id'ye gitsin
    		this.id = id;
    		this.kul_ad = kul_ad;
    		this.sifre = sifre;
    	}
    	
    }
}
