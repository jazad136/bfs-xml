/*******************************************************************************
 *    Copyright (c) 2018 Jonathan A. Saddler
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 *    Contributors:
 *     Jonathan A. Saddler
 *******************************************************************************/
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
