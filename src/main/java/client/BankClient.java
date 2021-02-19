package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import client.msg.MsgGetBalance;
import client.msg.MsgGetBalanceResp;
import client.msg.MsgHead;
import client.msg.MsgLogin;
import client.tools.PackageTool;
import client.tools.ParseTool;
import client.msg.MsgLoginResp;

public class BankClient extends Thread{
	private String serverIP;
	private int port;
	private Socket client;
	private InputStream is;
	private OutputStream os;
	private String username = "Dai";
	
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
	public byte[] receiveMsg() throws IOException {
		DataInputStream dis = new DataInputStream(is);
		int totalLen = dis.readInt();
		System.out.println("TotalLen"+totalLen);
		byte[] data = new byte[totalLen - 4];
		dis.read(data);
		System.out.println(data.toString());
		return data;
	}
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		MsgLogin msgLogin = new MsgLogin();
		int len = 50;
		byte type = 0x02;
		
		msgLogin.setUsername(username);
		msgLogin.setPassword(password);
		msgLogin.setTotalLen(len);
		msgLogin.setType(type);
		try {
			byte[] sendMsg = PackageTool.packMsg(msgLogin);
			os.write(sendMsg);
			// Receive
			byte[] data = receiveMsg();
			System.out.println("Nhận data");
			MsgHead recMsg = ParseTool.parseMsg(data);
			System.out.println(recMsg);
			if (recMsg.getType() != 0x22) {
				return false;
			}
			MsgLoginResp mlr = (MsgLoginResp) recMsg;
			byte state = mlr.getState();
			if (state == 1) {
				this.username = username;
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Long getBalance(String username) {
		Long balance = 0l;
		int len = 20;
		byte type = 0x03;
		MsgGetBalance mgb = new MsgGetBalance();
		mgb.setTotalLen(len);
		mgb.setType(type);
		mgb.setUsername(username);
		try {
			byte[] sendMsg = PackageTool.packMsg(mgb);
			os.write(sendMsg);
			byte[] data = receiveMsg();
			System.out.println("Nhận data get balance");
			MsgHead recMsg = ParseTool.parseMsg(data);
			System.out.println(recMsg);
			if (recMsg.getType() != 0x30) {
				return 0l;
			}
			MsgGetBalanceResp mlr = (MsgGetBalanceResp) recMsg;
			balance = mlr.getBalance();
			System.out.println(balance);
		} catch(IOException e) {
			
		}
		return balance;
	}
	public boolean depositFund(String username, Long amount) {
		int len = 40;
		byte type = 0x04;
		MsgGetBalance mgb = new MsgGetBalance();
		mgb.setTotalLen(len);
		mgb.setType(type);
		mgb.setUsername(username);
		mgb.setAmount(amount);
		try {
			byte[] sendMsg = PackageTool.packMsg(mgb);
			os.write(sendMsg);
			byte[] data = receiveMsg();
			System.out.println("Nhận data deposit fund");
			MsgHead recMsg = ParseTool.parseMsg(data);
			System.out.println(recMsg);
			if (recMsg.getType() != 0x40) {
				return false;
			}
			MsgGetBalanceResp mlr = (MsgGetBalanceResp) recMsg;
			return true;
		} catch(IOException e) {
			
		}
		return false;
	}
	public boolean withdrawCash(String username, Long amount) {
		int len = 40;
		byte type = 0x05;
		MsgGetBalance mgb = new MsgGetBalance();
		mgb.setTotalLen(len);
		mgb.setType(type);
		mgb.setUsername(username);
		mgb.setAmount(amount);
		try {
			byte[] sendMsg = PackageTool.packMsg(mgb);
			os.write(sendMsg);
			byte[] data = receiveMsg();
			System.out.println("Nhận data withdrawCash");
			MsgHead recMsg = ParseTool.parseMsg(data);
			System.out.println(recMsg);
			if (recMsg.getType() != 0x50) {
				return false;
			}
			MsgGetBalanceResp mlr = (MsgGetBalanceResp) recMsg;
			return true;
		} catch(IOException e) {
			
		}
		return false;
	}
	public boolean transferMoney(String username, String destUsername, Long amount) {
		int len = 40;
		byte type = 0x06;
		MsgGetBalance mgb = new MsgGetBalance();
		mgb.setTotalLen(len);
		mgb.setType(type);
		mgb.setUsername(username);
		mgb.setAmount(amount);
		try {
			byte[] sendMsg = PackageTool.packMsg(mgb);
			os.write(sendMsg);
			byte[] data = receiveMsg();
			System.out.println("Nhận data transferMoney");
			MsgHead recMsg = ParseTool.parseMsg(data);
			System.out.println(recMsg);
			if (recMsg.getType() != 0x60) {
				return false;
			}
			MsgGetBalanceResp mlr = (MsgGetBalanceResp) recMsg;
			return true;
		} catch(IOException e) {
			
		}
		return false;
	}
}
