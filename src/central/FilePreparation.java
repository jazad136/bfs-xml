package central;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilePreparation {
	
	public static final NumberFormat statDecFormat = new DecimalFormat("#0.00");
	public static final NumberFormat statIntFormat = NumberFormat.getIntegerInstance();
	static {
		statIntFormat.setGroupingUsed(false);
		statDecFormat.setGroupingUsed(false);
	}
	public static String reappendExtension(String inputFilename, Path newOutputPath, String extension)
	{
		int lastDotPos = inputFilename.lastIndexOf('.'); 
		if(lastDotPos > 0) { 
			String outputF = inputFilename.substring(0, lastDotPos);
			return addSeparator(newOutputPath.toString()) + outputF + extension;
		}
		return addSeparator(newOutputPath.toString()) + inputFilename + extension;
	}
	
	public static String reappendExtension(String inputFilename, String newOutputPath, String extension)
	{
		int lastDotPos = inputFilename.lastIndexOf('.'); 
		if(lastDotPos > 0) { 
			String outputF = inputFilename.substring(0, lastDotPos);
			return addSeparator(newOutputPath) + outputF + extension;
		}
		return addSeparator(newOutputPath) + inputFilename + extension;
	}
	public static String addSeparator(String somePath) { 
		if(!somePath.isBlank() && !somePath.endsWith(File.separator)) 
			return somePath + File.separator;
		return somePath;
	}
	public static String sanitize(String in) {
		String out = in;
		out = out.replace(",", " ");
		out = out.replace("\n", "  ");
		return out;
	}
	public static String sanitizeXMLforAttribute(String in) {
		String out = in;
		out = out.replace("\n", " ");
		return out;
	}
	public static String getLineLimitedString(String inputString, int textLineLimit)
	{
		if(inputString == null)
			return "";
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(inputString, " \n");
		
		int count = 1;
		while(st.hasMoreTokens()) {
			if(count % textLineLimit == 0) 
				sb.append("\n");
			sb.append(st.nextToken().trim() + (st.hasMoreTokens() ? " " : ""));
			count++;
		}
		return sb.toString();
	}
	/**
	 * Return a string that contains no newline characters
	 */
	public static String getLineIndifferentString(String inputString) { 
		StringBuilder toReturn = new StringBuilder(); 
		StringTokenizer st = new StringTokenizer(inputString.trim(), "\n\r\f");
		if(st.hasMoreTokens()) {
			toReturn.append(st.nextToken());
			while(st.hasMoreTokens()) 
				toReturn.append(" " + st.nextToken());
		}
		return toReturn.toString();
	}
	public static long nonWSCharCount(String inputString) { 
		StringTokenizer st = new StringTokenizer(inputString.trim());
		long ct = 0;
		while(st.hasMoreTokens()) 
			ct += st.nextToken().length();
		return ct;
	}
	
	public static int wordCount(String inputString) { return (new StringTokenizer(inputString.trim())).countTokens(); }
	public static int lineCount(String inputString) {
//		Matcher m = Pattern.compile("\\r\\n|\\r|\\n").matcher(yourInput);
//		Matcher m = Pattern.compile(".*\\R.*").matcher(inputString);
		inputString = inputString.trim();
		Matcher m = Pattern.compile("\\R").matcher(inputString);
		int lines = 1;
		while (m.find())
			lines++;
		return lines;
	}
	
	/* Thanks goes out to 
	 * https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
	 */
	/**
	 * Check the potential string for conformance to the format of a real decimal number. 
	 */
	public static boolean checkNumeric(String potential) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(potential, pos);
		return potential.length() == pos.getIndex();	
	}
	
	public static long handlePositiveLong(String nextS)
	{
		if(nextS.isEmpty() || !FilePreparation.checkNumeric(nextS)) 
			return -1;
		BigInteger x = new BigInteger(nextS);
		if(x.compareTo(BigInteger.ZERO) < 0) 
			return -1;
		return x.longValue();
	}
	public static int handlePositiveNormalInt(String nextS) {
		if(nextS.isEmpty() || !FilePreparation.checkNumeric(nextS))
			return -1;
		BigInteger x = new BigInteger(nextS);
		if(x.compareTo(BigInteger.ZERO) < 0) 
			return -1;
		return x.intValue();
	}
	public static double handlePositiveDouble(String nextS)
	{
		if(nextS.isEmpty() || !FilePreparation.checkNumeric(nextS)) 
			return -1;
		BigInteger x = new BigInteger(nextS);
		if(x.compareTo(BigInteger.ZERO) < 0) 
			return -1;
		return x.doubleValue();
	}
	public static String mapToFileDecimal(Number nextOut)
	{
		String nextFd;
		if(nextOut instanceof Double || nextOut instanceof Float) 
			nextFd = statDecFormat.format(nextOut);
		else
			nextFd = statIntFormat.format(nextOut);
		return nextFd;
	}
	
	
	
}

