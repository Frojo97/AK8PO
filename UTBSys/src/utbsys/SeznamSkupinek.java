package utbsys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeznamSkupinek {
    private final ObservableList<Skupinka> seznamSkupinky = FXCollections.observableArrayList();
    
    public int getNewID(){ //Vrátí novou ID hodnotu pro novou skupinku
        if (seznamSkupinky.isEmpty())
            return 1;
        else{
            return seznamSkupinky.get(seznamSkupinky.size()-1).getID() + 1;
        }
    }
    
    public void pridatDoSeznamu(Skupinka sk){ //Přidá nové skupinky do seznamu
        this.seznamSkupinky.add(sk);
    }
    
    public void odstranitZeSeznamu(int ID){ //Odstraní danou skupinku
        this.seznamSkupinky.remove(getSkupinka(ID));
    }
    
    public Skupinka getSkupinka(int ID){ //Vrati danou skupinku pomoci ID
        for (int i = 0; i < seznamSkupinky.size(); i++){
            if (seznamSkupinky.get(i).getID() == ID)
                return seznamSkupinky.get(i); 
        }
        return null;
    }
    
    public ObservableList<Skupinka> getOBSeznam(){
        return seznamSkupinky;
    }
}
