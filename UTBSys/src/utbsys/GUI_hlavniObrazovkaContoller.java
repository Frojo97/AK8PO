package utbsys;

import java.io.IOException;
import static javafx.application.Platform.exit;
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
    private Button btn_pridatZamestnance;
    @FXML
    private ScrollPane sp_skupinkaOkno;
    @FXML
    private ListView/*<Zamestnanec>*/ lv_zamestnanci;
    //GUI rozmístění pro skupinku
    private GUI_GridPaneOkno gpo_skupinka = new GUI_GridPaneOkno();
    private SeznamSkupinek seznamSkupinek = new SeznamSkupinek();
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
    }
    
    public void showStage(){ //Pro zobrazení stage
        StageHlavniObrazovka.show();
    }
    
    private void falseVisibleButton(){
        btn_pridatPredmet.setVisible(false);
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
    }
    
    private void otevriOAplikaciDialog(){ //Akce informace pro tlačítko mi_oAplikaci
        Alert alertOAplikaci = new Alert(Alert.AlertType.INFORMATION);
        alertOAplikaci.setTitle("UTB Sys - O Aplikaci");
        ((Stage)alertOAplikaci.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("Icon.png")));
        alertOAplikaci.setHeaderText(null);
        alertOAplikaci.setContentText("UTB Sys je aplikace určená k vytváření předmětů a jejich rozdělení učitelům.\nAutor: Bc. Jan Hána\nVersion: 1.0");
        alertOAplikaci.showAndWait();
    }
    
    private void otevriPridatSkupinku(){
        //Otevre nove okno na pridani skupinky
        GUI_pridatSkupinkuController guiPridatSkupinku = new GUI_pridatSkupinkuController(this.seznamSkupinek);
        guiPridatSkupinku.showStage();
        //Vrati seznam
        seznamSkupinek = guiPridatSkupinku.vratSeznamSkupinek();
        //Prida stitek do GridPaneOkno
        gpo_skupinka.addStitek(new GUI_stitekSkupinkaController(guiPridatSkupinku.vratSkupinku()));
    }
    
    private void otevriPridatPredmet(){
        GUI_pridatPredmetController guiPridatPredmet = new GUI_pridatPredmetController();
        guiPridatPredmet.showStage();
    }
    
    private void otevriPridatZamestnance(){
        GUI_pridatZamestnanceController guiPridatZamestnance = new GUI_pridatZamestnanceController(this.seznamZamestnancu);
        guiPridatZamestnance.showStage();
        seznamZamestnancu = guiPridatZamestnance.vratSeznamZamestnancu();
        System.out.println(guiPridatZamestnance.vratZamestnance());
        zobrazDataVListView();
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
}
