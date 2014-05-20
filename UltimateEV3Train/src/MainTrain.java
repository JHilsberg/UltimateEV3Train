import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.Color;
import train.*;

public class MainTrain {

	private TrainController train = new TrainController();

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

			if (train.getColor() == Color.GREEN) {
				LCD.drawString("Farbe: grün", 0, 0);
				train.stop();
				train.unload();
				train.load();
				Thread.sleep(5000);
			}
			if (train.getColor() == Color.YELLOW) {
				LCD.drawString("Farbe: gelb", 0, 0);
				train.stop();
				Thread.sleep(1000);
			} else{
				LCD.drawString("Farberkennung...", 0, 0);
			}
		}
	}
}
