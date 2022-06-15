package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

import Utilities.Utils;
import structures.Promotion;

public class OrdersTable {
	
	public static final String TABLE_NAME = "ordini";

    private final Connection connection; 

    public OrdersTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public void addOrder(int orderCod, Date day, double total, Promotion promo) {
    	String query = "INSERT INTO `fairdb`.`" + TABLE_NAME + "` (`codOrdine`, `giorno`, `saldoTotale`, `codPromozione`) VALUES (?, ?, ?, ?);";
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, orderCod);
            statement.setDate(2, Utils.dateToSqlDate(day));
            statement.setDouble(3, total);
            statement.setString(4, promo.getPromoCode());
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public boolean checkId(int id) {
    	String query = "SELECT idOrdine FROM " + TABLE_NAME + " WHERE idOrdine = 1;";
    	boolean res = false;
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
    		statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	  int foundId = resultSet.getInt("idOrdine");
            	  if (foundId != 0) {
            		  res = true;
            	  }
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
    	return res;
    }
    
}
