package server.tools;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import server.msg.MsgHead;
import server.msg.MsgLogin;

public class ParseTool {

	private static String readString(DataInputStream dins, int len) throws IOException {
		byte[] data = new byte[len];
		dins.readFully(data);
		return new String(data).trim();
	}

	public static MsgHead parseMsg(byte[] data) throws IOException {
		int totalLen = data.length + 4; // 之前已经读取了4个字节的长度信息
		System.out.println("totalLen"+totalLen);
		ByteArrayInputStream bins = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bins);
		byte msgtype = dis.readByte();
//		int dest = dis.readInt();
//		int src = dis.readInt();
		System.out.println(msgtype);

		if (msgtype == 0x02) {
			String username = readString(dis, 11);
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
		}
		return null;

		
	}
}
