package utbsys;

import javafx.scene.layout.GridPane;

public class GUI_GridPaneOkno {
    private GridPane gp_okno;
    private int x = 0;
    private int y = 0;
    
    public GUI_GridPaneOkno(){
        this.gp_okno = new GridPane();
    }
    
    public GridPane getGP_okno(){
        return gp_okno;
    }  
    
    public void addStitek(GUI_stitekSkupinkaController stitekS){
        this.gp_okno.add(stitekS.getStitek(),x,y);
        x++;
        if (x == 10){ //Podmínka pro to, když je X deset, aby se přidal další řádek
            y++;
            x = 0;
        }
    }
}
