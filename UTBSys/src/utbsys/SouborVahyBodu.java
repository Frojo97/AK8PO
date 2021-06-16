package utbsys;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SouborVahyBodu {
    private final String nazevXML = "SouborVahyBodu.xml";
    
    private SouborVahyBodu(){} //Konstruktor
    
    private static SouborVahyBodu SVB = null;
    
    public static SouborVahyBodu SVB(){
        if(SVB == null)
            SVB = new SouborVahyBodu();
        return SVB;
    }
    
    private String getNazevXML(){ //Vrátí název souboru
        return nazevXML;
    }
    
    public VahyPracBodu nacteniVahyBodu(){
        VahyPracBodu vahyPracBodu = null;
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            if(!new File(getNazevXML()).exists())
                return null;
            Document document = documentBuilder.parse(new File(getNazevXML()));
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            NodeList nList = document.getElementsByTagName("vahy");           
            for (int i = 0; i < nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    vahyPracBodu = new VahyPracBodu(
                            Double.valueOf(element.getElementsByTagName("hodPred").item(0).getTextContent()),
                            Double.valueOf(element.getElementsByTagName("hodCvic").item(0).getTextContent()), 
                            Double.valueOf(element.getElementsByTagName("hodSem").item(0).getTextContent()),
                            Double.valueOf(element.getElementsByTagName("hodPredAJ").item(0).getTextContent()),
                            Double.valueOf(element.getElementsByTagName("hodCvicAJ").item(0).getTextContent()),
                            Double.valueOf(element.getElementsByTagName("hodSemAJ").item(0).getTextContent()),
                            Double.valueOf(element.getElementsByTagName("jedZap").item(0).getTextContent()),
                            Double.valueOf(element.getElementsByTagName("jedKlasZap").item(0).getTextContent()), 
                            Double.valueOf(element.getElementsByTagName("jedZkous").item(0).getTextContent()),
                            Double.valueOf(element.getElementsByTagName("jedZapAJ").item(0).getTextContent()),
                            Double.valueOf(element.getElementsByTagName("jedKlasZapAJ").item(0).getTextContent()),
                            Double.valueOf(element.getElementsByTagName("jedZkousAJ").item(0).getTextContent())
                    );
                }
            }
            return vahyPracBodu;
        }catch (SAXException | IOException |ParserConfigurationException ex){
           ex.printStackTrace(); 
        }
        return null;   
    }
}
