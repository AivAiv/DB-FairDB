package structures;

import java.util.Date;

public class Order {

    private int orderCode;
    private Date day;
    private double total;
    
    public Order(int orderCode, Date day, double total) {
        this.orderCode = orderCode;
        this.day = day;
        this.total = total;
    }
    
    public int getOrderCode() {
        return this.orderCode;
    }
    
    public Date getDay() {
        return this.day;
    }
    
    public double getTotal() {
        return this.total;
    }
    
}
