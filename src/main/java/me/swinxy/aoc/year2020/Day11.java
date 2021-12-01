package me.swinxy.aoc.year2020;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day11 {

	private static final char OCCUPIED_CHAR = '#';
	private static final char UNOCCUPIED_CHAR = 'L';
	private static final char FLOOR_CHAR = '.';

	public static void main(String[] args) {
		InputStream stream = Day11.class.getClassLoader().getResourceAsStream("2020/day11.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		List<String> lines = new ArrayList<>();
		new BufferedReader(new InputStreamReader(stream)).lines().forEach(lines::add);

		int height = lines.size();
		int width = lines.get(0).length();

		char[][] layout = new char[height][width];
		for (int i = 0; i < lines.size(); i++) {
			layout[i] = lines.get(i).toCharArray();
		}
		boolean hasChanged;

		do {
			char[][] next = new char[height][width];

			hasChanged = false;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {

					char thisChar = layout[y][x];

					if (thisChar != OCCUPIED_CHAR && thisChar != UNOCCUPIED_CHAR) {
						next[y][x] = thisChar;
						continue;
					}

					int occupied = 0;

					for (Direction direction : Direction.values()) {
						if (rayCast(layout, width, height, x, y, 1, direction) == OCCUPIED_CHAR) {
							occupied++;
						}
					}

					if (thisChar == UNOCCUPIED_CHAR && occupied == 0) {
						next[y][x] = OCCUPIED_CHAR;
						hasChanged = true;
					} else if (thisChar == OCCUPIED_CHAR && occupied >= 4) {
						next[y][x] = UNOCCUPIED_CHAR;
						hasChanged = true;
					} else {
						next[y][x] = thisChar;
					}
				}
			}
			layout = next;
		} while (hasChanged);

		int occupiedSeats = 0;

		for (char[] chars : layout) {
			System.out.println(chars);
			for (char aChar : chars) {
				if (aChar == OCCUPIED_CHAR) {
					occupiedSeats++;
				}
			}
		}

		System.out.println(occupiedSeats);

		for (int i = 0; i < lines.size(); i++) {
			layout[i] = lines.get(i).toCharArray();
		}

		int maxDistance = Math.max(width, height);

		do {
			char[][] next = new char[height][width];

			hasChanged = false;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {

					char thisChar = layout[y][x];

					if (thisChar != OCCUPIED_CHAR && thisChar != UNOCCUPIED_CHAR) {
						next[y][x] = thisChar;
						continue;
					}

					int occupied = 0;

					for (Direction direction : Direction.values()) {
						if (rayCast(layout, width, height, x, y, maxDistance, direction) == OCCUPIED_CHAR) {
							occupied++;
						}
					}

					if (thisChar == UNOCCUPIED_CHAR && occupied == 0) {
						next[y][x] = OCCUPIED_CHAR;
						hasChanged = true;
					} else if (thisChar == OCCUPIED_CHAR && occupied >= 5) {
						next[y][x] = UNOCCUPIED_CHAR;
						hasChanged = true;
					} else {
						next[y][x] = thisChar;
					}
				}
			}
			layout = next;
		} while (hasChanged);

		occupiedSeats = 0;

		for (char[] line : layout) {
			System.out.println(line);
			for (char c : line) {
				if (c == OCCUPIED_CHAR) {
					occupiedSeats++;
				}
			}
		}

		System.out.println(occupiedSeats);
	}

	private static char rayCast(char[][] chars, int w, int h, int x, int y, int max, Direction directions) {
		for (int i = 0; i < max; i++) {
			x += directions.x;
			y += directions.y;

			// Bounds check
			if (x < 0 || x >= w || y < 0 || y >= h) {
				return FLOOR_CHAR;
			}

			if (chars[y][x] != FLOOR_CHAR) {
				return chars[y][x];
			}
		}

		return FLOOR_CHAR;
	}

	private enum Direction {

		NW(-1, -1),
		N(0, -1),
		NE(1, -1),
		W(-1, 0),
		E(1, 0),
		SW(-1, 1),
		S(0, 1),
		SE(1, 1);

		public final int x, y;

		Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
