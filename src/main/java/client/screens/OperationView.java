package client.screens;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OperationView {
	private JFrame frame;
	private JPanel pnMain;

	private JButton btnCheckBalance;
	private JButton btnWithdrawCash;
	private JButton btnDepositFund;
	private JButton btnTransferFunds;
	private JButton btnCancel;
	
	

	private void initGUI() {
		btnCheckBalance = new JButton("Kiểm tra tài khoản");
		btnWithdrawCash = new JButton("Rút tiền");
		btnDepositFund = new JButton("Nạp tiền");
		btnTransferFunds = new JButton("Chuyển tiền");
		btnCancel = new JButton("Hủy bỏ giao dịch");
		
		pnMain = new JPanel();
		pnMain.setLayout(new GridLayout(4, 1));
		pnMain.add(btnCheckBalance);
		pnMain.add(btnWithdrawCash);
		pnMain.add(btnDepositFund);
		pnMain.add(btnTransferFunds);
		pnMain.add(btnCancel);
		
		
		frame = new JFrame("Client");
		frame.setSize(400, 400);
		frame.setLayout(new GridLayout(3, 1));
		frame.add(pnMain);
		frame.setVisible(true);
	}
	public OperationView() {
		initGUI();
	}
	
	public static void main(String args[]) {
		OperationView operationView = new OperationView();
	}
}
