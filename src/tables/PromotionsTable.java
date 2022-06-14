package tables;

import java.sql.Connection;
import java.util.Objects;

public class PromotionsTable {

	public static final String TABLE_NAME = "promozioni";

    private final Connection connection; 

    public PromotionsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
}
