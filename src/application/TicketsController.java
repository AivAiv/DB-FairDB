package application;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import static java.util.Calendar.*;

import Utilities.ConnectionProvider;
import Utilities.Triplet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import structures.OVT;
import structures.OrderVisitor;
import structures.Promotion;
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
    private int childrenNum;
    private Promotion promo;
    private double sum = 0;
    private Triplet<Visitor,Date,String> delete;
    int randnum = 0;
    
    private ObservableList<OrderVisitor> ovList;
    
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
    
    @FXML
    private TextField txtTrovaBiglietto;
    @FXML
    private TextField txtTrovaOrdine;
    
    @FXML
    private TableColumn<OrderVisitor, String> colCF;
    @FXML
    private TableColumn<OrderVisitor, String> colNome;
    @FXML
    private TableColumn<OrderVisitor, String> colCognome;
    @FXML
    private TableColumn<OrderVisitor, Integer> colCodOrd;
    @FXML
    private TableColumn<OrderVisitor, String> colBiglietto;
    @FXML
    private TableView<OrderVisitor> tblBiglietti;
    
    @FXML
    private Label lblTotale;
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static VisitorsTable visitorsTable = new VisitorsTable(connectionProvider.getMySQLConnection());
    final static OrdersTable ordersTable = new OrdersTable(connectionProvider.getMySQLConnection());
    final static OrdersVisitorsTicketsTable ovtTable = new OrdersVisitorsTicketsTable(connectionProvider.getMySQLConnection());
    final static DaysTable daysTable = new DaysTable(connectionProvider.getMySQLConnection());
    final static TicketsTable ticketsTable = new TicketsTable(connectionProvider.getMySQLConnection());
    final static PromotionsTable promotionsTable = new PromotionsTable(connectionProvider.getMySQLConnection());
    
    public void goBack(ActionEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("/EmployeesMenuPane.fxml"));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    stage.setScene(new Scene(root));
	    stage.show();
	}
    
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
    			visitorsTable.deleteVisitor(v.getFiscalCode());
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
    	lblTotale.setText(String.valueOf(this.getTotal()));
    }
    
    public void btnClearTicket(ActionEvent event) throws IOException {
    	orderRecords.forEach(or -> {
    		if (or.getFirst().getFiscalCode().equals(txtCancellaBiglietto.getText())) {
    			delete = or;
    		}
    	});
    	orderRecords.remove(delete);
    	lstOrdine.setItems(FXCollections.observableArrayList(orderRecords.stream().map(v -> v.getFirst().toString().concat(" --- " + v.getSecond() + " " + v.getThird())).collect(Collectors.toList())));
    	promo = null;
    	this.btnAddPromo(event);
    	lblTotale.setText(String.valueOf(this.getTotal()));
    	txtCancellaBiglietto.setText("");
    }
    
    public void btnAddPromo(ActionEvent event) throws IOException {
    	List<String> templst = orderRecords.stream().map(v -> v.getFirst().toString().concat(" --- " + v.getSecond() + " " + v.getThird())).collect(Collectors.toList());
    	templst.add("---------------------------");
    	List<Triplet<String,Integer,String>> promos = promotionsTable.getAllPromotionsNames();
    	
    	if(orderRecords.size() >= 5 && orderRecords.size() <= 10) {
    		promos.forEach(p -> {
    			if (p.getFirst().equals("gruppo 5-10")) {
    				templst.add("Promozione applicata: " + promos.get(promos.indexOf(p)).getFirst());
    				promo = new Promotion(p.getFirst(), p.getSecond(), p.getThird());
    			}
    		});
    	}
    	
    	if(orderRecords.size() > 10 && orderRecords.size() <= 30) {
    		promos.forEach(p -> {
    			if (p.getFirst().equals("gruppo 11-30")) {
    				templst.add("Promozione applicata: " + promos.get(promos.indexOf(p)).getFirst());
    				promo = new Promotion(p.getFirst(), p.getSecond(), p.getThird());
    			}
    		});
    	}
    	
    	if(orderRecords.size() > 30) {
    		promos.forEach(p -> {
    			if (p.getFirst().equals("gruppo pi� 30")) {
    				templst.add("Promozione applicata: " + promos.get(promos.indexOf(p)).getFirst());
    				promo = new Promotion(p.getFirst(), p.getSecond(), p.getThird());
    			}
    		});
    	}
    	
    	orderRecords.forEach( or -> {
    		if (this.getDiffYears(or.getFirst().getBirthDate(), this.getTodaysDate()) < 14) {
    			childrenNum ++;
    		}
    	});
    	
    	if(orderRecords.size() == 4 && childrenNum >= 2) {
    		promos.forEach(p -> {
    			if (p.getFirst().equals("famiglia")) {
    				templst.add("Promozione applicata: " + promos.get(promos.indexOf(p)).getFirst());
    				promo = new Promotion(p.getFirst(), p.getSecond(), p.getThird());
    			}
    		});
    	}
    	
    	lstOrdine.setItems(FXCollections.observableArrayList(templst));
    	lblTotale.setText(String.valueOf(this.getTotal()));
    	childrenNum = 0;
    }
    
    public void btnClearPromo(ActionEvent event) throws IOException {
    	lstOrdine.setItems(FXCollections.observableArrayList(orderRecords.stream().map(v -> v.getFirst().toString().concat(" --- " + v.getSecond() + " " + v.getThird())).collect(Collectors.toList())));
    	promo = null;
    	lblTotale.setText(String.valueOf(this.getTotal()));
    }
    
    public void btnConfirmOrder(ActionEvent event) throws IOException {
    	Random rand = new Random();
    	boolean assigned = false;
    	while(assigned == false) {
    		randnum = rand.nextInt(200)+1;
    		if (ordersTable.checkId(randnum) == false) {
    			ordersTable.addOrder(randnum, this.getTodaysDate(), this.getTotal(), promo);
    			assigned = true;
    		}
    	}
    	
    	orderRecords.forEach( or -> {
    		ovtTable.addOVT(or.getFirst().getFiscalCode(), randnum,  or.getThird(), or.getSecond());
    	});
    	
    	lblTotale.setText("00,00");
    }
    
    public void btnFindTicket(ActionEvent event) throws IOException {
    	List<OVT> ovtList = ovtTable.findOVT(txtTrovaBiglietto.getText());
    	Visitor vis = visitorsTable.findVisitor(txtTrovaBiglietto.getText());
    	List<OrderVisitor> ov = new LinkedList<>();
    	ovtList.forEach(ovt -> {
    		ov.add(new OrderVisitor(vis.getFiscalCode(), vis.getName(), vis.getSurname(), ovt.getIdOrder(), ovt.getIdTicket()));
    	});
    	
    	ovList = FXCollections.observableArrayList(ov);
		
	    colCF.setCellValueFactory(new PropertyValueFactory<OrderVisitor,String>("fiscalCode"));
	    colNome.setCellValueFactory(new PropertyValueFactory<OrderVisitor,String>("name"));
	    colCognome.setCellValueFactory(new PropertyValueFactory<OrderVisitor,String>("surname"));
	    colCodOrd.setCellValueFactory(new PropertyValueFactory<OrderVisitor,Integer>("idOrder"));
	    colBiglietto.setCellValueFactory(new PropertyValueFactory<OrderVisitor,String>("idTicket"));
	    
		tblBiglietti.setItems(ovList);
		
		txtTrovaBiglietto.setText("");
    }
    
    public void btnFindOrder(ActionEvent event) throws IOException {
	    try {
			int order = Integer.parseInt(txtTrovaOrdine.getText());
			
			List<OVT> ovtList = ovtTable.findOVT(order);
	    	List<OrderVisitor> ov = new LinkedList<>();
	    	ovtList.forEach(ovt -> {
	    		Visitor vis = visitorsTable.findVisitor(ovt.getIdVisitor());
	    		ov.add(new OrderVisitor(vis.getFiscalCode(), vis.getName(), vis.getSurname(), ovt.getIdOrder(), ovt.getIdTicket()));
	    	});
	    	
	    	ovList = FXCollections.observableArrayList(ov);
			
		    colCF.setCellValueFactory(new PropertyValueFactory<OrderVisitor,String>("fiscalCode"));
		    colNome.setCellValueFactory(new PropertyValueFactory<OrderVisitor,String>("name"));
		    colCognome.setCellValueFactory(new PropertyValueFactory<OrderVisitor,String>("surname"));
		    colCodOrd.setCellValueFactory(new PropertyValueFactory<OrderVisitor,Integer>("idOrder"));
		    colBiglietto.setCellValueFactory(new PropertyValueFactory<OrderVisitor,String>("idTicket"));
		    
			tblBiglietti.setItems(ovList);
			
			txtTrovaOrdine.setText("");
		} catch (IllegalArgumentException arg) {
			System.out.println("Devi inserire per forza un numero!!");
		}
    }

    private Date getTodaysDate() {
         Calendar cal = Calendar.getInstance();
         Date date = cal.getTime();
         return date;
    }
    
    private int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) || 
            (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    private double getTotal() {
    	sum = 0;
        orderRecords.forEach(or -> {
        	sum += ticketsTable.getTicketPrice(or.getThird());
        });
        if (promo != null) {
        	sum = sum - (sum * promo.getPercDiscount() / 100);
        }
        return sum;
   }
    
    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
}
