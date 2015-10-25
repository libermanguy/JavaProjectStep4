package general;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.widgets.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@SuppressWarnings("serial")
public class Properties implements Serializable 
{
	int threadcount;
	String workspace;
	int display;
	
	public Properties() {
		threadcount =0;
		display=0;
		workspace="default";
	}
	
	public void loadProp(String file) throws Exception
	{
		File inputfile = new File(file);
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
		Document doc = dbuilder.parse(inputfile);
		doc.getDocumentElement().normalize();
		NodeList nlist = doc.getElementsByTagName("properties");
		Node n = nlist.item(0);
		Element e = (Element)n;
		threadcount = Integer.parseInt(e.getElementsByTagName("threadcount").item(0).getTextContent());
		workspace =  e.getElementsByTagName("workspace").item(0).getTextContent();
		display = Integer.parseInt(e.getElementsByTagName("display").item(0).getTextContent());
	}
	
	public void saveProp(String file) throws Exception
	{
		File outputfile = new File(file);
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
		Document doc = dbuilder.newDocument();
		Element e = doc.createElement("properties");
		doc.appendChild(e);
		
		Element e2 = doc.createElement("threadcount");
		e2.setTextContent(threadcount+"");
		e.appendChild(e2);
		
		
		Element e3 = doc.createElement("workspace");
		e3.setTextContent(workspace);
		e.appendChild(e3);
		
		Element e4 = doc.createElement("display");
		e4.setTextContent(display+"");
		e.appendChild(e4);
		
		
		TransformerFactory ts = TransformerFactory.newInstance();
		Transformer transformer = ts.newTransformer();
		DOMSource dm = new DOMSource(doc);
		transformer.transform(dm, new StreamResult(outputfile));
		}

	public int getThreadcount() {
		return threadcount;
	}

	public String getWorkspace() {
		return workspace;
	}

	public int getDisplay() {
		return display;
	}
	
	
}
