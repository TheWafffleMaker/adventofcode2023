import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a game entry with ID and the max number of each colour cube
 */
public class Game {
	private final int id;
	private int maxRed = 0;
	private int maxGreen = 0;
	private int maxBlue = 0;

	/**
	 * Builds a game from a string entry
	 * @param stringEntry the string containing the game id and the draws separated by ';'
	 */
	public Game(String stringEntry) {
		this.id = Integer.parseInt(stringEntry.substring(5, stringEntry.indexOf(":")));
		String remainingString = stringEntry.substring(stringEntry.indexOf(":") + 2);
		List<Draw> draws = new ArrayList<>();
		if (stringEntry.contains(";")) {
			String[] drawsStrings = remainingString.split("; ");

			for (String drawString : drawsStrings) {
				draws.add(new Draw(drawString));
			}
		} else {
			draws.add(new Draw(remainingString));
		}
		for (Draw draw : draws) {
			maxRed = Math.max(maxRed, draw.getRed());
			maxGreen = Math.max(maxGreen, draw.getGreen());
			maxBlue = Math.max(maxBlue, draw.getBlue());
		}

	}

	/**
	 * Computes the "power" of a set
	 * @return an int resulting of the multiplication of each max number of colour
	 */
	public int getSetPower() {
		return maxRed * maxGreen * maxBlue;
	}

	public int getId() {
		return id;
	}

	public int getMaxRed() {
		return maxRed;
	}

	public int getMaxGreen() {
		return maxGreen;
	}

	public int getMaxBlue() {
		return maxBlue;
	}
}
