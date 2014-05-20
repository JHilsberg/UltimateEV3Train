import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import terminal.TerminalControl;

public class MainTerminal {

	public static void main(String[] args) {
		new MainTerminal();
	}

	MainTerminal() {
		TerminalControl terminal = new TerminalControl();
		terminal.loadTerminalRight();


		while (Button.ESCAPE.isDown() == false) {
			LCD.drawString("Links: ", 0, 0);
			LCD.drawInt(terminal.getElevatorLeft().getTachoCount(), 10, 0);
			LCD.drawString("Rechts: ", 0, 1);
			LCD.drawInt(terminal.getElevatorRight().getTachoCount(), 10, 1);
			LCD.drawString("Rotation: "
					+ terminal.getRotationMotor().getTachoCount(), 0, 2);
			}
	}
}
