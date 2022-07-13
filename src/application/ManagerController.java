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
import structures.Company;
import structures.Product;
import structures.Stand;
import structures.Turn;
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
	private ObservableList<Stand> standsList;
	private ObservableList<Company> companiesList;
	private ObservableList<Product> productsList;
	private ObservableList<Turn> turnsList;
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
    

    @FXML
    private TableColumn<Company, Integer> colCodiceAz;
    @FXML
    private TableColumn<Company, String> colDenominazione;
    @FXML
    private TableColumn<Company, String> colSpecAz;
    @FXML
    private TableColumn<Company, Integer> colNumPadiglione;
    
    @FXML
    private TableColumn<Product,Integer> colCodAzienda;
    @FXML
    private TableColumn<Product, Integer> colCodiceProd;
    @FXML
    private TableColumn<Product, String> colDesc;
    @FXML
    private TableColumn<Product, String> colNome;
    @FXML
    private TableColumn<Product, Double> colPrezzo;
    
    @FXML
    private TableColumn<Turn, Integer> colCodPad;
    @FXML
    private TableColumn<Turn, String> colCodiceFiscale;
    @FXML
    private TableColumn<Turn, Integer> colCodiceTurno;
    @FXML
    private TableColumn<Turn, Date> colGiorno;
    @FXML
    private TableColumn<Turn, Time> colOrario;
    
    @FXML
    private TableView<Company> tblAziende;
    @FXML
    private TableView<Product> tblProdotti;
    @FXML
    private TableView<Turn> tblTurni;

	
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
		standsList = FXCollections.observableArrayList(standsTable.getAllStands());
		
		colCodice.setCellValueFactory(new PropertyValueFactory<Stand,Integer>("standCod"));
		colSpecializzazione.setCellValueFactory(new PropertyValueFactory<Stand,String>("specialization"));
		colOrarioApertura.setCellValueFactory(new PropertyValueFactory<Stand,Time>("open"));
		colOrarioChiusura.setCellValueFactory(new PropertyValueFactory<Stand,Time>("close"));
		colBambini.setCellValueFactory(new PropertyValueFactory<Stand,Date>("children"));
		colSpaziTot.setCellValueFactory(new PropertyValueFactory<Stand,Integer>("expTot"));
		colSpaziOcc.setCellValueFactory(new PropertyValueFactory<Stand,Integer>("expOcc"));
		 
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
		 
		// Fills the companies table.
		companiesList = FXCollections.observableArrayList(companiesTable.getAllCompanies());
		
	    colCodiceAz.setCellValueFactory(new PropertyValueFactory<Company,Integer>("codCompany"));
	    colDenominazione.setCellValueFactory(new PropertyValueFactory<Company,String>("name"));
	    colSpecAz.setCellValueFactory(new PropertyValueFactory<Company,String>("specialization"));
	    colNumPadiglione.setCellValueFactory(new PropertyValueFactory<Company,Integer>("codStand"));
	    
		tblAziende.setItems(companiesList);
		
		// Fills the products table.
		productsList = FXCollections.observableArrayList(productsTable.getAllProducts());
		
		colCodiceProd.setCellValueFactory(new PropertyValueFactory<Product,Integer>("codProduct"));
		colNome.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
		colPrezzo.setCellValueFactory(new PropertyValueFactory<Product,Double>("price"));
		colDesc.setCellValueFactory(new PropertyValueFactory<Product,String>("description"));
		colCodAzienda.setCellValueFactory(new PropertyValueFactory<Product,Integer>("companyCode"));
		
		tblProdotti.setItems(productsList);
		
		// Fills the turns table.
		turnsList = FXCollections.observableArrayList(turnsTable.getAllTurns());
		
		colCodiceTurno.setCellValueFactory(new PropertyValueFactory<Turn,Integer>("cod"));
		colGiorno.setCellValueFactory(new PropertyValueFactory<Turn,Date>("day"));
		colOrario.setCellValueFactory(new PropertyValueFactory<Turn,Time>("time"));
		colCodiceFiscale.setCellValueFactory(new PropertyValueFactory<Turn,String>("cf"));
		colCodPad.setCellValueFactory(new PropertyValueFactory<Turn,Integer>("codStand"));
		
		tblTurni.setItems(turnsList);
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
	    		if (standsTable.checkId(randnum) == false) {
	    			standsTable.addStand(randnum, txtSpecializzazione.getText(), spnApertura.getValue(), spnChiusura.getValue(), cmbBambini.getValue(), totalSpaces, occupiedSpaces);
	    			this.updateStandTable();
	    			assigned = true;
	    		}
	    	}
		} catch (IllegalArgumentException arg) {
			System.out.println("Devi inserire per forza un numero!!");
		}catch (Exception e) {
			System.out.println("Non è possibile occupare più spazi di quelli totali!");
		}
		
		txtSpecializzazione.setText("");
		txtSpaziTot.setText("");
		txtSpaziOcc.setText("");
	}
	
	public void btnDeleteStand(ActionEvent event) throws IOException {
	    try {
			int code = Integer.parseInt(txtCancellaPadiglione.getText());
			standsTable.deleteStand(code);
			this.updateStandTable();
		} catch (IllegalArgumentException arg) {
			System.out.println("Devi inserire per forza un numero!!");
		}
	    
	    txtCancellaPadiglione.setText("");
    }
	
	public void btnAddCompany(ActionEvent event) throws IOException {
	    Random rand = new Random();
    	boolean assigned = false;
    	while(assigned == false) {
    		randnum = rand.nextInt(200)+1;
    		if (companiesTable.checkId(randnum) == false) {
    			int num = standsTable.findStandNum(cmbSpecializzazione.getValue());
    		    companiesTable.addCompany(randnum, txtDenominazione.getText(), cmbSpecializzazione.getValue(), num);
    		    this.updateCompanyTable();
    			assigned = true;
    		}
    	}
    	
    	txtDenominazione.setText("");
    	lblPadiglioneAz.setText("0");
    }
	
	public void btnDeleteCompany(ActionEvent event) throws IOException {
	    try {
			int code = Integer.parseInt(txtCancellaAzienda.getText());
			companiesTable.deleteCompany(code);
			this.updateCompanyTable();
		} catch (IllegalArgumentException arg) {
			System.out.println("Devi inserire per forza un numero!!");
		}
	    
	    txtCancellaAzienda.setText("");
    }
	
	public void btnAddProduct(ActionEvent event) throws IOException {
	    Random rand = new Random();
    	boolean assigned = false;
    	while(assigned == false) {
    		randnum = rand.nextInt(200)+1;
    		if (productsTable.checkId(randnum) == false) {
    			try {
    				double price = Integer.parseInt(txtPrezzoProd.getText());
    				productsTable.addProduct(randnum, txtNomeProd.getText(), price, txtDescrizioneProd.getText(), cmbAzienda.getValue());
    				this.updateProductsTable();
    			} catch (IllegalArgumentException arg) {
    				System.out.println("Devi inserire per forza un numero!!");
    			}
    			assigned = true;
    		}
    	}
    	
    	txtPrezzoProd.setText("");
    	txtNomeProd.setText("");
    	txtDescrizioneProd.setText("");
    }
	
	public void btnDeleteProduct(ActionEvent event) throws IOException {
		try {
			int code = Integer.parseInt(txtCancellaProdotto.getText());
			productsTable.deleteProduct(code);
			this.updateProductsTable();
		} catch (IllegalArgumentException arg) {
			System.out.println("Devi inserire per forza un numero!!");
		}
		
		txtCancellaProdotto.setText("");
    }
	
	public void btnAddTurn(ActionEvent event) throws IOException {
		Random rand = new Random();
    	boolean assigned = false;
    	while(assigned == false) {
    		randnum = rand.nextInt(200)+1;
    		if (companiesTable.checkId(randnum) == false) {
    			turnsTable.addTurn(randnum, cmbGiorno.getValue(), spnOrario.getValue(), cmbCodiceFiscalePers.getValue(), cmbPadiglione.getValue());
    			this.updateTurnsTable();
    			assigned = true;
    		}
    	}
    }

    public void btnDeleteTurn(ActionEvent event) throws IOException {
        try {
			int code = Integer.parseInt(txtCancellaTurno.getText());
			turnsTable.deleteTurn(code);
			this.updateTurnsTable();
		} catch (IllegalArgumentException arg) {
			System.out.println("Devi inserire per forza un numero!!");
		}
        
        txtCancellaTurno.setText("");
    }
    
    public void updateStandTable(){
    	standsList = FXCollections.observableArrayList(standsTable.getAllStands());
		tblPadiglioni.setItems(standsList);
    }
    
    public void updateCompanyTable(){
    	companiesList = FXCollections.observableArrayList(companiesTable.getAllCompanies());
		tblAziende.setItems(companiesList);
    }
    
    public void updateProductsTable(){
    	productsList = FXCollections.observableArrayList(productsTable.getAllProducts());
		tblProdotti.setItems(productsList);
    }
    
    public void updateTurnsTable(){
    	turnsList = FXCollections.observableArrayList(turnsTable.getAllTurns());
		tblTurni.setItems(turnsList);
    }
    
    public void updateStandNum(ActionEvent event) throws IOException{
    	lblPadiglioneAz.setText(String.valueOf(standsTable.findStandNum(cmbSpecializzazione.getValue())));
    }
	
}
