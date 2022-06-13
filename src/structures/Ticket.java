package structures;

public class Ticket {
    
    private String ticketCode;
    private double price;
    private String description;
    
    public Ticket(String ticketCode, double price, String description) {
        this.ticketCode = ticketCode;
        this.price = price;
        this.description = description;
    }
    
    public String getTicketCode() {
        return this.ticketCode;
    }
    
    public Double getPrice() {
        return this.price;
    }
    
    public String getDescription() {
        return this.description;
    }
    
}
