package server.msg;

public class MsgGetBalance extends MsgHead{
	private String username;
	private String destUsername;
	private Long amount;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getDestUsername() {
		return destUsername;
	}

	public void setDestUsername(String destUsername) {
		this.destUsername = destUsername;
	}
}
