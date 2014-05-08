import lejos.hardware.Button;
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
			colorDetection.setColor();
			if (colorDetection.getColor() == 1) {
				trainMovement.stop();
				trainMovement.unload();
				trainMovement.load();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
