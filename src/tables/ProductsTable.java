package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import structures.Product;

public class ProductsTable {
    public static final String TABLE_NAME = "prodotti";

    private final Connection connection; 

    public ProductsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public List<Product> getAllProducts() {
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        List<Product> res = new LinkedList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	int codProd = resultSet.getInt("codiceProdotto");
                String name = resultSet.getString("nome");
                double price = resultSet.getDouble("prezzo");
                String desc = resultSet.getString("descrizione");
                int codComp = resultSet.getInt("codiceAzienda");
            	res.add(new Product(codProd, name, price, desc, codComp));
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
        return res;
    }
    
    public void addProduct(int codProd, String name, double price, String description, int codComp) {
        String query = "INSERT INTO `fairdb`.`" + TABLE_NAME + "` (`codiceProdotto`, `nome`, `prezzo`, `descrizione`, `codiceAzienda`) VALUES (?, ?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, codProd);
            statement.setString(2, name);
            statement.setDouble(3, price);
            statement.setString(4, description);
            statement.setInt(5, codComp);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public void deleteProduct(int code) {
    	String query = "DELETE FROM `fairdb`.`" + TABLE_NAME + "` WHERE (`codiceProdotto` = ?);";
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, code);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public boolean checkId(int id) {
    	String query = "SELECT codiceProdotto FROM " + TABLE_NAME + " WHERE codiceProdotto = ?;";
    	boolean res = false;
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
    		statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	  int foundId = resultSet.getInt("codiceProdotto");
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
