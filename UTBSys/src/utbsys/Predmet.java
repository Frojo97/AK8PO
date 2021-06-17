package utbsys;

import javafx.collections.ObservableList;

public class Predmet {
    private String nazevPredmetu;
    private String zkratkaPredmetu;
    private int pocetKreditu;
    private int pocetTydnu;
    private int hodinPrednasek;
    private int hodinCviceni;
    private int hodinSeminaru;
    private EnumZakonceni zakonceni;
    private EnumJazyk jazyk;
    private int velikostTridy;
    private ObservableList<Skupinka> seznamSkupin;
    
    public Predmet(String nazevPredmetu, String zkratkaPredmetu, int pocetKreditu, int pocetTydnu, int hodinPrednasek, int hodinCviceni, int hodinSeminaru, EnumZakonceni zakonceni, EnumJazyk jazyk, int velikostTridy, SeznamSkupinek SS){
        this.nazevPredmetu = nazevPredmetu;
        this.zkratkaPredmetu = zkratkaPredmetu;
        this.pocetKreditu = pocetKreditu;
        this.pocetTydnu = pocetTydnu;
        this.hodinPrednasek = hodinPrednasek;
        this.hodinCviceni = hodinCviceni;
        this.hodinSeminaru = hodinSeminaru;
        this.zakonceni = zakonceni;
        this.jazyk = jazyk;
        this.velikostTridy = velikostTridy;
        this.seznamSkupin = SS.getOBSeznam();
    }
    
    public void setVelikostTridy(int velikostTridy){
        this.velikostTridy = velikostTridy;
    }
    
    public void setSeznamSkupin(ObservableList<Skupinka> seznamSkupin){
        this.seznamSkupin = seznamSkupin;
    }
    
    public String getNazevPredmetu(){
	return this.nazevPredmetu;
    }

    public String getZkratkaPredmetu(){
	return this.zkratkaPredmetu;
    }

    public int getPocetKreditu(){
	return this.pocetKreditu;
    }

    public int getPocetTydnu(){
	return this.pocetTydnu;
    }

    public int getHodinPrednasek(){
	return this.hodinPrednasek;
    }
    
    public int getHodinCviceni(){
	return this.hodinCviceni;
    }

    public int getHodinSeminaru(){
	return this.hodinSeminaru;
    }

    public EnumZakonceni getZakonceni(){
	return this.zakonceni;
    }

    public EnumJazyk getJazyk(){
	return this.jazyk;
    }
    
    public int getVelikostTridy(){
	return this.velikostTridy;
    }
    
    public ObservableList<Skupinka> getSkupinkaOL(){
        return this.seznamSkupin;
    }
    
    @Override
    public String toString(){
        return this.zkratkaPredmetu + " - " + this.nazevPredmetu +  " - " + this.jazyk + "    Počet kreditů: " + this.pocetKreditu + "\n" + 
                "Počet týdnů: " + this.pocetTydnu + "    Hodin přednášek: " + this.hodinPrednasek + 
                "    Hodin cvičení: " + this.hodinCviceni + "    Hodin seminářů: " + this.hodinSeminaru + "\n" +
                "Zakončení: " + this.zakonceni + "\n" + 
                "Velikost třídy: " + this.velikostTridy;
    }
}
