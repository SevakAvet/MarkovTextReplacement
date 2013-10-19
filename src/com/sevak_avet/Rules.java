package com.sevak_avet;

import java.util.Arrays;
import java.util.List;

public class Rules {
	private static final List<String> rulesLeftOrig = Arrays.asList("1>x<", "d1",
			"dm", "d>", "<>x<", "e1", "em", "e>");
	
	private static final List<String> rulesRightOrig = Arrays.asList(">x<d",
			"1md", "md", ">", "<e", "e", "1e", ">");
	
	public static List<String> getRulesLeft() {
		return rulesLeftOrig;
	}
	
	public static List<String> getRulesRight() {
		return rulesRightOrig;
	}
	
	public static void addRule(String left, String right) {
		rulesLeftOrig.add(left);
		rulesRightOrig.add(right);
	}
}
