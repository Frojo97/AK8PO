package utbsys;

import java.io.IOException;
import static javafx.application.Platform.exit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI_hlavniObrazovkaContoller {
    private final Stage StageHlavniObrazovka;
    @FXML
    private MenuItem mi_konec;
    @FXML
    private MenuItem mi_oAplikaci;
    @FXML
    private Button btn_pridatSkupinku;
    @FXML
    private Button btn_pridatPredmet;
    @FXML
    private Button btn_editovatPredmet;
    @FXML
    private Button btn_smazatPredmet;
    @FXML
    private Button btn_pridatZamestnance;
    @FXML
    private Button btn_editovatZamestnance;
    @FXML
    private Button btn_smazatZamestnance;
    @FXML
    private ScrollPane sp_skupinkaOkno;
    @FXML
    private ListView<Zamestnanec> lv_zamestnanci;
    //GUI rozmístění pro skupinku
    private GUI_GridPaneOkno gpo_skupinka = new GUI_GridPaneOkno();
    private SeznamSkupinek seznamSkupinek = new SeznamSkupinek();
    private SeznamPredmetu seznamPredmetu = new SeznamPredmetu();
    private SeznamZamestnancu seznamZamestnancu = new SeznamZamestnancu();
    
    public GUI_hlavniObrazovkaContoller(){
        StageHlavniObrazovka = new Stage();
        try {
            FXMLLoader hlavniObrazovkaController = new FXMLLoader(getClass().getResource("GUI_hlavniObrazovka.fxml"));
            hlavniObrazovkaController.setController(this);
            StageHlavniObrazovka.setScene(new Scene(hlavniObrazovkaController.load()));
            Image icona = new Image(getClass().getResourceAsStream("Icon.png"));
            StageHlavniObrazovka.getIcons().add(icona);
            StageHlavniObrazovka.setTitle("UTB Sys");
            StageHlavniObrazovka.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Do ScrollPane okna je přidán GridPaneOkno pro Skupinku
        sp_skupinkaOkno.setContent(gpo_skupinka.getGP_okno());
        setDisableBTNZamestnanec();
        
        lv_zamestnanci.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Zamestnanec>() {
            public void changed(ObservableValue<? extends Zamestnanec> observable, Zamestnanec oldValue, Zamestnanec newValue) {
                setEnableBTNZamestnanec();
            }
        });
        
        seznamSkupinek = SouborSkupinka.SK().nacteniSkupinky();
        ObservableList<Skupinka> oblSkupinka = seznamSkupinek.getOBSeznam();
        for(int i = 0; i < oblSkupinka.size(); i++){
            createStitekSkupinka(oblSkupinka.get(i));
        }
        
        seznamPredmetu = SouborPredmet.SP().nacteniPredmetu();
        setDisableBTNPredmet();
        
        seznamZamestnancu = SouborZamestnanec.SZ().nacteniZamestnancu();
        zobrazDataVListView();
    }
    
    public void showStage(){ //Pro zobrazení stage
        StageHlavniObrazovka.show();
    }
    
    private void setDisableBTNPredmet(){
        btn_editovatPredmet.setDisable(true);
        btn_smazatPredmet.setDisable(true);
    }
    
    private void setEnableBTNPredmet(){
        btn_editovatPredmet.setDisable(false);
        btn_smazatPredmet.setDisable(false);
    }
    
    private void setDisableBTNZamestnanec(){
        btn_editovatZamestnance.setDisable(true);
        btn_smazatZamestnance.setDisable(true);
    }
    
    private void setEnableBTNZamestnanec(){
        btn_editovatZamestnance.setDisable(false);
        btn_smazatZamestnance.setDisable(false);
    }
    
    @FXML
    private void initialize() { //Inicializace jednotlivých komponent z fxml a přiřazení eventů
        mi_konec.setOnAction(event -> {
            exit();
        });
        mi_oAplikaci.setOnAction(event -> otevriOAplikaciDialog());
        btn_pridatSkupinku.setOnAction(event -> otevriPridatSkupinku());
        btn_pridatPredmet.setOnAction(event -> otevriPridatPredmet());
        btn_pridatZamestnance.setOnAction(event -> otevriPridatZamestnance());
        btn_editovatZamestnance.setOnAction(event -> editaceZamestnance());
        btn_smazatZamestnance.setOnAction(event -> smazaniZamestnance());
    }
    
    private void otevriOAplikaciDialog(){ //Akce informace pro tlačítko mi_oAplikaci
        Alert alertOAplikaci = new Alert(Alert.AlertType.INFORMATION);
        alertOAplikaci.setTitle("UTB Sys - O Aplikaci");
        ((Stage)alertOAplikaci.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("Icon.png")));
        alertOAplikaci.setHeaderText(null);
        alertOAplikaci.setContentText("UTB Sys je aplikace určená k vytváření předmětů a jejich rozdělení učitelům.\nAutor: Bc. Jan Hána\nVersion: 1.0");
        alertOAplikaci.showAndWait();
    }
    
    //Skupinka
    private void otevriPridatSkupinku(){
        //Otevre nove okno na pridani skupinky
        GUI_pridatSkupinkuController guiPridatSkupinku = new GUI_pridatSkupinkuController(this.seznamSkupinek);
        guiPridatSkupinku.showStage();
        //Podmínka zajištuje kdybychom nevytvořili novou skupinku
        if (guiPridatSkupinku.vratSkupinku() != null){
            //Vrati seznam
            seznamSkupinek = guiPridatSkupinku.vratSeznamSkupinek();
            createStitekSkupinka(guiPridatSkupinku.vratSkupinku());
            SouborSkupinka.SK().ulozeniSkupinky(seznamSkupinek.getOBSeznam());
        }
    }
    
    private void createStitekSkupinka(Skupinka newSk){
        //Prida stitek do GridPaneOkno
        GUI_stitekSkupinkaController gui_stitekSC = new GUI_stitekSkupinkaController(newSk);
        //Nastaveni eventů pro buttony
        gui_stitekSC.mi_editovatSS.setOnAction(event -> editaceStitekSkupinka());
        gui_stitekSC.mi_odstranitSS.setOnAction(event -> odstranStitekSkupinka(gui_stitekSC.getID()));
        gpo_skupinka.addStitek(gui_stitekSC);
    }
    
    private void editaceStitekSkupinka(){
        
    }
    
    private void odstranStitekSkupinka(int ID){
        gpo_skupinka.deleteStitek(ID);
        seznamSkupinek.odstranitZeSeznamu(ID);
        gpo_skupinka.refactorGridPane();
        ObservableList<Skupinka> sk = seznamSkupinek.getOBSeznam();
        for (int i = 0; i < sk.size(); i++){
            createStitekSkupinka(sk.get(i));
        }
        SouborSkupinka.SK().ulozeniSkupinky(seznamSkupinek.getOBSeznam());
    }
    
    //Předmět
    private void otevriPridatPredmet(){
        GUI_pridatPredmetController guiPridatPredmet = new GUI_pridatPredmetController(seznamPredmetu);
        guiPridatPredmet.showStage();
        
        if (guiPridatPredmet.vratPredmet() != null){
            seznamPredmetu = guiPridatPredmet.vratSeznamPredmetu();
            SouborPredmet.SP().ulozeniPredmetu(seznamPredmetu.getOBSeznam());
        }       
    }
    
    //Zaměstnanec
    private void otevriPridatZamestnance(){
        GUI_pridatZamestnanceController guiPridatZamestnance = new GUI_pridatZamestnanceController(this.seznamZamestnancu);
        guiPridatZamestnance.showStage();
        seznamZamestnancu = guiPridatZamestnance.vratSeznamZamestnancu();
        zobrazDataVListView();
        SouborZamestnanec.SZ().ulozeniZamestnancu(seznamZamestnancu.vratSeznamOL());
    }
    
    private void zobrazDataVListView(){ //Zajištuje zobrazení dat v ListView 
        lv_zamestnanci.setItems(seznamZamestnancu.vratSeznamOL());
        lv_zamestnanci.setCellFactory(param -> new ListCell<Zamestnanec>() {
            @Override
            protected void updateItem(Zamestnanec item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.toString() == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
    }
    
    private void editaceZamestnance(){
        GUI_editaceZamestnanceController guiEditovatZamestnance = new GUI_editaceZamestnanceController(this.seznamZamestnancu, lv_zamestnanci.getSelectionModel().getSelectedItem().getID());
        guiEditovatZamestnance.showStage();
        seznamZamestnancu = guiEditovatZamestnance.vratSeznamZamestnancu();
        zobrazDataVListView();
        lv_zamestnanci.getSelectionModel().clearSelection();
        setDisableBTNZamestnanec();
        SouborZamestnanec.SZ().ulozeniZamestnancu(seznamZamestnancu.vratSeznamOL());
    }
    
    private void smazaniZamestnance(){
        seznamZamestnancu.odstranitZeSeznamu(lv_zamestnanci.getSelectionModel().getSelectedItem().getID());
        zobrazDataVListView();
        lv_zamestnanci.getSelectionModel().clearSelection();
        setDisableBTNZamestnanec();
        SouborZamestnanec.SZ().ulozeniZamestnancu(seznamZamestnancu.vratSeznamOL());
    }
}
