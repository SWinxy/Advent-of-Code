package me.swinxy.aoc2020;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class Day6 {

	public static void main(String[] args) throws IOException {
		InputStream stream = Day6.class.getClassLoader().getResourceAsStream("day6.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		String[] groups = new String(stream.readAllBytes()).split("\r\n\r\n");

		int sum = 0;

		for (String group : groups) {
			Set<Character> uniqueChars = new HashSet<>();
			for (char c : group.toCharArray()) {
				if (c >= 'a' && c <= 'z') {
					uniqueChars.add(c);
				}
			}
			sum += uniqueChars.size();
		}

		System.out.println(sum);

		sum = 0;

		for (String group : groups) {
			String[] individuals = group.split("\r\n");

			for (char c = 'a'; c <= 'z'; c++) {
				boolean allHave = true;
				for (int i = 0; i < individuals.length && allHave; i++) {
					allHave = containsChar(individuals[i], c);
				}

				if (allHave) {
					sum++;
				}
			}
		}

		System.out.println(sum);
	}

	private static boolean containsChar(String s, char c) {
		for (char f : s.toCharArray()) {
			if (f == c) {
				return true;
			}
		}
		return false;
	}
}