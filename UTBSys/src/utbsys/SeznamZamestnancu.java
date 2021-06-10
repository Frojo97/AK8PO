package utbsys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeznamZamestnancu {
    private final ObservableList<Zamestnanec> seznamZamestnanec = FXCollections.observableArrayList();
    
    public void pridatDoSeznamu(Zamestnanec zm){ //Přidá nového zamestnance do seznamu
        this.seznamZamestnanec.add(zm);
    }
}
