
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
public class Implementacion_modelo_DOM {
    public class DOM {
        static final String CLASS_NAME = DOM.class.getSimpleName();
        static final Logger LOG = Logger.getLogger(CLASS_NAME);

        public static void main(String argv[]) {

            if (argv.length != 1) {
                LOG.severe("-No se encuentra archivo XML- ");
                System.exit(1);}

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            try {
                DocumentBuilder db = dbf.newDocumentBuilder();
                //------------------------------------------//
                Document doc = db.parse(new File(argv[0]));
                //------------------------------------------//
                doc.getDocumentElement().normalize();
                //------------------------------------------//
                Element root = doc.getDocumentElement();
                //------------------------------------------//
                LOG.info("Root: " + root.getNodeName());
                //------------------------------------------//
                NodeList nList = root.getChildNodes();
                //------------------------------------------//
                LOG.info("Child nodes: " + nList.getLength());
                //------------------------------------------//
                visitChildNodes(nList);
                //------------------------------------------//
            } catch (ParserConfigurationException e) {

                LOG.severe(e.getMessage());

            } catch (IOException e) {

                LOG.severe(e.getMessage());

            } catch (SAXException e) {

                LOG.severe(e.getMessage());
            }
        }
        private static void visitChildNodes(NodeList nList) {
            int x1 = 0;
            int y1 = 0;
            int x2 = 0;
            int y2 = 0;
            int height = 0;
            int ancho = 0;
            double distancia = 0;
            final double pi = 3.14159;
            int r = 0;
            double area = 0, perimetro = 0;
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("Node Name = " + node.getNodeName()
                            + "; Value = " + node.getTextContent());
                    if (node.hasAttributes()) {
                        NamedNodeMap nodeMap = node.getAttributes();
                        for (int i = 0; i < nodeMap.getLength(); i++) {
                            Node tempNode = nodeMap.item(i);
                            System.out.println("Attr name : " + tempNode.getNodeName()
                                    + "; Value = " + tempNode.getNodeValue());
                            if (node.getNodeName() == "rect") {
                                if (tempNode.getNodeName() == "height")
                                    height = Integer.parseInt(tempNode.getNodeValue());
                                if (tempNode.getNodeName() == "width")
                                    ancho = Integer.parseInt(tempNode.getNodeValue());
                            } else if (node.getNodeName() == "circle") {
                                if (tempNode.getNodeName() == "r")
                                    r = Integer.parseInt(tempNode.getNodeValue());
                            } else if (node.getNodeName() == "line") {
                                if (tempNode.getNodeName() == "x1")
                                    x1 = Integer.parseInt(tempNode.getNodeValue());
                                if (tempNode.getNodeName() == "x2")
                                    x2 = Integer.parseInt(tempNode.getNodeValue());
                                if (tempNode.getNodeName() == "y1")
                                    y1 = Integer.parseInt(tempNode.getNodeValue());
                                if (tempNode.getNodeName() == "y2")
                                    y2 = Integer.parseInt(tempNode.getNodeValue());
                            }
                        }
                        if (node.hasChildNodes()) {
                            visitChildNodes(node.getChildNodes());
                        }
                    }
                    if (node.getNodeName() == "r") {
                        perimetro = (2 * ancho) + (2 * height);
                        System.out.println("->perimetro: " + perimetro);
                        area = ancho * height;
                        System.out.println("->Area: " + area);

                    } else if (node.getNodeName() == "circulo") {
                        perimetro = 2 * pi * r;
                        System.out.println("->perimetro: " + perimetro);
                        area = pi * Math.pow(r, 2);
                        System.out.println("->area: " + area);
                    } else if (node.getNodeName() == "line") {
                        distancia = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                        System.out.println("->distancia: " + distancia);
                    }
                }
            }
        }
    }
}
