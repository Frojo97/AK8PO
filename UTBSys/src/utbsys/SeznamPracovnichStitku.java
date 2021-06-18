package utbsys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeznamPracovnichStitku {
    private final ObservableList<PracovniStitek> seznamPracovnichStitku = FXCollections.observableArrayList();
    
    public void pridatDoSeznamu(PracovniStitek ps){ //Přidá nové skupinky do seznamu
        this.seznamPracovnichStitku.add(ps);
    }
    
    public void editacePracovnihoStitku(String nazevStitku, PracovniStitek upravenyPracStitek){
        for (int i = 0; i < seznamPracovnichStitku.size(); i++){
            if (seznamPracovnichStitku.get(i).getNazev().equals(upravenyPracStitek.getNazev())){
                seznamPracovnichStitku.get(i).setPracovniStitek(upravenyPracStitek.getZamestnanecID(),
                        upravenyPracStitek.getPredmetID(),
                        upravenyPracStitek.getTypStitku(),
                        upravenyPracStitek.getPocetStudentu(),
                        upravenyPracStitek.getPocetHodin(),
                        upravenyPracStitek.getPocetTydnu(),
                        upravenyPracStitek.getJazyk()
                );
            }
        }
    }
    
    public void pridaniZamDoPracovnihoStitku(String nazevStitku, Zamestnanec zamestnanec){
        for (int i = 0; i < seznamPracovnichStitku.size(); i++){
            if (seznamPracovnichStitku.get(i).getNazev().equals(nazevStitku)){
                seznamPracovnichStitku.get(i).setZamestnanec(zamestnanec.getID()); 
            }
        }
    }
    
    public void odebratZamZPracovnihoStitku(String nazevStitku){
        for (int i = 0; i < seznamPracovnichStitku.size(); i++){
            if (seznamPracovnichStitku.get(i).getNazev().equals(nazevStitku)){
                seznamPracovnichStitku.get(i).setZamestnanec(0); 
            }
        }
    }
    
    public ObservableList<PracovniStitek> vratSeznamOL(){
        return seznamPracovnichStitku;
    }
}
