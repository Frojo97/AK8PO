package utbsys;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI_pridatZamestnanceController {
    private final Stage StagePridatZamestnance;
    
    public GUI_pridatZamestnanceController(){
        StagePridatZamestnance = new Stage();
        try {
            FXMLLoader pridatZamestnanceController = new FXMLLoader(getClass().getResource("GUI_pridatZamestnance.fxml"));
            pridatZamestnanceController.setController(this);
            StagePridatZamestnance.setScene(new Scene(pridatZamestnanceController.load()));
            Image icona = new Image(getClass().getResourceAsStream("Icon.png"));
            StagePridatZamestnance.getIcons().add(icona);
            StagePridatZamestnance.setTitle("UTB Sys - Přidat zaměstnance");
            StagePridatZamestnance.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    public void showStage(){ //Pro zobrazení stage
        StagePridatZamestnance.show();
    }
}
