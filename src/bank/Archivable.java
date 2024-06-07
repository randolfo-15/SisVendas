package bank;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Archivable {
    public static final int
            COLUMN = 0 ,
            VALUES = 1 ;

    public void read(ResultSet result) throws SQLException;
    public String edit();
    public String[] write();
    public void clear();
}
