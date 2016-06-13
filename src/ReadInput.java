import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadInput {

    public void run() {

        try {
            File xmlFile = new File(getClass().getResource("depo.xml").getFile());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(xmlFile);

            document.getDocumentElement().normalize();

            NodeList list = document.getElementsByTagName("deposit");

            for (int i = 0; i < list.getLength(); i++) {
                Node n = list.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    System.out.println("-----");

                    System.out.println("customerNumber \t\t:" + element.getAttribute("customerNumber"));
                    System.out.println("depositType \t\t: " + element.getElementsByTagName("depositType").item(0).getTextContent());
                    System.out.println("depositBalance\t: " + element.getElementsByTagName("depositBalance").item(0).getTextContent());
                    System.out.println("durationInDays\t: " + element.getElementsByTagName("durationInDays").item(0).getTextContent());

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("nashod");
        }


}
    public static void main(String[] args) {
ReadInput readInput = new ReadInput();
        readInput.run();
    }
}