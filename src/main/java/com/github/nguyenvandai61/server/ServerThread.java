package com.github.nguyenvandai61.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerThread extends Thread{
	private Socket client;
	public ServerThread(Socket client) {
		// TODO Auto-generated constructor stub
		this.client = client;
	}
	public void run() {
		
	}
	private void processLogin() throws IOException {
		InputStream is = client.getInputStream();
		DataInputStream dis = new DataInputStream(is);
	}
}
