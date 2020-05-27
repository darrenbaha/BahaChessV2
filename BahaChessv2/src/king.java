import javax.swing.ImageIcon;

public class king extends piece {

	public king(char Color, space Space) {
		super("King", Space);
		this.color = Color;
		
		if (Color == 'W') {
			bg.setIcon(new ImageIcon(game.window.images.wking));
		} else {
			bg.setIcon(new ImageIcon(game.window.images.bking));
		}
		
		this.diagonalThreat = true;
		this.linearThreat = true;
	}
	
	/*
	 * 
	 * TODO : Castling 
	 * 
	 */
	
	@Override
	public void movement(space moveTo, char x, int y) {
		if ((this.currentX + 1 == x || this.currentX == x && (this.currentY + 1 == y || this.currentY == y || this.currentY - 1 == y)) || (this.currentX - 1 == x || this.currentX == x && (this.currentY + 1 == y || this.currentY == y || this.currentY - 1 == y)))  {
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
	
	/*
	 * 
	 * TODO : Break this method down because it has repeated functions!
	 * 
	 */
	
	public boolean calculateCheck() {
		
		String currentXmod = "";
		space pointer = null;
		String s, s2 = "";	
		
		boolean[] direction = {false,false,false,false,false,false,false,false};
		
		//Scan the 8 different directions
		for (int i = 1; i < 9; i++) {
			
			//Left
			if ((this.currentX - i) > 64 && direction[0] == false) {
				
				currentXmod = Character.toString((char) (this.currentX - i));
				
				try {
					pointer = game.spaceMap.get(currentXmod + this.currentY);
					if (pointer.getOnSpace().getColor() != this.getColor() && pointer.getOnSpace().linearThreat == true) {
						System.out.println("Left Found : " + pointer.getOnSpace().getName());
						return true; 
					} else {
						direction[0] = true;
					}
				} catch (NullPointerException e) {
					//Do nothing
				}
			}
			
			//right
			
			if ((this.currentX + i) < 73 && direction[1] == false) {
				currentXmod = Character.toString((char) (this.currentX + i));
				
				try {
					
					pointer = game.spaceMap.get(currentXmod + this.currentY);
					
					if (pointer.getOnSpace().getColor() != this.getColor() && pointer.getOnSpace().linearThreat == true) {
						System.out.println("Right Found : " + pointer.getOnSpace().getName());
						return true; 
					} else {
						direction[1] = true;
					}
					
				} catch (NullPointerException e) {
					
				}
			}
			
			//down
			
			if ((this.currentY - i) > 0 && direction[2] == false) {
				
				try {
					
					pointer = game.spaceMap.get(this.getX() + (this.currentY - i));
					
					if (pointer.getOnSpace().getColor() != this.getColor() && pointer.getOnSpace().linearThreat == true) {
						System.out.println("Down Found : " + pointer.getOnSpace().getName());
						return true; 
					} else {
						direction[2] = true;
					}	
					
				} catch (NullPointerException e) {
					
				}
			}			
			
			
			//up
			
			if ((this.currentY + i) < 9 && direction[3] == false) {
				
				try {
					
					pointer = game.spaceMap.get(this.getX() + (this.currentY + i));
					
					if (pointer.getOnSpace().getColor() != this.getColor() && pointer.getOnSpace().linearThreat == true) {
						System.out.println("Up Found : " + pointer.getOnSpace().getName());
						return true; 
					} else {
						direction[3] = true;
					}	
					
				} catch (NullPointerException e) {
					
				}
			}
			
			//up right
			
			if ((this.currentY + i) < 9 && (this.currentX + i) < 73 && direction[4] == false) {
				
				currentXmod = Character.toString((char) (this.currentX + i));
				
				try {
					
					pointer = game.spaceMap.get(currentXmod + (this.currentY + i));
					
					if (pointer.getOnSpace().getColor() != this.getColor() && pointer.getOnSpace().diagonalThreat == true) {
						if (pointer.getOnSpace().getName().equals("Pond")) {
							if (calculateDistance(this.currentX, pointer.getOnSpace().currentX) == 1 && this.color == 'W') {
								return true;
							} else {
								
							}
						} else {
							System.out.println("UR Found : " + pointer.getOnSpace().getName());
							return true; 	
						}
					} else {
						direction[4] = true;
					}	
					
				} catch (NullPointerException e) {
					
				}
			}
			
			//up left
			
			if ((this.currentY + i) < 9 && (this.currentX - i) > 64 && direction[5] == false) {
				
				currentXmod = Character.toString((char) (this.currentX - i));
				
				try {
					
					pointer = game.spaceMap.get(currentXmod + (this.currentY + i));
					
					if (pointer.getOnSpace().getColor() != this.getColor() && pointer.getOnSpace().diagonalThreat == true) {
						if (pointer.getOnSpace().getName().equals("Pond")) {
							if (calculateDistance(this.currentX, pointer.getOnSpace().currentX) == 1 && this.color == 'W') {
								return true;
							} else {
								
							}
						} else {
							System.out.println("UL Found : " + pointer.getOnSpace().getName());
							return true; 	
						}
					} else {
						direction[5] = true;
					}	
					
				} catch (NullPointerException e) {
					
				}
			}
			
			//down right
			
			if ((this.currentY - i) > 0 && (this.currentX + i) < 73 && direction[6] == false) {
				
				currentXmod = Character.toString((char) (this.currentX + i));
				
				try {
					
					pointer = game.spaceMap.get(currentXmod + (this.currentY - i));
					
					if (pointer.getOnSpace().getColor() != this.getColor() && pointer.getOnSpace().diagonalThreat == true) {
						if (pointer.getOnSpace().getName().equals("Pond")) {
							if (calculateDistance(this.currentX, pointer.getOnSpace().currentX) == 1 && this.color == 'B') {
								return true;
							} else {
								
							}
						} else {
							System.out.println("DR Found : " + pointer.getOnSpace().getName());
							return true; 	
						}
					} else {
						direction[6] = true;
					}	
					
				} catch (NullPointerException e) {
					
				}
			}
			
			//down left
			
			if ((this.currentY - i) > 0 && (this.currentX - i) > 64 && direction[7] == false) {
				
				currentXmod = Character.toString((char) (this.currentX - i));
				
				try {
					
					pointer = game.spaceMap.get(currentXmod + (this.currentY - i));
					
					if (pointer.getOnSpace().getColor() != this.getColor() && pointer.getOnSpace().diagonalThreat == true && direction[7] == false) {
						if (pointer.getOnSpace().getName().equals("Pond")) {
							if (calculateDistance(this.currentX, pointer.getOnSpace().currentX) == 1 && this.color == 'B') {
								return true;
							} else {
								
							}
						} else {
							System.out.println("DL Found : " + pointer.getOnSpace().getName());
							return true; 	
						}
					} else {
						direction[7] = true;
					}	
					
				} catch (NullPointerException e) {
					
				}
			}
			
			if (direction[0] == true && direction[1] == true && direction[2] == true && direction[3] == true && direction[4] == true && direction[5] == true && direction[6] == true && direction[7] == true) {
				break;
			}
			
		}
		
		//Check for knights
		
		for (int i = 1; i < 5; i++) {
			
			s = "";
			s2 = "";
			
			if (i % 2 == 0) {
				s = s + Character.toString((char) (this.currentX + 1));
				s2 = s2 + Character.toString((char) (this.currentX + 2));
			} else {
				s = s + Character.toString((char) (this.currentX - 1));
				s2 = s2 + Character.toString((char) (this.currentX - 2));
			}
			
			if (i == 1 || i == 4) {
				s = s + (this.currentY + 2);
				s2 = s2 + (this.currentY + 1);
			} else {
				s = s + (this.currentY - 2);
				s2 = s2 + (this.currentY - 1);
			}
			
			try {
				pointer = game.spaceMap.get(s);
				if (pointer.getOnSpace().color != this.color && pointer.getOnSpace().getName().equals("Knight")) {
					return true;
				}
			} catch (NullPointerException e) {
				
			}
			
			try {
				pointer = game.spaceMap.get(s2);
				if (pointer.getOnSpace().color != this.color && pointer.getOnSpace().equals("Knight")) {
					return true;
				}
			} catch (NullPointerException e) {
				
			}	
		}
		return false; 
	}
	
	private int calculateDistance(char x, char y) {
		return Math.abs(x - y);
	}
}
