import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import terminal.Server;

public class ServerTest {

	private Server server = new Server(1111);

	public static void main(String[] args) {
		try {
			new ServerTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	ServerTest() throws IOException {
		while (!Button.ESCAPE.isDown()) {
			
			String receivedData = server.readData();

			LCD.drawString("Input: " + receivedData, 0, 0);

			if (receivedData.equals("green")) {
				server.writeData("unload");
			}
			if (receivedData.equals("yellow")) {
				server.writeData("go");
			}
		}
	}
}
