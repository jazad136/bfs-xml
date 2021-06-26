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

import static java.lang.String.format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class Inputs {
	public static File defaultOutputFile;
	public static Document inputXML;
	public static File argsInputFile;
	public static HashMap<String, Integer> argName;
	static {
		argName = new HashMap<>();
		argName.put("-f", 0);
	}
	public static boolean[] found;
	private static List<String>[] argumentStrings;
	
	public static String[] parseArgs(String[] args) {
		ArrayList<String> remaining = new ArrayList<String>(Arrays.asList(args));
		Iterator<String> it = remaining.iterator();
		
		File inspectFile = null;
		while(it.hasNext()) {
			String target = it.next();
			switch(target) { 
			case "-f":
				try {
					String constrMsg = checkAndSetConstraints(target);
					if(!constrMsg.isEmpty()) 
						throw new IllegalArgumentException(constrMsg);
					it.remove();
					String arg = it.next();
					argumentStrings[argName.get(target)].add(arg);
					argsInputFile = new File(arg).getAbsoluteFile();
					inspectFile = argsInputFile;
					Document obj;
					obj = DOMParseHelper.readNormDocumentThrowExceptions(inspectFile);
					String inPath = filepathString(arg);
					String newOut = FilePreparation.reappendExtension(inspectFile.getName(), inPath, ".csv");
					defaultOutputFile = new File(newOut);
					inputXML = obj;
				}
				catch(ParserConfigurationException pce) { 
					throw new IllegalArgumentException(format("Arguments Parser: Document Builder Problem: %s", pce.getLocalizedMessage()), pce);
				}
				catch(SAXParseException sxe) { 
					throw new IllegalArgumentException(format("Arguments Parser: File specified to %s argument"
							+ "\n\"%s\"\ndid not contain a recognized \'srcml\' XML document.\n%s",
							target, inspectFile.getAbsoluteFile()), sxe);
				}
				catch(SAXException se) { 
					throw new IllegalArgumentException(format("Arguments Parser: File specified to %s argument"
							+ "\n\"%s\"\ndid not contain a recognized \'srcml\' XML document.\n%s",
							target, inspectFile.getAbsoluteFile()), se);
				}
				catch(NoSuchElementException e) { 
					throw new IllegalArgumentException(format("Arguments Parser: %s argument cannot be empty", target));
				}
				catch (FileNotFoundException e) {
					throw new IllegalArgumentException(format("Arguments Parser: File"
							+ "\n\"%s\"\ncould not be found on the file system.", inspectFile.getAbsoluteFile()));
				}
				catch(IOException xmle) {
					throw new IllegalArgumentException(format("Arguments Parser: File specified to %s argument"
							+ "\n\"%s\"\ndid not contain a recognized \'srcml\' XML document.\n%s",
							target, inspectFile.getAbsoluteFile()), xmle);
				}
			}
		}
		if(!wasFound("-f")) { 
//			throw new IllegalArgumentException("'-f' argument not specified.");
		}
		return new String[0];
	}
	
	public static String filepathString(String rawPath) {
		File file = new File(rawPath);
		String toReturn = file.getPath();
		toReturn = toReturn.substring(0, toReturn.lastIndexOf(File.separatorChar)+1);
		return toReturn;
	}
	@SuppressWarnings("unchecked")
	public static String[] resetAndParseArgs(String[] args) { 
		found = new boolean[1];
		argumentStrings = (LinkedList<String>[])new LinkedList[argName.size()];
		for(int i = 0; i < argumentStrings.length; i++) 
			argumentStrings[i] = new LinkedList<>();
		
		return parseArgs(args);
	}
	
	public static String checkAndSetConstraints(String target) { 
		int argTag = argName.get(target);
		if(found[argTag])
			return String.format("Arguments Parser: %s parameter cannot be defined twice.", target);
		found[argTag] = true;
		return "";
	}
	public static boolean wasFound(String name) {return found[argName.get(name)]; }
	
}
