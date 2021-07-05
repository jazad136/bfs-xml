# bfs-xml
Source code repository Breadth First Search Style XML Parser
by Jonathan A. Saddler

maintainer email: Jonathan A. Saddler, Ph. D. 

Reading structured inputs in an object oriented programming languages can be hard. For XML, there can be lots of legwork involved. 

* errors in assumptions of the structure of the file format incorrect (these can take a day of work to discover)
* How the input file is traversed to see points of failure in reading and writing to files can take a day to understand 

XML files can be huge.  Understanding how your computer can understand and interpret what's inside is what developers wish they had at multiple stages of the process. 

BFS-XML's job is to turn your input file into an iterable object that can return elements of your XML tree as output in breadth first search order. Breadth first search is an algorithm taught in computing schools that predictably returns all elements of a tree when implemented correctly. 

Imagine turning your XML file on its side: on the leftmost, you have the XML root element, and beneath you have siblings or children of that root.  Proceeding, we have children of the first and second of those root-children returned from the file. 

We wrote our library with Java 13 in mind, and are confident that our solution can help you safely and with simplicity iterate through your XML file, reading all its inputs, into your favorite XML internal process. 

  

Target Language: Java JRE version 13.

