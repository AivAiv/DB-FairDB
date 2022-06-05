package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RolesScene extends Scene {
    
    private Stage stage;
    private GridPane rolesPane;
    @FXML
    private Button btnManager;
    @FXML
    private Button btnEmployee;
    private SceneSwitcher sw;
    
    public RolesScene(Stage stage, GridPane pane, SceneSwitcher sw) {
        super(pane);
        this.stage = stage;
        this.rolesPane = pane;
        this.sw = sw;
        
        //this.btnManager = new Button("Manager");
        //this.btnEmployee = new Button("Personale");
        
        //GridPane.setConstraints(btnManager, 1, 1);
        //GridPane.setConstraints(btnEmployee, 2, 1);
        //pane.setAlignment(Pos.CENTER);
        //pane.setGridLinesVisible(true);
        //pane.setHgap(20);
        
        //this.btnManager.setOnAction(action ->  {
        //    sw.showManager();
        // });
        
        //this.btnEmployee.setOnAction(action ->  {
        //    sw.showEmployeesMenu();
        // });
        
        //pane.getChildren().addAll(btnManager, btnEmployee);
    }
    
    public Button getBtnManager() {
        return this.btnManager;
    }
    
    public Button getBtnEmployee() {
        return this.btnEmployee;
    }

}
