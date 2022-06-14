package application;

public class AppController {
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "labjdbc";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    //final static StudentsTable studentsTable = new StudentsTable(connectionProvider.getMySQLConnection());

}
