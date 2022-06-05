module FairManager {
	requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;
    requires java.xml;
	
	opens application to javafx.graphics, javafx.fxml;
}
