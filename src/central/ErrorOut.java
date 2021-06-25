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

	public ErrorOut(String message) { super(message); } 
	public ErrorOut(Throwable cause, String message) { super(message, cause); }
	
	public static String someStackTrace(int num, Exception e) { 
		return Arrays.stream(e.getStackTrace())
				.limit(num)
				.map(StackTraceElement::toString)
				.collect(Collectors.joining("\n\t"));
	}
}
