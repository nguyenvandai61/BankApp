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
		String username = "daica";
		Long amount = 500000l;
//		startClient.bankClient.depositFund(username, amount);
//		startClient.bankClient.getBalance(username);
//		startClient.bankClient.withdrawCash(username, amount);
//		startClient.bankClient.transferMoney("Dai", "daica", amount);
		MainView mainView = new MainView(startClient.bankClient);
	}

}
