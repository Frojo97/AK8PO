package utbsys;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class GUI_GridPaneOknoPracovniStitek {
    private GridPane gp_okno;
    private int x = 0;
    private int y = 0;
    
    public GUI_GridPaneOknoPracovniStitek(){
        this.gp_okno = new GridPane();
    }
    
    public GridPane getGP_okno(){
        return gp_okno;
    }  
    
    public void addStitek(GUI_pracovniStitekController stitekP){
        this.gp_okno.add(stitekP.getStitek(),x,y);
        x++;
        if (x == 10){ //Podmínka pro to, když je X deset, aby se přidal další řádek
            y++;
            x = 0;
        }
    }
    
    public void deleteStitek(int ID){
        for (final Node node : this.gp_okno.getChildren()) {
            if (node != null 
                && node.getId() != null
                && node.getId().equals(Integer.toString(ID))){
                this.gp_okno.getChildren().remove(node);
                if (x == 0 && y > 0){
                    y = y - 1;
                    x = 9;                    
                }
                else
                    x = x - 1;
                break;
            }
        }   
    }
    
    public void refactorGridPane(){
        this.gp_okno.getChildren().clear();
        x = 0;
        y = 0;
    }
}
