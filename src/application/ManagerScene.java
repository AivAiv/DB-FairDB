package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ManagerScene extends Scene {

    private Stage stage;
    private GridPane managerPane;
    private Button btnTurns;
    private Label lblSectionName;
    
    public ManagerScene(Stage stage, GridPane pane) {
        super(pane);
        this.stage = stage;
        this.managerPane = pane;
        
        this.lblSectionName = new Label("Manager");
        this.btnTurns = new Button("Turni");
        
        GridPane.setConstraints(lblSectionName, 0, 0);
        GridPane.setConstraints(btnTurns, 2, 1);
        
        pane.getChildren().addAll(lblSectionName, btnTurns);
    }

}
