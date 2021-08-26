package eapli.base.utils.filesearch;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class FileSearchXML {
//. A título de exemplo, para este ficheiro, a especificação de uma XPath semelhante a esta: /products/product[id="XYJ789"]
// permitirá facilmente obter o elemento <product> cujo <id> tem o valor "XYJ789".
//2.4. Podem/devem pensar em maneiras fáceis de um utilizador dar-vos a informação necessária a
// construirem uma XPath. Notem que não faz sentido suportarem todas as possibilidades advindas
// do XPath. É suficiente conseguirem encontrar 1 elemento com base num identificador
// (e.g. código do produto) que pode estar especificado como atributo ou como sub-elemento do elemento a obter.

    /*
    Realização (Automática): Consiste em: (1) consultar um ficheiro XML de produtos e com base no código do produto
obter o preço de venda unitário e a categoria do produto;
 (2) calcular o valor total (preço unitário X quantidade); (3)
com base no valor total calculado determinar a percentagem base de desconto a aplicar (e.g. se valor > Z o desconto
é 3% caso contrário é 1%); (4) para produtos com a categoria "ABC" atualizar a percentagem base de desconto em
mais 0.5%; (5) calcular o valor de desconto e o valor total após descontos; (5) enviar um email ao colaborador que
efetuou o pedido cujo texto pré-definido varia consoante o tipo de cliente e onde se apresentam alguns dos valores
calculados.
     */

    //Para o exemplo Codigo do Produto : trazer o preço e o nome
final static String ficheiroExemplo = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n"+
            "<products>\n" +
        "<product>\n" +
        "<id>XYJ123</id>\n" +
        "<name>Product One</name>\n" +
        "<description>This is the description for product one.</description>\n" +
        "<price>19.99</price>\n" +
        "</product>\n" +
        "<product>\n" +
        "<id>XYJ234</id>\n" +
        "<name>Product Two</name>\n" +
        "<description>This is the description for product two.</description>\n" +
        "<price>19.99</price>\n" +
        "</product>\n" +
        "<product>\n" +
        "<id>XYJ456</id>\n" +
        "<name>Product Three</name>\n" +
        "<description>This is the description for product three.</description>\n" +
        "<price>19.99</price>\n" +
        "</product>\n" +
        "<product>\n" +
        "<id>XYJ789</id>\n" +
        "<name>Product Four</name>\n" +
        "<description>This is the description for product four.</description>\n" +
        "<price>19.99</price>\n" +
        "</product>\n" +
        "<product>\n" +
        "<id>XYH123</id>\n" +
        "<name>Product Five</name>\n" +
        "<description>This is the description for product five.</description>\n" +
        "<price>19.99</price>\n" +
        "</product>\n" +
        "</products>";
final static String  ficheiroXMLexemplo2 = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
        "<inventory>\n" +
        "    <!--Test is test comment-->\n" +
        "        <book year=\"2000\">\n" +
        "        <title>Snow Crash</title>\n" +
        "        <author>Neal Stephenson</author>\n" +
        "        <publisher>Spectra</publisher>\n" +
        "        <isbn>0553380958</isbn>\n" +
        "        <price>14.95</price>\n" +
        "    </book>\n" +
        "    <book year=\"2005\">\n" +
        "        <title>Burning Tower</title>\n" +
        "        <author>Larry Niven</author>\n" +
        "        <author>Jerry Pournelle</author>\n" +
        "        <publisher>Pocket</publisher>\n" +
        "        <isbn>0743416910</isbn>\n" +
        "        <price>5.99</price>\n" +
        "    </book>\n" +
        "    <book year=\"1995\">\n" +
        "        <title>Zodiac</title>\n" +
        "        <author>Neal Stephenson</author>\n" +
        "        <publisher>Spectra</publisher>\n" +
        "        <isbn>0553573862</isbn>\n" +
        "        <price>7.50</price>\n" +
        "    </book>\n" +
        "</inventory>";
    //final static byte ficheeiroXML[] = ficheiroXMLexemplo2.getBytes();
    final static byte ficheiroXML[] = ficheiroExemplo.getBytes();
    //Build DOM

    public static Map<String, String> findInXML(byte[] file,String rootTag ,String tagToSearch, String tagToSearchValue, List<String> listTagsToBeReturned) {
        Map<String, String> tagsMap = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        //Ficheiro
        //Document doc = builder.parse("/Users/ruimarinho/OneDrive - Instituto Superior de Engenharia do Porto/ISEP_2020_2021/LAPR4/REPO/lei20_21_s4_2na_2/eapli.base (20210331)/eapli.base/base.core/src/main/java/eapli/base/utils/filesearch/inventory.xml");
        //STRING
        //Document doc = builder.parse(new InputSource(new StringReader(ficheiroXMLexemplo2)));
        //ByteArray
        Document doc = null;
        try {
            doc = builder.parse(new ByteArrayInputStream(file));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create XPath

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();


        // 11) Get book titles written by Neal Stephenson
        //"//product[id='XYJ123']//*"
        String xpathString= "//" + rootTag + "[" + tagToSearch + "=" + "'" + tagToSearchValue +"'" +  "]//*";
        System.out.println("xpath: " + xpathString );
        XPathExpression expr = null;
        try {
            expr = xpath.compile(xpathString);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Object result = null;
        try {
            result = expr.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        NodeList nodes  = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            String tagname = nodes.item(i).getNodeName();
            String tagvalue = nodes.item(i).getTextContent();
            if(listTagsToBeReturned.contains(tagname)){
                tagsMap.put(tagname,tagvalue);
            }
        }

        return tagsMap;

    }

    public static Map<String, String> findInXML(String file,String rootTag ,String tagToSearch, String tagToSearchValue, List<String> listTagsToBeReturned) {
        Map<String, String> tagsMap = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        //Ficheiro
        //Document doc = builder.parse("/Users/ruimarinho/OneDrive - Instituto Superior de Engenharia do Porto/ISEP_2020_2021/LAPR4/REPO/lei20_21_s4_2na_2/eapli.base (20210331)/eapli.base/base.core/src/main/java/eapli/base/utils/filesearch/inventory.xml");
        //STRING
        //Document doc =
        //ByteArray
        Document doc = null;
        try {
            doc = builder.parse(new InputSource(new StringReader(file)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create XPath

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();


        // 11) Get book titles written by Neal Stephenson
        //"//product[id='XYJ123']//*"
        String xpathString= "//" + rootTag + "[" + tagToSearch + "=" + "'" + tagToSearchValue +"'" +  "]//*";
        System.out.println("xpath: " + xpathString );
        XPathExpression expr = null;
        try {
            expr = xpath.compile(xpathString);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Object result = null;
        try {
            result = expr.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        NodeList nodes  = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            String tagname = nodes.item(i).getNodeName();
            String tagvalue = nodes.item(i).getTextContent();
            if(listTagsToBeReturned.contains(tagname)){
                tagsMap.put(tagname,tagvalue);
            }
        }
        System.out.println(tagsMap);
        return tagsMap;

    }
/*
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        //Build DOM
        List<String> tagsToBeReturned = new ArrayList<>();
        tagsToBeReturned.add("name");
        tagsToBeReturned.add("price");
         //Map<String, String>  returnedMap =   findInXML(ficheiroXML,"product", "id", "XYJ123",  tagsToBeReturned );
            Map<String, String>  returnedMap =   findInXML(ficheiroExemplo,"product", "id", "XYJ123",  tagsToBeReturned );
        System.out.println(returnedMap);
    }
*/

}
