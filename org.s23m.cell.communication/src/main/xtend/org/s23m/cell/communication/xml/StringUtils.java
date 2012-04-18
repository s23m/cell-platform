package org.s23m.cell.communication.xml;

public class StringUtils {
	
	public static String repeat(int n, String s) {
		return new String(new char[n]).replace("\0", s);	
	}
	
}
