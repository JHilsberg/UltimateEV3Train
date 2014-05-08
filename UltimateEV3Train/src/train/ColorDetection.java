package train;

import lejos.hardware.*;
import lejos.hardware.lcd.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.*;

public class ColorDetection {
	
	private EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
	private int detectedColor;
	
	public ColorDetection(){
		while(Button.ESCAPE.isDown() == false){
			detectedColor = colorSensor.getColorID();
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
