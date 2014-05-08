package train;

import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class TrainMovement {
	
	private RegulatedMotor movingMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	private RegulatedMotor loadingMotor = new EV3MediumRegulatedMotor(MotorPort.D);
	
	private int movingSpeed = 300;
	private int loadingSpeed = 40;
	
	public void forward(){
		this.movingMotor.setSpeed(this.movingSpeed);
		this.movingMotor.backward();
	}
	
	public void stop(){
		this.movingMotor.stop();
		this.movingMotor.flt();
	}
	
	public void unload(){
		this.loadingMotor.setSpeed(this.loadingSpeed);
		this.loadingMotor.forward();
	}
}
