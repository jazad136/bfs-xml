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
	public boolean isEmpty() { return size == 0; } 
}
