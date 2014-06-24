import java.io.*;
import lejos.hardware.*;
import lejos.hardware.lcd.*;
import lejos.robotics.*;
import train.*;

class MainTrain extends TrainControl {
	private Client client;
	private boolean greenDetected, yellowDetected;
	private String receivedData;
	private File horn;

	public static void main(String[] args) {
		
		try {
			new MainTrain();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	public MainTrain() throws IOException {
		horn = new File("horn.wav");
		client = new Client("192.168.0.7", 1111);

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
			Sound.playSample(horn);
		}
		if (receivedData.equals("GoFromGreen")) {
			yellowDetected = false;
			greenDetected = true;
			Sound.playSample(horn);
		}
	}
}
