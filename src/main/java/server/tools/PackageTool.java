package server.tools;

import java.io.DataOutputStream;
import java.io.IOException;

import server.msg.MsgHead;

public class PackageTool {

	public PackageTool() {
		// TODO Auto-generated constructor stub
	}
	private static void writeString(DataOutputStream dos, int len, String s) throws IOException {
		byte[] data = s.getBytes();
		if (data.length > len) {
			throw new IOException("Data has length bigger");
		}
		dos.write(data);
		while (data.length < len) {
			dos.writeByte('\0');
			len--;
		}
	}
	private static void writeHead(DataOutputStream dos, MsgHead msg) throws IOException {
		dos.writeInt(msg.getTotalLen());
		dos.writeByte(msg.getType());
		dos.writeInt(msg.getDest());
		dos.writeInt(msg.getSrc());
	}
}
