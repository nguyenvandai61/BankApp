package client.screens;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PaneBase extends JPanel{
	private OperationView operationView;
	private JButton btnBack;
	private JButton btnCancel;
	public PaneBase(OperationView ov) {
		this.operationView = ov;
	}
	protected void initGUI() {
		btnBack = new JButton("Trở lại");
		btnCancel = new JButton("Hủy bỏ giao dịch");
		this.add(btnBack);
		this.add(btnCancel);
		eventHandler();
	}
	private void eventHandler() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("lala");
				operationView.getCardLayout().show(operationView.getPaneMain(), "0");
			}
		});
	}
	
}
