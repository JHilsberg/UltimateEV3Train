package train;

import java.io.*;
import java.net.*;

import lejos.hardware.lcd.LCD;

public class Client {

	private Socket socket;
	private PrintWriter clientOutput;
	private BufferedReader clientInput;

	public Client(String ipAddress, int socketPort) {

		try {
			socket = new Socket(ipAddress, socketPort);
			clientOutput = new PrintWriter(socket.getOutputStream(), true);
			clientInput = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LCD.drawString("ERROR please start", 0, 0);
			LCD.drawString("other train first", 0, 1);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}

	public void writeData(String data) throws IOException {
		clientOutput.println(data);
		clientOutput.flush();
	}

	public String readData() throws IOException {
		String receivedData;
		while ((receivedData = clientInput.readLine()) != null) {
			return receivedData;
		}
		return "no data";
	}
}
