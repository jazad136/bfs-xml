# bfs-xml
Source code repository Breadth First Search Style XML Parser
by Jonathan A. Saddler

maintainer email: jsaddlerecu@gmail.com

# News
Hey new release today, morning of the 26th. Here are the notes: 
* I don't know of any reasons why Java 8 can't be supported. So you can now to use Java 8 to run this simple program. 
* I don't know of any reasons why Java 11 can't be supported. So you will be able to use Java 11 to run this simple program. 
* Why not make it easiest to use the product by making a JAR file so it can be run easily? 

Jar files for Java 8, 11, and 13 will be provided on the "Releases" page linked to near the top half of my project repository page for bfs-xml page. 
Reach that page [here](https://github.com/jazad136/bfs-xml/releases/).

## Why Use BFS-XML
Reading structured inputs in an object oriented programming languages can be hard. For XML, there can be lots of legwork involved. 

* errors in assumptions of the structure of the file format incorrect (these can take a day of work to discover)
* How the input file is traversed to see points of failure in reading and writing to files can take a day to understand 

XML files can be huge.  Understanding how libraries can understand and interpret what's inside is what developers wish they had at multiple stages of the process of working with XML input. 

BFS-XML's job is to turn your input file into an "iterable" object that can return elements of your XML tree as output in breadth-first-search order. Breadth-first search is an algorithm taught in computing schools that predictably returns all elements of a tree when implemented correctly. 

Imagine turning your XML file on its side: on the leftmost, you have the XML root element, and beneath you have siblings or children of that root.  Proceeding, we have children of the first and second rows of those root-children returned from the file. 

We wrote our library with Java 13 in mind, and are confident that our solution can help you safely and with simplicity iterate through your XML file, reading all its inputs into your favorite XML internal process. 
### Lines of Code Snapshot - July 22, 2021 
![CurrentLinesOfCode](https://github.com/jazad136/bfs-xml/blob/main/imagehistory/Jul22_2021_status.bmp)

## How to Use
First comes just running the software. 
To make the process easy, you can use the command

```java -jar bfm.jar``` 

if you have Java version 13 installed, replacing *bfm.jar* with either of the other two files *bfm-jre11.jar* or *bfm-jre8.jar* if you are using Java version 11 installed or Java version 8.

To run without the use of Maven or a jar file, run the command: `javac -d bin --module-path src src/central/*.java`
at a command line prompt open to the root of the directory you've extracted all files from this repository (where you can see the directory named src)

After compiling all the files, there is a test embeeded in the code in TestProcessXML.class, that will run without any file dependencies.
To run it: run the command `cd bin` then `java central.TestProcessXML` after following the steps above. 

### What just happened? 
![BFSXMLUML](https://github.com/jazad136/bfs-xml/blob/main/imagehistory/BFSXMLImage.png)
There are two big use cases. The first use case comes from just iterating through each element of an XML file. The file used in this test can be found at issue #1 in our issue tracker. An XML file is a tree with many intermediate branches and leaves. One file might look like this: 
```
<shiporder> 
  <orderperson>John Smith</orderperson> 
    <shipto> 								
    <name>Ola Nordman</name> 			
    <address>Langgt 23</address>			
    <city>4000 Stavanger</city> 
    <country>Norway</country>			
  </shipto>
  <item>
    <title>Empire Burlesque</title>
    <note>Special Edition</note>
    <quantity>1</quantity>
    <price>10.90</price>
  </item>					
</shiporder>	
```
In this test we use a plan that waits until all branches under an XML element get visited before printing out the values.
We use the *XMLIterator* to accomplish this process, which has methods like *fullyExploredParent()* that allow us to check for this condition. 
After downloading and importing our packages yourself you can run code like this. If els is an instance of XMLIterable created from a document... 
```
XMLIterator it = els.iter;
for(Definition def : els) { 
  if(it.fullyExploredParent()) {
    Definition parent = it.getParent();
    System.out.println(parent);
  }
}
```
You'll get the output for the test to look like this after running the command above (make sure to have java installed): 
![BFSXML Test Output](https://github.com/jazad136/bfs-xml/blob/main/imagehistory/BFSXMLTestOutput.png)

The second use case is to simply iterate through all the elements, and print out every element, attribute, and text definition you see. 
```
for(Definition def : els) { 
  System.out.println(def);
}
```
## Intentional Design Decisions

BFS-XML is a tool created by a Java developer with a desire to learn deep control of XML API's deep enough to circumvent *all* major Java dependencies to parse Java XML input arguments. We are proud enough of our product to present the tool as a free open source tool that other developers can modify for their own use. 

Java SE 13 uses the W3C XML DOM parser that comes with the Java SE standard libraries for use by developers who wish to parse XML documents. bfs-xml uses DOM directly to read in XML documents, and we make it possible via this library to efficiently "breadth first search" through the XML tree. 

*Target Language*: Java JRE version 13.

