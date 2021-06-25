package central;

public class TestProcessXML {
	
	public static void main(String[] args) {
		try { Inputs.resetAndParseArgs(args); } 
		catch(Exception e) { IterateBFS.errorOut(e); }
		IterateBFS it = new IterateBFS();
		it.startXMLBFS(Inputs.inputXML);
	}
	
}
