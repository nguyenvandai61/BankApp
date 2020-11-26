package client.tools;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import client.msg.MsgHead;
import client.msg.MsgLogin;

public class PackageTool {

	public PackageTool() {
		// TODO Auto-generated constructor stub
	}
	protected static void writeString(DataOutputStream dos, int len, String s) throws IOException {
		byte[] data = s.getBytes();
		System.out.println("Send"+s);
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
		if (msgType == 0x02) {
			MsgLogin mli = (MsgLogin) msg;
			writeString(dos, 10, mli.getUsername());
			dos.flush();
			writeString(dos, 10, mli.getPassword());
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
