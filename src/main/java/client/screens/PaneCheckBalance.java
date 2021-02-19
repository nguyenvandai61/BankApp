package client.screens;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaneCheckBalance extends PaneBase{
	private JLabel lbBalance;
	private JButton btnBack;
	private JButton btnCancel;
	
	private Long balance = 0l;
	public void initGUI() {
		lbBalance = new JLabel("Số tiền của bạn là: "+Long.toString(balance));
		this.add(lbBalance);
		super.initGUI();
	}
	public void setTextBalance(Long balance) {
		lbBalance.setText("Số tiền của bạn là: "+Long.toString(balance));
	}

	public PaneCheckBalance(OperationView ov) {
		super(ov);
		this.balance = ov.getBalance();;
		initGUI();
	}
}
