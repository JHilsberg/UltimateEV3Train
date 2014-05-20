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
			terminal.liftElevator();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			terminal.unloadTerminalRight();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			terminal.resetTerminal();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
