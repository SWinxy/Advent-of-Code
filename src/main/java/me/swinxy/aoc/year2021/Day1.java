package me.swinxy.aoc.year2021;

import me.swinxy.aoc.util.TXT;

import java.io.IOException;

public class Day1 {
	public static void main(String[] args) throws IOException {
		int[] input = TXT.toInts(TXT.load(2021, 1));

		int x = input[0];
		int increases = 0;

		System.out.println(x + " (N/A - no previous measurement)");

		for (int i = 1; i < input.length; i++) {
			if (input[i] > x) {
				increases++;
				System.out.println(input[i] + " (increased)");
			} else if (input[i] == x) {
				System.out.println(input[i] + " (no change)");
			} else {
				System.out.println(input[i] + " (decreased)");
			}

			x = input[i];
		}

		System.out.println(increases);

		x = input[0] + input[1] + input[2];
		increases = 0;
		System.out.println("A: " + x + " (N/A - no previous sum)");
		char c = 'B';
		for (int i = 1; i < input.length - 2; i++) {
			int sum = input[i] + input[i + 1] + input[i + 2];

			if (sum > x) {
				increases++;
				System.out.println(c + ": " + sum + " (increased)");
			} else if (sum == x) {
				System.out.println(c + ": " + sum + " (no change)");
			} else {
				System.out.println(c + ": " + sum + " (decreased)");
			}

			x = sum;
			c++;
		}

		System.out.println(increases);
	}
}
