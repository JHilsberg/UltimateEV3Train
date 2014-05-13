package train;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.*;

public class ColorDetection {
	
	private EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
	private int detectedColor;
	
	public int getColor() {
		LCD.drawString("erkannte Farbe: " + this.detectedColor, 0, 0);
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

}
