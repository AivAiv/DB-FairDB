package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

import application.Utils;

public class VisitorsTable {
	
	public static final String TABLE_NAME = "visitatori";

    private final Connection connection; 

    public VisitorsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public void addVisitor(String fiscalCode, String name, String surname, Date birthDate, String gender) {
    	String query = "INSERT INTO `fairdb`.`" + TABLE_NAME + "` (`codice fiscale`, `nome`, `cognome`, `data nascita`, `sesso`) VALUES (?, ?, ?, ?, ?);";
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
    }

}
