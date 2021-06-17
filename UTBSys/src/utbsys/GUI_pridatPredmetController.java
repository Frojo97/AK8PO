package utbsys;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI_pridatPredmetController implements Initializable {
    private final Stage StagePridatPredmet;
    @FXML
    private TextField tf_nazevPredmetu;
    @FXML
    private TextField tf_zkratkaPredmetu;
    @FXML
    private Spinner<Integer> s_pocetKreditu;
    @FXML
    private ChoiceBox<Integer> chb_pocetTydnu;
    @FXML
    private Spinner<Integer> s_hodinyPrednasek;
    @FXML
    private Spinner<Integer> s_hodinyCviceni;
    @FXML
    private Spinner<Integer> s_hodinySeminaru;
    @FXML
    private ChoiceBox<EnumZakonceni> chb_zakonceniPredmetu;
    @FXML
    private ChoiceBox<EnumJazyk> chb_jazykPredmetu;
    @FXML
    private ChoiceBox<Integer> chb_velikostTridy;
    @FXML
    private Button btn_pridatSkupinku;
    @FXML
    private Button btn_pridat;
    private Predmet novyPredmet;
    private SeznamPredmetu seznamPredmetu;
    private SeznamSkupinek seznamSkupinek;
    private SeznamSkupinek seznamSkupinekPT = new SeznamSkupinek();
    
    public GUI_pridatPredmetController(SeznamPredmetu seznamPredmetu, SeznamSkupinek seznamSkupinek){//Inicializace okna a nastavení jeho prvků
        StagePridatPredmet = new Stage();
        this.seznamPredmetu = seznamPredmetu;
        this.seznamSkupinek = seznamSkupinek;
        
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
        
        btn_pridat.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                pridatPredmet();
            }
        });
        
        btn_pridatSkupinku.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                pridaniSkupinek();
            }
        });
    }
    
    public void showStage(){ //Pro zobrazení stage
        StagePridatPredmet.showAndWait();
    }
        
    private void pridatPredmet(){
        if (kontrolaTextIsEmpty()){
            if (chb_pocetTydnu.getValue() == 1 && chb_velikostTridy.getValue() == 0 ||
                chb_pocetTydnu.getValue() == 14 && chb_velikostTridy.getValue() == 24 ||
                chb_pocetTydnu.getValue() == 14 && chb_velikostTridy.getValue() == 12){
                if (seznamSkupinekPT.getOBSeznam().size() > 0){
                    novyPredmet = new Predmet(tf_nazevPredmetu.getText(),
                            tf_zkratkaPredmetu.getText(),
                            s_pocetKreditu.getValue(),
                            chb_pocetTydnu.getValue(),
                            s_hodinyPrednasek.getValue(),
                            s_hodinyCviceni.getValue(),
                            s_hodinySeminaru.getValue(),
                            chb_zakonceniPredmetu.getValue(),
                            chb_jazykPredmetu.getValue(),
                            chb_velikostTridy.getValue(),
                            seznamSkupinekPT);
                    if (!seznamPredmetu.zjistiZdaExistuje(novyPredmet)){
                        seznamPredmetu.pridatDoSeznamu(novyPredmet);
                        StagePridatPredmet.close();
                    }
                    else {
                        AlertOkno alert = new AlertOkno('E', "Chyba", "Daný předmět se zkratkou už existuje!");
                    }
                }
                else {
                        AlertOkno alert = new AlertOkno('E', "Chyba", "K danému předmětu nebyla přidělena alespoň jedna skupinka!");
                }   
            }
            else {
                AlertOkno alert = new AlertOkno('E', "Chyba", "Nesprávná kombinace počet tydnů a velikosti třídy!\nPro kombi studium je počet týdnů = 1 a velikost třídy = 0\nPro prezenční studium je počet týdnů = 14 a velikost třídy = 24 nebo 12");
            }    
        }
        else {
            AlertOkno alert = new AlertOkno('E', "Chyba", "Nevyplnil si všechny pole!");
        }
    }
    
    public SeznamPredmetu vratSeznamPredmetu(){
        return seznamPredmetu;
    }
    
    public Predmet vratPredmet(){
        return novyPredmet;
    }
    
    private boolean kontrolaTextIsEmpty(){
        if (!tf_nazevPredmetu.getText().trim().isEmpty() &&
                !tf_zkratkaPredmetu.getText().trim().isEmpty() &&
                chb_zakonceniPredmetu.getValue() != null &&
                chb_jazykPredmetu.getValue() != null) 
            return true;
        else
            return false;
    }  

    private void pridaniSkupinek(){
        EnumFormaStudia studium; 
        if (chb_pocetTydnu.getValue() == 1 && chb_velikostTridy.getValue() == 0 ){
            studium = EnumFormaStudia.K;
        }  
        else if (chb_pocetTydnu.getValue() == 14 && chb_velikostTridy.getValue() == 24 || chb_pocetTydnu.getValue() == 14 && chb_velikostTridy.getValue() == 12){
            studium = EnumFormaStudia.P;
        }   
        else 
            studium = null;
        
        if (studium != null){
            ObservableList<Skupinka> sk = seznamSkupinek.getOBSeznam();
            SeznamSkupinek docasnySeznam = new SeznamSkupinek();
            for (int i = 0; i < sk.size(); i++){
                if (sk.get(i).getFormaStudia().equals(studium.toString())){
                    docasnySeznam.pridatDoSeznamu(sk.get(i));
                }
            }
            if (sk.size() > 0) {
                GUI_pridaniSkupinekDoPredmetuController gui_addSK = new GUI_pridaniSkupinekDoPredmetuController(docasnySeznam, null);
                gui_addSK.showStage();
                seznamSkupinekPT = gui_addSK.vratSkupinky();
            }
            else{
                AlertOkno alert = new AlertOkno('E', "Chyba", "Neexistuje, žádná skupina!");
            }
        }
        else {
            AlertOkno alert = new AlertOkno('E', "Chyba", "Nebyla zadáná správna kombinace pro zobrazení skupinek!");
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        SpinnerValueFactory<Integer> svf2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        SpinnerValueFactory<Integer> svf3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        SpinnerValueFactory<Integer> svf4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        this.s_pocetKreditu.setValueFactory(svf);
        this.s_hodinyPrednasek.setValueFactory(svf2);
        this.s_hodinyCviceni.setValueFactory(svf3);
        this.s_hodinySeminaru.setValueFactory(svf4);
        
        chb_pocetTydnu.getItems().add(14);
        chb_pocetTydnu.getItems().add(1);
        chb_pocetTydnu.setValue(14);
        
        chb_velikostTridy.getItems().add(24);
        chb_velikostTridy.getItems().add(12);
        chb_velikostTridy.getItems().add(0);
        chb_velikostTridy.setValue(24);
    }
}
