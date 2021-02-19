package client.msg;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import client.tools.ParseTool;

public class MsgHead {
	private int totalLen;
	private byte type;
	private int src;
	private int dest;
	
	public MsgHead() {
		// TODO Auto-generated constructor stub
	}
	
	public int getTotalLen() {
		return totalLen;
	}

	public void setTotalLen(int totalLen) {
		this.totalLen = totalLen;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}
	public MsgHead readMessage(DataInputStream dis) throws IOException {
		int totalLen = dis.readInt();
		byte[] data = new byte[totalLen - 4];
		dis.readFully(data);
		
		MsgHead message = ParseTool.parseMsg(data);
		return message;
	}
	
	public void send(OutputStream outputStream) throws IOException {
		byte[] message = this.packMessage();
		outputStream.write(message);
		outputStream.flush();
	}
	
	protected void writeString(DataOutputStream dos, int len, String s) throws IOException {
		byte[] data = s.getBytes();
		dos.write(data);
		while (data.length < len) {
			dos.writeByte('\0');
			len--;
		}
	}

	private byte[] packMessage() throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		packMessageHead(dos);
		dos.flush();
		
		return null;
	}

	private void packMessageHead(DataOutputStream dos) throws IOException {
		// TODO Auto-generated method stub
		dos.writeInt(getTotalLen());
		dos.writeByte(getType());
		dos.writeInt(getDest());
		dos.writeInt(getSrc());
	}
	
	
}
