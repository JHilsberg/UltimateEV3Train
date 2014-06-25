import java.io.*;

import lejos.hardware.*;
import lejos.hardware.lcd.*;
import lejos.robotics.*;
import train.*;

class MainTrain extends TrainControl {
	private Client client;
	private boolean greenDetected, yellowDetected;
	private String receivedData;
	private File horn, cableCar;

	public static void main(String[] args) {
		
		try {
			new MainTrain();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MainTrain() throws IOException, InterruptedException {
		horn = new File("horn.wav");
		cableCar = new File("cableCar.wav");
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

	public void waitForTerminalAnswer() throws IOException, InterruptedException {
		receivedData = client.readData();
		
		LCD.clear();
		LCD.drawString("Input:" + receivedData, 0, 0);

		if (receivedData.equals("unload")) {
			super.unload();
			super.load();
			this.waitForTerminalAnswer();
		}
		if (receivedData.equals("GoFromYellow")) {
			Sound.playSample(horn);
			greenDetected = false;
			yellowDetected = true;
			
		}
		if (receivedData.equals("GoFromGreen")) {
			Sound.playSample(cableCar);
			yellowDetected = false;
			greenDetected = true;
		}
	}
}
