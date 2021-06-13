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

public class SouborSkupinka {
    private final String nazevXML = "SouborSkupinka.xml";
    
    private SouborSkupinka(){} //Konstruktor
    
    private static SouborSkupinka SK = null;
    
    public static SouborSkupinka SK(){
        if(SK == null)
            SK = new SouborSkupinka();
        return SK;
    }
    
    private String getNazevXML(){ //Vrátí název souboru
        return nazevXML;
    }
    
    public void ulozeniSkupinky(ObservableList<Skupinka> seznamSkupinek){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element hlavniElement = document.createElement("skupiny");
            document.appendChild(hlavniElement);
            
            for(int i = 0; i < seznamSkupinek.size(); i++){
                Element e_skupina = document.createElement("skupina");
                hlavniElement.appendChild(e_skupina);
                
                Element e_id = document.createElement("id");
                e_id.appendChild(document.createTextNode(String.valueOf(seznamSkupinek.get(i).getID())));
                e_skupina.appendChild(e_id);
                
                Element e_nazevSkupiny = document.createElement("nazevSkupiny");
                e_nazevSkupiny.appendChild(document.createTextNode(seznamSkupinek.get(i).getNazevSkupinky()));
                e_skupina.appendChild(e_nazevSkupiny);
                
                Element e_zkratkaSkupiny = document.createElement("zkratkaSkupiny");
                e_zkratkaSkupiny.appendChild(document.createTextNode(seznamSkupinek.get(i).getZkratkaSkupinky()));
                e_skupina.appendChild(e_zkratkaSkupiny);
                
                Element e_rocnik = document.createElement("rocnik");
                e_rocnik.appendChild(document.createTextNode(seznamSkupinek.get(i).getRocnik()));
                e_skupina.appendChild(e_rocnik);
                
                Element e_semestr = document.createElement("semestr");
                e_semestr.appendChild(document.createTextNode(seznamSkupinek.get(i).getSemestr()));
                e_skupina.appendChild(e_semestr);
                
                Element e_pocetStudentu = document.createElement("pocetStudentu");
                e_pocetStudentu.appendChild(document.createTextNode(seznamSkupinek.get(i).getPocetStudentu()));
                e_skupina.appendChild(e_pocetStudentu);
                
                Element e_formaStudia = document.createElement("formaStudia");
                e_formaStudia.appendChild(document.createTextNode(seznamSkupinek.get(i).getFormaStudia()));
                e_skupina.appendChild(e_formaStudia);
                
                Element e_typStudia = document.createElement("typStudia");
                e_typStudia.appendChild(document.createTextNode(seznamSkupinek.get(i).getTypStudia()));
                e_skupina.appendChild(e_typStudia);
                
                Element e_jazyk = document.createElement("jazyk");
                e_jazyk.appendChild(document.createTextNode(seznamSkupinek.get(i).getJazyk()));
                e_skupina.appendChild(e_jazyk);
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
 
    public SeznamSkupinek nacteniSkupinky(){
        SeznamSkupinek seznamSkupinek = new SeznamSkupinek();
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
                    seznamSkupinek.pridatDoSeznamu(new Skupinka( Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()),
                            element.getElementsByTagName("nazevSkupiny").item(0).getTextContent(), 
                            element.getElementsByTagName("zkratkaSkupiny").item(0).getTextContent(),
                            Integer.parseInt(element.getElementsByTagName("rocnik").item(0).getTextContent()),
                            EnumSemestr.valueOf(element.getElementsByTagName("semestr").item(0).getTextContent()),
                            Integer.parseInt(element.getElementsByTagName("pocetStudentu").item(0).getTextContent()),
                            EnumFormaStudia.valueOf(element.getElementsByTagName("formaStudia").item(0).getTextContent()),
                            EnumTypStudia.valueOf(element.getElementsByTagName("typStudia").item(0).getTextContent()),
                            EnumJazyk.valueOf(element.getElementsByTagName("jazyk").item(0).getTextContent())   
                    ));
                }
            }
            return seznamSkupinek;
        }catch (SAXException | IOException |ParserConfigurationException ex){
           ex.printStackTrace(); 
        }
        return null;   
    }
}
