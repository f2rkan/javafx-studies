package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TableviewExtendedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Kayitlar_X> kayitlar_table;

    @FXML
    private TableColumn<Kayitlar_X, Integer> id;

    @FXML
    private TableColumn<Kayitlar_X, String> kul_adi;

    @FXML
    private TableColumn<Kayitlar_X, Double> ortalama;

    @FXML
    private TableColumn<Kayitlar_X, String> yorum;

    @FXML
    private TableColumn<Kayitlar_X, String> secili;

    @FXML
    private TableColumn<Kayitlar_X, String> buton_sutun;

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
    private Button btn_goster;

    @FXML
    private Button btn_yorum;

    @FXML
    private Button btn_secili;
    
    ObservableList<Kayitlar_X>veriler;
    Button[] butonlar = new Button[10];
    int butonNumarasi = 0;

    @FXML
    void btn_ekle_Click(ActionEvent event) {
    	veriler = FXCollections.observableArrayList();
    	veriler.add(new Kayitlar_X(3, txt_kul.getText(), Double.parseDouble(txt_sifre.getText()), "varsayilan", butonlar[butonNumarasi]));
    	kayitlar_table.getItems().addAll(veriler);
    }

    @FXML
    void btn_goster_Click(ActionEvent event) {
    	Kayitlar_X kayit = new Kayitlar_X();
    	if(kayitlar_table.getSelectionModel().getSelectedIndex() != -1){//integer bir deger d�nd�r�r -1 se�ili ise bir sat�r se�ili de�il
    		kayit = (Kayitlar_X) kayitlar_table.getItems().get(kayitlar_table.getSelectionModel().getSelectedIndex());
    		System.out.println("secilen kullanici: " + kayit.getKul_adi() + "\n" + 
    		"ortalama: " + kayit.getOrtalama() + "\n" +
    		"id: " + kayit.getId());
    	}
    	else {
    		System.out.println("herhangi bir kayit secili degil");
    	}
    }

    @FXML
    void btn_guncelle_Click(ActionEvent event) {

    	Kayitlar_X kayit = new Kayitlar_X();
    	if(kayitlar_table.getSelectionModel().getSelectedIndex() != -1){//integer bir deger d�nd�r�r -1 se�ili ise bir sat�r se�ili de�il
    		kayit = (Kayitlar_X) kayitlar_table.getItems().get(kayitlar_table.getSelectionModel().getSelectedIndex());
    		int idm = kayit.getId();
    		//yeni kay�t
    		Kayitlar_X kk = new Kayitlar_X(idm, txt_kul.getText(), Double.parseDouble(txt_sifre.getText()), "guncellenmis mesaj", butonlar[idm]);
    		int sira = kayitlar_table.getSelectionModel().getSelectedIndex();
    		kayitlar_table.getItems().set(sira, kk); //istedigin s�raya istedigin guncellemeyi ata
    	}
    	else {
    		System.out.println("herhangi bir kayit secili degil");
    	}
    	
    }

    @FXML
    void btn_secili_Click(ActionEvent event) {
    	//CheckBox'ta se�ilmi� olan sat�rlar�n de�erlerini alma
        	for (Kayitlar_X islem : veriler) {
    			if(islem.getOnay().isSelected()) {
    				System.out.println("Secilen: " + islem.getKul_adi());
    			}
   }
}

    @FXML
    void btn_sil_Click(ActionEvent event) {
    	ObservableList<Kayitlar_X>secilenKayitlar, tumKayitlar;
    	tumKayitlar = kayitlar_table.getItems();
    	secilenKayitlar = kayitlar_table.getSelectionModel().getSelectedItems();
    	
    	secilenKayitlar.forEach(tumKayitlar::remove);
    }

    @FXML
    void btn_yorum_Click(ActionEvent event) {
    	
    	//yorum girilmi� sat�rlar belirlenerek yorum degerleri al�ns�n
    	for (Kayitlar_X islem : veriler) {
			if(!islem.getMetinKutusu().getText().isEmpty()) {
				System.out.println("Secilen: " + islem.getKul_adi());
			}
		}
    }
    @FXML
    void kayitlar_table_Click(MouseEvent event) {
    	//tablodan herhangi bir deger secildiginde cal�s�r
    	Kayitlar_X kayitlar = new Kayitlar_X();
    	kayitlar = (Kayitlar_X)kayitlar_table.getItems().get(kayitlar_table.getSelectionModel().getSelectedIndex());
    	 
    	txt_kul.setText(kayitlar.getKul_adi());
    	//kayitlar icindeki getOrtalama degerini String'e d�n��t�r�p at
    	txt_sifre.setText(String.valueOf(kayitlar.getOrtalama()));
    }

    @FXML
    void initialize() {
        for (int i = 0; i < butonlar.length; i++) {
			butonlar[i] = new Button();
			//t�klanan butona id atama
			butonlar[i].setId("btn " + i);
			//aksiyon atama = setOnAction
			butonlar[i].setOnAction(this::butonOlayi);
		}
        
        //tableview'e verileri girme:
        veriler = FXCollections.observableArrayList();
        veriler.add(new Kayitlar_X(1, "admin", 0.5, "mesaj 1", butonlar[butonNumarasi]));
        butonNumarasi++;
        veriler.add(new Kayitlar_X(1, "root ", 0.89, "mesaj 2", butonlar[butonNumarasi]));
        butonNumarasi++;
        veriler.add(new Kayitlar_X(1, "user", 0.56, "mesaj 3", butonlar[butonNumarasi]));
        butonNumarasi++;
        
        //neyin nerede tutulacag�:
        //1. id, tableColumn; 2. id, Kayitlar_X i�erisindeki
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        kul_adi.setCellValueFactory(new PropertyValueFactory<>("kul_adi"));
        ortalama.setCellValueFactory(new PropertyValueFactory<>("ortalama"));
        secili.setCellValueFactory(new PropertyValueFactory<>("onay"));
        buton_sutun.setCellValueFactory(new PropertyValueFactory<>("buton"));
        yorum.setCellValueFactory(new PropertyValueFactory<>("metinKutusu"));
        
        kayitlar_table.setItems(veriler);
    }
    //�retilen butonlar icin olay atama
    @FXML
    void butonOlayi(ActionEvent event) {
    	//bir t�klama olay� oldugu zaman hangisine t�kland�g�n� id'ye g�re ayr��t�r�rs�n
    	for (int i = 0; i < butonlar.length; i++) {
			//tetiklenen butonu bulma
    		if(event.getSource() == butonlar[i]) {
    			System.out.println(i + ". buton tiklandi...");
    		}
		}
    }
}
