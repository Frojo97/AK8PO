package utbsys;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private TextField tf_hodinyPrednasek;
    @FXML
    private TextField tf_hodinyCviceni;
    @FXML
    private TextField tf_hodinySeminaru;
    @FXML
    private ChoiceBox<EnumZakonceni> chb_zakonceniPredmetu;
    @FXML
    private ChoiceBox<EnumJazyk> chb_jazykPredmetu;
    @FXML
    private Button btn_pridat;
    
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
    
    @FXML
    private void initialize() { //Inicializace jednotlivých komponent z fxml a přiřazení eventů
        btn_pridat.setOnAction(event -> pridatPredmet());
    }
    
    private void pridatPredmet(){
        if (kontrolaTextIsEmpty()){
            int pocetKreditu;
            int pocetTydnu;
            int hodinyPrednasek;
            int hodinyCviceni;
            int hodinySeminaru;
            try {
                pocetKreditu = Integer.parseInt(tf_pocetKreditu.getText());
                pocetTydnu = Integer.parseInt(tf_pocetTydnu.getText());
                hodinyPrednasek = Integer.parseInt(tf_hodinyPrednasek.getText());
                hodinyCviceni = Integer.parseInt(tf_hodinyCviceni.getText());
                hodinySeminaru = Integer.parseInt(tf_hodinySeminaru.getText());
                
                
                
                
                /*if (pocetKreditu => 0 && pocetKreditu < 10 && pocetTydnu > 0 && pocetTydnu < 15 && hodinyPrednasek => 0 && hodinyPrednasek < 10 && hodinyCviceni => 0 && hodinyCviceni < 10 && hodinySeminaru => 0 && hodinySeminaru < 10){
                    
                }*/
            }
            catch (NumberFormatException e){
                AlertOkno alert = new AlertOkno('E', "Chyba", "Nebyla správně zadána číselná hodnota!");
            }
        }
        else {
            AlertOkno alert = new AlertOkno('E', "Chyba", "Nevyplnil si všechny pole!");
        }
    }
    
    private boolean kontrolaTextIsEmpty(){
        if (!tf_nazevPredmetu.getText().trim().isEmpty() &&
                !tf_zkratkaPredmetu.getText().trim().isEmpty() &&
                !tf_pocetKreditu.getText().trim().isEmpty() &&
                !tf_pocetTydnu.getText().trim().isEmpty() &&
                !tf_hodinyPrednasek.getText().trim().isEmpty() &&
                !tf_hodinyCviceni.getText().trim().isEmpty() &&
                !tf_hodinySeminaru.getText().trim().isEmpty() &&
                chb_zakonceniPredmetu.getValue() != null &&
                chb_jazykPredmetu.getValue() != null) 
            return true;
        else
            return false;
    }    
}
