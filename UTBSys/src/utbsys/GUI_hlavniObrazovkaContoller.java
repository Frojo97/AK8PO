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
    private ListView<Predmet> lv_predmet;
    @FXML
    private ListView<Zamestnanec> lv_zamestnanci;
    @FXML
    private ScrollPane sp_pracovniStitky;
    //GUI rozmístění pro skupinku
    private GUI_GridPaneOknoSkupinka gpo_skupinka = new GUI_GridPaneOknoSkupinka();
    private GUI_GridPaneOknoPracovniStitek gpo_pracovniStitky = new GUI_GridPaneOknoPracovniStitek();
    private SeznamSkupinek seznamSkupinek = new SeznamSkupinek();
    private SeznamPredmetu seznamPredmetu = new SeznamPredmetu();
    private SeznamZamestnancu seznamZamestnancu = new SeznamZamestnancu();
    private SeznamPracovnichStitku seznamPracovnichStitku = new SeznamPracovnichStitku();
    private VahyPracBodu vahyPracBodu;
    
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
        sp_pracovniStitky.setContent(gpo_pracovniStitky.getGP_okno());
             
        seznamSkupinek = SouborSkupinka.SK().nacteniSkupinky();
        ObservableList<Skupinka> oblSkupinka = seznamSkupinek.getOBSeznam();
        for(int i = 0; i < oblSkupinka.size(); i++){
            createStitekSkupinka(oblSkupinka.get(i));
        }
        
        seznamPredmetu = SouborPredmet.SP().nacteniPredmetu(seznamSkupinek);
        setDisableBTNPredmet();
        zobrazDataVListViewPredmet();
        
        seznamZamestnancu = SouborZamestnanec.SZ().nacteniZamestnancu();
        setDisableBTNZamestnanec();
        zobrazDataVListViewZamestnanec();
        
        vahyPracBodu = SouborVahyBodu.SVB().nacteniVahyBodu();
        
        seznamPracovnichStitku = SouborPracovniStitek.SPS().nacteniPracovnichStitku();
        ObservableList<PracovniStitek> oblPracovni = seznamPracovnichStitku.vratSeznamOL();
        for (int i = 0; i < oblPracovni.size(); i++){
            createPracovniStitek(oblPracovni.get(i));
        }

        lv_predmet.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Predmet>() {
            public void changed(ObservableValue<? extends Predmet> observable, Predmet oldValue, Predmet newValue) {
                setEnableBTNPredmet();
            }
        });
        
        lv_zamestnanci.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Zamestnanec>() {
            public void changed(ObservableValue<? extends Zamestnanec> observable, Zamestnanec oldValue, Zamestnanec newValue) {
                setEnableBTNZamestnanec();
            }
        });
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
        btn_editovatPredmet.setOnAction(event -> editacePredmetu());
        btn_smazatPredmet.setOnAction(event -> smazaniPredmetu());
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
        gui_stitekSC.mi_editovatSS.setOnAction(event -> editaceStitekSkupinka(gui_stitekSC.getID()));
        gui_stitekSC.mi_odstranitSS.setOnAction(event -> odstranStitekSkupinka(gui_stitekSC.getID()));
        gpo_skupinka.addStitek(gui_stitekSC);
    }
    
    private void editaceStitekSkupinka(int ID){
        GUI_editaceSkupinkaController gui_editSK = new GUI_editaceSkupinkaController(seznamSkupinek, ID);
        gui_editSK.showStage();
        seznamSkupinek = gui_editSK.vratSeznamSkupinek();
        gpo_skupinka.refactorGridPane();
        ObservableList<Skupinka> sk = seznamSkupinek.getOBSeznam();
        for (int i = 0; i < sk.size(); i++){
            createStitekSkupinka(sk.get(i));
        }
        SouborSkupinka.SK().ulozeniSkupinky(seznamSkupinek.getOBSeznam());
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
        GUI_pridatPredmetController guiPridatPredmet = new GUI_pridatPredmetController(seznamPredmetu, seznamSkupinek);
        guiPridatPredmet.showStage();
        
        if (guiPridatPredmet.vratPredmet() != null){
            seznamPredmetu = guiPridatPredmet.vratSeznamPredmetu();
            zobrazDataVListViewPredmet();
            SouborPredmet.SP().ulozeniPredmetu(seznamPredmetu.vratSeznamOL());
            Predmet pt = guiPridatPredmet.vratPredmet();
            
            //Přednáška je pro všechny stejná ohledne generování
            if (pt.getHodinPrednasek() > 0){
                PracovniStitek docastnyStitek = TovarnaNaStitky.TNS().vytvorPracovniStitek("Přednáška " + pt.getZkratkaPredmetu(), pt.getZkratkaPredmetu(), EnumTypStitku.přednáška, pt.getPocetStudentuOL(), pt.getHodinPrednasek(),  pt.getPocetTydnu(),  pt.getJazyk(), vahyPracBodu);
                seznamPracovnichStitku.pridatDoSeznamu(docastnyStitek);
                createPracovniStitek(docastnyStitek);
            }
            
            if (pt.getPocetTydnu() == 1 && pt.getVelikostTridy() == 0){
                if (pt.getHodinCviceni() > 0){
                    PracovniStitek docastnyStitek = TovarnaNaStitky.TNS().vytvorPracovniStitek("Cvičení " + pt.getZkratkaPredmetu(), pt.getZkratkaPredmetu(), EnumTypStitku.cvičení, pt.getPocetStudentuOL(), pt.getHodinCviceni(), pt.getPocetTydnu(), pt.getJazyk(), vahyPracBodu);
                    seznamPracovnichStitku.pridatDoSeznamu(docastnyStitek);
                    createPracovniStitek(docastnyStitek);
                }
                if (pt.getHodinSeminaru()> 0){
                    PracovniStitek docastnyStitek = TovarnaNaStitky.TNS().vytvorPracovniStitek("Seminář " + pt.getZkratkaPredmetu(), pt.getZkratkaPredmetu(), EnumTypStitku.seminář, pt.getPocetStudentuOL(), pt.getHodinSeminaru(), pt.getPocetTydnu(), pt.getJazyk(), vahyPracBodu);
                    seznamPracovnichStitku.pridatDoSeznamu(docastnyStitek);
                    createPracovniStitek(docastnyStitek);
                }
            }
            else {
                if (pt.getHodinCviceni() > 0){
                    double pocetStudentu = Double.valueOf(pt.getPocetStudentuOL());
                    double velikostTridy = Double.valueOf(pt.getVelikostTridy());
                    double pocetStitku = Math.ceil(pocetStudentu / velikostTridy);
                    double pocetStudentuNaStitku = Math.ceil(pocetStudentu / pocetStitku);
                    int pocetStitkuInt = (int) pocetStitku;
                    int pocetStudentuNaStitkuInt = (int) pocetStudentuNaStitku; 
                    if ((pocetStitkuInt * pocetStudentuNaStitkuInt) == pt.getPocetStudentuOL()){
                        int j = 1;
                        for (int i = 0; i < pocetStitkuInt; i++){
                            PracovniStitek docastnyStitek = TovarnaNaStitky.TNS().vytvorPracovniStitek("Cvičení " + pt.getZkratkaPredmetu() + " " + j, pt.getZkratkaPredmetu(), EnumTypStitku.cvičení, pocetStudentuNaStitkuInt, pt.getHodinCviceni(), pt.getPocetTydnu(), pt.getJazyk(), vahyPracBodu);
                            seznamPracovnichStitku.pridatDoSeznamu(docastnyStitek);
                            createPracovniStitek(docastnyStitek);
                            j++;
                        }
                    }
                    else{
                        int pocetNavic = pocetStudentuNaStitkuInt * pocetStitkuInt;
                        int navic = pocetNavic - pt.getPocetStudentuOL();
                        int oStudNavic = pocetStitkuInt - navic;
                        int odKdyI = pocetStitkuInt - oStudNavic;
                        int j = 1;
                        for (int i = 0; i < pocetStitku; i++){
                            if (i >= odKdyI){
                                PracovniStitek docastnyStitek = TovarnaNaStitky.TNS().vytvorPracovniStitek("Cvičení " + pt.getZkratkaPredmetu() + " " + j, pt.getZkratkaPredmetu(), EnumTypStitku.cvičení, pocetStudentuNaStitkuInt - 1, pt.getHodinCviceni(), pt.getPocetTydnu(), pt.getJazyk(), vahyPracBodu);
                                seznamPracovnichStitku.pridatDoSeznamu(docastnyStitek);
                                createPracovniStitek(docastnyStitek); 
                                j++;
                            }
                            else{
                                PracovniStitek docastnyStitek = TovarnaNaStitky.TNS().vytvorPracovniStitek("Cvičení " + pt.getZkratkaPredmetu() + " " + j, pt.getZkratkaPredmetu(), EnumTypStitku.cvičení, pocetStudentuNaStitkuInt, pt.getHodinCviceni(), pt.getPocetTydnu(), pt.getJazyk(), vahyPracBodu);
                                seznamPracovnichStitku.pridatDoSeznamu(docastnyStitek);
                                createPracovniStitek(docastnyStitek);
                                j++;
                            }    
                        }
                    }
                }
                if (pt.getHodinSeminaru()> 0){
                    double pocetStudentu = Double.valueOf(pt.getPocetStudentuOL());
                    double velikostTridy = Double.valueOf(pt.getVelikostTridy());
                    double pocetStitku = Math.ceil(pocetStudentu / velikostTridy);
                    double pocetStudentuNaStitku = Math.ceil(pocetStudentu / pocetStitku);
                    int pocetStitkuInt = (int) pocetStitku;
                    int pocetStudentuNaStitkuInt = (int) pocetStudentuNaStitku; 
                    if ((pocetStitku * pocetStudentuNaStitku) == pt.getPocetStudentuOL()){
                        int j = 1;
                        for (int i = 0; i < pocetStitku; i++){
                            PracovniStitek docastnyStitek = TovarnaNaStitky.TNS().vytvorPracovniStitek("Seminář " + pt.getZkratkaPredmetu() + " " + j, pt.getZkratkaPredmetu(), EnumTypStitku.seminář, pocetStudentuNaStitkuInt, pt.getHodinSeminaru(), pt.getPocetTydnu(), pt.getJazyk(), vahyPracBodu);
                            seznamPracovnichStitku.pridatDoSeznamu(docastnyStitek);
                            createPracovniStitek(docastnyStitek);
                            j++;
                        }
                    }
                    else{
                        int pocetNavic = pocetStudentuNaStitkuInt * pocetStitkuInt;
                        int navic = pocetNavic - pt.getPocetStudentuOL();
                        int oStudNavic = pocetStitkuInt - navic;
                        int odKdyI = pocetStitkuInt - oStudNavic;
                        int j = 1;
                        for (int i = 0; i < pocetStitku; i++){
                            if (i >= odKdyI){
                                PracovniStitek docastnyStitek = TovarnaNaStitky.TNS().vytvorPracovniStitek("Seminář " + pt.getZkratkaPredmetu() + " " + j, pt.getZkratkaPredmetu(), EnumTypStitku.seminář, pocetStudentuNaStitkuInt - 1, pt.getHodinSeminaru(), pt.getPocetTydnu(), pt.getJazyk(), vahyPracBodu);
                                seznamPracovnichStitku.pridatDoSeznamu(docastnyStitek);
                                createPracovniStitek(docastnyStitek);
                                j++;
                            }
                            else{
                                PracovniStitek docastnyStitek = TovarnaNaStitky.TNS().vytvorPracovniStitek("Seminář " + pt.getZkratkaPredmetu() + " "  + j, pt.getZkratkaPredmetu(), EnumTypStitku.seminář, pocetStudentuNaStitkuInt, pt.getHodinSeminaru(), pt.getPocetTydnu(), pt.getJazyk(), vahyPracBodu);
                                seznamPracovnichStitku.pridatDoSeznamu(docastnyStitek);
                                createPracovniStitek(docastnyStitek);
                                j++;
                            }    
                        }
                    }
    
                }
            } 
            SouborPracovniStitek.SPS().ulozeniPracovnihoStitku(seznamPracovnichStitku.vratSeznamOL());
        }       
    }
    
    private void createPracovniStitek(PracovniStitek pracovniStitek){
        pracovniStitek.vypocetBodu(vahyPracBodu);
        GUI_pracovniStitekController gui_pracovniStitek = new GUI_pracovniStitekController(pracovniStitek);
       /* gui_stitekSC.mi_editovatSS.setOnAction(event -> editaceStitekSkupinka());
        gui_stitekSC.mi_odstranitSS.setOnAction(event -> odstranStitekSkupinka(gui_stitekSC.getID()));*/
        gpo_pracovniStitky.addStitek(gui_pracovniStitek);
    }
    
    private void zobrazDataVListViewPredmet(){ //Zajištuje zobrazení dat v ListView 
        lv_predmet.setItems(seznamPredmetu.vratSeznamOL());
        lv_predmet.setCellFactory(param -> new ListCell<Predmet>() {
            @Override
            protected void updateItem(Predmet item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.toString() == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
    }
    
    private void editacePredmetu(){
        GUI_editacePredmetuController gui_editace = new GUI_editacePredmetuController(seznamPredmetu, lv_predmet.getSelectionModel().getSelectedItem().getZkratkaPredmetu(), seznamSkupinek);
        gui_editace.showStage();
        seznamPredmetu = gui_editace.vratSeznamPredmetu();
        zobrazDataVListViewPredmet();
        SouborPredmet.SP().ulozeniPredmetu(seznamPredmetu.vratSeznamOL());
    }
    
    private void smazaniPredmetu(){
        seznamPredmetu.odstranitZeSeznamu(lv_predmet.getSelectionModel().getSelectedItem().getZkratkaPredmetu());
        zobrazDataVListViewPredmet();
        lv_predmet.getSelectionModel().clearSelection();
        setDisableBTNPredmet();
        SouborPredmet.SP().ulozeniPredmetu(seznamPredmetu.vratSeznamOL());
    }
    
    //Zaměstnanec
    private void otevriPridatZamestnance(){
        GUI_pridatZamestnanceController guiPridatZamestnance = new GUI_pridatZamestnanceController(this.seznamZamestnancu);
        guiPridatZamestnance.showStage();
        seznamZamestnancu = guiPridatZamestnance.vratSeznamZamestnancu();
        zobrazDataVListViewZamestnanec();
        SouborZamestnanec.SZ().ulozeniZamestnancu(seznamZamestnancu.vratSeznamOL());
    }
    
    private void zobrazDataVListViewZamestnanec(){ //Zajištuje zobrazení dat v ListView 
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
        zobrazDataVListViewZamestnanec();
        lv_zamestnanci.getSelectionModel().clearSelection();
        setDisableBTNZamestnanec();
        SouborZamestnanec.SZ().ulozeniZamestnancu(seznamZamestnancu.vratSeznamOL());
    }
    
    private void smazaniZamestnance(){
        seznamZamestnancu.odstranitZeSeznamu(lv_zamestnanci.getSelectionModel().getSelectedItem().getID());
        zobrazDataVListViewZamestnanec();
        lv_zamestnanci.getSelectionModel().clearSelection();
        setDisableBTNZamestnanec();
        SouborZamestnanec.SZ().ulozeniZamestnancu(seznamZamestnancu.vratSeznamOL());
    }
}
