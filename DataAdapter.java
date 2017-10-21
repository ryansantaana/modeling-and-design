import java.sql.*;

public class DataAdapter {
	private Connection connection;

	public DataAdapter(Connection connection) {
		this.connection = connection;
	}

	public Product loadProduct(int id) {
		try {
			String query = "SELECT * FROM Product WHERE ProductID = " + id;

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				Product product = new Product();
				product.setProductID(resultSet.getInt(1));
				product.setName(resultSet.getString(2));
				product.setPrice(resultSet.getDouble(3));
				product.setQuantity(resultSet.getDouble(4));
				product.setTaxRate(resultSet.getDouble(5));
				resultSet.close();
				statement.close();

				return product;
			}

		} catch (SQLException e) {
			System.out.println("Database access error!");
			e.printStackTrace();
		}
		return null;
	}

	public boolean saveProduct(Product product) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Product WHERE ProductID = ?");
			statement.setInt(1, product.getProductID());

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) { // this product exists, update its fields
				statement = connection.prepareStatement(
						"UPDATE Product SET Name = ?, Price = ?, Quantity = ?, TaxRate = ? WHERE ProductID = ?");
				statement.setString(1, product.getName());
				statement.setDouble(2, product.getPrice());
				statement.setDouble(3, product.getQuantity());
				statement.setInt(5, product.getProductID());
				statement.setDouble(4, product.getTaxRate());
			} else { // this product does not exist, use insert into
				statement = connection.prepareStatement("INSERT INTO Product VALUES (?, ?, ?, ?, ?)");
				statement.setString(2, product.getName());
				statement.setDouble(3, product.getPrice());
				statement.setDouble(4, product.getQuantity());
				statement.setInt(1, product.getProductID());
				statement.setDouble(5, product.getTaxRate());
			}
			statement.execute();
			resultSet.close();
			statement.close();
			return true; // save successfully

		} catch (SQLException e) {
			System.out.println("Database access error!");
			e.printStackTrace();
			return false; // cannot save!
		}
	}

	public Order loadOrder(int id) {
		try {
			Order order = null;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Order WHERE OrderID = " + id);

			if (resultSet.next()) {
				order = new Order();
				order.setOrderID(resultSet.getInt("OrderID"));
				order.setCustomerName(resultSet.getString("Customer"));
				order.setTotalCost(resultSet.getDouble("TotalCost"));
				order.setDate(resultSet.getDate("OrderDate"));
				resultSet.close();
				statement.close();
			}

			// loading the order lines for this order
			resultSet = statement.executeQuery("SELECT * FROM OrderLine WHERE OrderID = " + id);
			
			while (resultSet.next()) {
				OrderLine line = new OrderLine();
				line.setOrderID(resultSet.getInt(1));
				line.setProductID(resultSet.getInt(2));
				line.setQuantity(resultSet.getDouble(3));
				line.setCost(resultSet.getDouble(4));
				line.setTax(resultSet.getDouble(5));
				order.addLine(line);
			}

			return order;

		} catch (SQLException e) {
			System.out.println("Database access error!");
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveOrder(Order order) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO \"Order\" (OrderDate, Customer, TotalCost, TotalTax) VALUES (?, ?, ?, ?)");
			statement.setDate(1, order.getDate());
			statement.setString(2, order.getCustomerName());
			statement.setDouble(3, order.getTotalCost());
			statement.setDouble(4, order.getTotalTax());
			statement.execute(); // commit to the database;
			statement.close();
			
			ResultSet resultSet;
			Statement statementRead = connection.createStatement();
			
			resultSet = statementRead.executeQuery("SELECT OrderID FROM \"Order\" ORDER BY OrderID DESC LIMIT 1");
			
			resultSet.next();
			
			int orderID = resultSet.getInt(1);

			statement = connection.prepareStatement("INSERT INTO OrderLine (OrderID, ProductID, Quantity, Cost, Tax) VALUES (?, ?, ?, ?, ?)");
			for (OrderLine line : order.getLines()) { // store for each order
														// line!
				statement.setInt(1, orderID);
				statement.setInt(2, line.getProductID());
				statement.setDouble(3, line.getQuantity());
				statement.setDouble(4, line.getCost());
				statement.setDouble(5, line.getTax());
				statement.execute(); // commit to the database;
			}
			statement.close();
			return true; // save successfully!
		} catch (SQLException e) {
			System.out.println("Database access error!");
			e.printStackTrace();
			return false;
		}
	}
}
