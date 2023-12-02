import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayTwoApp {
	private static final int MAX_RED = 12;
	private static final int MAX_GREEN = 13;
	private static final int MAX_BLUE = 14;
	public static void main(String[] args) {
		boolean partOne = Boolean.parseBoolean(args[0]);
		List<Game> games = new ArrayList<>();
		File entryFile = new File("day-two\\src\\main\\resources\\games.txt");

		try {
			Scanner reader = new Scanner(entryFile);
			while (reader.hasNextLine()) {
				games.add(new Game(reader.nextLine()));
			}

			int total = 0;
			if (partOne) {
				for (Game game : games) {
					if (game.getMaxRed() <= MAX_RED && game.getMaxGreen() <= MAX_GREEN && game.getMaxBlue() <= MAX_BLUE) {
						total += game.getId();
					}
				}

				System.out.println("The sum of the possible games IDs is " + total);
			} else {
				for (Game game : games) {
					total += game.getSetPower();
				}

				System.out.println("The sum of set powers is " + total);
			}


		} catch (FileNotFoundException e) {
			System.err.println("File not found !\n" + e.getMessage());
		}
	}
}
