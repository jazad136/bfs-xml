package central;

import java.util.Iterator;

import org.w3c.dom.Document;

public class XMLIterator implements Iterator<Definition> {
	IterateBFS itController;
	public XMLIterator(Document xmlDoc) { 
		itController = new IterateBFS(xmlDoc); 
		itController.startXMLBFS();
	}
	public boolean hasNext() { return !itController.bfsDone(); } 
	
	public boolean lastSibling() { return itController.lastSibling(); }
	
	public Definition next() { 
		Definition next = itController.bfsVisit();
		boolean useful = itController.continueXMLBFS(next);
		while(!useful && !itController.bfsDone()) {
			next = itController.bfsVisit();
			useful = itController.continueXMLBFS(next);
		}
		return next;
	}
	public boolean parentIsReady() { return itController.bfsv.parentIsReady(); }
	public Definition readyParent() { return itController.bfsv.readyParent(); } 
	public Definition getParent() { return itController.lastParent(); } 
}
