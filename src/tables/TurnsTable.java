package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import Utilities.Utils;
import structures.Turn;

public class TurnsTable {
    
public static final String TABLE_NAME = "turni";
    
    private final Connection connection; 

    public TurnsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    public List<Turn> getAllTurns() {
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        List<Turn> res = new LinkedList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	int codTurn = resultSet.getInt("idTurno");
                Date day = resultSet.getDate("giorno");
                Time time = resultSet.getTime("orario");
                String cf = resultSet.getString("codiceFiscale");
                int pad = resultSet.getInt("padiglione");
            	res.add(new Turn(codTurn, day, time, cf, pad));
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
        return res;
    }
    
    public void addTurn(int codTurn, Date day, Time time, String cf, int codPad) {
        String query = "INSERT INTO `fairdb`.`" + TABLE_NAME + "` (`idTurno`, `giorno`, `orario`, `codiceFiscale`, `padiglione`) VALUES (?, ?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, codTurn);
            statement.setDate(2, Utils.dateToSqlDate(day));
            statement.setTime(3, time);
            statement.setString(4, cf);
            statement.setInt(5, codPad);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public void deleteTurn(int code) {
    	String query = "DELETE FROM `fairdb`.`" + TABLE_NAME + "` WHERE (`idTurno` = ?);";
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, code);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public List<Turn> findTurn(String cf) {
    	String query = "SELECT * FROM fairdb." + TABLE_NAME + " WHERE codiceFiscale = ?;";
    	List<Turn> res = new LinkedList<>();
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
    		statement.setString(1, cf);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	  int codTurn = resultSet.getInt("idTurno");
            	  Date day = resultSet.getDate("giorno");
            	  Time time = resultSet.getTime("orario");
            	  String codf = resultSet.getString("codiceFiscale");
            	  int pad = resultSet.getInt("padiglione");
            	  res.add(new Turn(codTurn, day, time, codf, pad));
            }
        } catch (final SQLException e) { 
            throw new IllegalStateException(e);
        }
    	return res;
    }
    
}
