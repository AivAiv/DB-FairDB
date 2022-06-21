package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Utilities.ConnectionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import structures.Stand;
import tables.CompaniesTable;
import tables.DaysTable;
import tables.ProductsTable;
import tables.StaffTable;
import tables.StandsTable;
import tables.TurnsTable;

public class ManagerController implements Initializable {
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "fairdb";

	private Stage stage;
	private int randnum;
	final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
	final static DaysTable daysTable = new DaysTable(connectionProvider.getMySQLConnection());
	final static StandsTable standsTable = new StandsTable(connectionProvider.getMySQLConnection());
	final static CompaniesTable companiesTable = new CompaniesTable(connectionProvider.getMySQLConnection());
	final static ProductsTable productsTable = new ProductsTable(connectionProvider.getMySQLConnection());
	final static TurnsTable turnsTable = new TurnsTable(connectionProvider.getMySQLConnection());
	final static StaffTable staffTable = new StaffTable(connectionProvider.getMySQLConnection());

	@FXML
	private Spinner<Time> spnApertura;
	@FXML
	private Spinner<Time> spnChiusura;
	@FXML
	private ComboBox<Date> cmbBambini;
	@FXML
    private TableColumn<Stand, Date> colBambini;
    @FXML
    private TableColumn<Stand, Integer> colCodice;
    @FXML
    private TableColumn<Stand, Time> colOrarioApertura;
    @FXML
    private TableColumn<Stand, Time> colOrarioChiusura;
    @FXML
    private TableColumn<Stand, Integer> colSpaziOcc;
    @FXML
    private TableColumn<Stand, Integer> colSpaziTot;
    @FXML
    private TableColumn<Stand, String> colSpecializzazione;
	@FXML
    private TableView<Stand> tblPadiglioni;
	@FXML
	private TextField txtSpaziTot;
	@FXML
	private TextField txtSpaziOcc;
	@FXML
	private TextField txtSpecializzazione;
	@FXML
	private TextField txtCancellaPadiglione;
	
    @FXML
    private TextField txtDenominazione;
    @FXML
    private ComboBox<String> cmbSpecializzazione;
    @FXML
    private Label lblPadiglioneAz;
    @FXML
    private TextField txtCancellaAzienda;
    

    @FXML
    private TextField txtCancellaProdotto;
    @FXML
    private TextField txtNomeProd;
    @FXML
    private TextField txtPrezzoProd;
    @FXML
    private TextArea txtDescrizioneProd;
    @FXML
    private ComboBox<Integer> cmbAzienda;
    
    @FXML
    private ComboBox<Date> cmbGiorno;
    @FXML
    private ComboBox<Integer> cmbPadiglione;
    @FXML
    private Spinner<Time> spnOrario;
    @FXML
    private ComboBox<String> cmbCodiceFiscalePers;
    @FXML
    private TextField txtCancellaTurno;
	
	@Override
	public void initialize (URL url, ResourceBundle rb) {
		
		// Sets up the opening/closing spinners.
		ObservableList<Time> hours = FXCollections.observableArrayList(new Time(-2211735600000L), new Time(-2211733800000L), new Time(-2211732000000L), new Time(-2211730200000L), //6 
				new Time(-2211728400000L), new Time(-2211726600000L), new Time(-2211724800000L), new Time(-2211723000000L), //8
				new Time(-2211721200000L), new Time(-2211719400000L), new Time(-2211717600000L), new Time(-2211715800000L), //10
				new Time(-2211714000000L), new Time(-2211712200000L), new Time(-2211710400000L), new Time(-2211708600000L), //12
				new Time(-2211706800000L), new Time(-2211705000000L), new Time(-2211703200000L), new Time(-2211701400000L), //14
				new Time(-2211699600000L), new Time(-2211697800000L), new Time(-2211696000000L), new Time(-2211694200000L), //16
				new Time(-2211692400000L), new Time(-2211690600000L), new Time(-2211688800000L), new Time(-2211687000000L), //18
				new Time(-2211685200000L), new Time(-2211683400000L), new Time(-2211681600000L), new Time(-2211679800000L), //20
				new Time(-2211678000000L));//22

		SpinnerValueFactory<Time> valueFactory1 = new SpinnerValueFactory.ListSpinnerValueFactory<Time>(hours);
		SpinnerValueFactory<Time> valueFactory2 = new SpinnerValueFactory.ListSpinnerValueFactory<Time>(hours);
		SpinnerValueFactory<Time> valueFactory3 = new SpinnerValueFactory.ListSpinnerValueFactory<Time>(hours);

		valueFactory1.setValue(new Time(-2211714000000L));
		valueFactory2.setValue(new Time(-2211714000000L));
		valueFactory3.setValue(new Time(-2211714000000L));
		       
		spnApertura.setValueFactory(valueFactory1);
		spnChiusura.setValueFactory(valueFactory2);
		spnOrario.setValueFactory(valueFactory3);
		
		// Sets up the children places combobox.
		cmbBambini.getItems().clear();
		cmbBambini.getItems().addAll(daysTable.getAllDays());
		
		// Fills the stands table.
		ObservableList<Stand> standsList = FXCollections.observableArrayList(standsTable.getAllStands());
		
		 colCodice.setCellValueFactory(new PropertyValueFactory<Stand,Integer>("codicePadiglione"));
		 colSpecializzazione.setCellValueFactory(new PropertyValueFactory<Stand,String>("specializzazioneRichiesta"));
		 colOrarioApertura.setCellValueFactory(new PropertyValueFactory<Stand,Time>("orarioApertura"));
		 colOrarioChiusura.setCellValueFactory(new PropertyValueFactory<Stand,Time>("OrarioChiusura"));
		 colBambini.setCellValueFactory(new PropertyValueFactory<Stand,Date>("giornoAperturaAreaBambini"));
		 colSpaziTot.setCellValueFactory(new PropertyValueFactory<Stand,Integer>("numSpaziEsposizioneTot"));
		 colSpaziOcc.setCellValueFactory(new PropertyValueFactory<Stand,Integer>("numSpaziEsposizioneOccupati"));
		 
		 tblPadiglioni.setItems(standsList);
		 
		 cmbSpecializzazione.getItems().clear();
		 cmbSpecializzazione.getItems().addAll(standsTable.getAllStands().stream().map(s -> s.getSpecialization()).collect(Collectors.toList()));
		 
		 cmbAzienda.getItems().clear();
		 cmbAzienda.getItems().addAll(companiesTable.getAllCompaniesCodes());
		 
		 cmbPadiglione.getItems().clear();
		 cmbPadiglione.getItems().addAll(standsTable.getAllStands().stream().map(s -> s.getStandCod()).collect(Collectors.toList()));
		 cmbCodiceFiscalePers.getItems().clear();
		 cmbCodiceFiscalePers.getItems().addAll(staffTable.getAllStaffCF());
		 cmbGiorno.getItems().clear();
		 cmbGiorno.getItems().addAll(daysTable.getAllDays());
	}

	public void goBack(ActionEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("/RolePane.fxml"));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    stage.setScene(new Scene(root));
	    stage.show();
	}
	
	public void btnAddStand(ActionEvent event) throws IOException {
	    int totalSpaces;
	    int occupiedSpaces;
		try {
			totalSpaces = Integer.parseInt(txtSpaziTot.getText());
			occupiedSpaces = Integer.parseInt(txtSpaziOcc.getText());
			if (occupiedSpaces > totalSpaces) {
				throw new Exception();
			}
			Random rand = new Random();
	    	boolean assigned = false;
	    	while(assigned == false) {
	    		randnum = rand.nextInt(200)+1;
	    		System.out.println(randnum);
	    		if (standsTable.checkId(randnum) == false) {
	    			standsTable.addStand(randnum, txtSpecializzazione.getText(), spnApertura.getValue(), spnChiusura.getValue(), cmbBambini.getValue(), totalSpaces, occupiedSpaces);
	    			assigned = true;
	    		}
	    	}
		} catch (IllegalArgumentException arg) {
			System.out.println("Devi inserire per forza un numero!!");
		}catch (Exception e) {
			System.out.println("Non è possibile occupare più spazi di quelli totali!");
		}
	}
	
	public void btnDeleteStand(ActionEvent event) throws IOException {
	    try {
			int code = Integer.parseInt(txtCancellaPadiglione.getText());
			standsTable.deleteStand(code);
		} catch (IllegalArgumentException arg) {
			System.out.println("Devi inserire per forza un numero!!");
		}
    }
	
	public void btnAddCompany(ActionEvent event) throws IOException {
	    Random rand = new Random();
    	boolean assigned = false;
    	while(assigned == false) {
    		randnum = rand.nextInt(200)+1;
    		if (companiesTable.checkId(randnum) == false) {
    			int num = standsTable.findStandNum(cmbSpecializzazione.getValue());
    			//lblPadiglioneAz.setText(String.valueOf(num));
    		    companiesTable.addCompany(randnum, txtDenominazione.getText(), cmbSpecializzazione.getValue(), num);
    			assigned = true;
    		}
    	}
    }
	
	public void btnDeleteCompany(ActionEvent event) throws IOException {
	    try {
			int code = Integer.parseInt(txtCancellaAzienda.getText());
			companiesTable.deleteCompany(code);
		} catch (IllegalArgumentException arg) {
			System.out.println("Devi inserire per forza un numero!!");
		}
    }
	
	public void btnAddProduct(ActionEvent event) throws IOException {
	    Random rand = new Random();
    	boolean assigned = false;
    	while(assigned == false) {
    		randnum = rand.nextInt(200)+1;
    		if (companiesTable.checkId(randnum) == false) {
    			try {
    				int price = Integer.parseInt(txtPrezzoProd.getText());
    				productsTable.addProduct(randnum, txtNomeProd.getText(), price, txtDescrizioneProd.getText(), cmbAzienda.getValue());
    			} catch (IllegalArgumentException arg) {
    				System.out.println("Devi inserire per forza un numero!!");
    			}
    			
    			assigned = true;
    		}
    	} 
    }
	
	public void btnDeleteProduct(ActionEvent event) throws IOException {
		try {
			int code = Integer.parseInt(txtCancellaProdotto.getText());
			productsTable.deleteProduct(code);
		} catch (IllegalArgumentException arg) {
			System.out.println("Devi inserire per forza un numero!!");
		}
    }
	
	public void btnAddTurn(ActionEvent event) throws IOException {
		Random rand = new Random();
    	boolean assigned = false;
    	while(assigned == false) {
    		randnum = rand.nextInt(200)+1;
    		if (companiesTable.checkId(randnum) == false) {
    			turnsTable.addTurn(randnum, cmbGiorno.getValue(), spnOrario.getValue(), cmbCodiceFiscalePers.getValue(), cmbPadiglione.getValue());
    			assigned = true;
    		}
    	} 
    }
        
        public void btnDeleteTurn(ActionEvent event) throws IOException {
            try {
    			int code = Integer.parseInt(txtCancellaTurno.getText());
    			turnsTable.deleteTurn(code);
    		} catch (IllegalArgumentException arg) {
    			System.out.println("Devi inserire per forza un numero!!");
    		}
        }
	
}
