import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class ArtyArtArt {

	public static void main(String[] args) throws InterruptedException {
		
		DrawingPanel panel = new DrawingPanel(500, 500);
		Graphics graphs = panel.getGraphics();
		Random rand = new Random();
		graphs.setColor(Color.BLACK);
		graphs.fillRect(0, 0, 500, 500);
		DynamicPiece[] pieces = new DynamicPiece[10];
		for(int i = 0; i < 10; i++){
			pieces[i] = new DynamicPiece(panel, rand.nextInt(451), rand.nextInt(451), rand.nextInt(11) + 40);
		}
		
		while(true){
			for(int i = 0; i < 10; i++){
				pieces[i].move();
			}
			Thread.sleep(15);
		}
		
	}
	
}
