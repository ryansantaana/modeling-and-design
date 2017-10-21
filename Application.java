import java.sql.*;

import javax.swing.JOptionPane;

public class Application {
 
	private static Application instance; // Singleton pattern

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}
		return instance;
	}
	// Main components of this application

	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	private DataAdapter dataAdapter;

	// Create the Product View and Controller here!

	private ProductView productView = new ProductView();

	private CheckoutScreen checkoutScreen = new CheckoutScreen();

	private MainScreen mainScreen = new MainScreen();

	private PaymentView paymentView = new PaymentView();

	private PaymentController paymentController;

	public MainScreen getMainScreen() {
		return mainScreen;
	}

	public ProductView getProductView() {
		return productView;
	}

	public CheckoutScreen getCheckoutScreen() {
		return checkoutScreen;
	}

	private ProductController productController;

	public ProductController getProductController() {
		return productController;
	}

	private CheckoutController checkoutController;

	public CheckoutController getCheckoutController() {
		return checkoutController;
	}

	public DataAdapter getDataAdapter() {
		return dataAdapter;
	}

	private Application() {
		// create SQLite database connection here!
		Statement stmt;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:store.db");
			stmt = connection.createStatement();
			stmt.executeQuery("select * from Product");
		} catch (ClassNotFoundException ex) {
			System.out.println("SQLite is not installed. System exits with error!");
			System.exit(1);
		}

		catch (SQLException ex) {
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:store.db");
				stmt = connection.createStatement();
				stmt.execute("CREATE TABLE if not exists \"Order\" (OrderID INTEGER PRIMARY KEY "
						+ "AUTOINCREMENT NOT NULL, OrderDate "
						+ "datetime NOT NULL, Customer CHAR(30), TotalCost DOUBLE, TotalTax DOUBLE);");
				stmt.execute("CREATE TABLE if not exists \"Product\" (ProductID INT NOT NULL, "
						+ "Name CHAR(30) NOT NULL, Price DOUBLE NOT NULL, Quantity DOUBLE, TaxRate DOUBLE);");
				stmt.execute("CREATE TABLE if not exists OrderLine (OrderID INT NOT NULL, "
						+ "ProductID INT NOT NULL, Quantity DOUBLE, Cost "
						+ "DOUBLE, Tax DOUBLE, PRIMARY KEY (ProductID, OrderID));");
				JOptionPane.showMessageDialog(null,
						"Database did not exist, but one was created for you. Please add items before attempting to checkout!");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.exit(2);
			}
		}

		// Create data adapter here!
		dataAdapter = new DataAdapter(connection);

		productController = new ProductController(productView, dataAdapter);

		checkoutController = new CheckoutController(checkoutScreen, dataAdapter);

		paymentController = new PaymentController(paymentView, dataAdapter);
	}

	public static void main(String[] args) {
		Application.getInstance().getMainScreen().setVisible(true);
	}

	public PaymentController getPaymentController() {
		return paymentController;
	}

	public PaymentView getPaymentView() {
		return paymentView;
	}

}
