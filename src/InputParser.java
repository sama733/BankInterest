import exceptionHandling.DepositBalanceNotValidException;
import exceptionHandling.DurationInDaysNotValidException;
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
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class InputParser {


    public static void main(String[] args) {
        run();
    }

    public static void run() {

        ArrayList<Deposit> listOfDeposits = new ArrayList<Deposit>();
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
                Deposit deposit = null;
                try {
                    Node node = list.item(i);

                    Element element = null;
                    deposit = new Deposit();
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        element = (Element) node;
                        deposit.setCustomerNumber(Integer.parseInt(element.getElementsByTagName("customerNumber").item(0).getTextContent()));
                        deposit.setDepositBalance(BigDecimal.valueOf(Long.parseLong(element.getElementsByTagName("depositBalance").item(0).getTextContent())));
                        if (deposit.getDepositBalance().compareTo(BigDecimal.ZERO) <= 0) {
                            throw new DepositBalanceNotValidException("موجودی صحیح نیست");
                        }
                        deposit.setDurationInDays(Integer.valueOf(element.getElementsByTagName("durationInDays").item(0).getTextContent()));
                        if (deposit.getDurationInDays() <= 0) {
                            throw new DurationInDaysNotValidException("بازه زمانی اشتباه است");
                        }
                    }


                    //reflection code -> khandane depositType va sakhtane kelase morede nazar
                    String depositTypeStr = element.getElementsByTagName("depositType").item(0).getTextContent();
                    Class typeOfDeposit = Class.forName(depositTypeStr);
                    DepositType depositType = (DepositType) typeOfDeposit.newInstance();
                    deposit.setDepositType(depositType);

                    //  mohasebe sode seporde
                    BigDecimal Days = new BigDecimal("36500");
                    BigDecimal interestOfDeposit = (deposit.getDepositBalance().multiply(new BigDecimal(deposit.getDurationInDays())))
                            .multiply(new BigDecimal(depositType.getInterestRate()))
                            .divide(Days, 3, BigDecimal.ROUND_DOWN);
                    deposit.setInterest(interestOfDeposit);

                    //save values in objects
                    listOfDeposits.add(deposit);
                } catch (ClassNotFoundException e) {
                    continue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (DepositBalanceNotValidException e) {
                    continue;
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (DurationInDaysNotValidException e) {
                    continue;
                }


            }

            Collections.sort(listOfDeposits);
            RandomAccessFile outputFile = new RandomAccessFile("source/outPut.txt", "rw");
            for (Deposit depositSorted : listOfDeposits) {
                //System.out.println(depositSorted.getInterest());
                outputFile.writeBytes(depositSorted.getCustomerNumber() + "#" + depositSorted.getInterest() + "\n");
            }
            outputFile.close();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}





