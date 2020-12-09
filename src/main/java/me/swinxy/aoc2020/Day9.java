package me.swinxy.aoc2020;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day9 {

	public static void main(String[] args) {
		InputStream stream = Day9.class.getClassLoader().getResourceAsStream("day9.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		List<String> lines = new ArrayList<>();
		new BufferedReader(new InputStreamReader(stream)).lines().forEach(lines::add);

		long[] numbers = new long[lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			numbers[i] = Long.parseLong(lines.get(i));
		}

		long error = 0;

		for (int i = 25; i < numbers.length; i++) {
			if (!hasSum(numbers, i - 25, i - 1, numbers[i])) {
				error = numbers[i];
				break;
			}
		}

		System.out.println(error);

		for (int i = 0; i < numbers.length; i++) {
			long sum = 0;
			long smallest = numbers[i];
			long largest = numbers[i];
			for (int j = i; j < numbers.length && sum < error; j++) {
				long number = numbers[j];
				sum += number;
				if (number < smallest) {
					smallest = number;
				}
				if (number > largest) {
					largest = number;
				}
				if (sum == error) {
					System.out.println(smallest + largest);
					break;
				}
			}
		}
	}

	private static boolean hasSum(long[] array, int start, int end, long sum) {
		for (int i = start; i < array.length && i <= end; i++) {
			for (int j = i + 1; j < array.length && j <= end; j++) {
				if (array[i] + array[j] == sum) {
					return true;
				}
			}
		}
		return false;
	}
}
