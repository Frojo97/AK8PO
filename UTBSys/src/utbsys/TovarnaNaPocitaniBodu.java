package utbsys;

public class TovarnaNaPocitaniBodu {
    private static TovarnaNaPocitaniBodu TNPB = null;
    
    public static TovarnaNaPocitaniBodu TNPB(){
        if(TNPB == null)
            TNPB = new TovarnaNaPocitaniBodu();
        return TNPB;
    }
    
    public double vypocitej(PracovniStitek pracStitek, VahyPracBodu vahy){
        double pracBody;
        
        if (pracStitek.getTypStitku() == EnumTypStitku.přednáška){
            if (pracStitek.getJazyk() == EnumJazyk.CZ){
                pracBody = vahy.getHodPred() * pracStitek.getPocetHodin();
            }
            else{
                pracBody = vahy.getHodPredAJ() * pracStitek.getPocetHodin();
            }
            return pracBody;
        }
        
        if (pracStitek.getTypStitku() == EnumTypStitku.cvičení){
            if (pracStitek.getJazyk() == EnumJazyk.CZ){
                pracBody = vahy.getHodCvic() * pracStitek.getPocetHodin();
            }
            else{
                pracBody = vahy.getHodCvicAJ() * pracStitek.getPocetHodin();
            }
            return pracBody;
        }
        
        if (pracStitek.getTypStitku() == EnumTypStitku.seminář){
            if (pracStitek.getJazyk() == EnumJazyk.CZ){
                pracBody = vahy.getHodSem() * pracStitek.getPocetHodin();
            }
            else{
                pracBody = vahy.getHodSemAJ() * pracStitek.getPocetHodin();
            }
            return pracBody;
        }
        
        if (pracStitek.getTypStitku() == EnumTypStitku.zápočet){
            if (pracStitek.getJazyk() == EnumJazyk.CZ){
                pracBody = vahy.getJedZap() * pracStitek.getPocetHodin();
            }
            else{
                pracBody = vahy.getJedZapAJ() * pracStitek.getPocetHodin();
            }
            return pracBody;
        }
        
        if (pracStitek.getTypStitku() == EnumTypStitku.klasifikovaný_zápočet){
            if (pracStitek.getJazyk() == EnumJazyk.CZ){
                pracBody = vahy.getJedKlas()* pracStitek.getPocetHodin();
            }
            else{
                pracBody = vahy.getJedKlasAJ()* pracStitek.getPocetHodin();
            }
            return pracBody;
        }
        
        if (pracStitek.getTypStitku() == EnumTypStitku.zkouška){
            if (pracStitek.getJazyk() == EnumJazyk.CZ){
                pracBody = vahy.getJedZkous() * pracStitek.getPocetHodin();
            }
            else{
                pracBody = vahy.getJedZkous() * pracStitek.getPocetHodin();
            }
            return pracBody;
        }     
        return 0;
    }
}
