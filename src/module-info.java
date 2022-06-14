module FairManager {
	requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;
    requires java.xml;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
}
