import javax.swing.ImageIcon;

public class bishop extends piece {

	public bishop(char Color, space Space) {
		super("Bishop", Space);
		this.color = Color;
		
		if (Color == 'W') {
			bg.setIcon(new ImageIcon(game.window.images.wbishop));
		} else {
			bg.setIcon(new ImageIcon(game.window.images.bbishop));
		}
		
		this.diagonalThreat = true;
	}
	
	@Override
	public void movement(space moveTo, char x, int y) {
		
		if (((Math.abs(this.currentX - x)) == Math.abs((this.currentY - y))) && this.checkDiagonalPath(x, y) == false) {
			
			if (moveTo.isOccupied() == true && moveTo.getOnSpace().getColor() != this.getColor() && !(moveTo.getOnSpace().getName().equals("King"))) {
				this.eatPiece(moveTo);
			} else {
				this.setSpace(moveTo);
			}
			
		} else {
			this.updatePosition();
		}
	}
}
