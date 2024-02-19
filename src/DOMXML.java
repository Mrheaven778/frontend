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
		reservaElement.appendChild(createElement(doc, "difficulty", campeon.getDifficulty()));
		reservaElement.appendChild(createElement(doc, "releaseYear", campeon.getReleaseYear()));
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

	private Node createElement(Document doc, String name, Object value) {
	    Element node = doc.createElement(name);
	    
	    if (value instanceof Integer) {
	        // Si el valor es de tipo Integer, añadir como atributo entero
	        node.appendChild(doc.createTextNode(String.valueOf(value)));
	        node.setAttribute("type", "int");
	    } else {
	        // De lo contrario, asumir que es una cadena y añadir como nodo de texto
	        node.appendChild(doc.createTextNode((String) value));
	        node.setAttribute("type", "string");
	    }

	    return node;
	}


	public static void sacarDatosXML(ArrayList<Campeon> campeones) {
		try {
			// Crear un DocumentBuilder
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Parsear el archivo XML
			Document document = builder.parse("datosCampeones.xml");

			// Obtener la lista de nodos de campeon
			NodeList championNodes = document.getElementsByTagName("campeon");

			// Iterar a través de los nodos de campeon
			for (int i = 0; i < championNodes.getLength(); i++) {
				Node championNode = championNodes.item(i);
				if (championNode.getNodeType() == Node.ELEMENT_NODE) {
					Element championElement = (Element) championNode;

					String id = championElement.getElementsByTagName("id").item(0).getTextContent();
					String name = championElement.getElementsByTagName("name").item(0).getTextContent();
					String role = championElement.getElementsByTagName("role").item(0).getTextContent();
					String lane = championElement.getElementsByTagName("lane").item(0).getTextContent();
					String attackType = championElement.getElementsByTagName("attackType").item(0).getTextContent();
					String lore = championElement.getElementsByTagName("lore").item(0).getTextContent();
					int difficulty = Integer
							.parseInt(championElement.getElementsByTagName("difficulty").item(0).getTextContent());
					int releaseYear = Integer
							.parseInt(championElement.getElementsByTagName("releaseYear").item(0).getTextContent());

					// Imprimir los detalles del campeon
					System.out.println("ID: " + id);
					System.out.println("Name: " + name);
					System.out.println("Role: " + role);
					System.out.println("Lane: " + lane);
					System.out.println("Attack Type: " + attackType);
					System.out.println("Lore: " + lore);
					System.out.println("Difficulty: " + difficulty);
					System.out.println("Release Year: " + releaseYear);
					System.out.println("--------------------------------------");
					campeones.add(new Campeon(id, name, role, lane, attackType, difficulty, releaseYear, lore));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}