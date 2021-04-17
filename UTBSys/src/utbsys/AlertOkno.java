package utbsys;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertOkno {
    private char typAlertu;
    private String titulek;
    private String vzkaz;
    private Alert alertik;
    
    public AlertOkno(char typAlertu, String titulek, String vzkaz){
        this.typAlertu = typAlertu;
        this.titulek = titulek;
        this.vzkaz = vzkaz;
        zobrazAlert();
    }
    
    private void zobrazAlert(){   
        switch(typAlertu){
            case 'E':{
                alertik = new Alert(Alert.AlertType.ERROR);
            }
            break;
        }
                
        alertik.setTitle("UTB Sys - " + titulek);
        
        ((Stage)alertik.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("Icon.png")));
        alertik.setHeaderText(null);     
        
        alertik.setContentText(vzkaz);
         
               
        
        alertik.showAndWait();    
    }
}
