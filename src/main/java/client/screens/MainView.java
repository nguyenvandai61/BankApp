package client.screens;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.BankClient;

public class MainView {
	private BankClient bankClient;

	private JFrame mainFrame;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JLabel lbNotification;
	private JButton btnLogin;
	private JPanel pnLogin;

	private String username;
	private String password;

	private void initLoginGUI() {
		tfUsername = new JTextField(20);
		tfPassword = new JTextField(20);
		lbNotification = new JLabel("");

		btnLogin = new JButton("Login");

		pnLogin = new JPanel();
		mainFrame = new JFrame("Client");

		pnLogin.setLayout(new GridLayout(4, 1));
		pnLogin.add(tfUsername);
		pnLogin.add(tfPassword);
		pnLogin.add(btnLogin);
		pnLogin.add(lbNotification);

		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.add(pnLogin);
		mainFrame.setVisible(true);

		actionHandler();
	}

	private void handleLogin() {
		username = tfUsername.getText();
		password = tfPassword.getText();
//		username = "Dai";
//		password = "123";
//		requestLogin(username, password);
	}

	private void requestLogin(String username, String password) {
		// TODO Auto-generated method stub
		if ("".equals(username)) {
			JOptionPane.showMessageDialog(null, "Please fill in username!", "Error", JOptionPane.ERROR_MESSAGE);
		} else if ("".equals(password)) {
			JOptionPane.showMessageDialog(null, "Please fill in password!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			System.out.println("Thuc hien login");
			boolean successLogin = bankClient.login(username, password);
			if (!successLogin) {
				lbNotification.setText("Login failed!");
			} else {
				mainFrame.setVisible(false);
                mainFrame.dispose();
				new OperationView(bankClient);
			}
		}

	}

	public MainView(BankClient bankClient) {
		// TODO Auto-generated constructor stub
		this.bankClient = bankClient;
		initLoginGUI();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public void actionHandler() {
		// TODO Auto-generated method stub
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleLogin();
				requestLogin(username, password);
			}
		});
	}
}
