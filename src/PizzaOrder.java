/**
 * SE1020 - Object Oriented Programming
 * Lab Sheet 05 - Question 1
 * Pizza Restaurant System - PizzaOrder class
 */
public class PizzaOrder {

    // ---------- Constants ----------
    private static final double SMALL_PRICE = 8.00;
    private static final double MEDIUM_PRICE = 10.00;
    private static final double LARGE_PRICE = 12.00;
    private static final double TOPPING_PRICE = 1.50;
    private static final double DELIVERY_FEE = 5.00;
    private static final int BASE_PREP_TIME = 15;        // minutes
    private static final int PREP_TIME_PER_TOPPING = 2;  // minutes

    // ---------- Attributes ----------
    private String orderID;
    private String customerName;
    private String size;
    private int toppingCount;
    private boolean isDelivery;

    // ---------- (b) Default constructor ----------
    public PizzaOrder() {
        this.orderID = "Unknown";
        this.customerName = "Unknown";
        this.size = "Small";
        this.toppingCount = 0;
        this.isDelivery = false;
    }

    // ---------- (c)(i) Constructor with orderID and customerName ----------
    public PizzaOrder(String orderID, String customerName) {
        this();                       // start from the default values
        this.orderID = orderID;
        this.customerName = customerName;
    }

    // ---------- (c)(ii) Constructor with all five attributes ----------
    public PizzaOrder(String orderID, String customerName, String size,
                      int toppingCount, boolean isDelivery) {
        this.orderID = orderID;
        this.customerName = customerName;
        setSize(size);                // validated through the setter
        setToppingCount(toppingCount);
        this.isDelivery = isDelivery;
    }

    // ---------- (d) Getter and setter for size ----------
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if ("Small".equals(size) || "Medium".equals(size) || "Large".equals(size)) {
            this.size = size;
        } else {
            this.size = "Small";      // invalid size -> default
        }
    }

    // ---------- Other getters and setters ----------
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getToppingCount() {
        return toppingCount;
    }

    public void setToppingCount(int toppingCount) {
        this.toppingCount = (toppingCount < 0) ? 0 : toppingCount;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean isDelivery) {
        this.isDelivery = isDelivery;
    }

    // ---------- (e) Base price according to size ----------
    public double getBasePrice() {
        switch (size) {
            case "Medium":
                return MEDIUM_PRICE;
            case "Large":
                return LARGE_PRICE;
            default:
                return SMALL_PRICE;
        }
    }

    // ---------- (f)(i) Total price using the stored values ----------
    public double calculateTotalPrice() {
        double total = getBasePrice() + (toppingCount * TOPPING_PRICE);
        if (isDelivery) {
            total += DELIVERY_FEE;
        }
        return total;
    }

    // ---------- (f)(ii) Total price after a discount (overloaded) ----------
    public double calculateTotalPrice(double discountPercentage) {
        double total = calculateTotalPrice();
        if (discountPercentage < 0 || discountPercentage > 100) {
            System.out.println("Invalid discount percentage. No discount applied.");
            return total;
        }
        return total - (total * discountPercentage / 100.0);
    }

    // ---------- (g) Preparation time estimator ----------
    public int estimatePreparationTime() {
        return BASE_PREP_TIME + (PREP_TIME_PER_TOPPING * toppingCount);
    }

    // ---------- (h) Display full order details ----------
    public void displayOrderDetails() {
        System.out.println("-------------- Order Details --------------");
        System.out.println("Order ID           : " + orderID);
        System.out.println("Customer Name      : " + customerName);
        System.out.println("Pizza Size         : " + size);
        System.out.println("Base Price         : $" + String.format("%.2f", getBasePrice()));
        System.out.println("Number of Toppings : " + toppingCount);
        System.out.println("Home Delivery      : " + (isDelivery ? "Yes" : "No"));
        System.out.println("Total Price        : $" + String.format("%.2f", calculateTotalPrice()));
        System.out.println("Preparation Time   : " + estimatePreparationTime() + " minutes");
        System.out.println("-------------------------------------------");
        System.out.println();
    }
}
