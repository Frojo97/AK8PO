package utbsys;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class UTBSys extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception { //Zajišťuje start a kontroluje zda existují dvě dané věci a zajistí další okna
        GUI_hlavniObrazovkaContoller guiHlavniObrazovka = new GUI_hlavniObrazovkaContoller();
        guiHlavniObrazovka.showStage();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

  
}

