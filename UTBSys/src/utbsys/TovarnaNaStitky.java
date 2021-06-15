package utbsys;

public class TovarnaNaStitky {
    private TovarnaNaStitky(){} //Konstruktor
    
    private static TovarnaNaStitky TNS = null;
    
    public static TovarnaNaStitky TNS(){
        if(TNS == null)
            TNS = new TovarnaNaStitky();
        return TNS;
    }
    
    public PracovniStitek vytvorPracovniStitek(){
        PracovniStitek pracStitek;
        //pracStitek = new PracovniStitek(nazev, 0, predmetID, 0, 0, 0, EnumJazyk.CZ, 0);
        return null;
    } 
}
