package structures;

public class OrderVisitor {
	
	private String fiscalCode;
    private String name;
    private String surname;
    private int idOrder;
    private String idTicket;
    
    public OrderVisitor(String fiscalCode, String name, String surname, int idOrder, String idTicket) {
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.surname = surname;
        this.idOrder = idOrder;
        this.idTicket = idTicket;
    }
    
    public String getFiscalCode() {
        return this.fiscalCode;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSurname() {
        return this.surname;
    }
    
    public int getIdOrder() {
        return this.idOrder;
    }
    
    public String getIdTicket() {
        return this.idTicket;
    }
    
}
