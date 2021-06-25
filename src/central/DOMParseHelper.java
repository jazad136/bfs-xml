package central;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DOMParseHelper {
	
	public static Document readNormDocumentThrowRuntimeEx(File xmlIn) { 
		Document retDoc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
		dbf.setNamespaceAware(false);
		try { 
			DocumentBuilder db = dbf.newDocumentBuilder();
			retDoc = db.parse(xmlIn);
			retDoc.normalizeDocument();
		}
		catch(ParserConfigurationException pce) { new RuntimeException(pce); } 
		catch(SAXParseException spe) { throw new RuntimeException(spe); }
		catch(SAXException sxe) { throw new RuntimeException(sxe); } 
		catch(IOException ioe) { throw new RuntimeException(ioe); } 
		return retDoc;
	}
	public static Document readNormDocumentThrowExceptions(File xmlIn)
		throws ParserConfigurationException, SAXException, IOException { 
		
		Document retDoc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
		dbf.setNamespaceAware(false);
		 
		DocumentBuilder db = dbf.newDocumentBuilder();
		retDoc = db.parse(xmlIn);
		retDoc.normalizeDocument();
		
		return retDoc;
	}
}
