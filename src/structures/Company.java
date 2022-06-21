package structures;

public class Company {
    
    private int codCompany;
    private String name;
    private String specialization;
    private int codStand;
    
    public Company(int codCompany, String name, String specialization, int codStand) {
        this.codCompany = codCompany;
        this.name = name;
        this.specialization = specialization;
        this.codStand = codStand;
    }
    
    public int getCodCompany() {
        return this.codCompany;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSpecialization() {
        return this.specialization;
    }
    
    public int getCodStand() {
        return this.codStand;
    }

}
