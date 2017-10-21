import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaymentView extends JFrame {

	private JButton btnCash, btnEBT, btnCredit;
	private JPanel container;
	private JLabel AmountDueLabel;
	private double amountDue;
	
	private Font font;

	public PaymentView(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public PaymentView() {
		super("Payment Menu");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setSize(500, 500);
		btnCash = new JButton("Cash");
		btnEBT = new JButton("EBT");
		btnCredit = new JButton("Credit");
		container = new JPanel();
		font = new Font("Sans Serif", Font.BOLD, 28);
		btnCash.setFont(font);
		btnEBT.setFont(font);
		btnCredit.setFont(font);
	
		amountDue = getAmountDue();
		AmountDueLabel = new JLabel("Amount Due: " + amountDue);
		AmountDueLabel.setFont(font);

		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		btnCash.setPreferredSize(new Dimension(300, 100));
		btnEBT.setPreferredSize(new Dimension(300, 100));
		btnCredit.setPreferredSize(new Dimension(300, 100));

		container.add(btnCash);
		container.add(btnEBT);
		container.add(btnCredit);
		
		
		getContentPane().add(container);
		getContentPane().add(AmountDueLabel);
		invalidate();

	}

	public double getAmountDue() {
		return amountDue;
	}
	
	public void setAmountDue(double amountDueIn) {
		amountDue = amountDueIn;
	}
	
	public JPanel getJPanel() {
		return container;
	}
	
	public JLabel getAmountDueLabel() {
		return AmountDueLabel;
	}
	
	public JButton getBtnCash() {
		return btnCash;
	}

	public JButton getBtnEBT() {
		return btnEBT;
	}

	public JButton getBtnCredit() {
		return btnCredit;
	}

	public PaymentView(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

}
