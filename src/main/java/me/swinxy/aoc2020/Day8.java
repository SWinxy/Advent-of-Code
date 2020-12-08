package me.swinxy.aoc2020;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day8 {
	public static void main(String[] args) {
		InputStream stream = Day8.class.getClassLoader().getResourceAsStream("day8.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		List<String> instructions = new ArrayList<>();
		new BufferedReader(new InputStreamReader(stream)).lines().forEach(instructions::add);

		int accum = 0;
		int currentLine = 0;
		Set<Integer> usedLines = new HashSet<>();

		while (!usedLines.contains(currentLine)) {
			String[] split = instructions.get(currentLine).split(" ");
			String instruction = split[0];
			int argument = Integer.parseInt(split[1]);

			usedLines.add(currentLine);

			switch (instruction) {
				case "jmp": {
					currentLine += argument;
					break;
				}
				case "acc":
					accum += argument;
				default:
					currentLine++;
			}
		}

		System.out.println(accum);

		boolean found = false;
		Set<Integer> checkedLines = new HashSet<>();

		while (!found) {
			accum = 0;
			currentLine = 0;
			boolean lineChanged = false;
			usedLines.clear();

			while (!usedLines.contains(currentLine)) {

				String[] split = instructions.get(currentLine).split(" ");
				String instruction = split[0];
				int argument = Integer.parseInt(split[1]);

				usedLines.add(currentLine);

				if (!lineChanged && !checkedLines.contains(currentLine)) {
					if (instruction.equals("jmp")) {
						instruction = "nop";
						lineChanged = true;
						checkedLines.add(currentLine);
					} else if (instruction.equals("nop")) {
						instruction = "jmp";
						lineChanged = true;
						checkedLines.add(currentLine);
					}
				}

				switch (instruction) {
					case "jmp": {
						currentLine += argument;
						break;
					}
					case "acc":
						accum += argument;
					default:
						currentLine++;
				}

				// Checks if we've escaped
				if (currentLine >= instructions.size()) {
					found = true;
					break;
				}
			}
		}

		System.out.println(accum);
	}
}
