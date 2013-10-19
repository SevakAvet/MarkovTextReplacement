package com.sevak_avet;

import java.util.Arrays;
import java.util.List;

public class Rules {
	private static List<String> rulesLeft = Arrays.asList("|>*<", "d|",
			"dm", "d>", "<>*<", "e|", "em", "e>");
	private static List<String> rulesRight = Arrays.asList(">*<d",
			"|md", "md", ">", "<e", "e", "|e", ">");
	
	public static List<String> getRulesLeft() {
		return rulesLeft;
	}
	
	public static List<String> getRulesRight() {
		return rulesRight;
	}
	
	public static void addRule(String left, String right) {
		rulesLeft.add(left);
		rulesRight.add(right);
	}
}
