
public class player {
	
	char color; 
	int id;
	static char turn = 'W'; //White always starts.
	boolean canCastle; 
	boolean isInChecked; 
	
	king wK = null;
	king bK = null;
	
	public player(char color, int id) {
	
		this.color = color;
		this.id = id;
		
		generatePieces();
		
	}
	
	private void generatePieces() {
		
		if (this.color == 'W') {
			
			rook wR1 = new rook('W', game.spaces.get(0));
			knight wK1 = new knight('W', game.spaces.get(1), false);
			bishop wB1 = new bishop('W', game.spaces.get(2));
			queen wQ = new queen('W', game.spaces.get(3));
			wK = new king('W', game.spaces.get(4));
			bishop wB2 = new bishop('W', game.spaces.get(5));
			knight wK2 = new knight('W', game.spaces.get(6), true);
			rook wR2 = new rook('W', game.spaces.get(7));
			
			for (int i = 0; i < 8; i++) {
				pawn wP = new pawn('W', game.spaces.get(8 + i));
			}
			
		} else {
			
			rook bR1 = new rook('B', game.spaces.get(63));
			knight bK1 = new knight('B', game.spaces.get(62), false);
			bishop bB1 = new bishop('B', game.spaces.get(61));
			queen bQ = new queen('B', game.spaces.get(60));
			bK = new king('B', game.spaces.get(59));
			bishop bB2 = new bishop('B', game.spaces.get(58));
			knight bK2 = new knight('B', game.spaces.get(57), true);
			rook bR2 = new rook('B', game.spaces.get(56));
			
			for (int i = 0; i < 8; i++) {
				pawn bP = new pawn('B', game.spaces.get(48 + i));
			}		
		}
	}
	
	public static void setTurn(char turn) {
		player.turn = turn;
	}
	
	public static char getTurn() {
		return player.turn;
	}
	
	public static void changeTurn() {
		if (player.turn == 'W') {
			player.turn = 'B';
		} else {
			player.turn = 'W';
		}
	}
	
	public boolean getChecked() {
		return isInChecked;
	}
	
	public void setChecked(boolean tf) {
		this.isInChecked = tf; 
	}
	
	public king getKing() {
		if (this.color == 'W') {
			return wK;
		}
		return bK;
	}
	
}
