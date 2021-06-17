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

public class GUI_editacePredmetuController implements Initializable{
    private final Stage StageEditovatPredmet;
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
    private SeznamPredmetu seznamPredmetu;
    private String upravovanyPredmet;
    private SeznamSkupinek seznamSkupinek;
    private ObservableList<Skupinka> seznamSkupinekPTOL;
    private SeznamSkupinek seznamSkupinekPT = new SeznamSkupinek();
    private Predmet hledPredmet;
    private Predmet novyPredmet; 
    
    public GUI_editacePredmetuController(SeznamPredmetu seznamPredmetu, String zkratkaPredmetu, SeznamSkupinek seznamSkupinek){
        StageEditovatPredmet = new Stage();
        this.seznamPredmetu = seznamPredmetu;
        this.upravovanyPredmet = zkratkaPredmetu;
        this.seznamSkupinek = seznamSkupinek;
        
        try {
            FXMLLoader pridatPredmetController = new FXMLLoader(getClass().getResource("GUI_pridatPredmet.fxml"));
            pridatPredmetController.setController(this);
            StageEditovatPredmet.setScene(new Scene(pridatPredmetController.load()));
            Image icona = new Image(getClass().getResourceAsStream("Icon.png"));
            StageEditovatPredmet.getIcons().add(icona);
            StageEditovatPredmet.setTitle("UTB Sys - Editovat předmět");
            StageEditovatPredmet.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        chb_zakonceniPredmetu.setItems(FXCollections.observableArrayList(EnumZakonceni.values()));
        chb_jazykPredmetu.setItems(FXCollections.observableArrayList(EnumJazyk.values()));
        
        btn_pridat.setText("Editovat");
        nahodInfoOPt(upravovanyPredmet);
        
        btn_pridatSkupinku.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                pridaniSkupinek();
            }
        });
        
        btn_pridat.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                pridani();
            }
        });
    }
    
    public void showStage(){ //Pro zobrazení stage
        StageEditovatPredmet.showAndWait();
    }
    
    private void nahodInfoOPt(String hledanyPredmet){
        hledPredmet = seznamPredmetu.getPredmet(hledanyPredmet);
        tf_nazevPredmetu.setText(hledPredmet.getNazevPredmetu());
        tf_zkratkaPredmetu.setText(hledPredmet.getZkratkaPredmetu());
        s_pocetKreditu.getValueFactory().setValue(hledPredmet.getPocetKreditu());
        chb_pocetTydnu.setValue(hledPredmet.getPocetTydnu());
        s_hodinyPrednasek.getValueFactory().setValue(hledPredmet.getHodinPrednasek());
        s_hodinyCviceni.getValueFactory().setValue(hledPredmet.getHodinCviceni());
        s_hodinySeminaru.getValueFactory().setValue(hledPredmet.getHodinSeminaru());
        chb_zakonceniPredmetu.setValue(hledPredmet.getZakonceni());
        chb_jazykPredmetu.setValue(hledPredmet.getJazyk());
        chb_velikostTridy.setValue(hledPredmet.getVelikostTridy());
        
        tf_nazevPredmetu.setDisable(true);
        tf_zkratkaPredmetu.setDisable(true);
        s_pocetKreditu.setDisable(true);
        chb_pocetTydnu.setDisable(true);
        s_hodinyPrednasek.setDisable(true);
        s_hodinyCviceni.setDisable(true);
        s_hodinySeminaru.setDisable(true);
        chb_zakonceniPredmetu.setDisable(true);
        chb_jazykPredmetu.setDisable(true);
        if (hledPredmet.getVelikostTridy() == 0)
            chb_velikostTridy.setDisable(true); 
        
        seznamSkupinekPTOL = hledPredmet.getSkupinkaOL();
        prevodSeznamu();
    }
    
    private void prevodSeznamu(){ //Převede seznam z ObservableListu do objektu SeznamSkupinek
        for (int i = 0; i < seznamSkupinekPTOL.size(); i++)
            seznamSkupinekPT.pridatDoSeznamu(seznamSkupinekPTOL.get(i));
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
        
        ObservableList<Skupinka> sk = seznamSkupinek.getOBSeznam();
        SeznamSkupinek docasnySeznam = new SeznamSkupinek();
        for (int i = 0; i < sk.size(); i++){
            if (sk.get(i).getFormaStudia().equals(studium.toString())){
                docasnySeznam.pridatDoSeznamu(sk.get(i));
            }
        }
        
        for (int i = 0; i < docasnySeznam.getOBSeznam().size(); i++){
            for (int j = 0; j < seznamSkupinekPTOL.size(); j++){
                if (docasnySeznam.getOBSeznam().get(i).getID() == seznamSkupinekPTOL.get(j).getID()){
                    docasnySeznam.odstranitZeSeznamu(seznamSkupinekPTOL.get(j).getID());
                }
            }
        }

        GUI_pridaniSkupinekDoPredmetuController gui_addSK = new GUI_pridaniSkupinekDoPredmetuController(docasnySeznam, seznamSkupinekPT);
        gui_addSK.showStage();
        seznamSkupinekPT = gui_addSK.vratSkupinky();
    }
    
    private void pridani(){
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
        
        seznamPredmetu.upravPredmet(hledPredmet, novyPredmet);
        StageEditovatPredmet.close();
    }
    
    public SeznamPredmetu vratSeznamPredmetu(){
        return seznamPredmetu;
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

        chb_velikostTridy.getItems().add(24);
        chb_velikostTridy.getItems().add(12);
        chb_velikostTridy.getItems().add(0);
    }  
}
