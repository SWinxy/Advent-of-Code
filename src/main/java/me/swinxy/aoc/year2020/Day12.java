package me.swinxy.aoc.year2020;

import me.swinxy.aoc.util.TXT;

import java.io.IOException;

public class Day12 {

	public static void main(String[] args) throws IOException {
		String[] lines = TXT.load(2020, 12);

		int angle = 90;
		int x = 0, y = 0;

		for (String line : lines) {
			char instruction = line.charAt(0);
			int spaces = Integer.parseInt(line.substring(1));

			switch (instruction) {
				case 'N' -> y += spaces;
				case 'S' -> y -= spaces;
				case 'E' -> x += spaces;
				case 'W' -> x -= spaces;
				case 'L' -> angle -= spaces;
				case 'R' -> angle += spaces;
				case 'F' -> {
					angle %= 360;
					if (angle < 0) {
						angle += 360;
					}

					if (angle == 0) { // North
						y += spaces;
					} else if (angle == 90) { // East
						x += spaces;
					} else if (angle == 180) { // South
						y -= spaces;
					} else if (angle == 270) { // West
						x -= spaces;
					} else {
						throw new NullPointerException("Angle was " + angle);
					}
				}
			}
		}

		System.out.println(Math.abs(x) + Math.abs(y));

		x = 0;
		y = 0;

		// Waypoint is relative
		int waypointX = 10;
		int waypointY = 1;

		for (String line : lines) {
			char instruction = line.charAt(0);
			int spaces = Integer.parseInt(line.substring(1));

			if (instruction == 'L' && spaces == 90 || instruction == 'R' && spaces == 270) {
				final int wx = waypointX;
				final int wy = waypointY;
				waypointY = wx;
				waypointX = -wy;
			} else if ((instruction == 'L' || instruction == 'R') && spaces == 180) {
				waypointX *= -1;
				waypointY *= -1;
			} else if (instruction == 'L' && spaces == 270 || instruction == 'R' && spaces == 90) {
				final int wx = waypointX;
				final int wy = waypointY;
				waypointY = -wx;
				waypointX = wy;
			}
			switch (instruction) {
				case 'N' -> waypointY += spaces;
				case 'S' -> waypointY -= spaces;
				case 'E' -> waypointX += spaces;
				case 'W' -> waypointX -= spaces;
				case 'F' -> {
					x += waypointX * spaces;
					y += waypointY * spaces;
				}
			}
		}

		System.out.println(Math.abs(x) + Math.abs(y));
	}
}
