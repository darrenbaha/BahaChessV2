import javax.swing.ImageIcon;

public class knight extends piece{
	
	boolean diretion; //false = left, true = right
	
	public knight(char Color, space Space, boolean direction) {
		super("Knight", Space);
		this.color = Color;
		
		if (Color == 'W') {
			if (direction == false) {
				bg.setIcon(new ImageIcon(game.window.images.wknightL));
			} else {
				bg.setIcon(new ImageIcon(game.window.images.wknightR));
			}
		} else {
			if (direction == false) {
				bg.setIcon(new ImageIcon(game.window.images.bknightL));
			} else {
				bg.setIcon(new ImageIcon(game.window.images.bknightR));
			}
		}
		
	}
	
	@Override
	public void movement(space moveTo, char x, int y) {
		
		if ((this.currentX + 1 == x && (this.currentY + 2 == y || this.currentY - 2 == y)) || this.currentX - 1 == x && (this.currentY + 2 == y || this.currentY - 2 == y) || this.currentX + 2 == x && (this.currentY + 1 == y || this.currentY - 1 == y) || this.currentX - 2 == x && (this.currentY + 1 == y || this.currentY - 1 == y)) {
			
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
