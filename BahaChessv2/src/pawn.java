import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class pawn extends piece {
	
	public pawn(char Color, space Space) {
		super("Pond", Space);
		this.color = Color;
		
		if (Color == 'W') {
			bg.setIcon(new ImageIcon(game.window.images.wpawn));
		} else {
			bg.setIcon(new ImageIcon(game.window.images.bpawn));
		}
		
		this.diagonalThreat = true;
	
	}
	
	/*
	 * TODO: Not efficient. Doubling the lines of code because of a char
	 * Future : Make this better.
	 * Problem : Black moves "negative" for pawns but white moves "positive" 
	 * TODO : En Passant
	 */
	
	@Override
	public void movement(space moveTo, char x, int y) {
		
		if (this.color == 'W') { //White Pieces

			if (currentX == x && y == (this.currentY + 1) && !(moveTo.isOccupied())) { //Move Forward
				this.setSpace(moveTo);	
			} else if (currentX == x && y == 4 && !(moveTo.isOccupied())) { //Move Double		
				this.setSpace(moveTo);	
			} else if (((x == currentX + 1) || (x == currentX - 1) ) && (y == currentY + 1) && moveTo.isOccupied() && moveTo.getOnSpace().color == 'B' && !(moveTo.getOnSpace().getName().equals("King"))) { //Eat Diagonal
				this.eatPiece(moveTo);	
			} else {
				this.updatePosition();
			}
		}
		
		if (this.color == 'B') { //Black Pieces
			
			if (currentX == x && y == (this.currentY - 1) && !(moveTo.isOccupied())) {
				this.setSpace(moveTo);
			} else if (currentX == x && y == 5 && !(moveTo.isOccupied())) {
				this.setSpace(moveTo);
			} else if (((x == currentX + 1) || (x == currentX - 1) ) && (y == currentY - 1) && moveTo.isOccupied() && moveTo.getOnSpace().color == 'W' && !(moveTo.getOnSpace().getName().equals("King"))) {	
				this.eatPiece(moveTo);
			} else {
				this.updatePosition();
			}			
		}
		
	}
}
