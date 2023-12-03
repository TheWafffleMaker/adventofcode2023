import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class DayThreeApp {
	static List<Schematic> schematics = new ArrayList<>();
	static boolean partOne = true;
	static File entryFile = new File("day-three\\src\\main\\resources\\schematics.txt");
	public static void main(String[] args) {

		try {
			Scanner reader = new Scanner(entryFile);
			while (reader.hasNextLine()) {
				schematics.add(new Schematic(reader.nextLine()));
			}

			int total = 0;
			if (partOne) {
				for (int i = 0; i < schematics.size(); ++i) {
					total += computeSchematicSum(schematics.get(i), i);
				}
				System.out.println("The sum of the part numbers is " + total);
			} else {
				for (int i = 0; i < schematics.size(); ++i) {
					total += computeSchematicGears(schematics.get(i), i);
				}
				System.out.println("The sum of gears power is " + total);
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found !\n" + e.getMessage());
		}
	}

	private static int computeSchematicSum(Schematic schematic, int i) {
		int lineTotal = 0;
		int indexBeforeNumber;
		int indexAfterNumber;
		int significantInt;
		String schematicLine = schematic.getLine();
		for (int j = 0; j < schematicLine.length(); ++j) {
			//System.out.println("for : j = " + j);
			if (Character.isDigit(schematicLine.charAt(j))) {
				indexBeforeNumber = j-1;
				while (j < schematicLine.length()-1 && Character.isDigit(schematicLine.charAt(j+1))) {
					++j;
				}
				indexAfterNumber = j+1;
				significantInt = Integer.parseInt(schematicLine.substring(indexBeforeNumber + 1, indexAfterNumber));
				if ((indexBeforeNumber >= 0 && schematicLine.charAt(indexBeforeNumber) != '.') || (indexAfterNumber < schematicLine.length() && schematicLine.charAt(indexAfterNumber) != '.')) {
					//System.out.println("significantInt (1) : " + significantInt);
					lineTotal += significantInt;
				} else {
					if (i < schematics.size()-1) {
						String nextSchematicLine = schematics.get(i+1).getLine();
						String nextSignificantLine = nextSchematicLine.substring(Math.max(indexBeforeNumber, 0), Math.min(indexAfterNumber+1, nextSchematicLine.length()));
						 if (containsSymbol(nextSignificantLine)) {
							 //System.out.println("significantInt (2) : " + significantInt);
							 lineTotal += significantInt;
						 } else if (i > 0){
							 String previousSchematicLine = schematics.get(i-1).getLine();
							 String previousSignificantLine = previousSchematicLine.substring(Math.max(indexBeforeNumber, 0), Math.min(indexAfterNumber+1, previousSchematicLine.length()));
							 if (containsSymbol(previousSignificantLine)) {
								 //System.out.println("significantInt (3) : " + significantInt);
								 lineTotal += significantInt;
							 }
						 }
					} else if (i > 0){
						String previousSchematicLine = schematics.get(i-1).getLine();
						String previousSignificantLine = previousSchematicLine.substring(Math.max(indexBeforeNumber, 0), Math.min(indexAfterNumber+1, previousSchematicLine.length()));
						if (containsSymbol(previousSignificantLine)) {
							//System.out.println("significantInt (4) : " + significantInt);
							lineTotal += significantInt;
						}
					}
				}
			}
		}

		return lineTotal;
	}

	private static int computeSchematicGears(Schematic schematic, int i) {
		int lineTotal = 0;
		int indexBeforeNumber;
		int indexAfterNumber;
		int significantInt;
		String schematicLine = schematic.getLine();
		for (int j = 0; j < schematicLine.length(); ++j) {
			//System.out.println("for : j = " + j);
			if (Character.isDigit(schematicLine.charAt(j))) {
				indexBeforeNumber = j-1;
				while (j < schematicLine.length()-1 && Character.isDigit(schematicLine.charAt(j+1))) {
					++j;
				}
				indexAfterNumber = j+1;
				if ((indexBeforeNumber >= 0 && schematicLine.charAt(indexBeforeNumber) != '.') || (indexAfterNumber < schematicLine.length() && schematicLine.charAt(indexAfterNumber) != '.')) {
					significantInt = Integer.parseInt(schematicLine.substring(indexBeforeNumber+1, indexAfterNumber));
					//System.out.println("significantInt (1) : " + significantInt);
					lineTotal += significantInt;
				} else {
					if (i < schematics.size()-1) {
						String nextSchematicLine = schematics.get(i+1).getLine();
						String nextSignificantLine = nextSchematicLine.substring(Math.max(indexBeforeNumber, 0), Math.min(indexAfterNumber+1, nextSchematicLine.length()));
						if (containsSymbol(nextSignificantLine)) {
							significantInt = Integer.parseInt(schematicLine.substring(indexBeforeNumber+1, indexAfterNumber));
							//System.out.println("significantInt (2) : " + significantInt);
							lineTotal += significantInt;
						} else if (i > 0){
							String previousSchematicLine = schematics.get(i-1).getLine();
							String previousSignificantLine = previousSchematicLine.substring(Math.max(indexBeforeNumber, 0), Math.min(indexAfterNumber+1, previousSchematicLine.length()));
							if (containsSymbol(previousSignificantLine)) {
								significantInt = Integer.parseInt(schematicLine.substring(indexBeforeNumber+1, indexAfterNumber));
								//System.out.println("significantInt (3) : " + significantInt);
								lineTotal += significantInt;
							}
						}
					} else if (i > 0){
						String previousSchematicLine = schematics.get(i-1).getLine();
						String previousSignificantLine = previousSchematicLine.substring(Math.max(indexBeforeNumber, 0), Math.min(indexAfterNumber+1, previousSchematicLine.length()));
						if (containsSymbol(previousSignificantLine)) {
							significantInt = Integer.parseInt(schematicLine.substring(indexBeforeNumber+1, indexAfterNumber));
							//System.out.println("significantInt (4) : " + significantInt);
							lineTotal += significantInt;
						}
					}
				}
			}
		}

		return lineTotal;
	}

	private static boolean containsSymbol(String substring) {
		AtomicBoolean result = new AtomicBoolean(false);
		substring.chars().forEach(c -> {
			if (c != '.' && !Character.isDigit(c)) {
				result.set(true);
			}
		});
		return result.get();
	}
}
