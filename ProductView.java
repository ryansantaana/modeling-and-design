import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class ProductView extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtProductID  = new JTextField(10);
    private JTextField txtProductName  = new JTextField(30);
    private JTextField txtProductPrice  = new JTextField(10);
    private JTextField txtProductQuantity  = new JTextField(10);
    private JTextField txtTaxRate  = new JTextField(10);

    private JButton btnLoad = new JButton("Load Product");
    private JButton btnSave = new JButton("Save Product");
    
    private Font font;

    public ProductView() {
    	
        this.setTitle("Product View");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        setSize(1000,1000);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
        btnLoad.setPreferredSize(new Dimension(300, 100));
        btnSave.setPreferredSize(new Dimension(300, 100));
        
        font = new Font("Sans Serif", Font.BOLD, 28);
        
        btnLoad.setFont(font);
        btnSave.setFont(font);
        
        JPanel panelButton = new JPanel();
        panelButton.add(btnLoad);
        panelButton.add(btnSave);
        this.getContentPane().add(panelButton);
        
        JLabel productLabel = new JLabel("Product ID: ");
        productLabel.setFont(font);
        JLabel nameLabel = new JLabel("Product Name: ");
        nameLabel.setFont(font);
        JLabel priceLabel = new JLabel("Price: ");
        priceLabel.setFont(font);
        JLabel quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setFont(font);
        JLabel taxLabel = new JLabel("Tax Rate: ");
        taxLabel.setFont(font);
        

        JPanel panelProductID = new JPanel();
        panelProductID.add(productLabel);
        txtProductID.setFont(font);
        panelProductID.add(txtProductID);
        txtProductID.setHorizontalAlignment(JTextField.RIGHT);
        this.getContentPane().add(panelProductID);

        JPanel panelProductName = new JPanel();
        panelProductName.add(nameLabel);
        txtProductName.setFont(font);
        panelProductName.add(txtProductName);
        this.getContentPane().add(panelProductName);

        JPanel panelProductInfo = new JPanel();
        panelProductInfo.add(priceLabel);
        txtProductPrice.setFont(font);
        panelProductInfo.add(txtProductPrice);
        txtProductPrice.setHorizontalAlignment(JTextField.RIGHT);

        panelProductInfo.add(quantityLabel);
        txtProductQuantity.setFont(font);
        panelProductInfo.add(txtProductQuantity);
        txtProductQuantity.setHorizontalAlignment(JTextField.RIGHT);
        
        JPanel panelTaxRate = new JPanel();
        panelTaxRate.add(taxLabel);
        txtTaxRate.setFont(font);
        txtTaxRate.setHorizontalAlignment(JTextField.RIGHT);
        panelTaxRate.add(txtTaxRate);
        this.getContentPane().add(panelTaxRate);

        this.getContentPane().add(panelProductInfo);

    }

    public JButton getBtnLoad() {
        return btnLoad;
    }

    public JButton getBtnSave() {
        return btnSave;
    }
    

    public JTextField getTxtProductID() {
        return txtProductID;
    }
    
    public JTextField getTxtTaxRate() {
        return txtTaxRate;
    }

    public JTextField getTxtProductName() {
        return txtProductName;
    }

    public JTextField getTxtProductPrice() {
        return txtProductPrice;
    }

    public JTextField getTxtProductQuantity() {
        return txtProductQuantity;
    }
}
