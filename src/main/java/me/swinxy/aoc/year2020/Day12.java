package me.swinxy.aoc.year2020;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day12 {

	public static void main(String[] args) {
		InputStream stream = Day12.class.getClassLoader().getResourceAsStream("2020/day12.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		List<String> lines = new ArrayList<>();
		new BufferedReader(new InputStreamReader(stream)).lines().forEach(lines::add);

		int angle = 0;
		double x = 0, y = 0;

		for (String line : lines) {
			char instruction = line.charAt(0);
			int spaces = Integer.parseInt(line.substring(1));

			switch (instruction) {
				case 'N' -> y += spaces;
				case 'S' -> y -= spaces;
				case 'E' -> x += spaces;
				case 'W' -> x -= spaces;
				case 'L' -> angle += spaces;
				case 'R' -> angle -= spaces;
				case 'F' -> {
					x += Math.cos(Math.toRadians(angle)) * spaces;
					y += Math.sin(Math.toRadians(angle)) * spaces;
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

			switch (instruction) {
				case 'N' -> waypointY += spaces;
				case 'S' -> waypointY -= spaces;
				case 'E' -> waypointX += spaces;
				case 'W' -> waypointX -= spaces;
				case 'L' -> {
					double ang = Math.toRadians(spaces);
					float tX = (float) (Math.cos(ang) * waypointX - Math.sin(ang) * waypointY);
					float tY = (float) (Math.sin(ang) * waypointX + Math.cos(ang) * waypointY);
					waypointX = (int) tX;
					waypointY = (int) tY;
				}
				case 'R' -> {
					double ang = Math.toRadians(-spaces);
					float tX = (int) (Math.cos(ang) * waypointX - Math.sin(ang) * waypointY);
					float tY = (int) (Math.sin(ang) * waypointX + Math.cos(ang) * waypointY);
					waypointX = (int) tX;
					waypointY = (int) tY;
				}
				case 'F' -> {
					x += waypointX * spaces;
					y += waypointY * spaces;
				}
			}

			System.out.printf("%c%d: (%f, %f) (%d, %d)%n", instruction, spaces, x, y, waypointX, waypointY);
		}

		System.out.println(Math.abs(x) + Math.abs(y));
	}
}
