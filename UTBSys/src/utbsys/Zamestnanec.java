package utbsys;

public class Zamestnanec {
    private String titulPred;
    private String jmeno;
    private String prijmeni;
    private String titulZa;
    private String pracTel;
    private String soukTel;
    private String pracEmail;
    private String soukEmail;
    private String kancelar;
    private boolean doktorand;
    private EnumUvazek uvazek;
    
    public Zamestnanec(String titulPred, String jmeno, String prijmeni, String titulZa, String pracTel, String soukTel, String pracEmail, String soukEmail, String kancelar, boolean doktorand, EnumUvazek uvazek){
        this.titulPred = titulPred;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.titulZa = titulZa;
        this.pracTel = pracTel;
        this.soukTel = soukTel;
        this.pracEmail = pracEmail;
        this.soukEmail = soukEmail;
        this.kancelar = kancelar;
        this.doktorand = doktorand;
        this.uvazek = uvazek;
    }
    
    public String getJmeno(){
        return jmeno;
    }
    
    @Override
    public String toString(){
        return this.titulPred + "" +
                this.jmeno + " " +
                this.prijmeni + " " +
                this.titulZa + "\nPracovní telefon: " + 
                this.pracTel + "     Pracovní email: " + 
                this.pracEmail + 
                (!this.soukTel.isEmpty() ? ("\nSoukromý telefon: " + this.soukTel) : "") + 
                (!this.soukEmail.isEmpty() ? ("     Soukromý email: " + this.soukEmail) : "") +
                "\nKancelář: " + 
                this.kancelar + "     Doktorand: " +
                (this.doktorand ? "Ano" : "Ne") + 
                "     Úvazek: " + 
                this.uvazek + "";
    }
}
