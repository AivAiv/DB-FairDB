package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SceneSwitcher {
    
    private Stage stage;
    private RolesScene rolesScene;
    @FXML
    private Parent rolePane;
    private EmployeesMenuScene employeesMenuScene;
    private ManagerScene managerScene;
    @FXML
    private Button btnManager;
    
    SceneSwitcher(Stage stage) {
        this.stage = stage;
        this.rolesScene = new RolesScene(stage, new GridPane(), this);
        this.employeesMenuScene = new EmployeesMenuScene(stage, new GridPane());
        this.managerScene = new ManagerScene(stage, new GridPane());
    }
    
    public void showRoles() throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/RolePane.fxml"))));
        stage.show();
    }
    
    public void showEmployeesMenu() {
        stage.setScene(employeesMenuScene);
        stage.show();
    }
    
    public void showManager() {
        //stage.setScene(managerScene);
        //stage.show();
        btnManager.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(managerScene);
                stage.show();
            }
        });
    }
    
    
    /*
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btnEmployee1;

    @FXML
    public void switchToRole(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/RolePane.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("role");
    }
    
    @FXML
    public void switchToEmpMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EmployeesMenuPane.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("empl");
    }*/
    
}
