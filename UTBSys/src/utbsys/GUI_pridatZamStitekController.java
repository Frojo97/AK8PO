package utbsys;

import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI_pridatZamStitekController {
    private final Stage StageAddZam;
    @FXML
    private ListView<Zamestnanec> lv_zamestnanci;
    @FXML
    private Button btn_pridatZam;
    private SeznamZamestnancu seznamZamestnancu;
    private SeznamPracovnichStitku seznamStitku;
    private String IDstitku;
    private Zamestnanec novyZamestnanec;
    
    public GUI_pridatZamStitekController(SeznamZamestnancu seznamZamestnancu, SeznamPracovnichStitku seznamStitku, String IDstitku){
        StageAddZam = new Stage();
        this.seznamZamestnancu = seznamZamestnancu;
        this.seznamStitku = seznamStitku;
        this.IDstitku = IDstitku;
        
        try {
            FXMLLoader hlavniObrazovkaController = new FXMLLoader(getClass().getResource("GUI_pridatZamStitek.fxml"));
            hlavniObrazovkaController.setController(this);
            StageAddZam.setScene(new Scene(hlavniObrazovkaController.load()));
            Image icona = new Image(getClass().getResourceAsStream("Icon.png"));
            StageAddZam.getIcons().add(icona);
            StageAddZam.setTitle("UTB Sys - Přidání zaměstnance do pracovního štítku");
            StageAddZam.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        zobrazDataLV();
        btn_pridatZam.setDisable(true);
        
        lv_zamestnanci.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Zamestnanec>() {
            public void changed(ObservableValue<? extends Zamestnanec> observable, Zamestnanec oldValue, Zamestnanec newValue) {
                btn_pridatZam.setDisable(false);
            }
        });
    }
    
    public void showStage(){ //Pro zobrazení stage
        StageAddZam.showAndWait();
    }
    
    @FXML
    private void initialize() { //Inicializace jednotlivých komponent z fxml a přiřazení eventů
        btn_pridatZam.setOnAction(event -> pridatZam());
    }
    
    private void pridatZam(){
        novyZamestnanec = lv_zamestnanci.getSelectionModel().getSelectedItem();
        seznamStitku.pridaniZamDoPracovnihoStitku(IDstitku, novyZamestnanec);
        StageAddZam.close();
    }
    
    public SeznamPracovnichStitku vratSeznamPracovnichStitku(){
        return this.seznamStitku;
    }
    
    private void zobrazDataLV(){
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
