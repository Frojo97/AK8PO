package utbsys;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GUI_stitekSkupinkaController {
    @FXML
    private Label lbl_nazevSkupinky;
    @FXML
    private Label lbl_zkratkaSkupinky;  
    @FXML
    private Label lbl_rocnik;
    @FXML
    private Label lbl_semestr;
    @FXML
    private Label lbl_pocetStudentu;
    @FXML
    private Label lbl_formaStudia;
    @FXML
    private Label lbl_typStudia;
    @FXML
    private Label lbl_jazyk;
    
    private VBox vb_skupinkaOkno;
    
    public GUI_stitekSkupinkaController(Skupinka skupinka){
        loadGUI();
        setData(skupinka);
        
        /*String cssLayout = "-fx-border-color: green;\n" +
                   "-fx-border-insets: 5;\n" +
                   "-fx-border-width: 3;\n" +
                   "-fx-border-style: dashed;\n";*/
        String cssLayout = "-fx-background-color: #d1e7dd;\n" +
                "-fx-border-color: #bcd0c7;\n" +
                   "-fx-border-insets: 2;\n" +
                   "-fx-border-width: 3;\n" +
                   "-fx-border-style: dashed;\n" +
                    "-fx-background-insets: 5;\n"
                   ;
        
        vb_skupinkaOkno.setStyle(cssLayout);
    }
    
    private void loadGUI(){
        FXMLLoader guiController = new FXMLLoader(getClass().getResource("GUI_stitekSkupinka.fxml"));
        guiController.setController(this);
        try {
            vb_skupinkaOkno = guiController.load();
        } catch (IOException ex) {
            Logger.getLogger(GUI_stitekSkupinkaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private void setData(Skupinka skupinka){
        lbl_nazevSkupinky.setText(skupinka.getNazevSkupinky());
        lbl_zkratkaSkupinky.setText(skupinka.getZkratkaSkupinky());
        lbl_rocnik.setText(skupinka.getRocnik());
        lbl_semestr.setText(skupinka.getSemestr());
        lbl_pocetStudentu.setText(skupinka.getPocetStudentu());
        lbl_formaStudia.setText(skupinka.getFormaStudia());
        lbl_typStudia.setText(skupinka.getTypStudia());
        lbl_jazyk.setText(skupinka.getJazyk());
        
    }
    
    public VBox getStitek(){
        return vb_skupinkaOkno;
    }
    
    
}
