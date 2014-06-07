import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import terminal.Server;

public class ServerTest {

	private Server server = new Server("192.168.0.104", 1111);

	public static void main(String[] args) {
		try {
			new ServerTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	ServerTest() throws Exception {
		while (!Button.ESCAPE.isDown()) {
			LCD.drawString("" + server.readData(), 0, 0);
			if (server.readData() == 1) {
				server.writeData(1);
			}
			if (server.readData() == 2) {
				server.writeData(2);
			}
		}
	}
}
