package structures;

import java.util.Date;

public class OVT {
	
	private String idVisitor;
    private int idOrder;
    private String idTicket;
    private Date day;
    
    public OVT(String idVisitor, int idOrder, String idTicket, Date day) {
        this.idVisitor = idVisitor;
    	this.idOrder = idOrder;
    	this.idTicket = idTicket;
    	this.day = day;
    }
    
    public String getIdVisitor() {
    	return this.idVisitor;
    }
    
    public int getIdOrder() {
        return this.idOrder;
    }
    
    public String getIdTicket() {
        return this.idTicket;
    }
    
    public Date getDay() {
    	return this.day;
    }
    
}
