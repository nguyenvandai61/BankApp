package client.screens;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaneDepositFund extends PaneBase{
	private JLabel lbNoti;
	
	private int balance = 0;
	public void initGUI() {
		lbNoti = new JLabel("Đặt tiền vào ngăn bên dưới");
		this.add(lbNoti);
		
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
	public PaneDepositFund(OperationView ov) {
		super(ov);
		initGUI();
		eventHandler();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
