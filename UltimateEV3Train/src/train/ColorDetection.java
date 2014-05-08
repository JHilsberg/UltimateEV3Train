package train;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class ColorDetection {
	
	private EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
	private int detectedColor;
	
	public ColorDetection(){
		while(Button.ESCAPE.isDown() == false){
			detectedColor = colorSensor.getColorID();
			//permanently color detection
			LCD.drawString("erkannte Farbe: " + detectedColor, 0, 0);
		}
	}
	
	public int getColor() {
		return detectedColor;
	}
	public void setColor(int color) {
		this.detectedColor = color;
	}

}
