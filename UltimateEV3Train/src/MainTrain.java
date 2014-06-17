import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.Color;
import train.*;

class MainTrain {

	private TrainController train = new TrainController();;
	private Client client = new Client("192.168.0.102", 1111);
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
				client.writeData("green");
				waitForTerminalAnswer();
			}
			if (train.getColor() == Color.YELLOW && yellowDetected == false) {
				train.stop();
				client.writeData("yellow");
				waitForTerminalAnswer();
			} else {
				train.forward();
				train.detectColor();
			}
		}
	}

	public void waitForTerminalAnswer() throws IOException {
		String receivedData = client.readData();
		LCD.drawString("Input: " + receivedData, 0, 0);
		if (receivedData.equals("unload")) {
			train.unload();
			train.load();
			yellowDetected = false;
			greenDetected = true;
		} else if (receivedData.equals("go")) {
			greenDetected = false;
			yellowDetected = true;
		}
	}
}
