package structures;

import java.sql.Time;
import java.util.Date;

public class Stand {
	
	private int standCod;
    private String specialization;
    private Time open;
    private Time close;
    private Date children;
    private int expTot;
    private int expOcc;
    
    public Stand(int standCod, String specialization, Time open, Time close, Date children, int expTot, int expOcc) {
        this.standCod = standCod;
        this.specialization = specialization;
        this.open = open;
        this.close = close;
        this.children = children;
        this.expTot = expTot;
        this.expOcc = expOcc;
    }
    
    public int getStandCod() {
    	return this.standCod;
    }
    
    public String getSpecialization() {
        return this.specialization;
    }
    
    public Time open() {
        return this.open;
    }
    
    public Time getClose() {
    	return this.close;
    }
    
    public Date children() {
        return this.children;
    }
    
    public int expTot() {
    	return this.expTot;
    }
    
    public int expOcc() {
        return this.expOcc;
    }
    
}
