import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import terminal.Server;
import terminal.TerminalControl;

public class MainTerminal extends TerminalControl {
	
	private Server rightTrain = new Server(1111);
	private Server leftTrain = new Server(1112);
	private boolean leftTrainLoaded = true, rightTrainLoaded, trainsLocked;

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
		String dataLeftTrain;
		String dataRightTrain;

		while (Button.ESCAPE.isDown() == false) {
			dataLeftTrain = rightTrain.readData();
			dataRightTrain = leftTrain.readData();

			LCD.clearDisplay();
			LCD.drawString("Train 1:" + dataLeftTrain, 0, 0);
			LCD.drawString("Train 2:" + dataRightTrain, 0, 1);

			if (dataLeftTrain.equals("yellow")
					&& dataRightTrain.equals("yellow")) {
				goFrom("yellow");
				trainsLocked = false;
			}
			if (dataLeftTrain.equals("green") && dataRightTrain.equals("green")) {
					loadTrains();
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
			loadTerminalLeft();
			rightTrain.writeData("unload");
			leftTrainLoaded = false;
			resetTerminal();
			unloadTrainRight();
			rightTrainLoaded = true;
		}
		if (trainsLocked == false && rightTrainLoaded == true) {
			loadTerminalRight();
			leftTrain.writeData("unload");
			rightTrainLoaded = false;
			resetTerminal();
			unloadTrainLeft();
			leftTrainLoaded = true;
		}
		trainsLocked = true;
		goFrom("green");
	}
}
