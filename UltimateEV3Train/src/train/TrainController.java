package train;

import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;

public class TrainController {

	private RegulatedMotor movingMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	private RegulatedMotor loadingMotor = new EV3MediumRegulatedMotor(
			MotorPort.D);
	private EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);

	private int detectedColor;
	private int movingSpeed = 300;
	private int loadingSpeed = 40;

	public void forward() {
		this.movingMotor.setSpeed(this.movingSpeed);
		this.movingMotor.backward();
	}

	public void stop() {
		this.movingMotor.stop();
		this.movingMotor.flt();
	}

	public void unload() {
		int loadAngle = 55;
		this.loadingMotor.setSpeed(this.loadingSpeed);

		while (this.loadingMotor.getTachoCount() < loadAngle) {
			this.loadingMotor.forward();
		}
		this.loadingMotor.stop();
		this.loadingMotor.flt();
	}

	public void load() {
		this.loadingMotor.setSpeed(this.loadingSpeed);

		while (this.loadingMotor.getTachoCount() > 0) {
			this.loadingMotor.backward();
		}
		this.loadingMotor.stop();
		this.loadingMotor.flt();
	}

	public int getColor() {
		return this.detectedColor;
	}

	public void detectColor() {
		this.detectedColor = colorSensor.getColorID();
	}
}
