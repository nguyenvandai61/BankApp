package server.msg;

import java.util.Arrays;

public class MsgLogin extends MsgHead{
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MsgLogin() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public String toString() {
		return "MsgLogin [username=" + username + ", password=" + password+ ", toString()=" + super.toString() + "]";
	}

}
