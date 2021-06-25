package central;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ErrorOut extends RuntimeException {

	public ErrorOut(String message) { super(message); } 
	public ErrorOut(Throwable cause, String message) { super(message, cause); }
	
	public static String someStackTrace(int num, Exception e) { 
		return Arrays.stream(e.getStackTrace())
				.limit(num)
				.map(StackTraceElement::toString)
				.collect(Collectors.joining("\n\t"));
	}
}
