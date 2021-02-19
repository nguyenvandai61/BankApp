package client.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaneWithdrawCash extends PaneBase{
	private JLabel lbBalance;
	private JButton btn100;
	private JButton btn200;
	private JButton btn500;
	private JButton btn1000;
	private JButton btnOther;
	private JButton btnBack;
	private JButton btnCancel;
	
	private OperationView ov;
	private int balance = 0;
	public void initGUI() {
		lbBalance = new JLabel("Bạn muốn rút bao nhiêu?");
		btn100 = new JButton("100.000");
		btn200 = new JButton("200.000");
		btn500 = new JButton("500.000");
		btn1000 = new JButton("1.000.000");
		btnOther = new JButton("Nhập khác");
		this.add(lbBalance);
		this.add(btn100);
		this.add(btn200);
		this.add(btn500);
		this.add(btn1000);
		this.add(btnOther);
		super.initGUI();
	}
	private void eventHandler() {
		btn100.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Thực hiện rút tiền 100000");
				ov.getBankClient().withdrawCash(ov.getUsername(), 100000l);
			}
		});
		btn200.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Thực hiện rút tiền 200000");
				ov.getBankClient().withdrawCash(ov.getUsername(), 200000l);
			}
		});
		btn500.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Thực hiện rút tiền 500000");
				ov.getBankClient().withdrawCash(ov.getUsername(), 500000l);
			}
		});
		btn1000.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Thực hiện rút tiền 1000000");
				ov.getBankClient().withdrawCash(ov.getUsername(), 1000000l);
			}
		});
	}
	public PaneWithdrawCash(OperationView ov) {
		super(ov);
		this.ov = ov;
		initGUI();
		eventHandler();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
