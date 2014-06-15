package com.codeproof.util;

import java.util.UUID;

public class UniqueIdGenerator {
	
	public static String randomStringOfLength(int length) {
	    StringBuffer buffer = new StringBuffer();
	    while (buffer.length() < length) {
	        buffer.append(uuidString());
	    }

	    return buffer.substring(0, length);  
	}


	private static String uuidString() {
	    return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
