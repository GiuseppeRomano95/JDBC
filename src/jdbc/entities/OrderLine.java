package jdbc.entities;

public class OrderLine {
    private int orderId;
    private int productId;
    private double unitPrice;
    private int qty;
    private double discount;

    public OrderLine(int orderId, int productId, double unitPrice, int qty, double discount) {
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.discount = discount;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public double getDiscount() {
        return discount;
    }
}
