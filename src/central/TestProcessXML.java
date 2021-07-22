/*******************************************************************************
 *    Copyright (c) 2021 Jonathan A. Saddler
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

import org.w3c.dom.Document;

/**
 * Test code file for the TestProcessXML class. This class is used to test the processing of XML in 
 * the bfs-xml project. 
 * @author Jonathan A. Saddler 
 */
public class TestProcessXML {
	
	public static void main(String[] args) {
		Document doc = Inputs.readXMLString(someTestXML2());
		testIteration3(doc);
	}
	
	public static void testIteration3(Document doc) {
		XMLIterable els = new XMLIterable(doc);
		XMLIterator it = els.iter;
		for(Definition def : els) { 
			if(it.fullyExploredParent()) {
				Definition parent = it.getParent();
				System.out.println(parent);
			}
		}
	}
	public static void testIteration2(Document doc) { 
		XMLIterator it = new XMLIterator(doc);
//		Definition parent = new Definition(doc.getDocumentElement());
		Definition nextDef;
		while(it.hasNext()) { 
			nextDef = it.next();
//			parent = it.lastSibling() ? parent : it.readyParent();
//			if(!nextDef.isEmpty && it.lastSibling()) {
			if(it.fullyExploredParent()) { 
//			&& nextDef.getNode().compareDocumentPosition(doc) != 0) {  
//				if(it.getParent().getNode().compareDocumentPosition(doc) != 0)
					System.out.println(it.getParent()); 
			}
		}
		System.out.println("Done.");
	}
	public static void testIteration(Document doc) { 
		IterateBFS it = new IterateBFS(doc);
		it.startXMLBFS();
		while(!it.bfsDone()) { 
			Definition nextDef = it.bfsv.bfsVisit();
			boolean useful = it.continueXMLBFS(nextDef);
			if(useful && it.bfsv.parentIsReady())
				System.out.println(it.bfsv.readyParent());
		}
		System.out.println("Done.");
	}
	
	public static void readArguments(String[] args) {
		try { Inputs.resetAndParseArgs(args); } 
		catch(Exception e) { IterateBFS.errorOut(e); }
		testIteration(Inputs.inputXML);
	}
	
	public static StringBuilder someTestXML() { 
		StringBuilder sb = new StringBuilder();
		sb.append("<note>");
		sb.append("  <to>Tove</to>");
		sb.append("  <from>Jani</from>");
		sb.append("  <heading>Reminder</heading>");
		sb.append("  <body>Don't forget me this weekend</body>");
		sb.append("</note>");
		return sb;
	}
	public static StringBuilder someTestXML2() { 
		//https://www.w3schools.com/xml/schema_example.asp
		StringBuilder sb = new StringBuilder();
		sb.append("<shiporder>"); 							sb.append("\n");
		sb.append("<orderperson>John Smith</orderperson>"); sb.append("\n");
		sb.append("<shipto>"); 								sb.append("\n");
		sb.append("  <name>Ola Nordman</name>"); 			sb.append("\n");
		sb.append("  <address>Langgt 23</address>");			sb.append("\n");
		sb.append("  <city>4000 Stavenger</city>"); sb.append("\n");
		sb.append("  <country>Norway</country>");			sb.append("\n");
		sb.append("</shipto>");								sb.append("\n");
		sb.append("<item>");								sb.append("\n");
		sb.append("  <title>Emptire Burlesque</title>");	sb.append("\n");
		sb.append("  <note>Special Edition</note>");		sb.append("\n");
		sb.append("  <quantity>1</quantity>");				sb.append("\n");
		sb.append("  <price>10.90</price>");				sb.append("\n");
		sb.append("</item>");								sb.append("\n");
		sb.append("</shiporder>");							
		return sb;
	}
	
}
