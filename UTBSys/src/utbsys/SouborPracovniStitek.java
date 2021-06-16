package utbsys;

import java.io.File;
import java.io.IOException;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SouborPracovniStitek {
    private final String nazevXML = "SouborPracovniStitek.xml";
    
    private SouborPracovniStitek(){} //Konstruktor
    
    private static SouborPracovniStitek SPS = null;
    
    public static SouborPracovniStitek SPS(){
        if(SPS == null)
            SPS = new SouborPracovniStitek();
        return SPS;
    }
    
    private String getNazevXML(){ //Vrátí název souboru
        return nazevXML;
    }
    
    public void ulozeniPracovnihoStitku(ObservableList<PracovniStitek> seznamPracovnichStitku){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element hlavniElement = document.createElement("pracovniStitky");
            document.appendChild(hlavniElement);
            
            for(int i = 0; i <seznamPracovnichStitku.size(); i++){
                Element e_pracovniStitek = document.createElement("pracovniStitek");
                hlavniElement.appendChild(e_pracovniStitek);
                
                Element e_nazev = document.createElement("nazev");
                e_nazev.appendChild(document.createTextNode(seznamPracovnichStitku.get(i).getNazev()));
                e_pracovniStitek.appendChild(e_nazev);
                
                Element e_zamestnanecID = document.createElement("zamestnanecID");
                e_zamestnanecID.appendChild(document.createTextNode(String.valueOf(seznamPracovnichStitku.get(i).getZamestnanecID())));
                e_pracovniStitek.appendChild(e_zamestnanecID);
                
                Element e_predmetID = document.createElement("predmetID");
                e_predmetID.appendChild(document.createTextNode(String.valueOf(seznamPracovnichStitku.get(i).getPredmetID())));
                e_pracovniStitek.appendChild(e_predmetID);
                
                Element e_typStitku = document.createElement("typStitku");
                e_typStitku.appendChild(document.createTextNode(seznamPracovnichStitku.get(i).getTypStitku().toString()));
                e_pracovniStitek.appendChild(e_typStitku);
                
                Element e_pocetStudentu = document.createElement("pocetStudentu");
                e_pocetStudentu.appendChild(document.createTextNode(String.valueOf(seznamPracovnichStitku.get(i).getPocetStudentu())));
                e_pracovniStitek.appendChild(e_pocetStudentu);
                
                Element e_pocetHodin = document.createElement("pocetHodin");
                e_pocetHodin.appendChild(document.createTextNode(String.valueOf(seznamPracovnichStitku.get(i).getPocetHodin())));
                e_pracovniStitek.appendChild(e_pocetHodin);
                
                Element e_pocetTydnu = document.createElement("pocetTydnu");
                e_pocetTydnu.appendChild(document.createTextNode(String.valueOf(seznamPracovnichStitku.get(i).getPocetTydnu())));
                e_pracovniStitek.appendChild(e_pocetTydnu);
                
                Element e_jazyk = document.createElement("jazyk");
                e_jazyk.appendChild(document.createTextNode(seznamPracovnichStitku.get(i).getJazyk().toString()));
                e_pracovniStitek.appendChild(e_jazyk);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(getNazevXML()));
            transformer.transform(domSource, streamResult);
        }catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch (TransformerConfigurationException tce){
            tce.printStackTrace();
        }catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
 
    public SeznamPracovnichStitku nacteniPracovnichStitku(){
        SeznamPracovnichStitku seznamPracovnichStitku = new SeznamPracovnichStitku();
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            if(!new File(getNazevXML()).exists())
                return new SeznamPracovnichStitku();
            Document document = documentBuilder.parse(new File(getNazevXML()));
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            NodeList nList = document.getElementsByTagName("pracovniStitek");           
            for (int i = 0; i < nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    seznamPracovnichStitku.pridatDoSeznamu(new PracovniStitek(element.getElementsByTagName("nazev").item(0).getTextContent(), 
                            Integer.parseInt(element.getElementsByTagName("zamestnanecID").item(0).getTextContent()),
                            element.getElementsByTagName("predmetID").item(0).getTextContent(),
                            EnumTypStitku.valueOf(element.getElementsByTagName("typStitku").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("pocetStudentu").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("pocetHodin").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("pocetTydnu").item(0).getTextContent()),
                            EnumJazyk.valueOf(element.getElementsByTagName("jazyk").item(0).getTextContent())
                    ));
                }
            }
            return seznamPracovnichStitku;
        }catch (SAXException | IOException |ParserConfigurationException ex){
           ex.printStackTrace(); 
        }
        return null;   
    }
}
