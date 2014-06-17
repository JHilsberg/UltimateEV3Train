package train;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private Socket s;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public Client(String ipAdress, int socketPort){
		try {
			s = new Socket(ipAdress, socketPort);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeData(int data) throws IOException{
		dos.write(data);
		dos.flush();
	}
	
	public int readData() throws IOException{
		return dis.read();
	}
}
