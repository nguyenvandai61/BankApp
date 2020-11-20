package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainView {
	private BankClient bankClient;
	
	private JFrame mainFrame;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JButton btnLogin;
	private JPanel pnLogin;
	
	private String username;
	private String password;
	
	private void initLoginGUI() {
		tfUsername = new JTextField(20);
		tfPassword = new JTextField(20);
		btnLogin = new JButton("Login");
		
		pnLogin = new JPanel();
		mainFrame = new JFrame("Client");
		
		pnLogin.setLayout(new FlowLayout());
		pnLogin.add(tfUsername);
		pnLogin.add(tfPassword);
		pnLogin.add(btnLogin);
		
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.add(pnLogin);
		mainFrame.setVisible(true);
	}
	
	private void handleLogin() {
//		username = tfUsername.getText();
//		password = tfPassword.getText();
		username = "Dai";
		password = "123";
		requestLogin(username, password);
	}
	private void requestLogin(String username, String password) {
		// TODO Auto-generated method stub
		if ("".equals(username)) {
			JOptionPane.showMessageDialog(null, "Please fill in username!", "Error", JOptionPane.ERROR_MESSAGE);
		} else if ("".equals(password)){
			JOptionPane.showMessageDialog(null, "Please fill in password!", "Error", JOptionPane.ERROR_MESSAGE);	
		} else {
			boolean res = bankClient.login(username, password);
			
		}
		
	}
	
	private void connectServer() {
		// TODO Auto-generated method stub
		bankClient = new BankClient("localhost", 3000);
		if (!bankClient.connectServer()) {
			System.out.println("Unable connect to server!");
		}
		System.out.println("Success in connecting to server!");
	}


	public MainView() {
		// TODO Auto-generated constructor stub
		initLoginGUI();
		connectServer();
		handleLogin();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainView mainView = new MainView();
	}
}
