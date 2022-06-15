package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import Utilities.Triplet;

public class PromotionsTable {

	public static final String TABLE_NAME = "promozioni";

    private final Connection connection; 

    public PromotionsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public List<Triplet<String,Integer,String>> getAllPromotionsNames() {
    	String query = "SELECT idPromozione, sconto FROM " + TABLE_NAME + ";";
    	List<Triplet<String,Integer,String>> res = new LinkedList<>();
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	  String promoName = resultSet.getString("idPromozione");
            	  int sconto = resultSet.getInt("sconto");
            	  res.add(new Triplet<>(promoName,sconto,null));
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
    	return res;
    }
    
}
