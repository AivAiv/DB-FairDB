package application;

import java.io.IOException;

import Utilities.ConnectionProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tables.TurnsTable;

public class TurnsController {
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "fairdb";
    
    private Stage stage;
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static TurnsTable turnsTable = new TurnsTable(connectionProvider.getMySQLConnection());
    
	@FXML
	private TextField txtCodiceFiscPers;
    
    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EmployeesMenuPane.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
	
    public void btnFindTurn(ActionEvent event) throws IOException {
    	turnsTable.findTurn(txtCodiceFiscPers.getText());
    }
    
}
