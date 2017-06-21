import java.awt.*;
import java.util.*;

public class DynamicPiece {

	// 0 = 0 degrees, 1 = 30 degrees, 2 = 45 degrees, 3 = 60 degrees, 4 = 90 degrees, 5 = 120 degrees
	//6 = 135 degrees, 7 = 150 degrees, 8 = 180 degrees, 9 = 210 degrees, 10 = 225 degrees, 11 = 240 degrees
	//12 = 270 degrees, 13 = 300 degrees, 14 = 315 degrees, 15 = 330 degrees
	int[][] directionSlopes = {{4, 1}, {2, 1}, {1, 1}, {1, 2}, {1, 4},
			{-1, 4}, {-1, 2}, {-1, 1}, {-2, 1}, {-4, 1},
			{-4, -1}, {-2, -1}, {-1, -1}, {-1, -2}, {-1, -4},
			{1, -4}, {1, -2}, {1, -1}, {2, -1}, {4, -1}};
	int currentDirection;
	Graphics graphs;
	int xPosition;
	int yPosition;
	int size;
	int xSpeed;
	int ySpeed;
	Color c;
	DrawingPanel panel;
	

	public DynamicPiece(DrawingPanel p, int xPos, int yPos, int s) {
		Random rand = new Random();
		xPosition = xPos;
		yPosition = yPos;
		size = s;
		panel = p;
		
		graphs = panel.getGraphics();
		panel.setBackground(Color.BLACK);

		c = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)); //Generate random color
		graphs.setColor(c);
		graphs.fillOval(xPosition, yPosition, size, size);
		
		currentDirection = rand.nextInt(directionSlopes.length); //sets random initial direction
		xSpeed = directionSlopes[currentDirection][0];
		ySpeed = directionSlopes[currentDirection][1];
	}

	public void move() throws InterruptedException{
		Random rand = new Random();
		
		//Moves the piece in whatever direction it needs to go
		clearScreen(); //clears area around piece
		
		//updates position
		xPosition += xSpeed;
		yPosition -= ySpeed;
		
		//if dynamicPiece goes out of boarders
		if(xPosition > panel.getWidth() - size){
			xPosition = panel.getWidth() - size;
		} else if(xPosition < 0){
			xPosition = 0;
		} else if(yPosition > panel.getHeight() - size){
			yPosition = panel.getHeight() - size;
		} else if(yPosition < 0){
			yPosition = 0;
		}
			
		graphs.fillOval(xPosition, yPosition, size, size); //draws updated position of dynamic piece
		
		if(isColliding()){
			if(xPosition <= 0 || xPosition >= panel.getWidth() - size){ //Collided with left or right wall
				xSpeed *= -1;
			} else { //Collided with bottom or top wall
				ySpeed *= -1;
			}
			
			//Sets color to random color
			c = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)); //Generate random color
			graphs.setColor(c);
		}
		
	}
	
	private void clearScreen(){
		//Clears the area around the dynamic piece
		graphs.setColor(Color.BLACK);
		graphs.fillOval(xPosition - 1, yPosition - 1, size + 2, size + 2);
		graphs.setColor(c);
	}

	private boolean isColliding() {
		if (xPosition <= 0 || xPosition >= panel.getWidth() - size || yPosition <= 0 || yPosition >= panel.getHeight() - size) {
			return true;
		}
		return false;
	}

}
