package structures;

import java.util.Date;

public class Visitor {
    
    private String fiscalCode;
    private String name;
    private String surname;
    private Date birthDate;
    private String gender;
    
    public Visitor(String fiscalCode, String name, String surname, Date birthDate, String gender) {
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
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
    
    public Date getBirthDate() {
        return this.birthDate;
    }
    
    public String getGender() {
        return this.gender;
    }

}
