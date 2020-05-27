import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class imageHandler {
	
	static BufferedImage wpawn = null;
	static BufferedImage wbishop = null;
	static BufferedImage wknightL = null;
	static BufferedImage wknightR = null; 
	static BufferedImage wqueen = null; 
	static BufferedImage wking = null;
	static BufferedImage wrook = null;
	static BufferedImage bpawn = null; 
	static BufferedImage bbishop = null;
	static BufferedImage bknightL = null;
	static BufferedImage bknightR = null; 
	static BufferedImage bqueen = null; 
	static BufferedImage bking = null;
	static BufferedImage brook = null;
	static BufferedImage black = null;
	static BufferedImage white = null;
	
	static long startTime = System.currentTimeMillis();
	
	public imageHandler() {

		loadImages();
		
	}
	
	private void loadImages() {
		try {
			System.out.println("Baha Chess loading...");
			System.out.print("Fetching Images...");
			wpawn = ImageIO.read(this.getClass().getResource("wPawn.png"));
			wbishop = ImageIO.read(this.getClass().getResource("wBishop.png"));
			wknightL = ImageIO.read(this.getClass().getResource("wKnightLeft.png"));
			wknightR = ImageIO.read(this.getClass().getResource("wKnightRight.png"));
			wqueen = ImageIO.read(this.getClass().getResource("wQueen.png"));
			wking = ImageIO.read(this.getClass().getResource("wKing.png"));
			wrook = ImageIO.read(this.getClass().getResource("wRook.png"));
			bpawn = ImageIO.read(this.getClass().getResource("bPawn.png"));
			bbishop = ImageIO.read(this.getClass().getResource("bBishop.png"));
			bknightL = ImageIO.read(this.getClass().getResource("bKnightLeft.png"));
			bknightR = ImageIO.read(this.getClass().getResource("bKnightRight.png"));
			bqueen = ImageIO.read(this.getClass().getResource("bQueen.png"));
			bking = ImageIO.read(this.getClass().getResource("bKing.png"));
			brook = ImageIO.read(this.getClass().getResource("bRook.png"));
			black = ImageIO.read(this.getClass().getResource("black_v2.png"));
			white = ImageIO.read(this.getClass().getResource("white_v2.png"));
			System.out.println("Done!");
		} catch (IOException e) {
			System.out.println("Failed to load iamges!");
		}		
	}
}
