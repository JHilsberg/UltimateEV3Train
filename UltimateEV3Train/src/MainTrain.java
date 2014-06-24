import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.Color;
import train.*;

class MainTrain extends TrainControl {
	private Client client;
	private boolean greenDetected, yellowDetected;
	private String receivedData;

	public static void main(String[] args) {
		try {
			new MainTrain();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	public MainTrain() throws IOException {
		client = new Client("192.168.0.7", 1112);

		while (!Button.ESCAPE.isDown()) {
			if (super.getColor() == Color.GREEN && greenDetected == false) {
				super.stop();
				client.writeData("green");
				this.waitForTerminalAnswer();
			}
			if (super.getColor() == Color.YELLOW && yellowDetected == false) {
				super.stop();
				client.writeData("yellow");
				this.waitForTerminalAnswer();
			} else {
				super.forward();
				super.detectColor();
			}
		}
	}

	public void waitForTerminalAnswer() throws IOException {
		receivedData = client.readData();

		LCD.clear();
		LCD.drawString("Input:" + receivedData, 0, 0);

		if (receivedData.equals("unload")) {
			super.unload();
			super.load();
			this.waitForTerminalAnswer();
		}
		if (receivedData.equals("GoFromYellow")) {
			greenDetected = false;
			yellowDetected = true;
		}
		if (receivedData.equals("GoFromGreen")) {
			yellowDetected = false;
			greenDetected = true;
		}
	}
}
