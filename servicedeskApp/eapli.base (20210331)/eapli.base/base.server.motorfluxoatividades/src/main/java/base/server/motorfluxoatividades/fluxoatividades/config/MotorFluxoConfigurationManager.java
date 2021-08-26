package base.server.motorfluxoatividades.fluxoatividades.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MotorFluxoConfigurationManager {

    private static MotorFluxoConfigurationManager myMotorFluxoConfigurationManager;
    private static MotorFluxoConfiguration myCurrentMotorFluxoConfiguration;

    private MotorFluxoConfigurationManager() {
    }

    public static MotorFluxoConfigurationManager getInstance() {
        if (myMotorFluxoConfigurationManager == null)
            myMotorFluxoConfigurationManager = new MotorFluxoConfigurationManager();
        return myMotorFluxoConfigurationManager;
    }

    /**
     * Used to load a configuration file by the path provided
     */
    public void loadConfigurationFile(String filePath) {
        //creating a constructor of file class and parsing an XML file
        File file = new File(filePath);
        //an instance of factory that gives a document builder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //an instance of builder to parse the specified xml file
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("motor_fluxo");
            // nodeList is not iterable, so we are using for loop
            myCurrentMotorFluxoConfiguration = new MotorFluxoConfiguration();
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String tipo =  eElement.getElementsByTagName("tipo").item(0).getTextContent();
                    String algoritmoColaborador =  eElement.getElementsByTagName("algoritmo_colaborador").item(0).getTextContent();
                    String algoritmoExecutor =  eElement.getElementsByTagName("algoritmo_executor").item(0).getTextContent();
                    myCurrentMotorFluxoConfiguration.defineTipo(tipo);
                    myCurrentMotorFluxoConfiguration.defineAlgoritmoColaborador(algoritmoColaborador);
                    myCurrentMotorFluxoConfiguration.defineAlgoritmoExecutor(algoritmoExecutor);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Current loaded Configuration
     */
    public MotorFluxoConfiguration getCurrentConfiguration() {
        return myCurrentMotorFluxoConfiguration;
    }
}
