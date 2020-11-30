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
import server.msg.MsgGetBalance;
import server.msg.MsgGetBalanceResp;

public class ServerThread extends Thread {
	private Socket client;
	private boolean isLogined = false;
//	private boolean isLogined = true;
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
		System.out.println("Mởi gói loại : "+ msg.getType());
		System.out.println(msg.toString());
		if (msg.getType() == 0x02) {
			MsgLogin ml = (MsgLogin) msg;
			System.out.println(ml.getUsername() + ml.getPassword());
			boolean isSuccessLogin = userController.isUser(ml.getUsername(), ml.getPassword());
			MsgLoginResp mlr;
			mlr = new MsgLoginResp(isSuccessLogin?1:0);
			mlr.send(os);
		} else if (msg.getType() == 0x03){
			System.out.println("Get balance");
			MsgGetBalance mgb = (MsgGetBalance) msg;
			Long balance = userController.getBalance(mgb.getUsername());
			MsgGetBalanceResp mgbr = new MsgGetBalanceResp();
			mgbr.setTotalLen(20);
			mgbr.setType((byte)0x30);
			mgbr.setBalance(balance);
			mgbr.send(os);
		} else if (msg.getType() == 0x04) {
			MsgGetBalance mgb = (MsgGetBalance) msg;
			boolean isSuccess = userController.increaseBalance(mgb.getUsername(), mgb.getAmount());
			System.out.println("Gửi thành công: "+ isSuccess);
			if (isSuccess) {
				byte state = 1; 
				
				MsgGetBalanceResp mgbr = new MsgGetBalanceResp();
				mgbr.setTotalLen(30);
				mgbr.setType((byte) 0x40);
				mgbr.setState(state);
				mgbr.send(os);
			}
		} else if (msg.getType() == 0x05) {
			MsgGetBalance mgb = (MsgGetBalance) msg;
			System.out.println(mgb.getUsername()+" muốn rút "+mgb.getAmount());
			boolean isSuccess = userController.descreaseBalance(mgb.getUsername(), mgb.getAmount());
			System.out.println("Rút thành công: "+ isSuccess);
			if (isSuccess) {
				byte state = 1; 
				
				MsgGetBalanceResp mgbr = new MsgGetBalanceResp();
				mgbr.setTotalLen(30);
				mgbr.setType((byte) 0x50);
				mgbr.setState(state);
				mgbr.send(os);
			}
		} else if (msg.getType() == 0x06) {
			MsgGetBalance mgb = (MsgGetBalance) msg;
			boolean isSuccess = userController.transferMoney(mgb.getUsername(), mgb.getUsername(),mgb.getAmount());
			System.out.println("Chuyển tiền thành công: "+ isSuccess);
			if (isSuccess) {
				byte state = 1; 
				
				MsgGetBalanceResp mgbr = new MsgGetBalanceResp();
				mgbr.setTotalLen(30);
				mgbr.setType((byte) 0x60);
				mgbr.setState(state);
				mgbr.send(os);
			}
		}
	}
	
}
