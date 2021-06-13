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
    private Skupinka novaSkupinka;
    private SeznamSkupinek seznamSkupinek;
    
    public GUI_pridatSkupinkuController(SeznamSkupinek seznamSkupinek){
        StagePridatSkupinku = new Stage();
        this.seznamSkupinek = seznamSkupinek;
        
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
            int rocnik;
            int pocetStudentu;
            try{
                rocnik = Integer.parseInt(tf_rocnik.getText());
                pocetStudentu = Integer.parseInt(tf_pocetStudentu.getText());
                if (rocnik > 0 && rocnik < 11 && pocetStudentu > -1 && pocetStudentu < 1000){
                    novaSkupinka = new Skupinka(seznamSkupinek.getNewID(),
                            tf_nazevSkupinky.getText(),
                            tf_zkratkaSkupinky.getText(),
                            Integer.parseInt(tf_rocnik.getText()),
                            chb_semestr.getValue(),
                            Integer.parseInt(tf_pocetStudentu.getText()),
                            chb_formaStudia.getValue(),
                            chb_typStudia.getValue(),
                            chb_jazyk.getValue());
                    seznamSkupinek.pridatDoSeznamu(novaSkupinka);
                    StagePridatSkupinku.close(); 
                }
                else{
                    AlertOkno alert = new AlertOkno('E', "Chyba", "Číselné hodnoty přesahují povolenou mez!\nHodnoty mohou být od 1 do 10 pro ročnik a pro pocet studentů od 0 do 1000.");
                }     
            }
            catch (NumberFormatException e){
                AlertOkno alert = new AlertOkno('E', "Chyba", "Nebyla správně zadána číselná hodnota!");
            }           
        }
        else{
            AlertOkno alert = new AlertOkno('E', "Chyba", "Nevyplnil si všechny pole!");
        }
    }
        
    public SeznamSkupinek vratSeznamSkupinek(){
        return seznamSkupinek;
    }
    
    public Skupinka vratSkupinku(){
        return novaSkupinka;
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
