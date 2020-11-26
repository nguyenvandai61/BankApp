package server.msg;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MsgLoginResp extends MsgHead{
	private int state;

	public MsgLoginResp(int state) {		
		setTotalLen(20);
		setType((byte)0x22);
		setState(state);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public byte[] packMessage() throws IOException {
		ByteArrayOutputStream bous = new ByteArrayOutputStream();
		DataOutputStream dous = new DataOutputStream(bous);
		packMessageHead(dous);
		dous.write(getState());
		dous.flush();
		byte[] data = bous.toByteArray();
		return data;
	}
}
