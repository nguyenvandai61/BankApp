package client;

import client.screens.MainView;

public class StartClient {
	private BankClient bankClient;
	private void connectServer() {
		// TODO Auto-generated method stub
		bankClient = new BankClient("localhost", 3000);
		if (!bankClient.connectServer()) {
			System.out.println("Unable connect to server!");
		}
		System.out.println("Success in connecting to server!");
	}
	public static void main(String[] args) {
		StartClient startClient = new StartClient();
		// TODO Auto-generated method stub
		startClient.connectServer();
		MainView mainView = new MainView(startClient.bankClient);
	}

}
