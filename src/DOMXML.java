import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class DOMXML {
  private DocumentBuilderFactory factory;
  private DocumentBuilder builder;
  private Document doc;
  private ArrayList<Campeon> campeones = new ArrayList();

  public DOMXML(ArrayList<Campeon> campeones) {
    this.campeones = campeones;
    factory = DocumentBuilderFactory.newInstance();
  }

  public void inicializarDOM(String nombreArchivo) throws Exception {
    builder = factory.newDocumentBuilder();
    doc = builder.parse(nombreArchivo);
  }





  private String obtenerContenidoElemento(Element elemento, String nombreElemento) {
    if (elemento.getElementsByTagName(nombreElemento).getLength() > 0) {
      return elemento.getElementsByTagName(nombreElemento).item(0).getTextContent();
    }
    return null;
  }

  public void CrearXML(String nombreArchivo) throws Exception {

    Document doc = crearDoc().newDocument();
    Element rootElement = doc.createElement("campeones");
    doc.appendChild(rootElement);

    for (Campeon campeon : campeones) {
      rootNodo(campeon, rootElement, doc);
    }

    crearFichero(nombreArchivo, doc);
  }

  public DocumentBuilder crearDoc() throws ParserConfigurationException {
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      return docBuilder;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  public void rootNodo(Campeon campeon, Element rootElement, Document doc) {
    Element reservaElement = doc.createElement("campeon");
    rootElement.appendChild(reservaElement);

    reservaElement.appendChild(createElement(doc, "id", campeon.getId()));
    reservaElement.appendChild(createElement(doc, "name", campeon.getName()));
    reservaElement.appendChild(createElement(doc, "role", campeon.getRole()));
    reservaElement.appendChild(createElement(doc, "lane", campeon.getLane()));
    reservaElement.appendChild(createElement(doc, "attackType", campeon.getAttackType()));
//    reservaElement.appendChild(createElement(doc, "difficulty", campeon.getDifficulty()));
//    reservaElement.appendChild(createElement(doc, "releaseYear", campeon.getReleaseYear()));
    reservaElement.appendChild(createElement(doc, "lore", campeon.getLore()));

  }

  public void crearFichero(String nombreArchivo, Document doc) throws TransformerException {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(nombreArchivo));
      transformer.transform(source, result);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private Node createElement(Document doc, String name, String value) {
    Element node = doc.createElement(name);
    node.appendChild(doc.createTextNode(value));
    return node;
  }

}