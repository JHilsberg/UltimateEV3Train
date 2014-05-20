package train;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.*;
import lejos.robotics.Color;

public class ColorDetection extends TrainMovement{

	private EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
	private int detectedColor;

	
	public int getColor() {
		if (this.detectedColor == Color.GREEN) {
			LCD.drawString("erkannte Farbe: grün", 0, 0);
		}
		if (this.detectedColor == Color.YELLOW) {
			LCD.drawString("erkannte Farbe: gelb", 0, 0);
		} else {
			LCD.drawString("Farberkennung...", 0, 0);
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.detectedColor;
	}

	public void setColor() {
		this.detectedColor = colorSensor.getColorID();
	}
	
	public void onGreenColor(){
		super.stop();
		super.unload();
		super.load();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onYellowColor() {
		// TODO Bluetooth Connection...
		
	}

}
