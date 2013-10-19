package com.sevak_avet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MarkovTextReplacement {
	private static List<Integer> usedRules;
	private static List<Integer> prioritiesList;

	private static List<String> repLeft;
	private static List<String> repRight;

	private static String textOrig;

	public static void runReplacing(String text) {	
		textOrig = text;

		text = text.replace("|", "1");
		text = text.replace("*", "x");

		repLeft = new ArrayList<>();
		repRight = new ArrayList<>();

		changeToPriority();

		int index = 0;
		while (canReplace(text)) {
			for (int i = 0; i < repLeft.size(); ++i) {
				if (text.indexOf(repLeft.get(i)) != -1) {
					index = Rules.getRulesLeft().indexOf(repLeft.get(i)) + 1;
					text = text.replaceFirst(repLeft.get(i), repRight.get(i));
					usedRules.add(index);
					break;
				}
			}
		}
		
		textOrig = text;
		textOrig = textOrig.replace("1", "|");
	}
	
	public static void runReplacing(String priorities, String text) {
		usedRules = new ArrayList<>();
		prioritiesList = new ArrayList<>();
		
		getPriorities(priorities);
		runReplacing(text);
	}

	public static List<Integer> getSolution() {
		return usedRules;
	}
	
	public static String getText() {
		return textOrig;
	}

	public static List<Integer> getPriorities() {
		return prioritiesList;
	}

	private static void getPriorities(String p) {
		String[] prioritiesString = p.split(" ");

		for (int i = 0; i < prioritiesString.length; ++i) {
			prioritiesList.add(Integer.parseInt(prioritiesString[i]));
		}
	}

	public static String listToString(List<Integer> solution) {
		StringBuilder sb = new StringBuilder();

		for (int i : solution) {
			sb.append(i);
			sb.append(" ");
		}

		return sb.toString();
	}

	private static void changeToPriority() {
		for (int i = 0; i < prioritiesList.size(); ++i) {
			int curPriority = prioritiesList.get(i) - 1;
			repLeft.add(Rules.getRulesLeft().get(curPriority));
			repRight.add(Rules.getRulesRight().get(curPriority));
		}
	}

	public static boolean canReplace(String str) {
		for (String s : repLeft) {
			if (str.indexOf(s) != -1) {
				return true;
			}
		}

		return false;
	}

	public static void readFromFile(String path) {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {

			textOrig = reader.readLine();
			getPriorities(reader.readLine());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void writeToFile(String path) {		
		try (PrintWriter pw = new PrintWriter(new File(path))) {
			for (int i : prioritiesList) {
				pw.printf("%d", i);
			}

			pw.write("   ");

			for (int i : usedRules) {
				pw.printf("%d", i);
			}
			
			pw.write("   ");
			
			pw.write(textOrig);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
