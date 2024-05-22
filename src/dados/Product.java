package dados;

import bank.Archivable;
import bank.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product implements Archivable {
    //==================================================================================================================
    // Field
    //==================================================================================================================
    private String
            name = "",
            ctry = "",
            code = "";

    private float value = 0.0F;
    private int amount = 0;

    //==================================================================================================================
    // Build
    //==================================================================================================================
    public Product(){}

    @Override
    public void read(ResultSet result) throws SQLException {
        name  = result.getString (  SQL.COLUMN_NAME);
        ctry  = result.getString (  SQL.COLUMN_CATEGORY  );
        code  = result.getString (  SQL.COLUMN_CODE      );
        value  = result.getFloat (  SQL.COLUMN_VALUE     );
        amount  = result.getInt  (  SQL.COLUMN_AMOUNT    );
    }

    @Override
    public String edit() {
        return "";
    }

    @Override
    public String[] write() {
        return new String[]{};
    }

    //==================================================================================================================
    // Exceptions class
    //==================================================================================================================
    public static class Existing_name extends Exception{ public String msg(){ return "Este nome já se encontra em uso.";   }}
    public static class Existing_code extends Exception{ public String msg(){ return "Este código já se encontra em uso."; }}

    //==================================================================================================================
    // Getting
    //==================================================================================================================
    public String get_name()     { return name;   }
    public String get_code()     { return code;   }
    public String get_category() { return ctry;   }
    public float  get_value()    { return value;  }
    public int    get_amount()   { return amount; }
    //==================================================================================================================
    // Setting
    //==================================================================================================================
    public void set_name(String name) throws Existing_name {
        if(/*Query.exist(SQL.TABLE_PRODUCT,SQL.COLUMN_NAME,name.toUpperCase)*/false ) throw new Existing_name();
        this.name=name.toUpperCase();
    }
    public void set_code(String code) throws Existing_code {
        if(/*Query.exist(SQL.TABLE_PRODUCT,SQL.COLUMN_CODE,name)*/false ) throw new Existing_code();
        this.code=code;
    }

    public void set_category(String category) { this.ctry=category.toUpperCase(); }

    public void set_value(float value) { this.value=value;   }

    public void set_amount(int amount) { this.amount=amount; }

    //==================================================================================================================
    // Operator
    //==================================================================================================================
    public void more(int n){ amount+=n; }
    public void less(int n){ amount-=n; }
}
