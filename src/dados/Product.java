package dados;

import Manager.Program;
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
        return
            SQL.COLUMN_NAME +"='"+name+"',"+
            SQL.COLUMN_CATEGORY +"='"+ctry+"',"+
            SQL.COLUMN_CODE +"='"+code+"',"+
            SQL.COLUMN_VALUE +"="+value+","+
            SQL.COLUMN_AMOUNT +"="+amount;
    }

    @Override
    public String[] write() {
        String column =
            SQL.COLUMN_NAME +","+
            SQL.COLUMN_CATEGORY +","+
            SQL.COLUMN_CODE+","+
            SQL.COLUMN_VALUE +","+
            SQL.COLUMN_AMOUNT;

        String values =
            "'"+name+"',"+
            "'"+ctry+"',"+
            "'"+code+"',"+
            "'"+value+"',"+
            "'"+amount+"'";

        return new String[]{column,values};
    }

    @Override
    public void clear() {
        name   = "";
        code   = "";
        ctry   = "";
        value  = 0.00F;
        amount = 0;
    }

    //==================================================================================================================
    // Exceptions class
    //==================================================================================================================
    public static class Lacking extends Cases_of_Error{ Lacking(){super(("Produto em falta.")); }}
    public static class Existing_code extends Cases_of_Error{ Existing_code(String code){ super(("O código "+code+" está em uso")); }}
    public static class Inconsistent extends Cases_of_Error{ Inconsistent(){ super(("Valor incoerente.")); }}

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

    public void set_name(String name) throws Empty_field {
        if( name.isEmpty() ) throw new Empty_field("Nome") ;
        this.name=name;
    }

    public void set_code(String code) throws Existing_code,Empty_field {
        if     (                       code.isEmpty()                      ) throw new Empty_field("Code");
        else if(Program.query.exist(SQL.TABLE_PRODUCT,SQL.COLUMN_CODE,name)) throw new Existing_code(code);
        this.code=code;
    }

    public void set_category(String category) { this.ctry=category.toUpperCase(); }

    public void set_value(float value) throws Inconsistent {
        if( value <= 0) throw  new Inconsistent();
        this.value=value;
    }

    public void set_amount(int amount) throws Lacking {
        if(amount <= 0) throw new Lacking();
        this.amount=amount;
    }

    //==================================================================================================================
    // Operator
    //==================================================================================================================
    public void more(int n){ amount+=n; }
    public void less(int n){ amount-=n; }


}
