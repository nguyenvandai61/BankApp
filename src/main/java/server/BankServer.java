package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {
	ServerSocket server;
	final static int PORT = 3000;
	public BankServer() {
		// TODO Auto-generated constructor stub
		try {
			server = new ServerSocket(PORT);
			System.out.print("Server is running at PORT "+ PORT);
			while(true) {
				Socket client = server.accept();
				System.out.println("Connect " + client.getRemoteSocketAddress());
				ServerThread st = new ServerThread(client);
				st.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeServer() {
		try {
			server.close();
			System.out.println("Success in closing server!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankServer cs = new BankServer();
	}

}
