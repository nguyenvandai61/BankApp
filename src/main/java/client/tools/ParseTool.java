package client.tools;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import client.msg.MsgHead;

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
		byte msgType = dis.readByte();
		int dest = dis.readInt();
		int src = dis.readInt();
		
		
		return null;
	}
}
