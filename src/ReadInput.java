import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadInput {

    public static void main(String[] args) {
        ReadInput readInput = new ReadInput();
        readInput.run();
    }

    //read xml file
    public void run() {

        try {

            File xmlFile = new File("C:\\Users\\DOTIN SCHOOL 4\\Desktop\\depo.xml");

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            System.out.println("[Root : " + document.getDocumentElement().getNodeName() + " ]");

            NodeList list = document.getElementsByTagName("deposit");


            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("................");
                    System.out.println("customer number:" + element.getElementsByTagName("customerNumber").item(0).getTextContent());
                    System.out.println("Deposit type:" + element.getElementsByTagName("depositType").item(0).getTextContent());
                    System.out.println("deposit balance:" + element.getElementsByTagName("depositBalance").item(0).getTextContent());
                    System.out.println("duration in day:" + element.getElementsByTagName("durationInDays").item(0).getTextContent());
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}