package utbsys;

public class TovarnaNaStitky {
    private TovarnaNaStitky(){} //Konstruktor
    
    private static TovarnaNaStitky TNS = null;
    
    public static TovarnaNaStitky TNS(){
        if(TNS == null)
            TNS = new TovarnaNaStitky();
        return TNS;
    }
    
    public PracovniStitek vytvorPracovniStitek(String nazev, String predmetID, EnumTypStitku typStitku, int pocetStudentu, int pocetHodin, int pocetTydnu, EnumJazyk jazyk, VahyPracBodu vahy){
        PracovniStitek pracStitek;
        pracStitek = new PracovniStitek(nazev, 0, predmetID, typStitku, pocetStudentu, pocetHodin, pocetTydnu, jazyk);
        return pracStitek;
    } 
}
