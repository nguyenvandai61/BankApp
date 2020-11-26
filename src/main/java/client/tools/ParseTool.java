package client.tools;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

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
		byte state = dis.readByte();
		MsgLoginResp mlr = new MsgLoginResp();

		System.out.println("MsgState"+state);
		mlr.setState(state);
		mlr.setType(msgType);
		return mlr;
	}
}
