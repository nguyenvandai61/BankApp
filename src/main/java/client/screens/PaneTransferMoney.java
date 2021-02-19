package client.screens;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PaneTransferMoney extends PaneBase{
	private JLabel lbNoti;
	private JTextField tfDest;
	private JLabel lbNoti2;
	private JTextField tfAmount;
	private JButton btnTransfer;
	private int balance = 0;
	public void initGUI() {
		lbNoti = new JLabel("Nhập số tài khoản: ");
		tfDest = new JTextField(20);
		lbNoti2 = new JLabel("Nhập số tiền gửi: ");
		tfAmount = new JTextField(20);
		btnTransfer = new JButton("Chuyển");
		
		this.add(lbNoti);
		this.add(tfDest);
		this.add(lbNoti2);
		this.add(tfAmount);
		this.add(btnTransfer);
		
		super.initGUI();
	}
	private void eventHandler() {
//		btnBack.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				OperationView.frame.add()
//			}
//		});
	}
	public PaneTransferMoney(OperationView ov) {
		super(ov);
		initGUI();
		eventHandler();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
