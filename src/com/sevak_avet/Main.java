package com.sevak_avet;

import static com.sevak_avet.MarkovTextReplacement.*;

public class Main {
	public static void main(String[] args) {
		readFromFile("C:\\input.txt");
		runReplacing(getText());
		writeToFile("C:\\output.txt");
	}
}