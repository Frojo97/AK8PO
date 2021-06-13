package utbsys;

public class Skupinka {
    private int ID;
    private String nazevSkupinky;
    private String zkratkaSkupinky;
    private int rocnik;
    private EnumSemestr semestr;
    private int pocetStudentu;
    private EnumFormaStudia formaStudia;
    private EnumTypStudia typStudia;
    private EnumJazyk jazyk;
    
    public Skupinka(int ID, String nazevSkupinky, String zkratkaSkupinky, int rocnik, EnumSemestr semestr, int pocetStudentu, EnumFormaStudia formaStudia, EnumTypStudia typStudia, EnumJazyk jazyk){
        this.ID = ID;
        this.nazevSkupinky = nazevSkupinky;
        this.zkratkaSkupinky = zkratkaSkupinky;
        this.rocnik = rocnik;
        this.semestr = semestr;
        this.pocetStudentu = pocetStudentu;
        this.formaStudia = formaStudia;
        this.typStudia = typStudia;
        this.jazyk = jazyk;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public String getNazevSkupinky(){
        return this.nazevSkupinky;
    }
    
    public String getZkratkaSkupinky(){
        return this.zkratkaSkupinky;
    }
    
    public String getRocnik(){
        return String.valueOf(this.rocnik);
    }
    
    public String getSemestr(){
        return this.semestr.toString();
    }
    
    public String getPocetStudentu(){
        return String.valueOf(this.pocetStudentu);
    }
    
    public String getFormaStudia(){
        return this.formaStudia.toString(); 
    }
    
    public String getTypStudia(){
        return this.typStudia.toString();
    }
    
    public String getJazyk(){
        return this.jazyk.toString();
    }
}
