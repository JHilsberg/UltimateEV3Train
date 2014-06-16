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
			if (train.getColor() == Color.GREEN && greenDetected == false) {
				train.stop();
				client.writeData(1);
				waitForTerminalAnswer();
			}
			if (train.getColor() == Color.YELLOW && yellowDetected == false) {
				train.stop();
				client.writeData(2);
				waitForTerminalAnswer();
			} else {
				train.forward();
				train.detectColor();
			}
		}
	}

	public void waitForTerminalAnswer() throws IOException {
		boolean inWaitingPosition = true;
		while (inWaitingPosition) {
			int receivedData = client.readData();
			if (receivedData == 1) {
				train.load();
				train.unload();
				yellowDetected = false;
				greenDetected = true;
				inWaitingPosition = false;
			} else if (receivedData == 2) {
				greenDetected = false;
				yellowDetected = true;
				inWaitingPosition = false;
			}
		}
	}
}
