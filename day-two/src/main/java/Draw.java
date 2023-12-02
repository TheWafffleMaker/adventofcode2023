/**
 * The class representing a single game draw (ie. "2 green, 3 red, 5 blue")
 */
public class Draw {
	private int red, green, blue;

	/**
	 * Builds a draw object from a game part
	 * @param stringEntry a string containing the count of each colour cube
	 * @throws IllegalArgumentException in case a colour is not recognized
	 */
	public Draw(String stringEntry) {
		String[] eachDraw = stringEntry.split(", ");
		for (String each : eachDraw) {
			String[] splittedDraw = each.split(" ");
			int count = Integer.parseInt(splittedDraw[0]);
			String colour = splittedDraw[1];
			switch (colour) {
				case "red":
					this.red = count;
					break;
				case "green":
					this.green = count;
					break;
				case "blue":
					this.blue = count;
					break;
				default:
					throw new IllegalArgumentException("Unrecognized colour. The draw entry must be formed like '1 red, 2 green, 3 blue'");
			}
		}
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}
}
