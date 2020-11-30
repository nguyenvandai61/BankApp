package server.msg;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MsgGetBalanceResp extends MsgHead{
	private byte state;
	private Long balance;
	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public MsgGetBalanceResp() {
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public byte[] packMessage() throws IOException {
		ByteArrayOutputStream bous = new ByteArrayOutputStream();
		DataOutputStream dous = new DataOutputStream(bous);

		System.out.println("Đóng gói gửi về loại: "+ this.getType());
		packMessageHead(dous);
		if (getType() == 0x30) {
			dous.writeLong(getBalance());			
		} else if (this.getType() == 0x40) {
			// Deposit fund
			dous.writeByte(state);
		} else if (this.getType() == 0x50) {
			// Withdraw cash
			dous.writeByte(state);	
		} else if (this.getType() == 0x60) {
			// Transfer money
			dous.writeByte(state);
		}
		dous.flush();
		byte[] data = bous.toByteArray();
		return data;
	}

	
}
