import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * App covering the day one problem of the Advent Of Code 2023 event
 * <p>
 * Attributes to specify if we want to use the file or the samples, and if we want to solve the entire problem or just the first part
 * </p>
 * @see <a href=https://adventofcode.com/2023/day/1>Advent Of Code 2023 - Day 1</a>
 * @author H. Bouvet
 */
public class App {
	public static void main(String[] args) {
		boolean useFile = false;
		boolean secondPart = false;

		try {
			List<String> values = new ArrayList<>();
			List<String> tempValues;
			File entryFile = new File("day-one\\src\\main\\resources\\calib_values.txt");

			Scanner reader = new Scanner(entryFile);
			if (useFile) {
				if (secondPart) {
					while (reader.hasNextLine()) {
						values.add(parseSpelledNumbers(reader.nextLine()));
					}
				} else {
					while (reader.hasNextLine()) {
						values.add(reader.nextLine());
					}
				}
			} else {
				if (secondPart) {
					tempValues = Arrays.asList("two1nine",
						"eightwothree",
						"abcone2threexyz",
						"xtwone3four",
						"4nineeightseven2",
						"zoneight234",
						"7pqrstsixteen");
					for (String value : tempValues) {
						values.add(parseSpelledNumbers(value));
					}
				} else {
					tempValues = Arrays.asList("1abc2",
							"pqr3stu8vwx",
							"a1b2c3d4e5f",
							"treb7uchet");
					values.addAll(tempValues);
				}
			}

			System.out.println("The calibration number you look for is " + computeCalibration(values));

		} catch (FileNotFoundException e) {
			System.err.println("Entry file not found !\n" + e.getMessage());
		}
	}

	/**
	 * Converts all letter-spelled digits of a string into their digit counterparts
	 * @param value the string to parse
	 * @return a string where all the spelled digit have been converted
	 */
	private static String parseSpelledNumbers(String value) {
		List<String> spelledDigits = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
		String modifiedValue = value;
		int minIndex = value.length();
		int maxIndex = 0;
		String minDigit = null;
		String maxDigit = null;

		Map<String, String> spelledDigitMapping = Stream.of(new Object[][] {
				{"one", "1"},
				{"two", "2"},
				{"three", "3"},
				{"four", "4"},
				{"five", "5"},
				{"six", "6"},
				{"seven", "7"},
				{"eight", "8"},
				{"nine", "9"}
		}).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));

		Integer firstDigitIndex = null;
		Integer lastDigitIndex = null;
		int tmpIndex;
		int tmpMaxIndex;

		for (int i = 0; i < value.length(); ++i) {
			if (Character.isDigit(value.charAt(i))) {
				if (Objects.isNull(firstDigitIndex)) {
					firstDigitIndex = i;
				}
				lastDigitIndex = i;
			}
		}

		for (String spelled : spelledDigits) {
			tmpIndex = value.indexOf(spelled);
			if (tmpIndex >= 0) {
				tmpMaxIndex = value.lastIndexOf(spelled);
				if (minIndex > tmpIndex && (Objects.isNull(firstDigitIndex) || tmpIndex < firstDigitIndex)) {
					minIndex = tmpIndex;
					minDigit = spelled;
				}
				if (maxIndex < tmpMaxIndex && (Objects.isNull(lastDigitIndex) || tmpMaxIndex > lastDigitIndex)) {
					maxIndex = tmpMaxIndex;
					maxDigit = spelled;
				}
			}
		}
		if (Objects.nonNull(minDigit)) {
			modifiedValue = modifiedValue.replaceFirst(minDigit, spelledDigitMapping.get(minDigit));
			maxIndex -= minDigit.length()-1;
		}
		if (Objects.nonNull(maxDigit)) {
			modifiedValue = modifiedValue.substring(0, maxIndex) + spelledDigitMapping.get(maxDigit) + modifiedValue.substring(maxIndex + maxDigit.length());
		}
		//System.out.println(value + " -> " + modifiedValue);
		return modifiedValue;
	}

	/**
	 * Computes the sum of all 2-digits integers formed by the first and last integer of each value
	 * @param values a list of strings where each entry may contain at least one digit
	 * @return an int summing up all the 2-digits numbers formed for each value
	 */
	private static int computeCalibration(List<String> values) {
		int total = 0;
		int determiningInt;
		Character firstDigit, lastDigit;

		for(String value : values) {
			firstDigit = null;
			lastDigit = null;
			for (int i = 0; i < value.length(); ++i) {
				if (Character.isDigit(value.charAt(i))) {
					if (Objects.isNull(firstDigit)) {
						firstDigit = value.charAt(i);
					}
					lastDigit = value.charAt(i);
				}
			}
			determiningInt = Integer.parseInt(firstDigit + String.valueOf(lastDigit));
			total += determiningInt;
			//System.out.println("Determining int : " +determiningInt);
		}

		return total;
	}
}
