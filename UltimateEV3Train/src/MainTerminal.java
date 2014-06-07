import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import terminal.TerminalControl;

public class MainTerminal {
	
	private TerminalControl terminal = new TerminalControl();

	public static void main(String[] args) {
		try {
			new MainTerminal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	MainTerminal()  throws InterruptedException{
		
		while (Button.ESCAPE.isDown() == false) {
			setLcdReady();
			
			// Entladen Terminal Links
			if (Button.UP.isDown() && Button.LEFT.isDown()) {
				terminal.liftElevator();
				LCD.refresh();
				Thread.sleep(2000);
				
				terminal.unloadTerminalLeft();
				LCD.refresh();
				Thread.sleep(1000);
				
				terminal.resetTerminal();
				LCD.refresh();
				Thread.sleep(1000);
				
				terminal.lowerElevator();
				LCD.refresh();
			}
			
			// Beladen Terminal Links
			else if (Button.DOWN.isDown() && Button.LEFT.isDown()) {
				terminal.loadTerminalLeft();
				Thread.sleep(5000);
				terminal.resetTerminal();
			}
			
			// Beladen Terminal Rechts
			else if (Button.DOWN.isDown() && Button.RIGHT.isDown()) {
				terminal.loadTerminalRight();
				Thread.sleep(5000);
				terminal.resetTerminal();
			}
			
			//Entladen Terminal Rechts
			else if (Button.UP.isDown() && Button.RIGHT.isDown()) {
				terminal.liftElevator();
				LCD.refresh();
				Thread.sleep(2000);
				
				terminal.unloadTerminalRight();
				LCD.refresh();
				Thread.sleep(1000);
				
				terminal.resetTerminal();
				LCD.refresh();
				Thread.sleep(1000);
				
				terminal.lowerElevator();
				LCD.refresh();
				Thread.sleep(1000);
			}
		}
	}

	private void setLcdReady() {
		LCD.drawString("Links: ", 0, 0);
		LCD.drawInt(terminal.getElevatorLeft().getTachoCount(), 10, 0);
		LCD.drawString("Rechts: ", 0, 1);
		LCD.drawInt(terminal.getElevatorRight().getTachoCount(), 10, 1);
		LCD.drawString("Rotation: "
				+ terminal.getRotationMotor().getTachoCount(), 0, 2);
	}
}
