import lejos.hardware.Button;
import terminal.TerminalControl;
import train.*;

public class MainTrain {

	private TrainMovement trainMovement = new TrainMovement();
	ColorDetection colorDetection = new ColorDetection();
	

	public static void main(String[] args) {
		new MainTrain();
	}

	MainTrain() {
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
