import lejos.hardware.Button;
import lejos.robotics.Color;
import train.*;

public class Main {

	private TrainMovement trainMovement = new TrainMovement();
	ColorDetection colorDetection = new ColorDetection();

	public static void main(String[] args) {
		new Main();
	}

	Main() {
		while (Button.ESCAPE.isDown() == false) {
			trainMovement.forward();
			if (colorDetection.getColor() == Color.GREEN) {
				trainMovement.stop();
			}
		}
	}
}
