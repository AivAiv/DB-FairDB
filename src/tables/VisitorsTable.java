package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

import Utilities.Utils;
import structures.Visitor;

public class VisitorsTable {
	
	public static final String TABLE_NAME = "visitatori";

    private final Connection connection; 

    public VisitorsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public void addVisitor(String fiscalCode, String name, String surname, Date birthDate, String gender) {
    	// Checks if the visitor already exists, if not adds it into the database.
    	if (findVisitor(fiscalCode) == null) {
    		String query = "INSERT INTO `fairdb`.`" + TABLE_NAME + "` (`codiceFiscale`, `nome`, `cognome`, `dataNascita`, `sesso`) VALUES (?, ?, ?, ?, ?);";
    		try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
    			statement.setString(1, fiscalCode);
    			statement.setString(2, name);
    			statement.setString(3, surname);
    			statement.setDate(4, Utils.dateToSqlDate(birthDate));
    			statement.setString(5, gender);
    			statement.executeUpdate();
    		} catch (final SQLException e) {
    			throw new IllegalStateException(e);
    		}
    	} else {
    		Visitor newV = new Visitor(fiscalCode, name, surname, birthDate, gender);
    		Visitor oldV = findVisitor(fiscalCode);
    		if (!(newV.getName().equals(oldV.getName()) && newV.getSurname().equals(oldV.getSurname()) 
    			&& newV.getBirthDate().equals(oldV.getBirthDate()) && newV.getGender().equals(oldV.getGender()))) {
    			throw new IllegalStateException("A different person has the same primary key");
	    	}
    	}
    }
    
    public void deleteVisitor(String cf) {
    	String query = "DELETE FROM `fairdb`.`" + TABLE_NAME + "` WHERE (`codiceFiscale` = ?);";
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, cf);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public Visitor findVisitor(String cf) {
    	String query = "SELECT * FROM fairdb.`" + TABLE_NAME + "` WHERE codiceFiscale = ?;";
    	Visitor res = null;
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
    		statement.setString(1, cf);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	  String codFis = resultSet.getString("codiceFiscale");
            	  String name = resultSet.getString("nome");
            	  String surn = resultSet.getString("cognome");
            	  Date data = resultSet.getDate("dataNascita");
            	  String gender = resultSet.getString("sesso");
            	  res = new Visitor(codFis, name, surn, data, gender);
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
    	return res;
    }

}
