import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

public class Order {
	private int orderID;
	private String customerName;
	private double totalCost;
	private double totalTax;
	private Date date;

	private List<OrderLine> lines;

	public Order() {
		lines = new ArrayList<>();
		date = new Date(System.currentTimeMillis());
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public boolean containsLine(OrderLine olIn) {
		return lines.contains(olIn);
	}

	public void addLine(OrderLine line) {
		if (containsLine(line)) {
			OrderLine ol = lines.get(lines.indexOf(line));
			ol.setQuantity(ol.getQuantity() + line.getQuantity());
			ol.setCost(ol.getCost() + line.getCost());
			ol.setTax(ol.getTax() + line.getTax());
			lines.set(lines.indexOf(line), ol);
		} else {
			lines.add(line);
		}
	}

	public void removeLine(OrderLine line) {
		lines.remove(line);
	}

	public List<OrderLine> getLines() {
		return lines;
	}
}
