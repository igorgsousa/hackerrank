package br.com.igorgsousa.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * {@link https://www.hackerrank.com/challenges/balanced-brackets/problem}
 */

enum Bracket {

	OPEN_SQUARE('[', true, null), OPEN_CURLY('{', true, null), OPEN_PARENTHESIS('(', true, null),
	CLOSE_SQUARE(']', false, Bracket.OPEN_SQUARE), CLOSE_CURLY('}', false, Bracket.OPEN_CURLY),
	CLOSE_PARENTHESIS(')', false, Bracket.OPEN_PARENTHESIS);

	private final char key;
	private final boolean isOpenBrackets;
	private final Bracket closerFrom;

	private Bracket(char key, boolean isOpenBrackets, Bracket closerFrom) {
		this.key = key;
		this.isOpenBrackets = isOpenBrackets;
		this.closerFrom = closerFrom;
	}

	public char getKey() {
		return key;
	}

	public boolean isOpenBrackets() {
		return isOpenBrackets;
	}

	public Bracket getCloserFrom() {
		return closerFrom;
	}

	public static Bracket from(char key) {
		for (Bracket bracket : Bracket.values()) {
			if (bracket.key == key) {
				return bracket;
			}
		}
		return null;
	}
}

public class Main {

	static String isBalanced(String s) {

		boolean isBalanced = true;

		List<Bracket> bracketsStack = new ArrayList<Bracket>();
		try {

			for (int i = 0; i < s.length(); i++) {

				Bracket bracket = Bracket.from(s.charAt(i));

				if (bracket.isOpenBrackets()) {
					bracketsStack.add(bracket);
				} else {
					if (bracketsStack.size() > 0) {
						int size = bracketsStack.size() - 1;

						if (bracket.getCloserFrom().equals(bracketsStack.get(size))) {
							bracketsStack.remove(size);
						} else {
							isBalanced = false;
							break;
						}
					} else {
						isBalanced = false;
						break;
					}
				}
			}

		} catch (Exception e) {
			System.out.println(s);
			throw e;
		}
		
		if(bracketsStack.size() > 0) {
			isBalanced = false;
		}

		return isBalanced ? "YES" : "NO";
	}

//	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("results/result.txt"));
    	Scanner scanner = new Scanner(new File("resources/input17.txt"));

    	int t = new Integer(scanner.nextLine());
//		int t = scanner.nextInt();

		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int tItr = 0; tItr < t; tItr++) {
			String s = scanner.nextLine();
			String result = isBalanced(s);

			bufferedWriter.write(result);
			bufferedWriter.newLine();
		}

		bufferedWriter.newLine();

		bufferedWriter.close();
	}
}
