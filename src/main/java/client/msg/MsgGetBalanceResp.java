package client.msg;

public class MsgGetBalanceResp extends MsgHead{
	private byte state;
	private Long balance;
	public MsgGetBalanceResp() {
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}
}
