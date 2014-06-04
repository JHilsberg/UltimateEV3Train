import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.Color;
import train.*;

class MainTrain {

	private TrainController train = new TrainController();;
	private Client client = new Client(1111);
	private boolean greenDetected = false, yellowDetected = false;

	public static void main(String[] args) {
		try {
			new MainTrain();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MainTrain() throws InterruptedException, IOException {
		while (!Button.ESCAPE.isDown()) {
			LCD.clearDisplay();

			train.forward();
			train.detectColor();

			if (train.getColor() == Color.GREEN && greenDetected == false) {
				LCD.drawString("Farbe: gruen", 0, 0);
				train.stop();
				client.writeData(1);
				if (client.readData() == 1) {
					train.load();
					train.unload();
					yellowDetected = false;
					greenDetected = true;
				}
				greenDetected = true;
			}
			if (train.getColor() == Color.YELLOW && yellowDetected == false) {
				LCD.drawString("Farbe: gelb", 0, 0);
				LCD.drawString("warte auf Befehl... ....", 0, 1);
				train.stop();
				client.writeData(2);
				while (!Button.ENTER.isDown()) {
					if (client.readData() == 2) {
						greenDetected = false;
						yellowDetected = true;
					}
				}
			} else {
				LCD.drawString("Farberkennung...", 0, 0);
				Thread.sleep(200);
			}
		}
	}
}
