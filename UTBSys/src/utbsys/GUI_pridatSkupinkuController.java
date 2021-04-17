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

public class GUI_pridatSkupinkuController {
    private final Stage StagePridatSkupinku;
    @FXML
    private TextField tf_nazevSkupinky;
    @FXML
    private TextField tf_zkratkaSkupinky;
    @FXML
    private TextField tf_rocnik;
    @FXML
    private ChoiceBox<EnumSemestr> chb_semestr;
    @FXML
    private TextField tf_pocetStudentu;
    @FXML
    private ChoiceBox<EnumFormaStudia> chb_formaStudia;
    @FXML
    private ChoiceBox<EnumTypStudia> chb_typStudia;
    @FXML
    private ChoiceBox<EnumJazyk> chb_jazyk;
    @FXML
    private Button btn_pridat;
    
    public GUI_pridatSkupinkuController(){
        StagePridatSkupinku = new Stage();
        
        try {
            FXMLLoader pridatSkupinkuController = new FXMLLoader(getClass().getResource("GUI_pridatSkupinku.fxml"));
            pridatSkupinkuController.setController(this);
            StagePridatSkupinku.setScene(new Scene(pridatSkupinkuController.load()));
            Image icona = new Image(getClass().getResourceAsStream("Icon.png"));
            StagePridatSkupinku.getIcons().add(icona);
            StagePridatSkupinku.setTitle("UTB Sys - Přidat skupinku");
            StagePridatSkupinku.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        chb_semestr.setItems(FXCollections.observableArrayList(EnumSemestr.values()));
        chb_formaStudia.setItems(FXCollections.observableArrayList(EnumFormaStudia.values()));
        chb_typStudia.setItems(FXCollections.observableArrayList(EnumTypStudia.values()));
        chb_jazyk.setItems(FXCollections.observableArrayList(EnumJazyk.values()));
    }
    
    public void showStage(){ //Pro zobrazení stage
        StagePridatSkupinku.showAndWait();
    }
    
    @FXML
    private void initialize() { //Inicializace jednotlivých komponent z fxml a přiřazení eventů
        btn_pridat.setOnAction(event -> pridatSkupinku());
    }
    
    private void pridatSkupinku(){
        if(kontrolaTextField()){
            System.out.println("vpohodě");
            Skupinka skupinka = new Skupinka(tf_nazevSkupinky.getText(),
                    tf_zkratkaSkupinky.getText(),
                    Integer.parseInt(tf_rocnik.getText()),
                    chb_semestr.getValue(),
                    Integer.parseInt(tf_pocetStudentu.getText()),
                    chb_formaStudia.getValue(),
                    chb_typStudia.getValue(),
                    chb_jazyk.getValue());
        }
        else{
            AlertOkno alert = new AlertOkno('E', "Chyba", "Nevyplnil si, žádné pole!");
        }
    }
    
    private boolean kontrolaTextField(){
        if (!tf_nazevSkupinky.getText().trim().isEmpty() &&
                !tf_zkratkaSkupinky.getText().trim().isEmpty() &&
                !tf_rocnik.getText().trim().isEmpty() &&
                chb_semestr.getValue() != null &&
                !tf_pocetStudentu.getText().isEmpty() &&
                chb_formaStudia.getValue() != null &&
                chb_typStudia.getValue() != null &&
                chb_jazyk.getValue() != null)
            return true;
        else
            return false;
    }
    
}
