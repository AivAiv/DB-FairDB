package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import structures.Visitor;
import tables.VisitorsTable;

public class TicketsController {
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "fairdb";
    
    private Stage stage;
    private List<Visitor> visitors = new LinkedList<>();
    
    @FXML
    private TextField txtCodiceFiscale;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCognome;
    @FXML
    private DatePicker dateNascita;
    @FXML
    private RadioButton rdbMaschio;
    @FXML
    private RadioButton rdbFemmina;
    @FXML
    private ListView<String> lstVisitatori;
    @FXML
    private TextField txtCancellaVisitatore;
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static VisitorsTable visitorsTable = new VisitorsTable(connectionProvider.getMySQLConnection());
    
    public void btnAddVisitor(ActionEvent event) throws IOException {
    	String gender = "Non specificato";
    	if (rdbMaschio.isSelected()) {
    		gender = "M";
    	}
    	if (rdbFemmina.isSelected()) {
    		gender = "F";
    	}
    	visitorsTable.addVisitor(txtCodiceFiscale.getText(), txtNome.getText(), txtCognome.getText(), java.sql.Date.valueOf(dateNascita.getValue()), gender);
    	visitors.add(new Visitor(txtCodiceFiscale.getText(), txtNome.getText(), txtCognome.getText(), java.sql.Date.valueOf(dateNascita.getValue()), gender));
    	lstVisitatori.setItems(FXCollections.observableArrayList(visitors.stream().map(v -> v.toString()).collect(Collectors.toList())));
    	
    	txtCodiceFiscale.setText("");
    	txtNome.setText("");
    	txtCognome.setText("");
    }
    
    public void btnClearVisitor(ActionEvent event) throws IOException {
    	visitors.forEach(v -> {
    		if (v.getFiscalCode().equals(txtCancellaVisitatore.getText())) {
    			visitors.remove(v);
    		}
    	});
    	lstVisitatori.setItems(FXCollections.observableArrayList(visitors.stream().map(v -> v.toString()).collect(Collectors.toList())));
    	
    	txtCancellaVisitatore.setText("");
    }
    
    public void btnInsertTickets(ActionEvent event) throws IOException {
    	System.out.println("insert tickets");
    }
    
    public void btnClearTicket(ActionEvent event) throws IOException {
    	System.out.println("clear ticket");
    }
    
    public void btnAddPromo(ActionEvent event) throws IOException {
    	System.out.println("add promo");
    }
    
    public void btnClearPromo(ActionEvent event) throws IOException {
    	System.out.println("clear promo");
    }
    
    public void btnConfirmOrder(ActionEvent event) throws IOException {
    	System.out.println("confirm order");
    }

}
