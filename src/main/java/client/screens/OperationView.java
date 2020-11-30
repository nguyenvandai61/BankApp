package client.screens;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import client.BankClient;

public class OperationView {
	private BankClient bankClient;
	static JFrame frame;
	private JPanel pnMain;
	private JPanel pnHome;
	private CardLayout cardLayout;
	private String username = "Dai";
	private Long balance = 0l;
	
	private JButton btnCheckBalance;
	private JButton btnWithdrawCash;
	private JButton btnDepositFund;
	private JButton btnTransferFunds;
	private JButton btnCancel;
	
	JPanel pnCheckBalance;
	JPanel pnWithdrawBalance;
	JPanel pnDepositFund;
	JPanel pnTransferMoney;
	private void initGUI() {
		btnCheckBalance = new JButton("Kiểm tra tài khoản");
		btnWithdrawCash = new JButton("Rút tiền");
		btnDepositFund = new JButton("Nạp tiền");
		btnTransferFunds = new JButton("Chuyển tiền");
		btnCancel = new JButton("Hủy bỏ giao dịch");
		
		cardLayout = new CardLayout();
		pnMain = new JPanel(cardLayout);
		
		pnHome = new JPanel();
		pnHome.setLayout(new GridLayout(5, 1));
		pnHome.add(btnCheckBalance);
		pnHome.add(btnWithdrawCash);
		pnHome.add(btnDepositFund);
		pnHome.add(btnTransferFunds);
		pnHome.add(btnCancel);
		
		
		pnCheckBalance = new PaneCheckBalance(this);
		pnWithdrawBalance = new PaneWithdrawCash(this);
		pnDepositFund = new PaneDepositFund(this);
		pnTransferMoney = new PaneTransferMoney(this);
		
		pnMain.add(pnHome, "0");
		pnMain.add(pnCheckBalance, "1");
		pnMain.add(pnWithdrawBalance, "2");
		pnMain.add(pnDepositFund, "3");
		pnMain.add(pnTransferMoney, "4");
		
		frame = new JFrame("Client");
		frame.setSize(400, 400);
		frame.setLayout(new GridLayout(3, 1));
		frame.add(pnMain);
		frame.setVisible(true);
	}
	
	private void eventHandler() {
		btnCheckBalance.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				balance = bankClient.getBalance(username);
				System.out.println(balance);
				((PaneCheckBalance) pnCheckBalance).setTextBalance(balance);
				cardLayout.show(pnMain, "1");
			}
		});
		
		btnWithdrawCash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(pnMain, "2");
			}
		});
		btnDepositFund.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(pnMain, "3");
			}
		});
		btnTransferFunds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(pnMain, "4");
			}
		});
	}
	public OperationView(BankClient bankClient) {
		this.bankClient = bankClient;
		initGUI();
		eventHandler();
	}
	public OperationView() {
		initGUI();
		eventHandler();
	}
	
	public String getUsername() {
		return username;
	}
	public BankClient getBankClient() {
		return bankClient;
	}
	public Long getBalance() {
		return balance;
	}
	
	public CardLayout getCardLayout() {
		return cardLayout;
	}
	public JPanel getPaneMain() {
		return pnMain;
	}
	public static void main(String args[]) {
		OperationView operationView = new OperationView();
	}
}
