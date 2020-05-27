import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class piece {

	space onSpace;
	private int x,y; 
	String name, foundName;
	
	char currentX;
	char color;
	
	int dx = 0; 
	int dy = 0;
	int previous_x = 0;
	int previous_y = 0;
	int currentY;
	
	boolean linearThreat;
	boolean diagonalThreat;
	
	JLabel bg = new JLabel();
	
	public piece(String name, space Space) {
		this.name = name;
		this.onSpace = Space;
		Space.setOnSpace(this);
		
		currentX = this.getSpace().getName().charAt(0);
		currentY = Integer.parseInt(this.getSpace().getName().substring(1, 2));
		
		render();
		controller();
	}
	
	
	private void controller() {
		piece finder = this; 
		
		bg.addMouseListener(new MouseAdapter() {
			
			String space = "";
			
		    public void mousePressed(MouseEvent e) {
		    	
		    	if (player.turn == finder.getColor()) {
		    	
		    	dx = e.getX();
		    	dy = e.getY();
		    	previous_x = ( (JLabel) e.getSource()).getX();
		    	previous_y = ( (JLabel) e.getSource()).getY();
		    	
		    	}
		    			   
		    	//System.out.println("Piece Name : " + finder.getName());
		    }
		    
		    public void mouseReleased(MouseEvent e) { 
	
		    	//System.out.println("Space : " + game.findSpaceName(((JLabel) e.getSource()).getX(), ((JLabel) e.getSource()).getY()));
		    	
		    	space = game.findSpaceName(((JLabel) e.getSource()).getX(), ((JLabel) e.getSource()).getY());
		    		
		    		validateMovement(space);	
		    }
		});
		
		bg.addMouseMotionListener(new MouseAdapter( ) {
			
			public void mouseDragged(MouseEvent e) {
				
				if (player.turn == finder.getColor()) {	
					((JLabel)e.getSource()).setLocation(((JLabel)e.getSource()).getX() + e.getX() + dx - 100, ((JLabel)e.getSource()).getY() + e.getY() + dy - 70);
				}
			}
		});
		
	}
	
	private void validateMovement(String space2) {
		space moveToSpace = game.spaceMap.get(space2);
		
		//Are we already on the space? Does it have a color of our own piece? 
		if (space2.equals("Invalid") || moveToSpace.getOnSpace() != null && moveToSpace.getOnSpace().getColor() == this.getColor()) {
			updatePosition();
		} else {
			movement(moveToSpace,space2.charAt(0),Integer.parseInt(space2.substring(1,2)));
		}
		
	}
	
	public void movement(space moveTO, char x, int y) {
		//original piece class does nothing
		//children of this class will have their respective moves. 
	}
	
	public void setX(int X) {
		this.x = x; 
	}
	
	public String getX() {
		return Character.toString(this.currentX);
	}
	
	public void setY(int y) {
		this.x = x; 
	}
	
	public int getY() {
		return y;
	}
	
	public char getColor() {
		return this.color;
	}
	
	public space getSpace() {
		return this.onSpace; 
	}
	
	/*
	 * NOTE: This should only be used when its 100% certain that there is nothing on that space!
	 */
	
	public void setSpace(space Space) {
		
		if (this.isKingChecked(Space, this, this.color) == true) {
			
			this.updatePosition();
			
		} else {
				
		String temp = this.onSpace.getName();
		
		this.onSpace.setOnSpace(null); 
		this.onSpace = Space; 
		Space.setOnSpace(this); 
		updatePosition(); 
		
		currentX = this.getSpace().getName().charAt(0);
		currentY = Integer.parseInt(this.getSpace().getName().substring(1, 2));
		
		//System.out.println(this.getColor() + this.getName() + " moved from " + temp + " to " + Space.getName());
		
		//TODO : Play Sound
		
		player.changeTurn();
		ui.console.append(this.getColor() + this.getName() + " moved from " + temp + " to " + Space.getName() + "\n");
		}
	}
	
	public void eatPiece(space Space) {
		
		if (this.isKingChecked(Space, this, this.color) == true) {
			this.updatePosition();
		} else {
		
		piece destroy = Space.getOnSpace();
		
		destroy.getSpace().setOnSpace(this); 
		destroy.onSpace = null; 
		this.setSpace(Space); 
		destroy.bg.removeMouseListener(destroy.bg.getMouseListeners()[0]); //should remove mouse pressed...?
		destroy.bg.setVisible(false);
		
		//System.out.println(this.getColor() + this.getName() + " ate " + destroy.getColor() + destroy.getName());
		
		//TODO : Play Sound
		
		player.getTurn();
		ui.console.append(this.getColor() + this.getName() + " ate " + destroy.getColor() + destroy.getName() + "\n");
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void updatePosition() {
		bg.setLocation(this.getSpace().getX() - 18,this.getSpace().getY());	
	}
	
	public boolean validDiagonalPosition(char x, int y) {
		
		if (this.currentX == x || this.currentY == y) {
			return false; 
		}
		
		if ((this.currentX > 64 && this.currentX < 73) && (this.currentY > 0 && this.currentY < 9)) {
			return true;
		}
		
		return false;
	}
	
	private void render() {
		
		bg.setName(this.name);
		bg.setLocation(this.getSpace().getX() - 18,this.getSpace().getY());			
		bg.setSize(68,68);
		game.window.panel.add(bg);
		
	}
	
	protected boolean checkLinearPath(char x, int y, char direction) {
		
		int amountToCheck = 0;
		
		String id = String.valueOf(x);
		
		//check the up/down direction
		if (direction == 'U') {
			
			amountToCheck = Math.abs(this.currentY - y);	
			
			for (int i = 1; i < amountToCheck; i++) {
				
				if (y > this.currentY) { //we're moving up
					
					if (game.spaceMap.get(id + (this.currentY + i)).getOnSpace() != null) {
						//System.out.println("Found : " + game.spaceMap.get(id + (this.currentY + i)).getOnSpace().getName());
						return true;
					}
					
				} else { //we're moving down
					
					if (game.spaceMap.get(id + (this.currentY - i)).getOnSpace() != null) {
						return true;
					}
				}
			}
		}
		
		if (direction == 'L') { //Left and right
			amountToCheck = Math.abs(this.currentX - x);
			
			for (int i = 1; i < amountToCheck; i++) {
				
				if (x > this.currentX) { //we're right
					
					id = Character.toString(((char)(this.currentX + i)));
					
					if (game.spaceMap.get((id + this.currentY)).getOnSpace() != null) {
						//System.out.println("Found : " + game.spaceMap.get(id + (this.currentY + i)).getOnSpace().getName());
						return true;
					}
					
				} else { //we're moving left		
					
					id = Character.toString(((char) (this.currentX - i)));
					
					if (game.spaceMap.get(id + this.currentY).getOnSpace() != null) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	protected boolean checkDiagonalPath(char x, int y) {
		
		int amountToCheck = 0;
		String id = "";
		
		amountToCheck = Math.abs(this.currentY - y);
		
		for (int i = 1; i < amountToCheck; i++) {
			
			
			if (x > this.currentX) { //move right
				id = Character.toString(((char) (this.currentX + i)));
				
				if (y > this.currentY) { //right up
					
					if (game.spaceMap.get(id + (this.currentY + i)).getOnSpace() != null) {
						return true;
					}
					
				} else { //right down
					if (game.spaceMap.get(id + (this.currentY - i)).getOnSpace() != null) {
						return true;
					}
				}
				
			} else { //move left
				id = Character.toString(((char) (this.currentX - i)));
				
				if (y > this.currentY) { //left up
					
					if (game.spaceMap.get(id + (this.currentY + i)).getOnSpace() != null) {
						return true;
					}
					
				} else { //left down
					if (game.spaceMap.get(id + (this.currentY - i)).getOnSpace() != null) {
						return true;
					}
				}
			}
		}
		return false; 
	}
	
	public boolean isKingChecked(space moveTo, piece moving, char color) {
		
		char old_x = this.currentX;
		int old_y = this.currentY;
		
		if (moveTo.isOccupied() == false) {
			moveTo.setOnSpace(moving);
			moving.getSpace().setOnSpace(null);
			
			this.currentX = moveTo.getName().charAt(0);
			this.currentY = Integer.parseInt(moveTo.getName().substring(1, 2));
			
			if (this.color == 'W') {
				if (game.p1.getKing().calculateCheck() == false) {
					moving.getSpace().setOnSpace(moving);
					moveTo.setOnSpace(null);
					
					this.currentX = old_x;
					this.currentY = old_y;
					
					return false;
				} else {
					
					moving.getSpace().setOnSpace(moving);
					moveTo.setOnSpace(null);
					
					this.currentX = old_x;
					this.currentY = old_y;
					return true;
				}
			} else {
				if (game.p2.getKing().calculateCheck() == false) {
					
					moving.getSpace().setOnSpace(moving);
					moveTo.setOnSpace(null);
					this.currentX = old_x;
					this.currentY = old_y;
					return false;
				} else {
					
					moving.getSpace().setOnSpace(moving);
					moveTo.setOnSpace(null);
					
					this.currentX = old_x;
					this.currentY = old_y;
					return true;
				}
			}
		} else {
			piece oldPiece = moveTo.getOnSpace();
			moveTo.setOnSpace(moving);
			moving.getSpace().setOnSpace(null);
			this.currentX = moveTo.getName().charAt(0);
			this.currentY = Integer.parseInt(moveTo.getName().substring(1, 2));
			if (this.color == 'W') {
				if (game.p1.getKing().calculateCheck() == false) {
					
					moveTo.setOnSpace(oldPiece);
					moving.getSpace().setOnSpace(moving);
					this.currentX = old_x;
					this.currentY = old_y;
					return false;
				} else {
					
					moveTo.setOnSpace(oldPiece);
					moving.getSpace().setOnSpace(moving);
					this.currentX = old_x;
					this.currentY = old_y;			
					return true;
				}
			} else {
				if (game.p2.getKing().calculateCheck() == false) {
					
					moveTo.setOnSpace(oldPiece);
					moving.getSpace().setOnSpace(moving);
					this.currentX = old_x;
					this.currentY = old_y;
					return false;
				} else {
					
					moveTo.setOnSpace(oldPiece);
					moving.getSpace().setOnSpace(moving);
					this.currentX = old_x;
					this.currentY = old_y;
					return true;
				}
			}
		}
	}
}
