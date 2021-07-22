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

import java.util.Arrays;
import java.util.stream.Collectors;

public class ErrorOut extends RuntimeException {
	public enum BFSVReason { NOINPUT , BADINPUT, MESSAGE, UNGIVEN };
	public BFSVReason statedReason;
	public String field;
	public String found;
	public String expected;
	public ErrorOut(String message) { 
		super(message); 
		statedReason = BFSVReason.MESSAGE;
	} 
	public ErrorOut(Throwable cause, String message) { 
		super(message, cause); 
		statedReason = BFSVReason.MESSAGE;
	}
	public ErrorOut(BFSVReason reason, String field, String expected) { 
		statedReason = reason; 
		this.expected = expected;
	}
	public String getLocalizedMessage() { 
		if(statedReason == BFSVReason.NOINPUT) {
			return "In field: " + field + ", " + expected + " was expected, but no input was found."; 
		}
		return super.getLocalizedMessage();
	}
	
	public static String someStackTrace(int num, Exception e) { 
		return Arrays.stream(e.getStackTrace())
				.limit(num)
				.map(StackTraceElement::toString)
				.collect(Collectors.joining("\n\t"));
	}
}
