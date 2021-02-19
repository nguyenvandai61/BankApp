package server.models;

public class User extends Entity{
    private String name;
    private String username;
    private String password;
    private String role;
    private Long balance;

    public User(Long id, String username, String password, String role, Long balance) {
        this.setId(id);
        this.username = username;
        this.password = password;
        this.role = role;
        this.setBalance(balance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
}
