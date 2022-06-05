package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmployeesMenuScene extends Scene{
    
    private Stage stage;
    private GridPane employeePane;
    private Label lblSectionName;
    private Button btnTurns;
    private Button btnTickets;
    
    public EmployeesMenuScene(Stage stage, GridPane pane) {
        super(pane);
        this.stage = stage;
        this.employeePane = pane;
        
        this.lblSectionName = new Label("Personale");
        this.btnTurns = new Button("Turni");
        this.btnTickets = new Button("Biglietti");
        
        GridPane.setConstraints(lblSectionName, 0, 0);
        GridPane.setConstraints(btnTurns, 1, 1);
        GridPane.setConstraints(btnTickets, 1, 2);
        
        pane.getChildren().addAll(lblSectionName, btnTurns, btnTickets);
    }
    
    public Button getBtnTurns() {
        return this.btnTurns;
    }
    
    public Button getBtnTickets() {
        return this.btnTickets;
    }

}
