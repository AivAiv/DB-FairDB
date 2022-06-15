package application;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Utilities.ConnectionProvider;
import Utilities.Triplet;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import structures.Visitor;
import tables.DaysTable;
import tables.OrdersTable;
import tables.OrdersVisitorsTicketsTable;
import tables.PromotionsTable;
import tables.TicketsTable;
import tables.VisitorsTable;

public class TicketsController {
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "fairdb";
    
    private Stage stage;
    private List<Visitor> visitors = new LinkedList<>();
    private List<Triplet<Visitor, Date, String>> orderRecords = new LinkedList<>();
    private Map<Visitor,String> visitorsTickets = new HashMap<>();
    private Map<Visitor,Date> visitorsDays = new HashMap<>();
    private int childrenNum = 0;
    
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
    
    @FXML
    private ComboBox<Date> cmbGiorno;
    @FXML
    private ComboBox<String> cmbBiglietto;
    @FXML
    private ListView<String> lstOrdine;
    @FXML
    private TextField txtCancellaBiglietto;
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static VisitorsTable visitorsTable = new VisitorsTable(connectionProvider.getMySQLConnection());
    final static OrdersTable ordersTable = new OrdersTable(connectionProvider.getMySQLConnection());
    final static OrdersVisitorsTicketsTable ovtTable = new OrdersVisitorsTicketsTable(connectionProvider.getMySQLConnection());
    final static DaysTable daysTable = new DaysTable(connectionProvider.getMySQLConnection());
    final static TicketsTable ticketsTable = new TicketsTable(connectionProvider.getMySQLConnection());
    final static PromotionsTable promotionsTable = new PromotionsTable(connectionProvider.getMySQLConnection());
    
    public void changetagTickets() throws IOException {
    	cmbGiorno.getItems().clear();
    	cmbGiorno.getItems().addAll(daysTable.getAllDays());
    	cmbBiglietto.getItems().clear();
    	cmbBiglietto.getItems().addAll(ticketsTable.getAllTicketsNames());
    }
    
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
    	visitors.forEach(v -> {
    		visitorsTickets.put(v, cmbBiglietto.getValue());
    		visitorsDays.put(v, cmbGiorno.getValue());
    		orderRecords.add(new Triplet<>(v, cmbGiorno.getValue(),cmbBiglietto.getValue()));
    	});
    	
    	lstOrdine.setItems(FXCollections.observableArrayList(orderRecords.stream().map(v -> v.getFirst().toString().concat(" --- " + v.getSecond() + " " + v.getThird())).collect(Collectors.toList())));
    	visitors.clear();
    	lstVisitatori.setItems(FXCollections.observableArrayList(visitors.stream().map(v -> v.toString()).collect(Collectors.toList())));
    }
    
    public void btnClearTicket(ActionEvent event) throws IOException {
    	orderRecords.forEach(or -> {
    		if (or.getFirst().getFiscalCode().equals(txtCancellaBiglietto.getText())) {
    			orderRecords.remove(or);
    		}
    	});
    	lstOrdine.setItems(FXCollections.observableArrayList(orderRecords.stream().map(v -> v.getFirst().toString().concat(" --- " + v.getSecond() + " " + v.getThird())).collect(Collectors.toList())));
    	txtCancellaBiglietto.setText("");
    }
    
    public void btnAddPromo(ActionEvent event) throws IOException {
    	System.out.println("add promo");
    	List<String> templst = orderRecords.stream().map(v -> v.getFirst().toString().concat(" --- " + v.getSecond() + " " + v.getThird())).collect(Collectors.toList());
    	templst.add("---------------------------");
    	List<Triplet<String,Integer,String>> promos = promotionsTable.getAllPromotionsNames();
    	
    	if(orderRecords.size() >= 5 && orderRecords.size() <= 10) {
    		promos.forEach(p -> {
    			if (p.getFirst().equals("gruppo 5-10")) {
    				templst.add("Promozione applicata: " + promos.get(promos.indexOf(p)).getFirst());
    			}
    		});
    	}
    	
    	if(orderRecords.size() > 10 && orderRecords.size() <= 30) {
    		promos.forEach(p -> {
    			if (p.getFirst().equals("gruppo 11-30")) {
    				templst.add("Promozione applicata: " + promos.get(promos.indexOf(p)).getFirst());
    			}
    		});
    	}
    	
    	if(orderRecords.size() > 30) {
    		promos.forEach(p -> {
    			if (p.getFirst().equals("gruppo più 30")) {
    				templst.add("Promozione applicata: " + promos.get(promos.indexOf(p)).getFirst());
    			}
    		});
    	}
    	
    	orderRecords.forEach( or -> {
    		if (this.getTodaysDate().getTime() - or.getSecond().getTime() < 14) {
    			childrenNum ++;
    		}
    	});
    	
    	if(orderRecords.size() == 4 && childrenNum >= 2) {
    		promos.forEach(p -> {
    			if (p.getFirst().equals("famiglia")) {
    				templst.add("Promozione applicata: " + promos.get(promos.indexOf(p)).getFirst());
    			}
    		});
    	}
    	lstOrdine.setItems(FXCollections.observableArrayList(templst));
    }
    
    public void btnClearPromo(ActionEvent event) throws IOException {
    	System.out.println("clear promo");
    }
    
    public void btnConfirmOrder(ActionEvent event) throws IOException {
    	System.out.println("confirm order");
    }

    private Date getTodaysDate() {
         Calendar cal = Calendar.getInstance();
         Date date = cal.getTime();
         return date;
    }
    
}
