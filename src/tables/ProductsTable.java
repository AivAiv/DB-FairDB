package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class ProductsTable {
    public static final String TABLE_NAME = "prodotti";

    private final Connection connection; 

    public ProductsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    //TODO
    /*public List<Product> getAllProducts() {
        String query = "SELECT codiceAzienda FROM " + TABLE_NAME + ";";
        List<Integer> res = new LinkedList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                  int companyCode = resultSet.getInt("codiceAzienda");
                  res.add(companyCode);
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
        return res;
    }*/
    
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
}
