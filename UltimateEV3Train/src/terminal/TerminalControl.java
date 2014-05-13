package terminal;

import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class TerminalControl {

	private RegulatedMotor elevatorLeft = new EV3LargeRegulatedMotor(
			MotorPort.A);
	private RegulatedMotor elevatorRight = new EV3LargeRegulatedMotor(
			MotorPort.B);
	private RegulatedMotor rotationMotor = new EV3MediumRegulatedMotor(
			MotorPort.C);

	private int movingSpeedElevator = 50;
	private int movingSpeedRotation = 20;

	public void liftElevator() {
		this.elevatorLeft.setSpeed(this.movingSpeedElevator);
		this.elevatorRight.setSpeed(this.movingSpeedElevator);
		this.elevatorLeft.forward();
		this.elevatorLeft.forward();
	}

	public void lowerElevator() {
		this.elevatorLeft.setSpeed(this.movingSpeedElevator);
		this.elevatorRight.setSpeed(this.movingSpeedElevator);
		this.elevatorLeft.backward();
		this.elevatorRight.backward();
	}

	public void stopElevator() {
		this.elevatorLeft.stop();
		this.elevatorRight.stop();
		this.elevatorLeft.flt();
		this.elevatorRight.flt();
	}

	public void rotateLeft() {
		this.rotationMotor.setSpeed(this.movingSpeedRotation);
		this.rotationMotor.forward();
	}

	public void rotateRight() {
		this.rotationMotor.setSpeed(this.movingSpeedRotation);
		this.rotationMotor.backward();
	}

	public void stopRotation() {
		this.rotationMotor.stop();
		this.rotationMotor.flt();
	}
	
	public void loadTerminal(){
		
	}
	
	public void unloadTerminal(){
		
	}
}
