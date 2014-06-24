package train;

import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;

public class TrainControl {

	private RegulatedMotor movingMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	private RegulatedMotor loadingMotor = new EV3MediumRegulatedMotor(
			MotorPort.D);
	private EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);

	private int detectedColor;
	private int movingSpeed = 300;
	private int loadingSpeed = 40;

	protected void forward() {
		this.movingMotor.setSpeed(this.movingSpeed);
		this.movingMotor.backward();
	}

	protected void stop() {
		this.movingMotor.stop();
		this.movingMotor.flt();
	}

	protected void unload() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int loadAngle = 55;
		this.loadingMotor.setSpeed(this.loadingSpeed);

		while (this.loadingMotor.getTachoCount() < loadAngle) {
			this.loadingMotor.forward();
		}
		this.loadingMotor.stop();
		this.loadingMotor.flt();
	}

	protected void load() {
		this.loadingMotor.setSpeed(this.loadingSpeed);

		while (this.loadingMotor.getTachoCount() > 0) {
			this.loadingMotor.backward();
		}
		this.loadingMotor.stop();
		this.loadingMotor.flt();
	}

	protected int getColor() {
		return this.detectedColor;
	}

	protected void detectColor() {
		this.detectedColor = colorSensor.getColorID();
	}
}
