package utbsys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeznamPredmetu {
    private final ObservableList<Predmet> seznamPredmetu = FXCollections.observableArrayList();
    
    public void pridatDoSeznamu(Predmet pt){ //Přidá nové skupinky do seznamu
        this.seznamPredmetu.add(pt);
    }
    
    public ObservableList<Predmet> vratSeznamOL(){
        return seznamPredmetu;
    }
    
    public boolean zjistiZdaExistuje(Predmet pt){ //Zjistí zda daný předmět existuje
        for (int i = 0; i < seznamPredmetu.size(); i++){
            if (seznamPredmetu.get(i).getZkratkaPredmetu().equals(pt.getZkratkaPredmetu())){
                return true;
            }
        }
        return false; 
    }
}
