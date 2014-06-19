package terminal;

import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class TerminalControl {
	// Aufruf Motoren
	private RegulatedMotor elevator1 = new EV3LargeRegulatedMotor(
			MotorPort.A);
	private RegulatedMotor elevator2 = new EV3LargeRegulatedMotor(
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
	private int loadAngle1 = 60;
	private int loadAngle2 = -60;
	private int unloadAngle1 = 87;
	private int unloadAngle2 = -77;
	// Positionsmeldung
	private boolean rotationPosition1 = false;
	private boolean rotationPosition2 = false;

	public void liftElevator() {

		while (elevator1.getTachoCount() < this.positionElevator + 10
				&& elevator2.getTachoCount() < this.positionElevator) {
			this.elevator1.setSpeed(this.movingSpeedElevator);
			this.elevator2.setSpeed(this.movingSpeedElevator);
			this.elevator1.forward();
			this.elevator2.forward();
		}
		this.stopElevator();
	}

	public void lowerElevator() {

		while (elevator1.getTachoCount() > 0
				&& elevator2.getTachoCount() > 0) {
			this.elevator1.setSpeed(this.movingSpeedElevator);
			this.elevator2.setSpeed(this.movingSpeedElevator);
			this.elevator1.backward();
			this.elevator2.backward();
		}
		this.stopElevator();
	}

	public void stopElevator() {
		this.elevator1.stop();
		this.elevator2.stop();
		this.elevator1.flt();
		this.elevator2.flt();
	}

	private void rotate1Reset() {
		this.rotationMotor.setSpeed(movingSpeedReset);
		this.rotationMotor.forward();
		;
	}

	private void rotate2Reset() {
		this.rotationMotor.setSpeed(movingSpeedReset);
		this.rotationMotor.backward();
	}

	private void rotate1Unload() {
		this.rotationMotor.setSpeed(this.movingSpeedRotationUnload);
		this.rotationMotor.forward();
	}

	private void rotate1Load() {
		this.rotationMotor.setSpeed(this.movingSpeedRotationLoad);
		this.rotationMotor.forward();
	}

	private void rotate2Unload() {
		this.rotationMotor.setSpeed(this.movingSpeedRotationUnload);
		this.rotationMotor.backward();
	}

	private void rotate2Load() {
		this.rotationMotor.setSpeed(this.movingSpeedRotationLoad);
		this.rotationMotor.backward();
	}

	private void stopRotation() {
		this.rotationMotor.stop();
	}

	public void loadTerminal1() {

		while (this.rotationMotor.getTachoCount() < this.loadAngle1) {
			this.rotate1Load();
		}
		this.stopRotation();
		this.rotationPosition1 = true;
	}

	public void loadTerminal2() {

		while (this.rotationMotor.getTachoCount() > this.loadAngle2) {
			this.rotate2Load();
		}
		this.stopRotation();
		this.rotationPosition2 = true;

	}

	public void unloadTerminal1() {

		while (this.rotationMotor.getTachoCount() < this.unloadAngle1) {
			this.rotate1Unload();
		}
		this.stopRotation();
		this.rotationPosition1 = true;
	}

	public void unloadTerminal2() {

		while (this.rotationMotor.getTachoCount() > this.unloadAngle2) {
			this.rotate2Unload();
		}
		this.stopRotation();
		this.rotationPosition2 = true;

	}

	public void resetTerminal() {

		if (this.rotationPosition1) {
			while (this.rotationMotor.getTachoCount() > 0) {
				this.rotate2Reset();
			}
			this.stopRotation();
			this.rotationPosition1 = false;

		} else if (this.rotationPosition2) {
			while (this.rotationMotor.getTachoCount() < 0) {
				this.rotate1Reset();
			}
			this.stopRotation();
			this.rotationPosition2 = false;
		}
	}

	public RegulatedMotor getRotationMotor() {
		return rotationMotor;
	}

	public void setRotationMotor(RegulatedMotor rotationMotor) {
		this.rotationMotor = rotationMotor;
	}

	public RegulatedMotor getElevator2() {
		return elevator2;
	}

	public void setElevator2(RegulatedMotor elevator2) {
		this.elevator2 = elevator2;
	}

	public RegulatedMotor getElevator1() {
		return elevator1;
	}

	public void setElevator1(RegulatedMotor elevator1) {
		this.elevator1 = elevator1;
	}

}