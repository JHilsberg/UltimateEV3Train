import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import terminal.Server;

public class ServerTest {

	private Server trainServer1 = new Server(1111);
	private Server trainServer2 = new Server(1112);

	public static void main(String[] args) {
		try {
			new ServerTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	ServerTest() throws IOException {
		String dataTrain1;
		String dataTrain2;

		while (!Button.ESCAPE.isDown()) {

			dataTrain1 = trainServer1.readData();
			dataTrain2 = trainServer2.readData();

			LCD.clearDisplay();
			LCD.drawString("Train 1:" + dataTrain1, 0, 0);
			LCD.drawString("Train 2:" + dataTrain2, 0, 1);

			if (dataTrain1.equals("green") && dataTrain2.equals("green")) {
				trainServer1.writeData("unload");
				trainServer1.writeData("GoFromGreen");
				trainServer2.writeData("GoFromGreen");
			}
			if (dataTrain1.equals("yellow") && dataTrain2.equals("yellow")) {
				trainServer1.writeData("GoFromYellow");
				trainServer2.writeData("GoFromYellow");
			}
		}
	}
}
