package server.msg;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import server.tools.ParseTool;


public class MsgHead {

	private int totalLen;
	private byte type;
	private int dest;
	private int src;

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

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
	}

	public static MsgHead readMessageFromStream(DataInputStream stream) throws IOException {
		System.out.println("Read message from stream");
		
		int totalLen = stream.readInt();
		System.out.println(totalLen);
		
		byte[] data = new byte[totalLen - 4];
		stream.read(data);
		System.out.println("Read message from stream total length "+ totalLen);
		MsgHead message = ParseTool.parseMsg(data);
		return message;
	}
	public void send(OutputStream outputStream) throws IOException {
		byte[] message = this.packMessage();
		outputStream.write(message);
		outputStream.flush();
	}

	protected void packMessageHead(DataOutputStream dous) throws IOException {
		dous.writeInt(getTotalLen());
		dous.writeByte(getType());
		dous.writeInt(getDest());
		dous.writeInt(getSrc());
	}
	protected void writeString(DataOutputStream dous, int len, String s) throws IOException {
		byte[] data = s.getBytes();
		if (data.length > len) {
			throw new IOException("Data has length bigger");
		}
		dous.write(data);
		while (data.length < len) {
			dous.writeByte('\0');
			len--;
		}
	}
	public byte[] packMessage() throws IOException {
		ByteArrayOutputStream bous = new ByteArrayOutputStream();
		DataOutputStream dous = new DataOutputStream(bous);
		packMessageHead(dous);
		dous.flush();
		byte[] data = bous.toByteArray();
		return data;
	}

	@Override
	public String toString() {
		return "MsgHead [totalLen=" + totalLen + ", type=" + type + ", dest=" + dest + ", src=" + src + "]";
	}
	
}
