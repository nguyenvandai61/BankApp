package com.github.nguyenvandai61.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainView {
	private JFrame mainFrame;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JPanel pnLogin;
	private void initLoginGUI() {
		tfUsername = new JTextField(20);
		tfPassword = new JTextField(20);
		
		pnLogin = new JPanel();
		mainFrame = new JFrame("Client");
		
		pnLogin.setLayout(new FlowLayout());
		pnLogin.add(tfUsername);
		pnLogin.add(tfPassword);
		
		
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.add(pnLogin);
		mainFrame.setVisible(true);
	}
	public MainView() {
		// TODO Auto-generated constructor stub
		initLoginGUI();
		connectServer();
	}
	
	private void connectServer() {
		// TODO Auto-generated method stub
		BankClient client = new BankClient("localhost", 3000);
		if (!client.connectServer()) {
			System.out.println("Unable connect to server!");
		}
		System.out.println("Success in connecting to server!");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainView mainView = new MainView();
	}
}
