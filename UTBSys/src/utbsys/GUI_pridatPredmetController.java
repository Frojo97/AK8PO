package utbsys;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI_pridatPredmetController {
    private final Stage StagePridatPredmet;
    @FXML
    private TextField tf_nazevPredmetu;
    @FXML
    private TextField tf_zkratkaPredmetu;
    @FXML
    private TextField tf_pocetKreditu;
    @FXML
    private TextField tf_pocetTydnu;
    @FXML
    private TextField tf_pocetPrednasek;
    @FXML
    private TextField tf_pocetSeminaru;
    @FXML
    private ChoiceBox<EnumZakonceni> chb_zakonceniPredmetu;
    @FXML
    private ChoiceBox<EnumJazyk> chb_jazykPredmetu;
    
    public GUI_pridatPredmetController(){//Inicializace okna a nastavení jeho prvků
        StagePridatPredmet = new Stage();
        try {
            FXMLLoader pridatPredmetController = new FXMLLoader(getClass().getResource("GUI_pridatPredmet.fxml"));
            pridatPredmetController.setController(this);
            StagePridatPredmet.setScene(new Scene(pridatPredmetController.load()));
            Image icona = new Image(getClass().getResourceAsStream("Icon.png"));
            StagePridatPredmet.getIcons().add(icona);
            StagePridatPredmet.setTitle("UTB Sys - Přidat předmět");
            StagePridatPredmet.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        chb_zakonceniPredmetu.setItems(FXCollections.observableArrayList(EnumZakonceni.values()));
        chb_jazykPredmetu.setItems(FXCollections.observableArrayList(EnumJazyk.values()));
    }
    
    public void showStage(){ //Pro zobrazení stage
        StagePridatPredmet.showAndWait();
    }
    
    
}
