package utbsys;

public class Skupinka {
    String nazevSkupinky;
    String zkratkaSkupinky;
    int rocnik;
    EnumSemestr semestr;
    int pocetStudentu;
    EnumFormaStudia formaStudia;
    EnumTypStudia typStudia;
    EnumJazyk jazyk;
    
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
}
