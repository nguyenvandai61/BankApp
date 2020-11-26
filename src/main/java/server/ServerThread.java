package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import server.controller.UserController;
import server.dao.UserDAO;
import server.msg.MsgHead;
import server.msg.MsgLogin;
import server.msg.MsgLoginResp;

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
		OutputStream os;
		UserController userController = new UserController();

		is = client.getInputStream();
		os = client.getOutputStream();
		
		DataInputStream dis = new DataInputStream(is);
		MsgHead msg = MsgHead.readMessageFromStream(dis);
		System.out.println(msg.toString());
		if (msg.getType() == 0x02) {
			MsgLogin ml = (MsgLogin) msg;
			System.out.println(ml.getUsername() + ml.getPassword());
			boolean isSuccessLogin = userController.isUser(ml.getUsername(), ml.getPassword());

			MsgLoginResp mlr;
			mlr = new MsgLoginResp(isSuccessLogin?1:0);
			mlr.send(os);
		}
	}
}
