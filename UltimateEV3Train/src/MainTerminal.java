import terminal.TerminalControl;

public class MainTerminal {

	public static void main(String[] args) {
		new MainTerminal();
	}

	MainTerminal() {
		TerminalControl terminal = new TerminalControl();

		terminal.loadTerminal(true, false);
		// terminal.unloadTerminal(true, false);
		// terminal.loadTerminal(false, true);
		// terminal.unloadTerminal(false, true);
		System.out.println(terminal.tachoElevatorLeft);
		System.out.println(terminal.tachoElevatorRight);
		System.out.println(terminal.tachoRotationMotor);
	}
}
