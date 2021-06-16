package utbsys;

public class VahyPracBodu {
    private double hodPred;
    private double hodCvic;
    private double hodSem;
    private double hodPredAJ;
    private double hodCvicAJ;
    private double hodSemAJ;
    private double jedZap;
    private double jedKlasZap;
    private double jedZkous;
    private double jedZapAJ;
    private double jedKlasZapAJ;
    private double jedZkousAJ;
    
    public VahyPracBodu(double hodPred, double hodCvic, double hodSem, double hodPredAJ, double hodCvicAJ, double hodSemAJ, double jedZap, double jedKlasZap, double jedZkous, double jedZapAJ, double jedKlasZapAJ, double jedZkousAJ){
        this.hodPred = hodPred;
        this.hodCvic = hodCvic;
        this.hodSem = hodSem;
        this.hodPredAJ = hodPredAJ;
        this.hodCvicAJ = hodCvicAJ;
        this.hodSemAJ = hodSemAJ;
        this.jedZap = jedZap;
        this.jedKlasZap = jedKlasZap;
        this.jedZkous = jedZkous;
        this.jedZapAJ = jedZapAJ;
        this.jedKlasZapAJ = jedKlasZapAJ;
        this.jedZkousAJ = jedZkousAJ;
    }
    
    public double getHodPred(){
        return this.hodPred;
    }
    
    public double getHodCvic(){
        return this.hodCvic;
    }
    
    public double getHodSem(){
        return this.hodSem;
    }
    
    public double getHodPredAJ(){
        return this.hodPredAJ;
    }
    
    public double getHodCvicAJ(){
        return this.hodCvicAJ;
    }
    
    public double getHodSemAJ(){
        return this.hodSemAJ;
    }
    
    public double getJedZap(){
        return this.jedZap;
    }
    
    public double getJedKlas(){
        return this.jedKlasZap;
    }
    
    public double getJedZkous(){
        return this.jedZkous;
    }
    
    public double getJedZapAJ(){
        return this.jedZapAJ;
    }
    
    public double getJedKlasAJ(){
        return this.hodCvicAJ;
    }
    
    public double getJedZkousAJ(){
        return this.jedZkousAJ;
    }
}
