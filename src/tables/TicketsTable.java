package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import Utilities.Triplet;

public class TicketsTable {
	
	public static final String TABLE_NAME = "biglietti";

    private final Connection connection; 

    public TicketsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public List<String> getAllTicketsNames() {
    	String query = "SELECT codiceBiglietto FROM " + TABLE_NAME + ";";
    	List<String> res = new LinkedList<>();
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	  String ticketName = resultSet.getString("codiceBiglietto");
            	  res.add(ticketName);
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
    	return res;
    }
    
    public List<Triplet<String,Double,String>> getAllTickets() {
    	String query = "SELECT * FROM " + TABLE_NAME + ";";
    	List<Triplet<String,Double,String>> res = new LinkedList<>();
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	  String ticketName = resultSet.getString("idBiglietto");
            	  double price = resultSet.getDouble("prezzo");
            	  String desc = resultSet.getString("descrizione");
            	  res.add(new Triplet<>(ticketName, price, desc));
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
    	return res;
    }
    
    public double getTicketPrice(String name) {
    	String query = "SELECT prezzo FROM " + TABLE_NAME + " WHERE codiceBiglietto = ?;";
    	double res = 0;
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
    		statement.setString(1, name);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	  double price = resultSet.getDouble("prezzo");
            	  res = price;
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
    	return res;
    }
    
}
