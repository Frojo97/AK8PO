package utbsys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeznamPredmetu {
    private final ObservableList<Predmet> seznamPredmetu = FXCollections.observableArrayList();
    
    public void pridatDoSeznamu(Predmet pt){ //Přidá nové skupinky do seznamu
        this.seznamPredmetu.add(pt);
    }
    
    public ObservableList<Predmet> getOBSeznam(){
        return seznamPredmetu;
    }
}
