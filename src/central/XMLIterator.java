package central;

import java.util.Iterator;

import org.w3c.dom.Document;

public class XMLIterator implements Iterator<Definition> {
	IterateBFS itController;
	public XMLIterator(Document xmlDoc) { itController = new IterateBFS(xmlDoc); }
	public boolean hasNext() { return itController.bfsDone(); } 
	
	public boolean lastSibling() { return itController.lastSibling(); }
	
	public Definition next() { 
		Definition next = itController.bfsVisit(); 
		itController.continueXMLBFS(next);
		return next;
	}
}
