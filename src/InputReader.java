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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class InputReader implements Comparable {
    Deposit deposit = new Deposit();

    public static void main(String[] args) {
        InputReader readInput = new InputReader();
        readInput.run();
    }

    public void run() {

        try {
            //read xml file
            File xmlFile = new File("depositList.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            //iterate file
            NodeList list = document.getElementsByTagName("deposit");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                Element element = null;
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    element = (Element) node;
                    deposit.setCustomerNumber(Integer.parseInt(element.getElementsByTagName("customerNumber").item(0).getTextContent()));
                    deposit.setDepositBalance(BigDecimal.valueOf(Long.parseLong(element.getElementsByTagName("depositBalance").item(0).getTextContent())));
                    deposit.setDurationInDays(Integer.valueOf(element.getElementsByTagName("durationInDays").item(0).getTextContent()));
                }
                //save values in objects
                for(int j=0 ; j < i ; j++) {
                    ArrayList<Deposit> listOfDeposits = new ArrayList<>();
                    listOfDeposits.add(deposit);

                }
                //reflection code
                String depositTypeStr = element.getElementsByTagName("depositType").item(0).getTextContent();
                Class aClass = Class.forName(depositTypeStr);
                DepositType depositType = (DepositType) aClass.newInstance();
                deposit.setDepositType(depositType);

                // neveshtane code mohasebe sode seporde
                BigDecimal big = (deposit.getDepositBalance().multiply(new BigDecimal(deposit.getDurationInDays())));
                BigDecimal big2 = big.multiply(new BigDecimal(depositType.getInterestRate()));
                BigDecimal big3 = new BigDecimal("36500");
                BigDecimal big4 = big2.divide(big3, 3, BigDecimal.ROUND_DOWN);
                deposit.setInterest(big4);

                // print values (test)
                System.out.println(depositType.getClass().toString());
                System.out.println(deposit.getCustomerNumber());
                System.out.println(deposit.getDepositBalance());
                System.out.println(deposit.getDurationInDays());
                System.out.println(deposit.getInterest());
                System.out.println();

                //compare object


            }


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    @Override
    public int compareTo(Object o) {

        return 0;
    }
}

