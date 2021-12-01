package me.swinxy.aoc.year2020;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 {
	public static void main(String[] args) {
		InputStream stream = Day10.class.getClassLoader().getResourceAsStream("2020/day10.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		List<String> lines = new ArrayList<>();
		new BufferedReader(new InputStreamReader(stream)).lines().forEach(lines::add);

		int[] adapterRatings = new int[lines.size() + 2];

		for (int i = 0; i < lines.size(); i++) {
			adapterRatings[i] = Integer.parseInt(lines.get(i));
		}
		adapterRatings[adapterRatings.length - 1] = Integer.MAX_VALUE;

		Arrays.sort(adapterRatings);

		adapterRatings[adapterRatings.length - 1] = adapterRatings[adapterRatings.length - 2] + 3;

		int oneJoltDifferences = 0;
		int twoJoltDifferences = 0;
		int threeJoltDifferences = 0;

		for (int i = 0; i < adapterRatings.length - 1; i++) {
			int difference = adapterRatings[i + 1] - adapterRatings[i];
			switch (difference) {
				case 1 -> oneJoltDifferences++;
				case 2 -> twoJoltDifferences++;
				case 3 -> threeJoltDifferences++;
			}
		}

		System.out.println(oneJoltDifferences * threeJoltDifferences);

		long[] permutations = new long[adapterRatings.length];
		permutations[permutations.length - 1] = 1;

		for (int i = adapterRatings.length - 2; i >= 0; i--) {
			for (int j = i + 1; j < adapterRatings.length && adapterRatings[j] - adapterRatings[i] <= 3; j++) {
				permutations[i] += permutations[j];
			}
		}

		System.out.println(permutations[0]);
	}
}
