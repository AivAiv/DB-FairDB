package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

import Utilities.Utils;

public class OrdersVisitorsTicketsTable {

	public static final String TABLE_NAME = "visitatori-ordini-biglietti";

    private final Connection connection; 

    public OrdersVisitorsTicketsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public void addOVT(String fiscalCode, int orderCod, String ticketCod, Date day) {
    	String query = "INSERT INTO `fairdb`.`" + TABLE_NAME + "` (`codVisitatore`, `codOrdine`, `codBiglietto`, `Data`) VALUES (?, ?, ?, ?);";
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, fiscalCode);
            statement.setInt(2, orderCod);
            statement.setString(3, ticketCod);
            statement.setDate(4, Utils.dateToSqlDate(day));
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public void findTicket(String cf) {
    	String query = "SELECT * FROM fairdb.`" + TABLE_NAME + "` WHERE codVisitatore = ?;";
    	//Ticket res;
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
    		statement.setString(1, cf);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	  String codFis = resultSet.getString("codVisitatore");
            	  int codOrd = resultSet.getInt("codOrdine");
            	  String codTicket = resultSet.getString("codBiglietto");
            	  Date day = resultSet.getDate("Data");
            	  //res = new Ticket();
            	  System.out.println(codFis + " " + codOrd + " " + codTicket + " " + day);
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
    	//return res;
    }
    
}
