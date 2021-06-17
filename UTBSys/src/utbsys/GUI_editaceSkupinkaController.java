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

public class GUI_editaceSkupinkaController {
    private final Stage StageEditovatSkupinku;
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
    private int IDupraveneSK;
    private SeznamSkupinek seznamSkupinek;
    
    public GUI_editaceSkupinkaController(SeznamSkupinek seznamSkupinek, int IDskupinka){
        StageEditovatSkupinku = new Stage();
        this.seznamSkupinek = seznamSkupinek;
        this.IDupraveneSK = IDskupinka;
        
        try {
            FXMLLoader pridatSkupinkuController = new FXMLLoader(getClass().getResource("GUI_pridatSkupinku.fxml"));
            pridatSkupinkuController.setController(this);
            StageEditovatSkupinku.setScene(new Scene(pridatSkupinkuController.load()));
            Image icona = new Image(getClass().getResourceAsStream("Icon.png"));
            StageEditovatSkupinku.getIcons().add(icona);
            StageEditovatSkupinku.setTitle("UTB Sys - Editovat skupinku");
            StageEditovatSkupinku.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        chb_semestr.setItems(FXCollections.observableArrayList(EnumSemestr.values()));
        chb_formaStudia.setItems(FXCollections.observableArrayList(EnumFormaStudia.values()));
        chb_typStudia.setItems(FXCollections.observableArrayList(EnumTypStudia.values()));
        chb_jazyk.setItems(FXCollections.observableArrayList(EnumJazyk.values()));
        
        btn_pridat.setText("Editovat");
        nahodInfoOSkupince(this.IDupraveneSK);
    }
    
    public void showStage(){ //Pro zobrazení stage
        StageEditovatSkupinku.showAndWait();
    }
    
    private void nahodInfoOSkupince(int IDskupinka){
        Skupinka hledanaSkupinka = this.seznamSkupinek.getSkupinka(IDskupinka);
        tf_nazevSkupinky.setText(hledanaSkupinka.getNazevSkupinky());
        tf_zkratkaSkupinky.setText(hledanaSkupinka.getZkratkaSkupinky());
        tf_rocnik.setText(hledanaSkupinka.getRocnik());
        chb_semestr.setValue(EnumSemestr.valueOf(hledanaSkupinka.getSemestr()));
        tf_pocetStudentu.setText(hledanaSkupinka.getPocetStudentu());
        chb_formaStudia.setValue(EnumFormaStudia.valueOf(hledanaSkupinka.getFormaStudia()));
        chb_typStudia.setValue(EnumTypStudia.valueOf(hledanaSkupinka.getTypStudia()));
        chb_jazyk.setValue(EnumJazyk.valueOf(hledanaSkupinka.getJazyk()));
        
        tf_nazevSkupinky.setDisable(true);
        tf_zkratkaSkupinky.setDisable(true);
        tf_rocnik.setDisable(true);
        chb_semestr.setDisable(true);
        chb_typStudia.setDisable(true);
        chb_formaStudia.setDisable(true);
        chb_jazyk.setDisable(true);        
    }
    
    @FXML
    private void initialize() { //Inicializace jednotlivých komponent z fxml a přiřazení eventů
        btn_pridat.setOnAction(event -> editovatSkupinku());
    }
    
    private void editovatSkupinku(){
        if (kontrola()){
            int pocet;
            try {
                pocet = Integer.parseInt(tf_pocetStudentu.getText());
                seznamSkupinek.zmenaPoctu(IDupraveneSK, pocet);
                StageEditovatSkupinku.close();
            }
            catch (NumberFormatException e){
                AlertOkno alert = new AlertOkno('E', "Chyba", "Nebyla správně zadána číselná hodnota!");
            } 
        }
        else{
            AlertOkno alert = new AlertOkno('E', "Chyba", "Pole pro počet studentů je prázdné!");
        }
    }
    
    private boolean kontrola(){
        return !tf_pocetStudentu.getText().trim().isEmpty();
    }
    
    public SeznamSkupinek vratSeznamSkupinek(){
        return seznamSkupinek;
    }
}
