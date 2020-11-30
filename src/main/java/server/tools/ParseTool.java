package server.tools;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import server.msg.MsgGetBalance;
import server.msg.MsgHead;
import server.msg.MsgLogin;

public class ParseTool {

	private static String readString(DataInputStream dins, int len) throws IOException {
		byte[] data = new byte[len];
		dins.readFully(data);
		return new String(data).trim();
	}

	public static MsgHead parseMsg(byte[] data) throws IOException {
		int totalLen = data.length + 4; 
		System.out.println("totalLen"+totalLen);
		for (int i = 0; i <data.length; i++) {
			System.out.println(data[i]+" ");
		}
		ByteArrayInputStream bins = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bins);
		byte msgtype = dis.readByte();
		System.out.println(msgtype);

		if (msgtype == 0x02) {
			String username = readString(dis, 10);
			String password = readString(dis, 10);
			System.out.println(username);
			System.out.println(password);
			MsgLogin mli = new MsgLogin();
			mli.setTotalLen(totalLen);
			mli.setType(msgtype);
//			mli.setDest(dest);
//			mli.setSrc(src);
			mli.setUsername(username);
			mli.setPassword(password);
			return mli;
		} else if (msgtype == 0x03) {
			String username = readString(dis, 10);
			MsgGetBalance mgb = new MsgGetBalance();
			mgb.setTotalLen(totalLen);
			mgb.setType(msgtype);
			mgb.setUsername(username);
			return mgb;
		} else if (msgtype == 0x04) {
			// Deposit fund
			String username = readString(dis, 10);
			Long amount = dis.readLong();
			MsgGetBalance mgb = new MsgGetBalance();
			mgb.setTotalLen(totalLen);
			mgb.setType(msgtype);
			mgb.setUsername(username);
			mgb.setAmount(amount);
			return mgb;
		} else if (msgtype == 0x05) {
			// Withdraw cash
			String username = readString(dis, 10);
			Long amount = dis.readLong();
			MsgGetBalance mgb = new MsgGetBalance();
			mgb.setTotalLen(totalLen);
			mgb.setType(msgtype);
			mgb.setUsername(username);
			mgb.setAmount(amount);
			return mgb;
		} else if (msgtype == 0x06) {
			// Transfer money
			String username = readString(dis, 10);
			String destUsername = readString(dis, 10);
			Long amount = dis.readLong();
			MsgGetBalance mgb = new MsgGetBalance();
			mgb.setTotalLen(totalLen);
			mgb.setType(msgtype);
			mgb.setUsername(username);
			mgb.setDestUsername(destUsername);
			mgb.setAmount(amount);
			return mgb;
		}
		return null;

		
	}
}
