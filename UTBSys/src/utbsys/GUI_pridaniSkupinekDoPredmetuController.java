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

public class GUI_pridaniSkupinekDoPredmetuController {
    private final Stage StageAddSkupinky;
    @FXML
    private ListView<Skupinka> lv_skupinky;
    @FXML
    private ListView<Skupinka> lv_pridatSkupinky;
    @FXML
    private Button btn_pridatSK;
    @FXML
    private Button btn_odebratSK;
    @FXML
    private Button btn_pridat;
    private SeznamSkupinek seznamDanychSkupinek;
    private SeznamSkupinek seznamDoPredmetu = new SeznamSkupinek();
    
    public GUI_pridaniSkupinekDoPredmetuController(SeznamSkupinek seznamDanychSkupinek, SeznamSkupinek seznamDoPredmetu){
        StageAddSkupinky = new Stage();
        this.seznamDanychSkupinek = seznamDanychSkupinek;
        if (seznamDoPredmetu != null)
            this.seznamDoPredmetu = seznamDoPredmetu;
        
        try {
            FXMLLoader hlavniObrazovkaController = new FXMLLoader(getClass().getResource("GUI_pridaniSkupinekDoPredmetu.fxml"));
            hlavniObrazovkaController.setController(this);
            StageAddSkupinky.setScene(new Scene(hlavniObrazovkaController.load()));
            Image icona = new Image(getClass().getResourceAsStream("Icon.png"));
            StageAddSkupinky.getIcons().add(icona);
            StageAddSkupinky.setTitle("UTB Sys - Přidání skupinky do předmětu");
            StageAddSkupinky.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        setDisableBTNPridat();
        setDisableBTNOdebrat();
        zobrazDataLV1();
        if (seznamDoPredmetu != null)
            zobrazDataLV2(); 
        
        lv_skupinky.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Skupinka>() {
            public void changed(ObservableValue<? extends Skupinka> observable, Skupinka oldValue, Skupinka newValue) {
                setEnableBTNPridat();
            }
        });
        
        lv_pridatSkupinky.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Skupinka>() {
            public void changed(ObservableValue<? extends Skupinka> observable, Skupinka oldValue, Skupinka newValue) {
                setEnableBTNOdebrat();
            }
        });
    }
    
    public void showStage(){ //Pro zobrazení stage
        StageAddSkupinky.showAndWait();
    }
    
    @FXML
    private void initialize() { //Inicializace jednotlivých komponent z fxml a přiřazení eventů
        btn_pridat.setOnAction(event -> pridatSkupiny());
        btn_pridatSK.setOnAction(event -> pridatSkupinyDoSeznamu());
        btn_odebratSK.setOnAction(event -> odebratSkupinyZeSeznamu());
    }
    
    private void setDisableBTNPridat(){
        btn_pridatSK.setDisable(true);
    }
    
    private void setEnableBTNPridat(){
        btn_pridatSK.setDisable(false);
    }
    
    private void setDisableBTNOdebrat(){
        btn_odebratSK.setDisable(true);
    }
    
    private void setEnableBTNOdebrat(){
        btn_odebratSK.setDisable(false);
    }
    
    private void pridatSkupiny(){
        if(seznamDoPredmetu.getOBSeznam().size() > 0){
            StageAddSkupinky.close();
        }
        else{
            AlertOkno alert = new AlertOkno('E', "Chyba", "Musí být vybrána aspoň jedna skupinka!");
        }     
    }
    
    public SeznamSkupinek vratSkupinky(){
        return seznamDoPredmetu;
    }
    
    private void pridatSkupinyDoSeznamu(){
        seznamDoPredmetu.pridatDoSeznamu(lv_skupinky.getSelectionModel().getSelectedItem());
        seznamDanychSkupinek.odstranitZeSeznamu(lv_skupinky.getSelectionModel().getSelectedItem().getID());
        zobrazDataLV2();
        lv_skupinky.getSelectionModel().clearSelection();
        setDisableBTNPridat();
    }
    
    private void odebratSkupinyZeSeznamu(){
        seznamDanychSkupinek.pridatDoSeznamu(lv_pridatSkupinky.getSelectionModel().getSelectedItem());
        seznamDoPredmetu.odstranitZeSeznamu(lv_pridatSkupinky.getSelectionModel().getSelectedItem().getID());
        zobrazDataLV1();
        lv_pridatSkupinky.getSelectionModel().clearSelection();
        setDisableBTNOdebrat();
    }
    
    private void zobrazDataLV1(){
        lv_skupinky.setItems(seznamDanychSkupinek.getOBSeznam());
        lv_skupinky.setCellFactory(param -> new ListCell<Skupinka>() {
            @Override
            protected void updateItem(Skupinka item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.toString() == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
    }
    
    private void zobrazDataLV2(){
        lv_pridatSkupinky.setItems(seznamDoPredmetu.getOBSeznam());
        lv_pridatSkupinky.setCellFactory(param -> new ListCell<Skupinka>() {
            @Override
            protected void updateItem(Skupinka item, boolean empty) {
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
