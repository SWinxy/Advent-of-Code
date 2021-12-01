package me.swinxy.aoc.year2020;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
	public static void main(String[] args) {
		InputStream stream = Day3.class.getClassLoader().getResourceAsStream("2020/day3.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		List<String> lines = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		reader.lines().forEach(lines::add);

		System.out.println(sumTrees(lines, 3, 1));

		System.out.println((long) sumTrees(lines, 1, 1) *
		sumTrees(lines, 3, 1) *
		sumTrees(lines, 5, 1) *
		sumTrees(lines, 7, 1) *
		sumTrees(lines, 1, 2));
	}

	public static int sumTrees(List<String> lines, int xSlope, int ySlope) {
		int width = lines.get(0).length();

		int hits = 0;

		for (int y = 0, x = 0; y < lines.size(); y += ySlope, x += xSlope) {
			if (lines.get(y).charAt(x % width) == '#') {
				hits++;
			}
		}

		return hits;
	}
}
