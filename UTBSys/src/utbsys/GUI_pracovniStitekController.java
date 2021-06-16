package utbsys;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GUI_pracovniStitekController{
    @FXML
    private Label lbl_nazev;
    @FXML
    private Label lbl_body;
    @FXML
    private Label lbl_predmetPlusJazyk;
    @FXML
    private Label lbl_typStitku;
    @FXML
    private Label lbl_zamestnanec;
    @FXML
    private Label lbl_pocetStudentu;
    @FXML
    private Label lbl_pocetHodin;
    @FXML
    private Label lbl_pocetTydnu;
    
    private VBox vb_stitekOkno;
    
    public GUI_pracovniStitekController(PracovniStitek pracovniStitek){
        loadGUI();
        setData(pracovniStitek);
    }
    
    private void loadGUI(){
        FXMLLoader guiController = new FXMLLoader(getClass().getResource("GUI_pracovniStitek.fxml"));
        guiController.setController(this);
        try {
            vb_stitekOkno = guiController.load();
        } catch (IOException ex) {
            Logger.getLogger(GUI_stitekSkupinkaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private void setData(PracovniStitek pracovniStitek){
        lbl_nazev.setText(pracovniStitek.getNazev());
        lbl_body.setText(String.valueOf(pracovniStitek.getPocetBodu()));
        lbl_predmetPlusJazyk.setText(pracovniStitek.getPredmetID() + " - " + pracovniStitek.getJazyk());
        lbl_typStitku.setText(pracovniStitek.getTypStitku().toString());
        lbl_pocetStudentu.setText(String.valueOf(pracovniStitek.getPocetStudentu()));
        lbl_pocetHodin.setText(String.valueOf(pracovniStitek.getPocetHodin()));
        lbl_pocetTydnu.setText(String.valueOf(pracovniStitek.getPocetTydnu()));
        nastavBarvu(pracovniStitek);
    }
    
    private void nastavBarvu(PracovniStitek pracovniStitek){
        if (!pracovniStitek.getPredmetID().isEmpty() && pracovniStitek.getZamestnanecID() > 0){
            String cssLayout = "-fx-background-color: #d1e7dd;\n" +
                "-fx-border-color: #bcd0c7;\n" +
                   "-fx-border-insets: 2;\n" +
                   "-fx-border-width: 3;\n" +
                   "-fx-border-style: dashed;\n" +
                    "-fx-background-insets: 5;\n"
                   ;
            vb_stitekOkno.setStyle(cssLayout);
        }
        else if (!pracovniStitek.getPredmetID().isEmpty() && pracovniStitek.getZamestnanecID() == 0){
            
        }
    }
    
    public VBox getStitek(){
        return vb_stitekOkno;
    }
    
}