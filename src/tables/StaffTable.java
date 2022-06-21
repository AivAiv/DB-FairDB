package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class StaffTable {
	   
	public static final String TABLE_NAME = "personale";
	    
	    private final Connection connection; 

	    public StaffTable(final Connection connection) {
	        this.connection = Objects.requireNonNull(connection);
	    }
	    
	    public List<String> getAllStaffCF() {
	        String query = "SELECT codiceFiscale FROM " + TABLE_NAME + ";";
	        List<String> res = new LinkedList<>();
	        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
	            final ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                  String cf = resultSet.getString("codiceFiscale");
	                  res.add(cf);
	            }
	        } catch (final SQLException e) { 
	            throw new IllegalStateException(e);
	        }
	        return res;
	    }
	    
}
