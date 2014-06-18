package terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket server;
	private Socket socket;
	private PrintWriter serverOutput;
	private BufferedReader serverInput;

	public Server(int socketPort) {
		try {
			server = new ServerSocket(socketPort);
			socket = server.accept();
			serverOutput = new PrintWriter(socket.getOutputStream(), true);
			serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readData() throws IOException {
		return serverInput.readLine();
	}
	
	public void writeData(String data) throws IOException{
		serverOutput.println(data);
		serverOutput.flush();
	}
}
