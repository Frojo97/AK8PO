package utbsys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeznamZamestnancu {
    private final ObservableList<Zamestnanec> seznamZamestnanec = FXCollections.observableArrayList();
    
    public int getNewID(){ //Vrátí novou ID hodnotu pro nového zaměstnance
        if (seznamZamestnanec.isEmpty())
            return 1;
        else{
            return seznamZamestnanec.get(seznamZamestnanec.size()-1).getID() + 1;
        }
    }
    
    public void pridatDoSeznamu(Zamestnanec zm){ //Přidá nového zamestnance do seznamu
        this.seznamZamestnanec.add(zm);
    }
    
    public void odstranitZeSeznamu(int ID){ //Odstraní daného zaměstnance
        this.seznamZamestnanec.remove(getZamestnanec(ID));
    }
    
    public void editaceZamestnance(int ID, Zamestnanec upravenyZamestnanec){
        for (int i = 0; i < seznamZamestnanec.size(); i++){
            if (seznamZamestnanec.get(i).getID() == ID){
                seznamZamestnanec.get(i).setZamestnanec(upravenyZamestnanec.getTitulPred(),
                        upravenyZamestnanec.getJmeno(),
                        upravenyZamestnanec.getPrijmeni(),
                        upravenyZamestnanec.getTitulZa(),
                        upravenyZamestnanec.getPracTel(),
                        upravenyZamestnanec.getSoukTel(),
                        upravenyZamestnanec.getPracEmail(),
                        upravenyZamestnanec.getSoukEmail(),
                        upravenyZamestnanec.getKancelar(),
                        upravenyZamestnanec.getDoktorand(),
                        upravenyZamestnanec.getUvazek());
            }
        }
    }
    
    public void editaceZamestnancePridatStitek(int ID, PracovniStitek pracStitek){
        for (int i = 0; i < seznamZamestnanec.size(); i++){
            if (seznamZamestnanec.get(i).getID()==ID){
                seznamZamestnanec.get(i).addPracovnichStitku(pracStitek);
            }
        }
    }
    
    public void editaceZamestnanceOdebratStitek(int ID, PracovniStitek pracStitek){
        for (int i = 0; i < seznamZamestnanec.size(); i++){
            if (seznamZamestnanec.get(i).getID()==ID){
                seznamZamestnanec.get(i).deletePracovnichStitku(pracStitek);
            }
        }
    }
    
    public Zamestnanec getZamestnanec(int ID){ //Vrati danou skupinku pomoci ID
        for (int i = 0; i < seznamZamestnanec.size(); i++){
            if (seznamZamestnanec.get(i).getID() == ID)
                return seznamZamestnanec.get(i); 
        }
        return null;
    }
    
    public ObservableList<Zamestnanec> vratSeznamOL(){
        return seznamZamestnanec;
    }
}
