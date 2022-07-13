package application;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import Utilities.ConnectionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import structures.Turn;
import tables.TurnsTable;

public class TurnsController {
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "fairdb";
    
    private Stage stage;
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static TurnsTable turnsTable = new TurnsTable(connectionProvider.getMySQLConnection());
    private ObservableList<Turn> TurnsList;
    
	@FXML
	private TextField txtCodiceFiscPers;
	@FXML
    private TableColumn<Turn, Integer> colCodTurno;
    @FXML
    private TableColumn<Turn, Date> colGiorno;
    @FXML
    private TableColumn<Turn, Time> ColOrario;
    @FXML
    private TableColumn<Turn, String> colCF;
    @FXML
    private TableColumn<Turn, Integer> colPadiglione;
    @FXML
    private TableView<Turn> tblTurni;

    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EmployeesMenuPane.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
	
    public void btnFindTurn(ActionEvent event) throws IOException {
    	TurnsList = FXCollections.observableArrayList(turnsTable.findTurn(txtCodiceFiscPers.getText()));
		
    	colCodTurno.setCellValueFactory(new PropertyValueFactory<Turn,Integer>("cod"));
    	colGiorno.setCellValueFactory(new PropertyValueFactory<Turn,Date>("day"));
    	ColOrario.setCellValueFactory(new PropertyValueFactory<Turn,Time>("time"));
    	colCF.setCellValueFactory(new PropertyValueFactory<Turn,String>("cf"));
    	colPadiglione.setCellValueFactory(new PropertyValueFactory<Turn,Integer>("codStand"));
	    
		tblTurni.setItems(TurnsList);
		
		txtCodiceFiscPers.setText("");
    }
    
}
