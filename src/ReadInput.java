import com.sun.org.apache.bcel.internal.classfile.InnerClasses;
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
import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class ReadInput {


    Deposit deposit = new Deposit();


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
            //get root node
            // System.out.println("[Root : " + document.getDocumentElement().getNodeName() + " ]");
            NodeList list = document.getElementsByTagName("deposit");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                Element element = null;
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    element = (Element) node;
                    deposit.setCustomerNumber(Integer.parseInt(element.getElementsByTagName("customerNumber").item(0).getTextContent()));
                    deposit.setDepositBalance(BigDecimal.valueOf(Long.parseLong(element.getElementsByTagName("depositBalance").item(0).getTextContent())));
                    deposit.setDurationInDays(Integer.valueOf(element.getElementsByTagName("durationInDays").item(0).getTextContent()));
                    // deposit.setDepositType(DepositType.class.cast(element.getElementsByTagName("depositType").item(0).getTextContent()));

                    //test values
                    /*BigDecimal dep = deposit.getDepositBalance();
                    System.out.println(dep);*/
                    System.out.println();
                }
                //reflection code
                String str = element.getElementsByTagName("depositType").item(0).getTextContent();
                //System.out.println(str);
                Class aClass = Class.forName("Deposit");
                Object aObject = aClass.newInstance();



            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}