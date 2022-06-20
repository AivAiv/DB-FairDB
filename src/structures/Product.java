package structures;

public class Product {
    private int codProduct;
    private String name;
    private double price;
    private String description;
    private int companyCode;
    
    public Product(int codProduct, String name, double price, String description, int companyCode) {
        this.codProduct = codProduct;
        this.name = name;
        this.price = price;
        this.description = description;
        this.companyCode = companyCode;
    }
    
    public int getCodProduct() {
        return this.codProduct;
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getCompanyCode() {
        return this.companyCode;
    }
    
}
