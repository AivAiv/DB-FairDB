package structures;

import java.sql.Time;
import java.util.Date;

public class Turn {
    private int cod;
    private Date day;
    private Time time;
    private String cf;
    private int codStand;
    
    public Turn(int cod, Date day, Time time, String cf, int codStand) {
        this.cod = cod;
        this.day = day;
        this.time = time;
        this.cf = cf;
        this.codStand = codStand;
    }
    
    public int getCod() {
        return this.cod;
    }
    
    public Date getDay() {
        return this.day;
    }
    
    public Time getTime() {
        return this.time;
    }
    
    public String getCf() {
        return this.cf;
    }
    
    public int getCodStand() {
        return this.codStand;
    }
    
}
