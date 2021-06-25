package central;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;

public class IterateBFS {
	
	public ArrayList<Definition> allDefs;
	public BFSVisitor bfsv;
	public Document xmlDoc;
	static Definition lastSibling = Definition.emptyDef;
	
	public IterateBFS() { } 
	
	
	public void startXMLBFS(Document xmlDoc) {  }
	
	protected class BFSVisitor { 
		Queue Q;
		int parent; 
		public BFSVisitor(Document xmlDoc) {  }
	}
	
	public static void errorOut(Exception e) { 
		System.err.println(e.getClass().getSimpleName() + ":");
		System.err.println(e.getLocalizedMessage());
		System.err.println("at\t" + ErrorOut.someStackTrace(4, e));
	}
}
