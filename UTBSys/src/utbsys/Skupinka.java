package utbsys;

public class Skupinka {
    private String nazevSkupinky;
    private String zkratkaSkupinky;
    private int rocnik;
    private EnumSemestr semestr;
    private int pocetStudentu;
    private EnumFormaStudia formaStudia;
    private EnumTypStudia typStudia;
    private EnumJazyk jazyk;
    
    public Skupinka(String nazevSkupinky, String zkratkaSkupinky, int rocnik, EnumSemestr semestr, int pocetStudentu, EnumFormaStudia formaStudia, EnumTypStudia typStudia, EnumJazyk jazyk){
        this.nazevSkupinky = nazevSkupinky;
        this.zkratkaSkupinky = zkratkaSkupinky;
        this.rocnik = rocnik;
        this.semestr = semestr;
        this.pocetStudentu = pocetStudentu;
        this.formaStudia = formaStudia;
        this.typStudia = typStudia;
        this.jazyk = jazyk;
    }
    
    public String getNazevSkupinky(){
        return nazevSkupinky;
    }
    
    public String getZkratkaSkupinky(){
        return zkratkaSkupinky;
    }
    
    public String getRocnik(){
        return String.valueOf(rocnik);
    }
    
    public String getSemestr(){
        return semestr.toString();
    }
    
    public String getPocetStudentu(){
        return String.valueOf(pocetStudentu);
    }
    
    public String getFormaStudia(){
        return formaStudia.toString(); 
    }
    
    public String getTypStudia(){
        return typStudia.toString();
    }
    
    public String getJazyk(){
        return jazyk.toString();
    }
}
