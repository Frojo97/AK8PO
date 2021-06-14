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

public class SouborPredmet {
    private final String nazevXML = "SouborPredmet.xml";
    
    private SouborPredmet(){} //Konstruktor
    
    private static SouborPredmet SP = null;
    
    public static SouborPredmet SP(){
        if(SP == null)
            SP = new SouborPredmet();
        return SP;
    }
    
    private String getNazevXML(){ //Vrátí název souboru
        return nazevXML;
    }
    
   /* public void ulozeniPredmetu(ObservableList<Predmet> seznamPredmetu){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element hlavniElement = document.createElement("predmety");
            document.appendChild(hlavniElement);
            
            for(int i = 0; i < seznamPredmet.size(); i++){
                Element e_predmet = document.createElement("predmet");
                hlavniElement.appendChild(e_predmet);
                
                Element e_id = document.createElement("id");
                e_id.appendChild(document.createTextNode(String.valueOf(seznamPredmet.get(i).getID())));
                e_predmet.appendChild(e_id);
                
                Element e_nazevPredmetu = document.createElement("nazevPredmetu");
                e_nazevPredmetu.appendChild(document.createTextNode(seznamPredmetu.get(i).getNazevPredmetu()));
                e_predmet.appendChild(e_nazevPredmetu);
                
                Element e_zkratkaPredmetu = document.createElement("zkratkaPredmetu");
                e_zkratkaPredmetu.appendChild(document.createTextNode(seznamPredmetu.get(i).getZkratkaSkupinky()));
                e_predmet.appendChild(e_zkratkaPredmetu);
                
                Element e_pocetKreditu = document.createElement("pocetKreditu");
                e_pocetKreditu.appendChild(document.createTextNode(seznamPredmetu.get(i).getPocetKreditu()));
                e_predmet.appendChild(e_pocetKreditu);
                
                Element e_pocetTydnu = document.createElement("pocetTydnu");
                e_pocetTydnu.appendChild(document.createTextNode(seznamPredmetu.get(i).getPocetTydnu()));
                e_predmet.appendChild(e_pocetTydnu);
                
                Element e_pocetPrednasek = document.createElement("pocetPrednasek");
                e_pocetPrednasek.appendChild(document.createTextNode(seznamPredmetu.get(i).getPocetStudentu()));
                e_predmet.appendChild(e_pocetPrednasek);
                
                Element e_pocetSeminaru = document.createElement("pocetSeminaru");
                e_pocetSeminaru.appendChild(document.createTextNode(seznamPredmetu.get(i).getPocetSeminaru()));
                e_predmet.appendChild(e_pocetSeminaru);
                
                Element e_zakonceni = document.createElement("zakonceni");
                e_zakonceni.appendChild(document.createTextNode(seznamPredmetu.get(i).getZakonceni()));
                e_predmet.appendChild(e_zakonceni);
                
                Element e_jazyk = document.createElement("jazyk");
                e_jazyk.appendChild(document.createTextNode(seznamPredmetu.get(i).getJazyk()));
                e_predmet.appendChild(e_jazyk);
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
 
    public SeznamPredmetu nacteniPredmetu(){
        SeznamPredmetu seznamPredmetu = new SeznamPredmetu();
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            if(!new File(getNazevXML()).exists())
                return new SeznamSkupinek();
            Document document = documentBuilder.parse(new File(getNazevXML()));
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            NodeList nList = document.getElementsByTagName("skupina");           
            for (int i = 0; i < nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    seznamPredmetu.pridatDoSeznamu(new Predmet( Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()),
                            element.getElementsByTagName("nazevPredmetu").item(0).getTextContent(), 
                            element.getElementsByTagName("zkratkaPredmetu").item(0).getTextContent(),
                            Integer.parseInt(element.getElementsByTagName("pocetKreditu").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("pocetTydnu").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("pocetPrednasek").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("pocetSeminaru").item(0).getTextContent()),
                            EnumZakonceni.valueOf(element.getElementsByTagName("zakonceni").item(0).getTextContent()),
                            EnumJazyk.valueOf(element.getElementsByTagName("jazyk").item(0).getTextContent())   
                    ));
                }
            }
            return seznamPredmetu;
        }catch (SAXException | IOException |ParserConfigurationException ex){
           ex.printStackTrace(); 
        }
        return null;   
    }*/
}
