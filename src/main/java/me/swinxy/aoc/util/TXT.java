package me.swinxy.aoc.util;

import java.io.IOException;
import java.io.InputStream;

public class TXT {

	public static String[] load(int year, int day) throws IOException {
		InputStream stream = TXT.class.getClassLoader().getResourceAsStream(year + "/day" + day + ".txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		return new String(stream.readAllBytes()).split("\n|\r\n");
	}

	public static int[] toInts(String[] s) {
		int[] out = new int[s.length];

		for (int i = 0; i < s.length; i++) {
			out[i] = Integer.parseInt(s[i]);
		}

		return out;
	}
}
