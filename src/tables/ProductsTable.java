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
    
    //TODO
    public List<Product> getAllProducts() {
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
    }
    
    //TODO
    public void addProduct(int codCompany, String name, String specialization, String agent, int codStand) {
        String query = "INSERT INTO `fairdb`.`" + TABLE_NAME + "` (`codAzienda`, `denominazione`, `specializzazione`, `rappresentante`, `codicePadiglione`) VALUES (?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, codCompany);
            statement.setString(2, name);
            statement.setString(3, specialization);
            statement.setString(4, agent);
            statement.setInt(5, codStand);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
