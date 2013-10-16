package com.sevak_avet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarkovTextReplacement {
	public static final List<String> repLeftOrig = Arrays.asList("|>*<", "d|",
			"dm", "d>", "<>*<", "e|", "em", "e>");
	public static final List<String> repRightOrig = Arrays.asList(">*<d",
			"|md", "md", ">", "<e", "e", "|e", ">");

	public static void main(String[] args) {
		String text = "<|||>*<||>";

		int[] proirities = getPriorities(args);

		List<String> repLeft = new ArrayList<>();
		List<String> repRight = new ArrayList<>();

		for (int i = 0; i < proirities.length; ++i) {
			int curPriority = proirities[i] - 1;

			repLeft.add(repLeftOrig.get(curPriority));
			repRight.add(repRightOrig.get(curPriority));
		}

		List<Integer> ruleUse = new ArrayList<>();

		while (canReplace(text)) {
			for (int i = 0; i < repLeft.size(); ++i) {
				String curRepLeft = repLeft.get(i);

				if (text.indexOf(curRepLeft) != -1) {
					text = text.replace(curRepLeft, repRight.get(i));
					ruleUse.add(repLeftOrig.indexOf(curRepLeft) + 1);
					break;
				}
			}
		}

		for (int i : proirities) {
			System.out.printf("%d", i);
		}

		System.out.print(" ");

		for (int i : ruleUse) {
			System.out.printf("%d", i);
		}

	}

	public static boolean canReplace(String str) {
		for (String s : repLeftOrig) {
			if (str.indexOf(s) != -1) {
				return true;
			}
		}

		return false;
	}

	public static int[] getPriorities(String[] args) {
		int[] priorities = new int[8];

		for (int i = 0; i < 8; ++i) {
			priorities[i] = Integer.parseInt(args[i]);
		}

		return priorities;
	}
}
