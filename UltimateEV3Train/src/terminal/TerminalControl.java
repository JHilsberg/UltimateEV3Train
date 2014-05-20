package terminal;

import lejos.hardware.lcd.LCD;
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

	private boolean rotationPositionLeft = false;
	private boolean rotationPositionRight = false;

	public void liftElevator() {
		int positionElevator = 180;

		while (elevatorLeft.getTachoCount() < positionElevator
				&& elevatorRight.getTachoCount() < positionElevator) {
			this.elevatorLeft.setSpeed(this.movingSpeedElevator);
			this.elevatorRight.setSpeed(this.movingSpeedElevator);
			this.elevatorLeft.forward();
			this.elevatorRight.forward();
		}
		this.stopElevator();
	}

	public void lowerElevator() {

		while (elevatorLeft.getTachoCount() > 0
				&& elevatorRight.getTachoCount() > 0) {
			this.elevatorLeft.setSpeed(this.movingSpeedElevator);
			this.elevatorRight.setSpeed(this.movingSpeedElevator);
			this.elevatorLeft.backward();
			this.elevatorRight.backward();
		}
		this.stopElevator();
	}

	public void stopElevator() {
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

	public void loadTerminalLeft() {
		int loadAngleLeft = 60;

		while (this.rotationMotor.getTachoCount() < loadAngleLeft) {
			this.rotateLeft();
		}
		this.stopRotation();
		this.rotationPositionLeft = true;
	}

	public void loadTerminalRight() {
		int loadAngleRight = -60;

		while (this.rotationMotor.getTachoCount() > loadAngleRight) {
			this.rotateRight();
		}
		this.stopRotation();
		this.rotationPositionRight = true;

	}

	public void resetTerminal() {

		if (rotationPositionLeft) {
			while (this.rotationMotor.getTachoCount() > 0) {
				this.rotateRight();

			}
			this.stopRotation();
			this.rotationPositionLeft = false;
		} else if (rotationPositionRight) {
			while (this.rotationMotor.getTachoCount() < 0) {
				this.rotateLeft();
			}
			this.stopRotation();
			this.rotationPositionRight = false;
		}
	}

	public void unloadTerminalLeft() {
		int loadAngleLeft = 90;

		while (this.rotationMotor.getTachoCount() < loadAngleLeft) {
			this.rotateLeft();
		}
		this.stopRotation();
		this.rotationPositionLeft = true;
	}

	public void unloadTerminalRight() {
		int loadAngleRight = -90;

		while (this.rotationMotor.getTachoCount() > loadAngleRight) {
			this.rotateRight();
		}
		this.stopRotation();
		this.rotationPositionRight = true;

	}

	public RegulatedMotor getRotationMotor() {
		return rotationMotor;
	}

	public void setRotationMotor(RegulatedMotor rotationMotor) {
		this.rotationMotor = rotationMotor;
	}

	public RegulatedMotor getElevatorRight() {
		return elevatorRight;
	}

	public void setElevatorRight(RegulatedMotor elevatorRight) {
		this.elevatorRight = elevatorRight;
	}

	public RegulatedMotor getElevatorLeft() {
		return elevatorLeft;
	}

	public void setElevatorLeft(RegulatedMotor elevatorLeft) {
		this.elevatorLeft = elevatorLeft;
	}

}