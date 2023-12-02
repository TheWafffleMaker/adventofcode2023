public class Draw {
	private int red, green, blue;

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
