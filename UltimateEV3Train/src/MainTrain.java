import lejos.hardware.Button;
import lejos.robotics.Color;
import train.*;

public class MainTrain {

	private TrainMovement trainMovement = new TrainMovement();
	ColorDetection colorDetection = new ColorDetection();
	

	public static void main(String[] args) {
		new MainTrain();
	}

	MainTrain() {
		while (Button.ESCAPE.isDown() == false) {
			
			if (colorDetection.getColor() == Color.GREEN) {
				colorDetection.onGreenColor();
			}
			if(colorDetection.getColor() == Color.YELLOW){
				colorDetection.onYellowColor();
			} else{
				trainMovement.forward();
				colorDetection.setColor();
			}
		}
	}
}
