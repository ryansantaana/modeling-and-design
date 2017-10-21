import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnCheckout = new JButton("Checkout");
    private JButton btnManage   = new JButton("Manage Product");
    private Font font;

    public MainScreen() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        font = new Font("Sans Serif", Font.BOLD, 28);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        btnManage.setPreferredSize(new Dimension(300, 100));
        btnCheckout.setPreferredSize(new Dimension(300, 100));
        btnCheckout.setFont(font);
        btnManage.setFont(font);

 

        JLabel title = new JLabel("Store Management System");
        title.setFont(font);
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        JPanel panelButton = new JPanel();
        panelButton.add(btnCheckout);
        panelButton.add(btnManage);

        this.getContentPane().add(panelButton);

        btnCheckout.addActionListener(new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getCheckoutScreen().setVisible(true);            }
        });


        btnManage.addActionListener(new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getProductView().setVisible(true);
            }
        });
    }


}
