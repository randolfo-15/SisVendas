package bank;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Archivable {
    public void read(ResultSet result) throws SQLException;
    public String edit();
}
