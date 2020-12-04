package me.swinxy.aoc2020;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Day4 {

	private static final String[] REQUIRED_FIELDS = new String[]{ "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };
	private static final String[] VALID_EYE_COLORS = new String[]{ "amb", "blu", "brn", "gry", "grn", "hzl", "oth" };
	private static final Map<String, ValidationMethod> VALIDATION = new HashMap<>();

	static {
		VALIDATION.put("byr", s -> {
			int year = Integer.parseInt(s);
			return year >= 1920 && year <= 2002;
		});
		VALIDATION.put("iyr", s -> {
			int year = Integer.parseInt(s);
			return year >= 2010 && year <= 2020;
		});
		VALIDATION.put("eyr", s -> {
			int year = Integer.parseInt(s);
			return year >= 2020 && year <= 2030;
		});
		VALIDATION.put("hgt", s -> {
			if (!s.matches("[0-9]+(cm|in)")) {
				return false;
			}
			int height = Integer.parseInt(s.replaceAll("(cm|in)", ""));
			if (s.contains("cm")) {
				return height >= 150 && height <= 193;
			}
			return height >= 59 && height <= 76;
		});
		VALIDATION.put("hcl", s -> s.matches("#[a-f0-9]{6}"));
		VALIDATION.put("ecl", s -> {
			for (String validHairColor : VALID_EYE_COLORS) {
				if (s.equals(validHairColor)) {
					return true;
				}
			}
			return false;
		});
		VALIDATION.put("pid", s -> s.matches("[0-9]{9}"));
	}

	public static void main(String[] args) throws IOException {
		InputStream stream = Day4.class.getClassLoader().getResourceAsStream("day4.txt");
		if (stream == null) {
			throw new NullPointerException();
		}

		String[] passports = new String(stream.readAllBytes()).split("\r\n\r\n");

		List<Map<String, String>> passportsFormatted = new ArrayList<>();

		for (String passport : passports) {
			Map<String, String> passportData = new HashMap<>();
			for (String pair : passport.replace("\r\n", " ").split(" ")) {
				String[] split = pair.split(":");
				passportData.put(split[0], split[1]);
			}
			passportsFormatted.add(passportData);
		}

		AtomicInteger validPassports = new AtomicInteger();

		passportsFormatted.forEach(passport -> {
			boolean isValid = true;
			for (String requiredField : REQUIRED_FIELDS) {
				if (!passport.containsKey(requiredField)) {
					isValid = false;
					break;
				}
			}
			if (isValid) {
				validPassports.getAndIncrement();
			}
		});

		System.out.println(validPassports);

		validPassports.set(0);

		passportsFormatted.forEach(passport -> {
			AtomicBoolean isValid = new AtomicBoolean(true);
			VALIDATION.forEach((s, validationMethod) -> {
				if (!passport.containsKey(s) || !validationMethod.isValid(passport.get(s))) {
					isValid.set(false);
				}
			});
			if (isValid.get()) {
				validPassports.getAndIncrement();
			}
		});

		System.out.println(validPassports);
	}

	@FunctionalInterface
	private interface ValidationMethod {

		boolean isValid(String s);
	}
}
