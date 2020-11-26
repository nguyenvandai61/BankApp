package client.msg;

public class MsgLoginResp extends MsgHead{
	private byte state;
	
	public MsgLoginResp() {
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}
	
}
