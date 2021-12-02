package me.swinxy.aoc.year2021;

import me.swinxy.aoc.util.TXT;

import java.io.IOException;

public class Day2 {

	public static void main(String[] args) throws IOException {
		String[] input = TXT.load(2021, 2);

		int depth = 0;
		int distance = 0;

		for (String line : input) {
			String[] s = line.split(" ");
			int x = Integer.parseInt(s[1]);
			switch (s[0]) {
				case "forward" -> distance += x;
				case "down" -> depth += x;
				case "up" -> depth -= x;
				default -> throw new NullPointerException(s[0]);
			}
		}

		System.out.println(depth * distance);

		depth = 0;
		distance = 0;
		int aim = 0;

		for (String line : input) {
			String[] s = line.split(" ");
			int x = Integer.parseInt(s[1]);
			switch (s[0]) {
				case "forward" -> {
					distance += x;
					depth += aim * x;
				}
				case "down" -> aim += x;
				case "up" -> aim -= x;
				default -> throw new NullPointerException(s[0]);
			}
		}

		System.out.println(depth * distance);
	}
}
