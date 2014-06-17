package train;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private Socket socket;
	private PrintWriter clientOutput;
	private BufferedReader clientInput;
	
	public Client(String ipAdress, int socketPort){
		try {
			socket = new Socket(ipAdress, socketPort);
			clientOutput = new PrintWriter(socket.getOutputStream(), true);
			clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeData(String data) throws IOException{
		clientOutput.println(data);
	}
	
	public String readData() throws IOException{
		return clientInput.readLine();
	}
}
