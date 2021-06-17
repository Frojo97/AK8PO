package utbsys;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI_pridaniSkupinekDoPredmetuController {
    private final Stage StageAddSkupinky;
    @FXML
    private ListView<Skupinka> lv_skupinky;
    @FXML
    private Button btn_pridat;
    
    public GUI_pridaniSkupinekDoPredmetuController(SeznamSkupinek SK){
        StageAddSkupinky = new Stage();
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
        
        lv_skupinky.setItems(SK.getOBSeznam());
        //lv_skupinky.setCellFactory(CheckBoxListCell.forListView(Task::selectedProperty));
        
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
    
    public void showStage(){ //Pro zobrazení stage
        StageAddSkupinky.showAndWait();
    }
}
