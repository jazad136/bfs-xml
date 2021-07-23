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

import java.util.Iterator;

import org.w3c.dom.Document;

public class XMLIterator implements Iterator<Definition> {
	IterateBFS itController;
	private Definition nextDef;
	public XMLIterator(Document xmlDoc) { 
		itController = new IterateBFS(xmlDoc); 
		itController.startXMLBFS();
		nextDef = new Definition("");
	}
	public boolean hasNext() { return !itController.bfsDone(); } 
	
	public boolean lastSibling() { return itController.lastSibling(); }
	public boolean fullyExploredParent() { 
		return itController.hasLastParent() && itController.lastSibling(); 
	}
	
	public Definition next() { 
		nextDef = itController.bfsVisit();
		boolean useful = itController.continueXMLBFS(nextDef);
		while(!useful && !itController.bfsDone()) {
			nextDef = itController.bfsVisit();
			useful = itController.continueXMLBFS(nextDef);
		}
		return nextDef; 
	}
	
	public boolean parentIsReady() { return itController.bfsv.parentIsReady(); }
//	public Definition readyParent() { return itController.bfsv.readyParent(); } 
	public Definition getParent() { return itController.lastParent(); } 
	public boolean hasLastParent() { return itController.hasLastParent(); } 
}
