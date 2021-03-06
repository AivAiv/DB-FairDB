package structures;

import java.util.Date;

public class Order {

	private int idOrder;
    private Date day;
    private double total;
    private String promo;
    
    public Order(int idOrder, Date day, double total, String promo) {
        this.idOrder = idOrder;
    	this.day = day;
        this.total = total;
        this.promo = promo;
    }
    
    public int getIdOrder() {
    	return this.idOrder;
    }
    
    public Date getDay() {
        return this.day;
    }
    
    public double getTotal() {
        return this.total;
    }
    
    public String getPromotion() {
    	return this.promo;
    }
    
}
