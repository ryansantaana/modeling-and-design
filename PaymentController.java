import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PaymentController implements ActionListener {

	private PaymentView paymentView;
	private DataAdapter dataAdapter;
	private double localCopyOfAmountDue;
	private double change = 0.00;

	public PaymentController(PaymentView paymentView, DataAdapter dataAdapter) {
		this.paymentView = paymentView;
		this.dataAdapter = dataAdapter;

		paymentView.getBtnCash().addActionListener(this);
		paymentView.getBtnEBT().addActionListener(this);
		paymentView.getBtnCredit().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == paymentView.getBtnCash()) {
			String input = JOptionPane.showInputDialog("Enter tender Amount");
			double inputDouble;
			try {
				if (input.indexOf('.') < input.length() - 3) {
					inputDouble = Double.parseDouble(input);
					JOptionPane.showMessageDialog(null, "Cannot pay a fraction of a cent!");
					return;
				}
				inputDouble = Double.parseDouble(input);
				localCopyOfAmountDue = paymentView.getAmountDue();
				localCopyOfAmountDue -= inputDouble;
				paymentView.setAmountDue(localCopyOfAmountDue);
				paymentView.getAmountDueLabel().setText("Amount Due: $" + String.format("%.2f", localCopyOfAmountDue));

				if (localCopyOfAmountDue < 0.005) {
					change = -1 * localCopyOfAmountDue;
					if (change < 0.05) {
						change = 0.0;
					}
					paymentView.getAmountDueLabel().setText("Change Due: $" + String.format("%.2f", change));
					if(change == 0.0){
						JOptionPane.showMessageDialog(null, "Thank you for shopping with us!");
					}
					else{
						JOptionPane.showMessageDialog(null, "Your change is $" + String.format("%.2f", change)
						+ ". Please take your change from the machine. Thank you for shopping with us!");
					}
					Application.getInstance().getPaymentView().setVisible(false);

				}
			}
			catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Invalid input!");
				return;
			}

		} else if (e.getSource() == paymentView.getBtnEBT()) {
			JOptionPane.showMessageDialog(null, "This function is not yet implemented!");
			return;
		} else if (e.getSource() == paymentView.getBtnCredit()) {
			JOptionPane.showMessageDialog(null, "This function is not yet implemented!");
			return;
		}
	}

}
