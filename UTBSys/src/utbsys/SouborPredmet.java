package utbsys;

import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
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
    
    public void ulozeniPredmetu(ObservableList<Predmet> seznamPredmetu){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element hlavniElement = document.createElement("predmety");
            document.appendChild(hlavniElement);
            
            for(int i = 0; i < seznamPredmetu.size(); i++){
                Element e_predmet = document.createElement("predmet");
                hlavniElement.appendChild(e_predmet);
                
                Element e_nazevPredmetu = document.createElement("nazevPredmetu");
                e_nazevPredmetu.appendChild(document.createTextNode(seznamPredmetu.get(i).getNazevPredmetu()));
                e_predmet.appendChild(e_nazevPredmetu);
                
                Element e_zkratkaPredmetu = document.createElement("zkratkaPredmetu");
                e_zkratkaPredmetu.appendChild(document.createTextNode(seznamPredmetu.get(i).getZkratkaPredmetu()));
                e_predmet.appendChild(e_zkratkaPredmetu);
                
                Element e_pocetKreditu = document.createElement("pocetKreditu");
                e_pocetKreditu.appendChild(document.createTextNode(String.valueOf(seznamPredmetu.get(i).getPocetKreditu())));
                e_predmet.appendChild(e_pocetKreditu);
                
                Element e_pocetTydnu = document.createElement("pocetTydnu");
                e_pocetTydnu.appendChild(document.createTextNode(String.valueOf(seznamPredmetu.get(i).getPocetTydnu())));
                e_predmet.appendChild(e_pocetTydnu);
                
                Element e_hodinPrednasek = document.createElement("hodinPrednasek");
                e_hodinPrednasek.appendChild(document.createTextNode(String.valueOf(seznamPredmetu.get(i).getHodinPrednasek())));
                e_predmet.appendChild(e_hodinPrednasek);
                
                Element e_hodinCviceni = document.createElement("hodinCviceni");
                e_hodinCviceni.appendChild(document.createTextNode(String.valueOf(seznamPredmetu.get(i).getHodinPrednasek())));
                e_predmet.appendChild(e_hodinCviceni);
                
                Element e_hodinSeminaru = document.createElement("hodinSeminaru");
                e_hodinSeminaru.appendChild(document.createTextNode(String.valueOf(seznamPredmetu.get(i).getHodinSeminaru())));
                e_predmet.appendChild(e_hodinSeminaru);
                
                Element e_zakonceni = document.createElement("zakonceni");
                e_zakonceni.appendChild(document.createTextNode(seznamPredmetu.get(i).getZakonceni().toString()));
                e_predmet.appendChild(e_zakonceni);
                
                Element e_jazyk = document.createElement("jazyk");
                e_jazyk.appendChild(document.createTextNode(seznamPredmetu.get(i).getJazyk().toString()));
                e_predmet.appendChild(e_jazyk);
                
                Element e_velikostTridy = document.createElement("velikostTridy");
                e_velikostTridy.appendChild(document.createTextNode(String.valueOf(seznamPredmetu.get(i).getVelikostTridy())));
                e_predmet.appendChild(e_velikostTridy);  
                
                ObservableList<Skupinka> skup = seznamPredmetu.get(i).getSkupinkaOL();
                for(int j = 0; j < skup.size(); j++){
                    Element Skupinky = document.createElement("skupinky");
                    e_predmet.appendChild(Skupinky);    
                    
                    Element Skupinka = document.createElement("skupinka");
                    Skupinka.appendChild(document.createTextNode(Integer.toString(skup.get(j).getID())));
                    Skupinky.appendChild(Skupinka);                
                }  
                
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
 
    public SeznamPredmetu nacteniPredmetu(SeznamSkupinek skup){
        SeznamPredmetu seznamPredmetu = new SeznamPredmetu();
        SeznamSkupinek SK = skup;
        ObservableList<Integer> IDpredmetu = FXCollections.observableArrayList();
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            if(!new File(getNazevXML()).exists())
                return new SeznamPredmetu();
            Document document = documentBuilder.parse(new File(getNazevXML()));
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            NodeList nList = document.getElementsByTagName("predmet");           
            for (int i = 0; i < nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    //Nacte reference na skupinky
                    NodeList n2 = element.getChildNodes();
                    for (int j = 0; j < n2.getLength(); j++){
                        if (n2.item(j).getNodeType() == Node.ELEMENT_NODE){
                            Element e2 = (Element) n2.item(j);
                            if(e2.getNodeName().contains("skupinky")){
                                IDpredmetu.add(Integer.parseInt(e2.getElementsByTagName("skupinka").item(0).getTextContent()));
                            }
                        }
                    }
                    
                    seznamPredmetu.pridatDoSeznamu(new Predmet( element.getElementsByTagName("nazevPredmetu").item(0).getTextContent(), 
                            element.getElementsByTagName("zkratkaPredmetu").item(0).getTextContent(),
                            Integer.parseInt(element.getElementsByTagName("pocetKreditu").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("pocetTydnu").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("hodinPrednasek").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("hodinCviceni").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("hodinSeminaru").item(0).getTextContent()),
                            EnumZakonceni.valueOf(element.getElementsByTagName("zakonceni").item(0).getTextContent()),
                            EnumJazyk.valueOf(element.getElementsByTagName("jazyk").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("velikostTridy").item(0).getTextContent()),
                            prehodDoSkupinky(SK, IDpredmetu)
                    ));
                }
            }
            return seznamPredmetu;
        }catch (SAXException | IOException |ParserConfigurationException ex){
           ex.printStackTrace(); 
        }
        return null;   
    }
    
    private SeznamSkupinek prehodDoSkupinky(SeznamSkupinek SK, ObservableList<Integer> IDpredmetu){
        SeznamSkupinek vratSK = new SeznamSkupinek();
        for (int i = 0; i < IDpredmetu.size(); i++){
            for (int j = 0; j < SK.getOBSeznam().size(); j++){
                if (IDpredmetu.get(i) == SK.getOBSeznam().get(j).getID())
                    vratSK.pridatDoSeznamu(SK.getSkupinka(IDpredmetu.get(i)));
            }
        }
        return vratSK;
    }
}
