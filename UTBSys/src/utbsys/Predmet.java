package utbsys;

import javafx.collections.ObservableList;

public class Predmet {
    private int ID;
    private String nazevPredmetu;
    private String zkratkaPredmetu;
    private int pocetKreditu;
    private int pocetTydnu;
    private int pocetPrednasek;
    private int pocetSeminaru;
    private EnumZakonceni zakonceni;
    private EnumJazyk jazyk;
    private ObservableList<Skupinka> seznamSkupin;
    
    public Predmet(int ID, String nazevPredmetu, String zkratkaPredmetu, int pocetKreditu, int pocetTydnu, int pocetPrednasek, int pocetSeminaru, EnumZakonceni zakonceni, EnumJazyk jazyk){
        this.ID = ID;
        this.nazevPredmetu = nazevPredmetu;
        this.zkratkaPredmetu = zkratkaPredmetu;
        this.pocetKreditu = pocetKreditu;
        this.pocetTydnu = pocetTydnu;
        this.pocetPrednasek = pocetPrednasek;
        this.pocetSeminaru = pocetSeminaru;
        this.zakonceni = zakonceni;
        this.jazyk = jazyk;
    }
}
