package utbsys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeznamSkupinek {
    private final ObservableList<Skupinka> seznamSkupinky = FXCollections.observableArrayList();
    
    public void pridatDoSeznamu(Skupinka sk){ //Přidá nové skupinky do seznamu
        this.seznamSkupinky.add(sk);
    }
    
    
}
