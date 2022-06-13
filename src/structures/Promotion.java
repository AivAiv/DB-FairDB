package structures;

public class Promotion {
    
    private String promoCode;
    private double percDiscount;
    private String description;
    
    public Promotion(String promoCode, double percDiscount, String description) {
        this.promoCode = promoCode;
        this.percDiscount = percDiscount;
        this.description = description;
    }
    
    public String getPromoCode() {
        return this.promoCode;
    }
    
    public Double getPercDiscount() {
        return this.percDiscount;
    }
    
    public String getDescription() {
        return this.description;
    }
    
}
