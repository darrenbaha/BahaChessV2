import javax.swing.ImageIcon;

public class queen extends piece{
	
	public queen(char Color, space Space) {
		super("Queen", Space);
		this.color = Color;
		
		if (Color == 'W') {
			bg.setIcon(new ImageIcon(game.window.images.wqueen));
		} else {
			bg.setIcon(new ImageIcon(game.window.images.bqueen));
		}
		
		this.diagonalThreat = true;
		this.linearThreat = true;
	}
	
	@Override
	public void movement(space moveTo, char x, int y) {
		
		//Diagonal
		if (((Math.abs(this.currentX - x)) == Math.abs((this.currentY - y))) && this.checkDiagonalPath(x, y) == false) {
			
			if (moveTo.isOccupied() == true && moveTo.getOnSpace().getColor() != this.getColor() && !(moveTo.getOnSpace().getName().equals("King"))) {
				this.eatPiece(moveTo);
			} else {
				this.setSpace(moveTo);
			}
		//Up or Down	
		} else if (this.currentX == x && this.currentY > 0 && this.currentY < 9 && this.checkLinearPath(x, y, 'U') == false) {
			if (moveTo.isOccupied() == true) { //is it occupied?
				if (moveTo.getOnSpace().getColor() != this.getColor() && !(moveTo.getOnSpace().getName().equals("King"))) {
					this.eatPiece(moveTo);
				} else {
					this.updatePosition();
				}
			} else {
				this.setSpace(moveTo);
			}
		//Left or Right
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
