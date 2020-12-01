package me.swinxy.aoc2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

	public static final int CURRENT_YEAR = 2020;

	public static void main(String[] args) {
		InputStream stream = Day1.class.getClassLoader().getResourceAsStream("day1.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		List<Integer> entries = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		reader.lines().mapToInt(Integer::parseInt).forEach(entries::add);

		for (int i = 0; i < entries.size(); i++) {
			int first = entries.get(i);
			for (int j = i + 1; j < entries.size(); j++) {
				int second = entries.get(j);

				if (first + second == CURRENT_YEAR) {
					System.out.printf("%d + %d = %d. %d * %d = %d%n", first, second, CURRENT_YEAR, first, second, first * second);
				}
			}
		}

		for (int i = 0; i < entries.size(); i++) {
			int first = entries.get(i);
			for (int j = i; j < entries.size(); j++) {
				int second = entries.get(j);
				for (int k = j; k < entries.size(); k++) {
					int third = entries.get(k);

					if (first + second + third == CURRENT_YEAR) {
						System.out.printf("%d + %d + %d = %d. %d * %d * %d = %d%n", first, second, third, CURRENT_YEAR, first, second, third, first * second * third);
					}
				}
			}
		}
	}
}
