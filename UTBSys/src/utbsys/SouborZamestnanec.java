package utbsys;

public class SouborZamestnanec {
    private final String nazevXML = "SouborZamestnanec.xml";
    
    private SouborZamestnanec(){} //Konstruktor
    
    private static SouborZamestnanec SK = null;
    
    public static SouborZamestnanec SK(){
        if(SK == null)
            SK = new SouborZamestnanec();
        return SK;
    }
    
    private String getNazevXML(){ //Vrátí název souboru
        return nazevXML;
    }
    
    
}
