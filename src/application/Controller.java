package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    
    private Stage stage;
    
    public void switchToRole(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/RolePane.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void switchToEmpMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EmployeesMenuPane.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void switchToManager(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ManagerPane.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
