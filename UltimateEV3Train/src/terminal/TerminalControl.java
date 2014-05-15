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
	private int movingSpeedRotation = 50;

	private void liftElevator() {
		this.elevatorLeft.setSpeed(this.movingSpeedElevator);
		this.elevatorRight.setSpeed(this.movingSpeedElevator);
		this.elevatorLeft.forward();
		this.elevatorRight.forward();
	}

	private void lowerElevator() {
		this.elevatorLeft.setSpeed(this.movingSpeedElevator);
		this.elevatorRight.setSpeed(this.movingSpeedElevator);
		this.elevatorLeft.backward();
		this.elevatorRight.backward();
	}

	private void stopElevator() {
		this.elevatorLeft.stop();
		this.elevatorRight.stop();
		this.elevatorLeft.flt();
		this.elevatorRight.flt();
	}

	private void rotateLeft() {
		this.rotationMotor.setSpeed(this.movingSpeedRotation);
		this.rotationMotor.forward();
	}

	private void rotateRight() {
		this.rotationMotor.setSpeed(this.movingSpeedRotation);
		this.rotationMotor.backward();
	}

	private void stopRotation() {
		this.rotationMotor.stop();
	}

	public void loadTerminal(boolean loadLeft, boolean loadRight) {
		int loadAngle = 40;

		while (this.elevatorLeft.getTachoCount() > 0
				&& this.elevatorRight.getTachoCount() > 0) {
			this.lowerElevator();
		}
		this.stopElevator();

		while (loadLeft && this.rotationMotor.getTachoCount() < loadAngle) {
			this.rotateLeft();
		}
		while (loadRight && this.rotationMotor.getTachoCount() < loadAngle) {
			this.rotateRight();
		}
		this.stopRotation();
	}

	public void unloadTerminal(boolean unloadLeft, boolean unloadRight) {
		int unloadHeight = 180;
		int unloadAngle = 50;

		while (this.elevatorLeft.getTachoCount() < unloadHeight
				&& this.elevatorRight.getTachoCount() < unloadHeight) {
			this.liftElevator();
		}
		this.stopElevator();

		while (unloadLeft && this.rotationMotor.getTachoCount() < unloadAngle
				&& this.elevatorLeft.getTachoCount() == unloadHeight
				&& this.elevatorRight.getTachoCount() == unloadHeight) {
			this.rotateLeft();
		}
		this.stopRotation();
		while (unloadRight && this.rotationMotor.getTachoCount() < unloadAngle
				&& this.elevatorLeft.getTachoCount() == unloadHeight
				&& this.elevatorRight.getTachoCount() == unloadHeight) {
			this.rotateRight();
		}
		this.stopRotation();
	}
}
