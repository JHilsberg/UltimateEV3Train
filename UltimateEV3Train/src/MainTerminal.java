import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import terminal.Server;
import terminal.TerminalControl;

public class MainTerminal {

	private TerminalControl terminal = new TerminalControl();
	private Server trainServer1 = new Server(1111);
	private Server trainServer2 = new Server(1112);
	private boolean train1Loaded = true, train2Loaded;

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
		String dataTrain1;
		String dataTrain2;

		while (Button.ESCAPE.isDown() == false) {
			dataTrain1 = trainServer1.readData();
			dataTrain2 = trainServer2.readData();
			
			LCD.clearDisplay();
			LCD.drawString("Train 1:" + dataTrain1, 0, 0);
			LCD.drawString("Train 2:" + dataTrain2, 0, 1);

			if (dataTrain1.equals("yellow") && dataTrain2.equals("yellow")) {
				trainServer1.writeData("GoFromYellow");
				trainServer2.writeData("GoFromYellow");
			}

			if (dataTrain1.equals("green") && dataTrain2.equals("green")
					&& train1Loaded == true) {
				load1();
				Thread.sleep(1000);
				trainServer1.writeData("unload");
				unload2();
				train1Loaded = false;
				train2Loaded = true;
				trainServer1.writeData("GoFromGreen");
				trainServer2.writeData("GoFromGreen");
			}
			if (dataTrain1.equals("green") && dataTrain2.equals("green")
					&& train2Loaded == true) {
				load2();
				Thread.sleep(1000);
				trainServer2.writeData("unload");
				unload1();
				train1Loaded = true;
				train2Loaded = false;
				trainServer1.writeData("GoFromGreen");
				trainServer2.writeData("GoFromGreen");
			}
		}
	}

	private void unload1() throws InterruptedException {
		terminal.liftElevator();
		LCD.refresh();
		Thread.sleep(2000);

		terminal.unloadTerminal1();
		LCD.refresh();
		Thread.sleep(1000);

		terminal.resetTerminal();
		LCD.refresh();
		Thread.sleep(1000);

		terminal.lowerElevator();
		LCD.refresh();
	}

	private void load1() throws InterruptedException {
		terminal.loadTerminal1();
		Thread.sleep(5000);
		terminal.resetTerminal();
	}

	private void unload2() throws InterruptedException {
		terminal.liftElevator();
		LCD.refresh();
		Thread.sleep(2000);

		terminal.unloadTerminal2();
		LCD.refresh();
		Thread.sleep(1000);

		terminal.resetTerminal();
		LCD.refresh();
		Thread.sleep(1000);

		terminal.lowerElevator();
		LCD.refresh();
		Thread.sleep(1000);
	}

	private void load2() throws InterruptedException {
		terminal.loadTerminal2();
		Thread.sleep(5000);
		terminal.resetTerminal();
	}

	private void setLcdReady() {
		LCD.drawString("Links: ", 0, 0);
		LCD.drawInt(terminal.getElevator1().getTachoCount(), 10, 0);
		LCD.drawString("Rechts: ", 0, 1);
		LCD.drawInt(terminal.getElevator2().getTachoCount(), 10, 1);
		LCD.drawString("Rotation: "
				+ terminal.getRotationMotor().getTachoCount(), 0, 2);
	}
}
