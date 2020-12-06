package me.swinxy.aoc2020;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Day5 {

	public static void main(String[] args) {

		InputStream stream = Day5.class.getClassLoader().getResourceAsStream("day5.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		List<String> lines = new ArrayList<>();
		new BufferedReader(new InputStreamReader(stream)).lines().forEach(lines::add);

		AtomicInteger highestId = new AtomicInteger();
		Set<Integer> seats = new HashSet<>();

		lines.forEach(line -> {
			int row = stringToBinary(line.substring(0, 7), 'B');
			int column = stringToBinary(line.substring(7, 10), 'R');
			int seatID = row * 8 + column;

			seats.add(seatID);

			if (seatID > highestId.get()) {
				highestId.set(seatID);
			}
		});

		Set<Integer> missingSeats = new HashSet<>();

		for (int i = highestId.get() - lines.size(); i < highestId.get(); i++) {
			if (!seats.contains(i)) {
				missingSeats.add(i);
			}
		}

		System.out.println(highestId);
		System.out.println(missingSeats);
	}

	private static byte stringToBinary(String seat, char highBit) {
		byte out = 0;

		for (int i = 0; i < seat.length(); i++) {
			out <<= 1;
			out |= seat.charAt(i) == highBit ? 1 : 0;
		}

		return out;
	}
}
