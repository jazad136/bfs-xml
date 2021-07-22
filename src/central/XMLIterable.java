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

public class XMLIterable implements Iterable<Definition>, Iterator<Definition>  {

	public XMLIterator iter;
	public XMLIterable(Document xmlDoc) {  iter = new XMLIterator(xmlDoc); }
	@Override
	public Iterator<Definition> iterator() { return iter; }
	
	public Definition next() { return iter.next(); } 
	public boolean hasNext() { return iter.hasNext(); }
	
}

