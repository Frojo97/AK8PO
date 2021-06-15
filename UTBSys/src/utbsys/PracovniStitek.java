package utbsys;

public class PracovniStitek {
    private String nazev;
    private int zamestnanecID;
    private String predmetID;
    private int pocetStudentu;
    private int pocetHodin;
    private int pocetTydnu;
    private EnumJazyk jazyk;
    private double pocetBodu;
    
    public PracovniStitek(String nazev, int zamestnanecID, String predmetID, int pocetStudentu, int pocetHodin, int pocetTydnu, EnumJazyk jazyk, double pocetBodu){
        this.nazev = nazev;
        setPracovniStitek(zamestnanecID, predmetID, pocetStudentu, pocetHodin, pocetTydnu, jazyk, pocetBodu);
    }
    
    public void setPracovniStitek(int zamestnanecID, String predmetID, int pocetStudentu, int pocetHodin, int pocetTydnu, EnumJazyk jazyk, double pocetBodu){
        setZamestnanec(zamestnanecID);
        setPredmet(predmetID);
        this.pocetStudentu = pocetStudentu;
        this.pocetHodin = pocetHodin;
        this.pocetTydnu = pocetTydnu;
        this.jazyk = jazyk;
        this.pocetBodu = pocetBodu;
    }
    
    public void setZamestnanec(int zamestnanecID){
        this.zamestnanecID = zamestnanecID;
    }
    
    public void setPredmet(String predmetID){
        this.predmetID = predmetID;
    }
    
    public String getNazev(){
        return this.nazev;
    }
    
    public int getZamestnanecID(){
        return this.zamestnanecID;
    }
    
    public String getPredmetID(){
        return this.predmetID;
    }
    
    public int getPocetStudentu(){
        return this.pocetStudentu;
    }
    
    public int getPocetHodin(){
        return this.pocetHodin;
    }
    
    public int getPocetTydnu(){
        return this.pocetTydnu;
    }
    
    public EnumJazyk getJazyk(){
        return this.jazyk;
    }
    
    public double getPocetBodu(){
        return this.pocetBodu;
    }
}
