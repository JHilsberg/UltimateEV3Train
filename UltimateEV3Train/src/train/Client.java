package train;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public Client(String ipAdress, int socketPort){
		try {
			socket = new Socket(ipAdress, socketPort);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeData(int data) throws IOException{
		dos.writeInt(data);
		dos.flush();
	}
	
	public int readData() throws IOException{
		return dis.readInt();
	}
}
