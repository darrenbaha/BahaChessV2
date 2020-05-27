import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class rook extends piece {
	
	boolean castleing;
	
	public rook(char Color, space Space) {
		super("Rook", Space);
		this.color = Color;
		
		if (Color == 'W') {
			bg.setIcon(new ImageIcon(game.window.images.wrook));
		} else {
			bg.setIcon(new ImageIcon(game.window.images.brook));
		}
		
		this.linearThreat = true;
		this.castleing = true;
	}
	
	/*
	 * 
	 * TODO : Castling 
	 * 
	 */
	
	@Override
	public void movement(space moveTo, char x, int y) {
		
		//Move up or down
		if (this.currentX == x && this.currentY > 0 && this.currentY < 9 && this.checkLinearPath(x, y, 'U') == false) {
				if (moveTo.isOccupied() == true) { //is it occupied?
					if (moveTo.getOnSpace().getColor() != this.getColor() && !(moveTo.getOnSpace().getName().equals("King"))) {
						this.eatPiece(moveTo);
					} else {
						this.updatePosition();
					}
				} else {
					this.setSpace(moveTo);
				}
		//Move left or right		
		} else if (this.currentY == y && this.currentX > 64 && this.currentX < 73 && this.checkLinearPath(x,y,'L') == false) {
			if (moveTo.isOccupied() == true) { //is it occupied?
				if (moveTo.getOnSpace().getColor() != this.getColor() && !(moveTo.getOnSpace().getName().equals("King"))) {
					this.eatPiece(moveTo);
				} else {
					this.updatePosition();
				}
			} else {
				this.setSpace(moveTo);
			}
		} else {
			this.updatePosition();
		}	
	}
}