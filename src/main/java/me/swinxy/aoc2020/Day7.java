package me.swinxy.aoc2020;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Day7 {

	public static void main(String[] args) {
		InputStream stream = Day7.class.getClassLoader().getResourceAsStream("day7.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		List<String> rules = new ArrayList<>();
		new BufferedReader(new InputStreamReader(stream)).lines().forEach(rules::add);

		Map<String, Map<String, Integer>> containers = new HashMap<>();

		rules.forEach(rule -> {
			String[] split = rule.split("( bags contain )|( bag(s?)[.,]( ?))");
			String bag = split[0];
			Map<String, Integer> contents = new HashMap<>();

			for (int i = 1; i < split.length; i++) {
				if (!split[i].equals("no other")) {
					String key = split[i].replaceAll("[0-9]+ ", "");
					int value = Integer.parseInt(split[i].replaceAll("[a-zA-Z ]+", ""));
					contents.put(key, value);
				}
			}

			containers.put(bag, contents);
		});

		AtomicInteger count = new AtomicInteger();

		containers.forEach((bag, contents) -> {
			if (contains(containers, bag, "shiny gold")) {
				count.getAndIncrement();
			}
		});

		System.out.println(count);

		System.out.println(numBagsInside(containers, "shiny gold"));
	}

	private static boolean contains(Map<String, Map<String, Integer>> lookup, String bag, String searchingFor) {
		for (String b : lookup.get(bag).keySet()) {
			if (b.equals(searchingFor) || contains(lookup, b, searchingFor)) {
				return true;
			}
		}
		return false;
	}

	private static int numBagsInside(Map<String, Map<String, Integer>> lookup, String bag) {
		AtomicInteger count = new AtomicInteger();
		lookup.get(bag).forEach((b, i) -> count.getAndAdd(i * (1 + numBagsInside(lookup, b))));
		return count.get();
	}
}
