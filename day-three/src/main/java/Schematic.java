import java.util.ArrayList;
import java.util.List;

public class Schematic {

	private String line;
	private List<Integer> symbolsPositions = new ArrayList<>();

	public Schematic(String line) {
		this.line = line;

		for (int i = 0; i < line.length(); ++i) {
			if (!Character.isDigit(line.charAt(i)) && line.charAt(i) != '.') {
				symbolsPositions.add(i);
			}
		}
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public List<Integer> getSymbolsPositions() {
		return symbolsPositions;
	}

	public void setSymbolsPositions(List<Integer> symbolsPositions) {
		this.symbolsPositions = symbolsPositions;
	}
}
