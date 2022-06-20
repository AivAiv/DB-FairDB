package structures;

public class Company {
    
    private int codCompany;
    private String name;
    private String specialization;
    private String agent;
    private int codStand;
    
    public Company(int codCompany, String name, String specialization, String agent, int codStand) {
        this.codCompany = codCompany;
        this.name = name;
        this.specialization = specialization;
        this.agent = agent;
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
    
    public String getAgent() {
        return this.agent;
    }
    
    public int getCodStand() {
        return this.codStand;
    }

}
