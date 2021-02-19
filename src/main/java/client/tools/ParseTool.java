package client.tools;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import client.msg.MsgGetBalanceResp;
import client.msg.MsgHead;
import client.msg.MsgLoginResp;

public class ParseTool {

	public ParseTool() {
		// TODO Auto-generated constructor stub
	}
	private static String readString(DataInputStream dis, int len) throws IOException {
		byte[] data = new byte[len];
		dis.readFully(data);
		return new String(data).trim();
	}
	
	public static MsgHead parseMsg(byte[] data) throws IOException {
		int totalLen = data.length + 4;
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		for (int i = 0; i <data.length; i++) {
			System.out.println(data[i]+" ");
		}
		byte msgType = dis.readByte();
		
//		int dest = dis.readInt();
//		int src = dis.readInt();
		System.out.println("MsgType"+msgType);
		if (msgType == 0x22) {			
			MsgLoginResp mlr = new MsgLoginResp();
			mlr.setType(msgType);
			byte state = dis.readByte();
			mlr.setState(state);
			return mlr;
		} else if (msgType == 0x30){
			MsgGetBalanceResp mgbr = new MsgGetBalanceResp();
			Long balance = dis.readLong();
			System.out.println("balance -> client: "+ balance);
			mgbr.setBalance(balance);
			mgbr.setType(msgType);
			return mgbr;
		} else if (msgType == 0x40){
			MsgGetBalanceResp mgbr = new MsgGetBalanceResp();
			byte state = mgbr.getState();
			System.out.println("is Success: "+ state);
			mgbr.setState(state);;
			mgbr.setType(msgType);
			return mgbr;
		} else if (msgType == 0x50){
			MsgGetBalanceResp mgbr = new MsgGetBalanceResp();
			byte state = mgbr.getState();
			System.out.println("is Success: "+ state);
			mgbr.setState(state);;
			mgbr.setType(msgType);
			return mgbr;
		} else if (msgType == 0x60){
			MsgGetBalanceResp mgbr = new MsgGetBalanceResp();
			byte state = mgbr.getState();
			System.out.println("is Success: "+ state);
			mgbr.setState(state);;
			mgbr.setType(msgType);
			return mgbr;
		}
		return null;
	}
}
