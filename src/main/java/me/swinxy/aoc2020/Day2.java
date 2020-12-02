package me.swinxy.aoc2020;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {

	public static void main(String[] args) {
		InputStream stream = Day1.class.getClassLoader().getResourceAsStream("day2.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		AtomicInteger matches = new AtomicInteger();
		List<String> lines = new ArrayList<>();
		new BufferedReader(new InputStreamReader(stream)).lines().forEach(lines::add);

		lines.forEach(s -> {
			int min, max;
			char letter;
			String password;

			// Gets min
			int start = 0;
			int end = 0;
			while (s.charAt(end) != '-') {
				end++;
			}
			min = Integer.parseInt(s.substring(start, end));
			end++;
			start = end;

			// Gets max
			while (s.charAt(end) != ' ') {
				end++;
			}
			max = Integer.parseInt(s.substring(start, end));

			// Gets character and password
			letter = s.charAt(end + 1);
			password = s.substring(end + 4);


			int numChars = 0;
			for (char c : password.toCharArray()) {
				if (c == letter) {
					numChars++;
				}
			}

			// Checks for conformity
			if (numChars >= min && numChars <= max) {
				matches.getAndIncrement();
			}
		});

		System.out.println(matches.get() + " matches.");

		matches.set(0);
		lines.forEach(s -> {
			int pos1, pos2;
			char letter;
			String password;

			// Gets pos1
			int start = 0;
			int end = 0;
			while (s.charAt(end) != '-') {
				end++;
			}
			pos1 = Integer.parseInt(s.substring(start, end));
			end++;
			start = end;

			// Gets pos2
			while (s.charAt(end) != ' ') {
				end++;
			}
			pos2 = Integer.parseInt(s.substring(start, end));

			// Gets character and password
			letter = s.charAt(end + 1);
			password = s.substring(end + 4);

			if (password.charAt(pos1 - 1) == letter ^ password.charAt(pos2 - 1) == letter) {
				matches.getAndIncrement();
			}
		});

		System.out.println(matches.get() + " matches.");
	}
}
