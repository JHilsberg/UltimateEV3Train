import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import terminal.TerminalControl;

public class MainTerminal {

	public static void main(String[] args) {
		new MainTerminal();
	}

	MainTerminal() {
		TerminalControl terminal = new TerminalControl();

		while (Button.ESCAPE.isDown() == false) {
			LCD.drawString("Links: ", 0, 0);
			LCD.drawInt(terminal.getElevatorLeft().getTachoCount(), 10, 0);
			LCD.drawString("Rechts: ", 0, 1);
			LCD.drawInt(terminal.getElevatorRight().getTachoCount(), 10, 1);
			LCD.drawString("Rotation: "
					+ terminal.getRotationMotor().getTachoCount(), 0, 2);
			// Entladen Terminal Links
			if (Button.UP.isDown() && Button.LEFT.isDown()) {
				terminal.liftElevator();
				LCD.refresh();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				terminal.unloadTerminalLeft();
				LCD.refresh();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				terminal.resetTerminal();
				LCD.refresh();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				terminal.lowerElevator();
				LCD.refresh();
			}
			// Beladen Terminal Links
			else if (Button.DOWN.isDown() && Button.LEFT.isDown()) {
				terminal.loadTerminalLeft();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				terminal.resetTerminal();
			}
			// Beladen Terminal Rechts
			else if (Button.DOWN.isDown() && Button.RIGHT.isDown()) {
				terminal.loadTerminalRight();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				terminal.resetTerminal();
			}
			// Entladen Terminal Rechts
			else if (Button.UP.isDown() && Button.RIGHT.isDown()) {
				terminal.liftElevator();
				LCD.refresh();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				terminal.unloadTerminalRight();
				LCD.refresh();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				terminal.resetTerminal();
				LCD.refresh();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				terminal.lowerElevator();
				LCD.refresh();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}

			}
		}
	}
}
