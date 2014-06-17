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

			LCD.drawString("Input: " + server.readData(), 0, 0);

			if (server.readData() == 1) {
				server.writeData(3);
			}
			if (server.readData() == 2) {
				server.writeData(4);
			}
		}
	}
}
