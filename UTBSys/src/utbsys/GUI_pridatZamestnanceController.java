package utbsys;

import java.io.IOException;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI_pridatZamestnanceController {
    private final Stage StagePridatZamestnance;
    @FXML
    private TextField tf_titulPred;
    @FXML
    private TextField tf_jmeno;
    @FXML
    private TextField tf_prijmeni;
    @FXML
    private TextField tf_titulZa;
    @FXML
    private TextField tf_pracTel;
    @FXML
    private TextField tf_soukTel;
    @FXML
    private TextField tf_pracEmail;
    @FXML
    private TextField tf_soukEmail;
    @FXML
    private TextField tf_kancelar;
    @FXML
    private CheckBox cheb_doktorand;
    @FXML
    private ChoiceBox<EnumUvazek> chob_uvazek;
    @FXML
    private Button btn_pridat;
    private Zamestnanec novyZamestnanec;
    private SeznamZamestnancu seznamZamestnancu;
     
    public GUI_pridatZamestnanceController(SeznamZamestnancu seznamZamestnancu){
        StagePridatZamestnance = new Stage();
        this.seznamZamestnancu = seznamZamestnancu;
        
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
        
        chob_uvazek.setItems(FXCollections.observableArrayList(EnumUvazek.values()));
    }
    
    public void showStage(){ //Pro zobrazení stage
        StagePridatZamestnance.showAndWait();
    }
    
    @FXML
    private void initialize() { //Inicializace jednotlivých komponent z fxml a přiřazení eventů
        btn_pridat.setOnAction(event -> pridatZamestnance());
    }
    
    private void pridatZamestnance(){
        if (kontrola()){
            if (kontrolaTelefonu()){
                if (kontrolaEmailu()){
                    novyZamestnanec = new Zamestnanec( seznamZamestnancu.getNewID(),
                            tf_titulPred.getText(),
                            tf_jmeno.getText(),
                            tf_prijmeni.getText(),
                            tf_titulZa.getText(),
                            tf_pracTel.getText(),
                            tf_soukTel.getText(),
                            tf_pracEmail.getText(),
                            tf_soukEmail.getText(),
                            tf_kancelar.getText(),
                            cheb_doktorand.isSelected(),
                            chob_uvazek.getValue()
                    );
                    seznamZamestnancu.pridatDoSeznamu(novyZamestnanec);
                    StagePridatZamestnance.close();
                } 
            }
        }
        else{
            AlertOkno alert = new AlertOkno('E', "Chyba", "Nevyplnil si všechna potřebná pole označená znakem * (hvězdičkou)!");
        }
    }
    
    public SeznamZamestnancu vratSeznamZamestnancu(){
        return seznamZamestnancu;
    }
    
    /*public Zamestnanec vratZamestnance(){
        return novyZamestnanec;
    }*/
    
    private boolean kontrola(){
        if (!tf_jmeno.getText().trim().isEmpty() &&
                !tf_prijmeni.getText().trim().isEmpty() &&
                !tf_pracTel.getText().trim().isEmpty() &&
                !tf_pracEmail.getText().trim().isEmpty() &&
                !tf_kancelar.getText().trim().isEmpty() &&
                chob_uvazek.getValue() != null
            )
            return true;
        else
            return false;
    } 
    
    private boolean kontrolaTelefonu(){
        if (!tf_pracTel.getText().trim().isEmpty()){
            if (kontrolaRegTelefonu(tf_pracTel.getText())){
                if (!tf_soukTel.getText().trim().isEmpty()){
                    if (kontrolaRegTelefonu(tf_soukTel.getText())){
                        return true;
                    }
                    else {
                        AlertOkno alert = new AlertOkno('E', "Chyba", "Soukromý telefon je v nesprávném tvaru!\nJe potřeba napsat i předvolbu (+420\\+421)");
                        return false;
                    }
                }
                else {
                    //pokracuje protože nemusí být zanesen soukromý telefon
                    return true;
                }
            }
            else{
                AlertOkno alert = new AlertOkno('E', "Chyba", "Pracovní telefon je v nesprávném tvaru!\nJe potřeba napsat i předvolbu (+420\\+421)");
                return false;
            }
        }
        else {
            AlertOkno alert = new AlertOkno('E', "Chyba", "Nezadal si pracovní telefon!"); //tento stav může, ale nikdy nenastane vyhodnotí kontrola()
            return false;
        }   
    }
    
    private boolean kontrolaEmailu(){
        if (!tf_pracEmail.getText().trim().isEmpty()){
            if (kontrolaRegEmailu(tf_pracEmail.getText())){
                if (!tf_soukEmail.getText().trim().isEmpty()){
                    if (kontrolaRegEmailu(tf_soukEmail.getText())){
                        return true;
                    }
                    else{
                        AlertOkno alert = new AlertOkno('E', "Chyba", "Soukromý email je v nesprávném tvaru!");
                        return false;
                    }     
                }
                else{
                    //pokracuje protože nemusí být zanesek soukromý email
                    return true; 
                }            
            }
            else{
                AlertOkno alert = new AlertOkno('E', "Chyba", "Pracovní email je v nesprávném tvaru!");
                return false;
            }            
        }
        else{
            AlertOkno alert = new AlertOkno('E', "Chyba", "Nevyplnil si pracovní email zaměstnance!"); //tento stav může, ale nikdy nenastane vyhodnotí kontrola()
            return false;
        }
    }
    
    private boolean kontrolaRegTelefonu(String telefon){
        Pattern regular = Pattern.compile("^(\\+420|\\+421) ?[0-9]{3} ?[0-9]{3} ?[0-9]{3}$");
        return regular.matcher(telefon).matches();
    }
    
    private boolean kontrolaRegEmailu(String email){
        Pattern regular = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        return regular.matcher(email).matches(); 
    }
}
