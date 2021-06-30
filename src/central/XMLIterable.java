package central;

import java.util.Iterator;

import org.w3c.dom.Document;

public class XMLIterable implements Iterable<Definition>, Iterator<Definition>  {

	public Iterator<Definition> iter;
	public XMLIterable(Document xmlDoc) {  iter = new XMLIterator(xmlDoc); }
	@Override
	public Iterator<Definition> iterator() { return iter; }
	
	public Definition next() { return iter.next(); } 
	public boolean hasNext() { return iter.hasNext(); } 	 
}

