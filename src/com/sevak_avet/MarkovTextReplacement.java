package com.sevak_avet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarkovTextReplacement {
	public static final List<String> repLeftOrig = Arrays.asList("1>x<", "d1",
			"dm", "d>", "<>x<", "e1", "em", "e>");
	
	public static final List<String> repRightOrig = Arrays.asList(">x<d",
			"1md", "md", ">", "<e", "e", "1e", ">");

	public static List<String> repLeft;
	public static List<String> repRight;

	private static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		String text = "";
		int[] priorities = new int[8];
		
		BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
		text = reader.readLine();
		
		String[] prioritiesString = reader.readLine().split(" ");
		for(int i=0; i<priorities.length; ++i) {
			priorities[i] = Integer.parseInt(prioritiesString[i]);
		}
		reader.close();
		
		text = text.replace("|", "1");
		text = text.replace("*", "x");


		repLeft = new ArrayList<>();
		repRight = new ArrayList<>();

		for (int i = 0; i < priorities.length; ++i) {
			int curPriority = priorities[i] - 1;

			repLeft.add(repLeftOrig.get(curPriority));
			repRight.add(repRightOrig.get(curPriority));
		}

		List<Integer> userRules = new ArrayList<>();
		int index = 0;

		while (canReplace(text)) {
			for (int i = 0; i < repLeft.size(); ++i) {
				if (text.indexOf(repLeft.get(i)) != -1) {
					index = repLeftOrig.indexOf((String) repLeft.get(i)) + 1;
					text = text.replaceFirst(repLeft.get(i), repRight.get(i));
					userRules.add(index);
					break;
				}
			}
		}
		
		writeToFile(args[1], priorities, userRules);

	}

	public static boolean canReplace(String str) {
		for (String s : repLeft) {
			if (str.indexOf(s) != -1) {
				return true;
			}
		}

		return false;
	}
	
	public static void writeToFile(String path, int[] priorities, List<Integer> usedRules) {
		try {
			File file = new File(path);
			
			pw = new PrintWriter(file);
			for (int i : priorities) {
				pw.printf("%d", i);
			}

			pw.write(" ");

			for (int i : usedRules) {
				pw.printf("%d", i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}

}