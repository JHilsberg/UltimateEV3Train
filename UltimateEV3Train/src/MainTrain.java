import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.Color;
import train.*;

public class MainTrain {

	private TrainController train = new TrainController();
	private boolean greenDetected = false;

	public static void main(String[] args) {
		try {
			new MainTrain();
		} catch (InterruptedException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		}
	}

	MainTrain() throws InterruptedException {
		while (Button.ESCAPE.isDown() == false) {
			LCD.clearDisplay();
			
			train.forward();
			train.detectColor();

			if (train.getColor() == Color.GREEN && greenDetected == false) {
				LCD.drawString("Farbe: gruen", 0, 0);
				train.stop();
				train.unload();
				train.load();
				greenDetected = true;
			}
			if (train.getColor() == Color.YELLOW) {
				LCD.drawString("Farbe: gelb", 0, 0);
				train.stop();
				greenDetected = false;
			} else{
				LCD.drawString("Farberkennung...", 0, 0);
				Thread.sleep(200);
			}
		}
	}
}
