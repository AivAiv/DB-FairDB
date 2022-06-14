package tables;

import java.sql.Connection;
import java.util.Objects;

public class TicketsDaysTable {

	public static final String TABLE_NAME = "biglietti-giorni";

    private final Connection connection; 

    public TicketsDaysTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
}
