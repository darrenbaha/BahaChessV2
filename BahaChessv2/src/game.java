import java.util.ArrayList;
import java.util.HashMap;

public class game {
	
	private final static int WIDTH = 1280;
	private final static int HEIGHT = 720;
	private final static String TITLE = "Baha Chess v2";
	static boolean DEBUG_MODE = false; //TODO : Implement this...
	
	protected static ui window = new ui(WIDTH,HEIGHT,TITLE);
	static ArrayList<space> spaces = new ArrayList<space>();
	static HashMap<String,space> spaceMap = new HashMap<String,space>();
	
	static player p1 = null;
	static player p2 = null;
	
	
	public static void main(String[] args) {
		
		//Create 8x8 Spaces and allocates them on the grid
		
		
		System.out.print("Creating Spaces...");
		
		createBoard();
		
		System.out.print("Done!");
		System.out.println();

		//Creates two sets of chess pieces
		
		System.out.print("Initializing Players...");
		
		//createChessPieces();
		createPlayers();
		
		System.out.print("Done!");
		System.out.println();
		
		//Finished loading
		
		window.showWindow();
		
		long endTime = System.currentTimeMillis();
		
		long runtime = endTime - imageHandler.startTime;
		
		System.out.println("Finished loading in "+ runtime + "ms");

	}
	
	private static void createBoard() {
		
		for(int i = 1; i < 9; i++) {
			
			if (i % 2 == 1) {
				space a = new space(65,560 - (i*65),'B',"A" + i);
				space b = new space(130,560 - (i*65),'W',"B" + i);
				space c = new space(195,560 - (i*65),'B',"C" + i);
				space d = new space(260,560 - (i*65),'W',"D" + i);
				space e = new space(325,560 - (i*65),'B',"E" + i);
				space f = new space(390,560 - (i*65),'W',"F" + i);
				space g = new space(455,560 - (i*65),'B',"G" + i);
				space h = new space(520,560 - (i*65),'W',"H" + i);
				
			} else {
				space a = new space(65,560 - (i*65),'W',"A" + i);
				space b = new space(130,560 - (i*65),'B',"B" + i);
				space c = new space(195,560 - (i*65),'W',"C" + i);
				space d = new space(260,560 - (i*65),'B',"D" + i);
				space e = new space(325,560 - (i*65),'W',"E" + i);
				space f = new space(390,560 - (i*65),'B',"F" + i);
				space g = new space(455,560 - (i*65),'W',"G" + i);
				space h = new space(520,560 - (i*65),'B',"H" + i);
			}	
		}
	}
	
	private static void createChessPieces() {
		
		//White Pieces
		rook wR1 = new rook('W', spaces.get(0));
		knight wK1 = new knight('W', spaces.get(1), false);
		bishop wB1 = new bishop('W', spaces.get(2));
		queen wQ = new queen('W', spaces.get(3));
		king wK = new king('W', spaces.get(4));
		bishop wB2 = new bishop('W', spaces.get(5));
		knight wK2 = new knight('W', spaces.get(6), true);
		rook wR2 = new rook('W', spaces.get(7));
		
		for (int i = 0; i < 8; i++) {
			pawn wP = new pawn('W', spaces.get(8 + i));
		}
		
		//Black Pieces
		rook bR1 = new rook('B', spaces.get(63));
		knight bK1 = new knight('B', spaces.get(62), false);
		bishop bB1 = new bishop('B', spaces.get(61));
		queen bQ = new queen('B', spaces.get(60));
		king bK = new king('B', spaces.get(59));
		bishop bB2 = new bishop('B', spaces.get(58));
		knight bK2 = new knight('B', spaces.get(57), true);
		rook bR2 = new rook('B', spaces.get(56));
		
		for (int i = 0; i < 8; i++) {
			pawn bP = new pawn('B', spaces.get(48 + i));
		}		
	}
	
	private static void createPlayers() {
		
		p1 = new player('W',1);
		p2 = new player('B',2);
		
	}
	
	/*
	 * 
	 * TODO : This is really hacky/dirty.Find a better way to this. This implementation is O(N) 
	 *        but I think there is a faster way to do this.
	 */
	
	public static String findSpaceName(int x, int y) {
		
		String space = "";
		char[] letter = {'A','B','C','D','E','F','G','H'};
		int offset_x = 20; //Pixel offset bc of Jabel, png etc.. really annoying
		int offset_y = 45;
		
		//Finds the Letter
		for (int i = 0; i < 8; i++) {
			
			try {
			if (x >= ((64 * i) + offset_x) && x <= (64 * (i+1)) + offset_x) {
				//System.out.println("Greater than : " + (64 * i) + " Less Than : " + (64 * (i+1)));
				//System.out.println("Index : " + i);
				space = space + String.valueOf((letter[i]));
				break;
			} 
			} catch (NullPointerException e) {
				//System.out.println("Invalid");
			}
			
		}
		
		//Finds the number
		for (int z = 0; z < 8; z++) {
			
			if (y <= ((560-z*64) - offset_y) && y >= (560 - ((z+ 1) *64)) - offset_y) {
				space = space + (z + 1);
			}
			
		}
		
		if (space.length() != 2) {
			return space = "Invalid";
		}
		
		return space; 
	}
	
}
