import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;



public class Main {
    public static void main(String[] args) {
        List<Employee> list = parseXML();

        String json = listToJson(list);

        writeString(json, "data2.json");



    }

    private static List<Employee> parseXML() {

        List<Employee> employees =
                new ArrayList<>();

        try {

            DocumentBuilder builder = DocumentBuilderFactory
                            .newInstance()
                            .newDocumentBuilder();

            Document document = builder.parse(new File("data.xml"));

            Node root = document.getDocumentElement();

            NodeList nodeList = root.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                Element employeeElement = (Element) node;

                long id = Long.parseLong(employeeElement
                                .getElementsByTagName("id")
                                .item(0)
                                .getTextContent());

                String firstName = employeeElement
                                .getElementsByTagName("firstName")
                                .item(0)
                                .getTextContent();

                String lastName = employeeElement
                                .getElementsByTagName("lastName")
                                .item(0)
                                .getTextContent();

                String country = employeeElement
                                .getElementsByTagName("country")
                                .item(0)
                                .getTextContent();

                int age = Integer.parseInt(employeeElement
                                .getElementsByTagName("age")
                                .item(0)
                                .getTextContent());

                employees.add(new Employee(
                                id,
                                firstName,
                                lastName,
                                country,
                                age
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static String listToJson(List<Employee> list) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        Type listType = new TypeToken<List<Employee>>() {}.getType();
        return gson.toJson(list, listType);
    }

    private static void writeString(
            String json,
            String fileName) {

        try (FileWriter writer =
                     new FileWriter(fileName)) {

            writer.write(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
