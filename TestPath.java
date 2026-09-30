
public class TestPath {
	
	public static void main(String[] args) {
		Map.TIME_DELAY = 250;
		boolean KEEP_WINDOW_OPEN = true;
		
		runPathTest("minemap1.txt");
		//Expected: 12 17 22 21 20 15 10 5 0 1 2 3 4 9 14 19 24
		
		//runPathTest("minemap2.txt");
		// Expected: 12 13 8 3 2
		
		//runPathTest("minemap3.txt");
		// Expected: No solution found
		

		if (!KEEP_WINDOW_OPEN) {
			System.exit(0);
		}
	}
	
	private static void runPathTest (String filename) {

		MineEscape search = new MineEscape(filename);
		String result = search.findEscapePath();
		System.out.println(result);
	}

}
