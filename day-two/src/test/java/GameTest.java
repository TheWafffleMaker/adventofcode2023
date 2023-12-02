import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

	private static final List<Game> games = new ArrayList<>();
	private static final int MAX_RED = 12;
	private static final int MAX_GREEN = 13;
	private static final int MAX_BLUE = 14;

	@BeforeAll
	public static void initializeGames() {
		try {
			Scanner reader = new Scanner(new File("src\\test\\resources\\sample.txt"));
			while (reader.hasNextLine()) {
				games.add(new Game(reader.nextLine()));
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found !\n" + e.getMessage());
		}
	}

	@Test
	public void testGameId() {
		int totalIds = 0;
		for (Game game : games) {
			totalIds += game.getId();
		}
		assertEquals(15, totalIds);
	}

	@Test
	public void testMaxRed() {
		assertEquals(4, games.get(0).getMaxRed());
		assertEquals(1, games.get(1).getMaxRed());
		assertEquals(20, games.get(2).getMaxRed());
		assertEquals(14, games.get(3).getMaxRed());
		assertEquals(6, games.get(4).getMaxRed());
	}

	@Test
	public void testPossibleGames() {
		int totalIds = 0;
		for (Game game : games) {
			if (game.getMaxRed() <= MAX_RED && game.getMaxGreen() <= MAX_GREEN && game.getMaxBlue() <= MAX_BLUE){
				totalIds += game.getId();
			}
		}

		assertEquals(8, totalIds);
	}

	@Test
	public void testSetPower() {
		assertEquals(48, games.get(0).getSetPower());
		assertEquals(12, games.get(1).getSetPower());
		assertEquals(1560, games.get(2).getSetPower());
		assertEquals(630, games.get(3).getSetPower());
		assertEquals(36, games.get(4).getSetPower());
	}
}
