package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TicketsTable {
	
	public static final String TABLE_NAME = "biglietti";

    private final Connection connection; 

    public TicketsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public List<String> getAllTicketsNames() {
    	String query = "SELECT idBiglietto FROM " + TABLE_NAME + ";";
    	List<String> res = new LinkedList<>();
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	  String ticketName = resultSet.getString("idBiglietto");
            	  res.add(ticketName);
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
    	return res;
    }
    
}
