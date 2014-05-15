import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import terminal.TerminalControl;

public class MainTerminal {

	public static void main(String[] args) {
		new MainTerminal();
	}

	MainTerminal() {
		TerminalControl terminal = new TerminalControl();
		 LCD.drawInt(terminal.tachoElevatorLeft, 0, 0);
		 LCD.drawInt(terminal.tachoElevatorRight, 0, 1);
		 LCD.drawInt(terminal.tachoRotationMotor, 0, 2);
		 LCD.refresh();
		while (Button.ESCAPE.isDown() == false) {
			//terminal.loadTerminal(true, false);
			// terminal.unloadTerminal(true, false);
			 terminal.loadTerminal(false, true);
			// terminal.unloadTerminal(false, true);
			
		}
	}
}
