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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class IterateBFS {
	
	public ArrayList<Definition> allDefs;
	public BFSVisitor bfsv;
	public Document xmlDoc;
	static Definition lastSibling = Definition.emptyDef;
	
	public IterateBFS(Document xmlDoc) { 
		this.xmlDoc = xmlDoc;
	}
	
	
	public static void errorOut(Exception e) { 
		System.err.println(e.getClass().getSimpleName() + ":");
		System.err.println(e.getLocalizedMessage());
		System.err.println("at\t" + ErrorOut.someStackTrace(4, e));
	}
	public void startXMLBFS() { 
	}
	public void continueXMLBFS() { 
		
	}
	protected class BFSVisitor { 
		Queue Q;
		int parent; 
		public BFSVisitor(Document xmlDoc) { 
		}
		
		public boolean bfsDone() { return Q.isEmpty(); } 
		
	}
	
}
