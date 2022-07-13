package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import structures.Company;

public class CompaniesTable {
    
    public static final String TABLE_NAME = "aziende";
    
    private final Connection connection; 

    public CompaniesTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public List<Integer> getAllCompaniesCodes() {
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
    
    public List<Company> getAllCompanies() {
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        List<Company> res = new LinkedList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	int compCod = resultSet.getInt("codiceAzienda");
                String name = resultSet.getString("denominazione");
                String spec = resultSet.getString("specializzazione");
                int codPad = resultSet.getInt("codicePadiglione");
            	res.add(new Company(compCod, name, spec, codPad));
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
        return res;
    }
    
    public void addCompany(int codCompany, String name, String specialization, int codStand) {
        String query = "INSERT INTO `fairdb`.`" + TABLE_NAME + "` (`codiceAzienda`, `denominazione`, `specializzazione`, `codicePadiglione`) VALUES (?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, codCompany);
            statement.setString(2, name);
            statement.setString(3, specialization);
            statement.setInt(4, codStand);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public boolean checkId(int id) {
    	String query = "SELECT codiceAzienda FROM " + TABLE_NAME + " WHERE codiceAzienda = ?;";
    	boolean res = false;
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
    		statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	  int foundId = resultSet.getInt("codiceAzienda");
            	  if (foundId != 0) {
            		  res = true;
            	  }
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
    	return res;
    }
    
    public void deleteCompany(int code) {
    	String query = "DELETE FROM `fairdb`.`" + TABLE_NAME + "` WHERE (`codiceAzienda` = ?);";
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, code);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
}
