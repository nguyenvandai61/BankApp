package client.tools;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import client.msg.MsgGetBalance;
import client.msg.MsgHead;
import client.msg.MsgLogin;

public class PackageTool {

	public PackageTool() {
		// TODO Auto-generated constructor stub
	}

	protected static void writeString(DataOutputStream dos, int len, String s) throws IOException {
		byte[] data = s.getBytes();
		System.out.println("Send" + s);
		dos.write(data);
		while (data.length < len) {
			dos.writeByte('\0');
			len--;
		}
	}

	public static byte[] packMsg(MsgHead msg) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		writeHead(dos, msg);
		int msgType = msg.getType();
		System.out.println("-----------------------------------");
		
		System.out.println("Đóng gói loại: "+ msgType);
		if (msgType == 0x02) {
			MsgLogin mli = (MsgLogin) msg;
			writeString(dos, 10, mli.getUsername());
			dos.flush();
			writeString(dos, 10, mli.getPassword());
		} else if (msgType == 0x03) {
			MsgGetBalance mgb = (MsgGetBalance) msg;
			writeString(dos, 10, mgb.getUsername());
		} else if (msgType == 0x04) {
			// Deposit fund
			MsgGetBalance mgb = (MsgGetBalance) msg;
			writeString(dos, 10, mgb.getUsername());
			dos.flush();
			System.out.println(mgb.getAmount());
			dos.writeLong(mgb.getAmount());
		} else if (msgType == 0x05) {
			// Deposit fund
			MsgGetBalance mgb = (MsgGetBalance) msg;
			writeString(dos, 10, mgb.getUsername());
			dos.flush();
			System.out.println(mgb.getAmount());
			dos.writeLong(mgb.getAmount());
		}else if (msgType == 0x06) {
			// Deposit fund
			MsgGetBalance mgb = (MsgGetBalance) msg;
			writeString(dos, 10, mgb.getUsername());
			dos.flush();
			writeString(dos, 10, mgb.getDestUsername());
			dos.flush();
			System.out.println(mgb.getAmount());
			dos.writeLong(mgb.getAmount());
		}
		dos.flush();
		byte[] data = baos.toByteArray();
		return data;
	}

	private static void writeHead(DataOutputStream dos, MsgHead msg) throws IOException {
		dos.writeInt(msg.getTotalLen());
		dos.writeByte(msg.getType());
//		dos.writeInt(msg.getDest());
//		dos.writeInt(msg.getSrc());
	}
}
