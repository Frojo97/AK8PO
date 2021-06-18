package utbsys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeznamPracovnichStitku {
    private final ObservableList<PracovniStitek> seznamPracovnichStitku = FXCollections.observableArrayList();
    
    public void pridatDoSeznamu(PracovniStitek ps){ //Přidá nové skupinky do seznamu
        this.seznamPracovnichStitku.add(ps);
    }
    
    public void odebratZeSeznamu(String nazevPredmetu){ //Přidá nové skupinky do seznamu
        for (int i = 0; i < seznamPracovnichStitku.size(); i++){
            if (seznamPracovnichStitku.get(i).getPredmetID().equals(nazevPredmetu))
                this.seznamPracovnichStitku.remove(seznamPracovnichStitku.get(i));
        }
        
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
    
    public int vratIDzamestnance(String nazevStitku){
        for (int i = 0; i < seznamPracovnichStitku.size(); i++){
            if (seznamPracovnichStitku.get(i).getNazev().equals(nazevStitku)){
                return seznamPracovnichStitku.get(i).getZamestnanecID();
            }
        }
        return 0;
    }
    
    public void odebraniZamZeVsechPracovnihoStitku(int ID){
        for (int i = 0; i < seznamPracovnichStitku.size(); i++){
            if (seznamPracovnichStitku.get(i).getZamestnanecID() == ID){
                seznamPracovnichStitku.get(i).setZamestnanec(0); 
            }
        }
    }
    
    public ObservableList<PracovniStitek> vratSeznamOL(){
        return seznamPracovnichStitku;
    }
    
    public PracovniStitek getPracovniStitek(String nazev){ //Vrati danou skupinku pomoci ID
        for (int i = 0; i < seznamPracovnichStitku.size(); i++){
            if (seznamPracovnichStitku.get(i).getNazev().equals(nazev))
                return seznamPracovnichStitku.get(i); 
        }
        return null;
    }
}
