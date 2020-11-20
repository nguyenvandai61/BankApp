package com.github.nguyenvandai61.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import server.msg.MsgHead;
import server.msg.MsgLogin;

public class ServerThread extends Thread {
	private Socket client;
	private boolean isLogined = false;

	public ServerThread(Socket client) {
		// TODO Auto-generated constructor stub
		System.out.println(client.toString());
		this.client = client;
		System.out.println(this.client.toString());

	}

	public void run() {
		try {
		while (!isLogined) {
				processLogin();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processLogin() throws IOException {
		InputStream is;
		is = client.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		System.out.println("lala");
		MsgHead msg = MsgHead.readMessageFromStream(dis);
		System.out.println(msg.toString());
		if (msg.getType() == 0x02) {
			MsgLogin ml = (MsgLogin) msg;
			System.out.println(ml.getUsername() + ml.getPassword());
		}
	}
}
