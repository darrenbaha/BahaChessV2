import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class space {

	private int x,y; 
	private char color;
	private piece onSpace; 
	String name; 
	JLabel bg = new JLabel();
	
	public space(int x, int y, char color, String name) {
		this.x = x;
		this.y = y;
		this.color = color; 
		this.name = name;
		
		render(x, y);
		
		game.spaceMap.put(name, this);
		game.spaces.add(this);
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public char getColor(char color) {
		return color;
	}
	
	public boolean isOccupied() {
		if (this.onSpace != null) {
			return true;
		}
		return false;
	}
	
	public piece getOnSpace() {
		return onSpace;
	}
	
	public void setOnSpace(piece piece) {
		this.onSpace = piece;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void render(int x, int y) {
		
		if (this.color == 'W') {
			bg.setIcon(new ImageIcon(game.window.images.white));
		} else {
			bg.setIcon(new ImageIcon(game.window.images.black));
		}		
		
		bg.setLocation(x, y);
		bg.setSize(64,64);
		game.window.frame.add(bg);
		
	}
	
}
