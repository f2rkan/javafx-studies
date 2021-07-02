package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import javafx.beans.value.ObservableValue;

public class SpinnerSliderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Spinner<Integer> spin1;

    @FXML
    private Spinner<String> spin2;

    @FXML
    private Spinner<Integer> spin3;

    @FXML
    private Spinner<Integer> spin4;

    @FXML
    private Spinner<Integer> spin5;

    @FXML
    private Spinner<Integer> spin6;

    @FXML
    private Spinner<Double> spin7;

    @FXML
    private Spinner<String> spin8;

    @FXML
    private Slider slider2;

    @FXML
    private Slider slider1;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Button btn1;

    @FXML
    void btn1_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	int secilen = 3;
        //kactan kaca kadar - 1 ve 5 de dahil
    	SpinnerValueFactory<Integer> deger1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, secilen);
    	spin1.setValueFactory(deger1);
    	
    	ObservableList<String> aylar = FXCollections.observableArrayList("Ocak", "Subat", "Mart", "Nisan", "Mayis");
    	SpinnerValueFactory<String> deger2 = new ListSpinnerValueFactory<String>(aylar);
    	deger2.setValue("denemeEkleme");
    	spin2.setValueFactory(deger2);
    	//spin2.setEditable(true);
    	
    	//Spinner goruntuleme turleri
    	SpinnerValueFactory<Integer> deger3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 150);
    	spin3.setValueFactory(deger3);
    	spin3.getStyleClass().add(Spinner.STYLE_CLASS_ARROWS_ON_LEFT_HORIZONTAL);
    	
    	spin4.setValueFactory(deger3);
    	spin4.getStyleClass().add(Spinner.STYLE_CLASS_ARROWS_ON_LEFT_VERTICAL);
    	
    	spin5.setValueFactory(deger3);
    	spin5.getStyleClass().add(Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
    	
    	spin6.setValueFactory(deger3);
    	spin6.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
    	
    	//min - max - baslangýc - kacar kacar
    	SpinnerValueFactory<Double> deger4 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.1, 5.7, 1.7, 0.5);
    	spin7.setValueFactory(deger4);
    	spin7.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
    	
    	
    	spin8.setValueFactory(deger2);
    	
    	//listener oluþturma
    	spin8.valueProperty().addListener(
    			
    			(obs, oldValue, newValue) -> System.out.println("\nSpinner Eski Deger: " + oldValue + "\nSpinner Yeni Deger: " + newValue)
    			
    			);
    	
    	slider1.setMax(75);
    	slider1.setMin(15);
    	//tik iþaretleri
    	slider1.setShowTickLabels(true);
    	//sayýlar
    	slider1.setShowTickMarks(true);
    	//kac degerde bir ölçeklendirme olsun
    	slider1.setMajorTickUnit(5);
    	//blok artýrma deðeri
    	slider1.setBlockIncrement(10);
    	
    	
    	slider1.valueProperty().addListener(new ChangeListener<Number>() {
    		
    		@Override
    		public void changed(ObservableValue<? extends Number> observed, Number oldValue, Number newValue) {
    			
    			System.out.println("Slider Yeni Degeri: " + newValue);
    			lbl1.setText("Yeni Deger: " + String.valueOf(newValue));
    			lbl2.setText("Eski Deger: " + String.valueOf(oldValue));
    		};
    	
    	});
    	
    	slider2.valueProperty().addListener(
    			(obs, oldValue, newValue) -> {
    				lbl1.setText("Yeni Deger: " + String.valueOf(newValue));
        			lbl2.setText("Eski Deger: " + String.valueOf(oldValue));
    			}
    			);
    }
}
