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

public class SouborZamestnanec {
    private final String nazevXML = "SouborZamestnanec.xml";
    
    private SouborZamestnanec(){} //Konstruktor
    
    private static SouborZamestnanec SZ = null;
    
    public static SouborZamestnanec SZ(){
        if(SZ == null)
            SZ = new SouborZamestnanec();
        return SZ;
    }
    
    private String getNazevXML(){ //Vrátí název souboru
        return nazevXML;
    }
    
    public void ulozeniZamestnancu(ObservableList<Zamestnanec> seznamZamestnancu){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element hlavniElement = document.createElement("zamestnanci");
            document.appendChild(hlavniElement);
            
            for(int i = 0; i < seznamZamestnancu.size(); i++){
                Element e_zamestnanec = document.createElement("zamestnanec");
                hlavniElement.appendChild(e_zamestnanec);
                
                Element e_id = document.createElement("id");
                e_id.appendChild(document.createTextNode(String.valueOf(seznamZamestnancu.get(i).getID())));
                e_zamestnanec.appendChild(e_id);
                
                Element e_titulPred = document.createElement("titulPred");
                e_titulPred.appendChild(document.createTextNode(seznamZamestnancu.get(i).getTitulPred()));
                e_zamestnanec.appendChild(e_titulPred);
                
                Element e_jmeno = document.createElement("jmeno");
                e_jmeno.appendChild(document.createTextNode(seznamZamestnancu.get(i).getJmeno()));
                e_zamestnanec.appendChild(e_jmeno);
                
                Element e_prijmeni = document.createElement("prijmeni");
                e_prijmeni.appendChild(document.createTextNode(seznamZamestnancu.get(i).getPrijmeni()));
                e_zamestnanec.appendChild(e_prijmeni);
                
                Element e_titulZa = document.createElement("titulZa");
                e_titulZa.appendChild(document.createTextNode(seznamZamestnancu.get(i).getTitulZa()));
                e_zamestnanec.appendChild(e_titulZa);
                
                Element e_pracTel = document.createElement("pracTel");
                e_pracTel.appendChild(document.createTextNode(seznamZamestnancu.get(i).getPracTel()));
                e_zamestnanec.appendChild(e_pracTel);
                
                Element e_soukTel = document.createElement("soukTel");
                e_soukTel.appendChild(document.createTextNode(seznamZamestnancu.get(i).getSoukTel()));
                e_zamestnanec.appendChild(e_soukTel);
                
                Element e_pracEmail = document.createElement("pracEmail");
                e_pracEmail.appendChild(document.createTextNode(seznamZamestnancu.get(i).getPracEmail()));
                e_zamestnanec.appendChild(e_pracEmail);
                
                Element e_soukEmail = document.createElement("soukEmail");
                e_soukEmail.appendChild(document.createTextNode(seznamZamestnancu.get(i).getSoukEmail()));
                e_zamestnanec.appendChild(e_soukEmail);

		Element e_kancelar = document.createElement("kancelar");
                e_kancelar.appendChild(document.createTextNode(seznamZamestnancu.get(i).getKancelar()));
                e_zamestnanec.appendChild(e_kancelar);
                
                Element e_doktorand = document.createElement("doktorand");
                e_doktorand.appendChild(document.createTextNode(Boolean.toString(seznamZamestnancu.get(i).getDoktorand())));
                e_zamestnanec.appendChild(e_doktorand);
                
                Element e_uvazek = document.createElement("uvazek");
                e_uvazek.appendChild(document.createTextNode(seznamZamestnancu.get(i).getUvazek().toString()));
                e_zamestnanec.appendChild(e_uvazek);
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
 
    public SeznamZamestnancu nacteniZamestnancu(){
        SeznamZamestnancu seznamZamestnancu = new SeznamZamestnancu();
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            if(!new File(getNazevXML()).exists())
                return new SeznamZamestnancu();
            Document document = documentBuilder.parse(new File(getNazevXML()));
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            NodeList nList = document.getElementsByTagName("zamestnanec");           
            for (int i = 0; i < nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    seznamZamestnancu.pridatDoSeznamu(new Zamestnanec( Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()),
                            element.getElementsByTagName("titulPred").item(0).getTextContent(), 
                            element.getElementsByTagName("jmeno").item(0).getTextContent(),
                            element.getElementsByTagName("prijmeni").item(0).getTextContent(),
                            element.getElementsByTagName("titulZa").item(0).getTextContent(),
                            element.getElementsByTagName("pracTel").item(0).getTextContent(),
                            element.getElementsByTagName("soukTel").item(0).getTextContent(),
                            element.getElementsByTagName("pracEmail").item(0).getTextContent(),
                            element.getElementsByTagName("soukEmail").item(0).getTextContent(),
                            element.getElementsByTagName("kancelar").item(0).getTextContent(),
                            Boolean.parseBoolean(element.getElementsByTagName("doktorand").item(0).getTextContent()),
                            EnumUvazek.valueOf(element.getElementsByTagName("uvazek").item(0).getTextContent())   
                    ));
                }
            }
            return seznamZamestnancu;
        }catch (SAXException | IOException |ParserConfigurationException ex){
           ex.printStackTrace(); 
        }
        return null;   
    }
}
