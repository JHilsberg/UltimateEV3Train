package terminal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket server;
	private Socket s;
	private DataInputStream dis;
	private DataOutputStream dos;

	public Server(int socketPort) {
		try {
			server = new ServerSocket(socketPort);
			s = server.accept();
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int readData() throws IOException {
		return dis.read();
	}
	
	public void writeData(int data) throws IOException{
		dos.write(data);
		dos.flush();
	}
}
