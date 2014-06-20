import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import terminal.*;

public class MainTerminal extends TerminalControl {

	private Server leftTrain, rightTrain;
	private boolean leftTrainLoaded = true, rightTrainLoaded, trainsLocked;
	private String dataLeftTrain, dataRightTrain;

	public static void main(String[] args) {
		try {
			new MainTerminal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	MainTerminal() throws InterruptedException, IOException {
		leftTrain = new Server(1111);
		rightTrain = new Server(1112);

		while (Button.ESCAPE.isDown() == false) {
			dataLeftTrain = rightTrain.readData();
			dataRightTrain = leftTrain.readData();

			LCD.refresh();
			LCD.drawString("Train 1:" + dataLeftTrain, 0, 0);
			LCD.drawString("Train 2:" + dataRightTrain, 0, 1);

			if (dataLeftTrain.equals("yellow")
					&& dataRightTrain.equals("yellow")) {
				this.goFrom("yellow");
				trainsLocked = false;
			}
			if (dataLeftTrain.equals("green") && dataRightTrain.equals("green")) {
				this.loadTrains();
			}
		}
	}

	private void goFrom(String color) throws IOException {
		if (color.equals("green")) {
			rightTrain.writeData("GoFromGreen");
			leftTrain.writeData("GoFromGreen");
		}
		if (color.equals("yellow")) {
			rightTrain.writeData("GoFromYellow");
			leftTrain.writeData("GoFromYellow");
		}
	}

	private void loadTrains() throws IOException, InterruptedException {
		if (trainsLocked == false && leftTrainLoaded == true) {
			super.loadTrainLeft();
			rightTrain.writeData("unload");
			leftTrainLoaded = false;
			super.resetTerminal();
			super.unloadTrainRight();
			rightTrainLoaded = true;
		}
		if (trainsLocked == false && rightTrainLoaded == true) {
			super.loadTrainRight();
			leftTrain.writeData("unload");
			rightTrainLoaded = false;
			super.resetTerminal();
			super.unloadTrainLeft();
			leftTrainLoaded = true;
		}
		trainsLocked = true;
		this.goFrom("green");
	}
}
