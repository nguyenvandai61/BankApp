package com.github.nguyenvandai61.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BankClient extends Thread{
	private String serverIP;
	private int port;
	private Socket client;
	private InputStream is;
	private OutputStream os;
	
	public BankClient(String serverIP, int port) {
		// TODO Auto-generated constructor stub
		this.serverIP = serverIP;
		this.port = port;
	}
	public void run() {
		
	}
	
	public boolean connectServer() {
		try {
			client = new Socket(serverIP, port);
			System.out.print("Connected to server!!");
			is = client.getInputStream();
			os = client.getOutputStream();
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
