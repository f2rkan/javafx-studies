package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class ComboListViewChoiceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt1;

    @FXML
    private ComboBox<String> combo1;

    @FXML
    private ListView<String> list1;

    @FXML
    private ChoiceBox<String> choice1;

    @FXML
    private Button btn1;

    @FXML
    private Label lbl_sonuc;

    @FXML
    private Button btn_getir;

    @FXML
    void btn1_Click(ActionEvent event) {
    	
    }

    @FXML
    void btn_getir_Click(ActionEvent event) {

    	combo1.getItems().add(txt1.getText());
    	
    	ObservableList<String>icerik;
    	icerik = list1.getSelectionModel().getSelectedItems();
    	for (String i : icerik) {
			System.out.println(i);
		}
    	ObservableList<Integer>indisler;
    	indisler = list1.getSelectionModel().getSelectedIndices();
    	for (int i : indisler) {
			System.out.println(i);
		}
    }

    @FXML
    void combo1_Action(ActionEvent event) {

    	//silme iþlemini butona atarýz
    	//hepsini siler
    	//týkladigin anda, action oldugu anda siler.
    	//combo1.getItems().clear();
    	
    	//belirli bir indisi silmek icin:
    	//combo1.getItems().remove(2);
    	
    	//belirlenen degeri silmek
    	//combo1.getItems().remove("deneme1");
    	
    	//silinmesini istedigimiz listeyi de olusturabiliriz
    	//combo1.getItems().removeAll("deneme1", "deneme2");
    	
    	//seçili olan degeri getirme:
    	//lbl_sonuc.setText(combo1.getSelectionModel().getSelectedItem());
    	
    	//seçili degerin indisini almak
    	lbl_sonuc.setText(String.valueOf(combo1.getSelectionModel().getSelectedIndex()));
    	
    	//belirli bir indisteki degeri almak
    	//System.out.println(combo1.getItems().get(2));
    	
    	//arama yaptýrmak
    	//ilk buldugunun indisini getirir
    	//System.out.println(combo1.getItems().indexOf("deneme1"));
    	
    	//bu elemanýn bulundugu son indisi getirir
    	System.out.println(combo1.getItems().lastIndexOf("deneme1"));
    }

    @FXML
    void initialize() {
        combo1.setPromptText("Deger Sec");
        String[] dizi = {"deneme1", "deneme2", "deneme3"};        
        
        combo1.getItems().addAll(dizi);
        
        //ChoiceBox'a diziyi toplu almak 
        choice1.getItems().addAll(dizi);
        
        //araya eleman ekleme
        combo1.getItems().add(0, "deneme ekleme");
        ObservableList<String>dizi2 = FXCollections.observableArrayList("deger1", "deger2", "deger3");
        combo1.getItems().addAll(dizi2);
        
        //eleman goruntulenmeyi sýnýrlama
        combo1.setVisibleRowCount(3);
        
        list1.getItems().addAll(dizi2);
       
  
        choice1.getItems().addAll(dizi2);
 
        //list1'de hangi deger seciliyse
        //o degeri getirir.
        list1.getSelectionModel().getSelectedItems();
        //list1'de secili olan degerin indis'ini getirir.
        list1.getSelectionModel().getSelectedIndex();
        
        //çoklu seçim:
        list1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //birden fazla eleman secili ise o indisleri getirir
        list1.getSelectionModel().getSelectedIndices();
        
        //birden fazla eleman secili ise bunu LÝSTE olarak döndürür
        list1.getSelectionModel().getSelectedItems();
        
        //listede kaçýncý indisi seçili hale getirmek istiyorsak onu yapar
        list1.getSelectionModel().select(2);
        
        //listenin tümünü seç
        list1.getSelectionModel().selectAll();
        
        //ilkini secmek
        list1.getSelectionModel().selectFirst();
        
        //son indisi secmek
        list1.getSelectionModel().selectLast();
        
        //hangi indis secili ise indis olarak bir sonrakini secer
        list1.getSelectionModel().selectNext();
        
        //hangi indis secili ise indis olarak bir öncekini secer
        list1.getSelectionModel().selectPrevious();
        
        //belirli bir aralýk seçme
        list1.getSelectionModel().selectRange(0, 1);
        
        //silme iþlemi
        list1.getSelectionModel().clearSelection();
        
    }
}
