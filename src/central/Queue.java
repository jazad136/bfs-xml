package central;

import java.util.Collection;

public class Queue {
	public static class QueueEmpty extends RuntimeException { 
		/** Throws an empty error for a queue being empty */
		public QueueEmpty(String message) { super(message); } 
	}
	
	private Holder head;
	private Holder tail;
	private int size;
	private static class Holder {
		public final Definition element;
		public Holder queueNext;
		public Holder(Definition def) { this.element = def; }
	}
	
	public Queue() {  
		head = new Holder(new Definition("")); 
		tail = new Holder(new Definition(""));
	}
	public void enqueueAll(Collection<Definition> defs) { 
		for(Definition def : defs) 
			enqueue(def);
	}

	public Definition first() { 
		if(size == 0) 
			throw new QueueEmpty("first() called with empty queue object.");
		return head.element;
	}
	public Definition dequeue() { 
		if(size == 0)
			throw new QueueEmpty("dequeue() called with empty queue");
		Definition toReturn = head.element;
		head = head.queueNext;
		size--;
		if(size == 0)
			tail = new Holder(Definition.emptyDef);
		return toReturn;
	}
	public int size() { return size; } 
	public void enqueue(Definition toProcess) { 
		Holder toInsert = new Holder(toProcess);
		if(size == 0) 
			head = toInsert;
		else
			tail.queueNext = toInsert;
		tail = toInsert;
		size++;
	}
	public boolean empty() { return size == 0; } 
}
