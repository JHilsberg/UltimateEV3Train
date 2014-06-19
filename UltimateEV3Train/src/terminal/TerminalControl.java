package terminal;

import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class TerminalControl {
	// Aufruf Motoren
	private RegulatedMotor elevatorLeft = new EV3LargeRegulatedMotor(
			MotorPort.A);
	private RegulatedMotor elevatorRight = new EV3LargeRegulatedMotor(
			MotorPort.B);
	private RegulatedMotor rotationMotor = new EV3MediumRegulatedMotor(
			MotorPort.C);
	// Geschwindigkeitsvorgaben für einzelne Bewegungen
	private int movingSpeedElevator = 50;
	private int movingSpeedReset = 50;
	private int movingSpeedRotationUnload = 200;
	private int movingSpeedRotationLoad = 100;
	// Positionsvorgabe für einzelne Bewegungen
	private int positionElevator = 180;
	private int loadAngleLeft = 60;
	private int loadAngleRight = -60;
	private int unloadAngleLeft = 87;
	private int unloadAngleRight = -77;
	// Positionsmeldung
	private boolean rotationPositionLeft = false;
	private boolean rotationPositionRight = false;

	public void liftElevator() {

		while (elevatorLeft.getTachoCount() < this.positionElevator + 10
				&& elevatorRight.getTachoCount() < this.positionElevator) {
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

	private void rotateLeftReset() {
		this.rotationMotor.setSpeed(movingSpeedReset);
		this.rotationMotor.forward();
		;
	}

	private void rotateRightReset() {
		this.rotationMotor.setSpeed(movingSpeedReset);
		this.rotationMotor.backward();
	}

	private void rotateLeftUnload() {
		this.rotationMotor.setSpeed(this.movingSpeedRotationUnload);
		this.rotationMotor.forward();
	}

	private void rotateLeftLoad() {
		this.rotationMotor.setSpeed(this.movingSpeedRotationLoad);
		this.rotationMotor.forward();
	}

	private void rotateRightUnload() {
		this.rotationMotor.setSpeed(this.movingSpeedRotationUnload);
		this.rotationMotor.backward();
	}

	private void rotateRightLoad() {
		this.rotationMotor.setSpeed(this.movingSpeedRotationLoad);
		this.rotationMotor.backward();
	}

	private void stopRotation() {
		this.rotationMotor.stop();
	}

	public void loadTerminalLeft() {

		while (this.rotationMotor.getTachoCount() < this.loadAngleLeft) {
			this.rotateLeftLoad();
		}
		this.stopRotation();
		this.rotationPositionLeft = true;
	}

	public void loadTerminalRight() {

		while (this.rotationMotor.getTachoCount() > this.loadAngleRight) {
			this.rotateRightLoad();
		}
		this.stopRotation();
		this.rotationPositionRight = true;

	}

	public void unloadTerminalLeft() {

		while (this.rotationMotor.getTachoCount() < this.unloadAngleLeft) {
			this.rotateLeftUnload();
		}
		this.stopRotation();
		this.rotationPositionLeft = true;
	}

	public void unloadTerminalRight() {

		while (this.rotationMotor.getTachoCount() > this.unloadAngleRight) {
			this.rotateRightUnload();
		}
		this.stopRotation();
		this.rotationPositionRight = true;

	}

	public void resetTerminal() {

		if (this.rotationPositionLeft) {
			while (this.rotationMotor.getTachoCount() > 0) {
				this.rotateRightReset();
			}
			this.stopRotation();
			this.rotationPositionLeft = false;

		} else if (this.rotationPositionRight) {
			while (this.rotationMotor.getTachoCount() < 0) {
				this.rotateLeftReset();
			}
			this.stopRotation();
			this.rotationPositionRight = false;
		}
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

	/*
	 * public void loadTrainLeft() throws InterruptedException {
	 * loadTerminalTrainLeft(); Thread.sleep(5000); resetTerminal(); }
	 * 
	 * public void loadTrainRight() throws InterruptedException {
	 * loadTerminalRight(); Thread.sleep(5000); resetTerminal(); }
	 */

	public void unloadTrainLeft() throws InterruptedException {
		liftElevator();
		Thread.sleep(2000);

		unloadTerminalLeft();
		Thread.sleep(1000);

		resetTerminal();
		Thread.sleep(1000);

		lowerElevator();
		Thread.sleep(1000);
	}

	public void unloadTrainRight() throws InterruptedException {
		liftElevator();
		Thread.sleep(2000);

		unloadTerminalRight();
		Thread.sleep(1000);

		resetTerminal();
		Thread.sleep(1000);

		lowerElevator();
		Thread.sleep(1000);
	}
}