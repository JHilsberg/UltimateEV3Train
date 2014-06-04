import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.Color;
import train.*;

class TrainMain {

	private TrainController train = new TrainController();;
	private Client client = new Client(1111);
	private boolean greenDetected = false, yellowDetected = false;

	public static void main(String[] args) {
		try {
			new TrainMain();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private TrainMain() throws InterruptedException, IOException {
		while (!Button.ESCAPE.isDown()) {
			LCD.clearDisplay();

			train.forward();
			train.detectColor();

			if (train.getColor() == Color.GREEN
					&& greenDetected == false) {
				LCD.drawString("Farbe: gruen", 0, 0);
				train.stop();
				client.writeData(1);
				if (client.readData() == 1) {
					train.load();
					train.unload();
					//123
					yellowDetected = false;
					greenDetected = true;
				}
				greenDetected = true;
			}
			if (train.getColor() == Color.YELLOW
					&& yellowDetected == false) {
				LCD.drawString("Farbe: gelb", 0, 0);
				LCD.drawString("warte auf Befehl... ....", 0, 1);
				train.stop();
				client.writeData(2);
				if (client.readData() == 2) {
					greenDetected = false;
					yellowDetected = true;
					return;
				}

			} else {
				LCD.drawString("Farberkennung...", 0, 0);
				Thread.sleep(200);
			}
		}
	}
}
