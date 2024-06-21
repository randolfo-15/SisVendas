package bank;

import java.sql.*;

public class Query{
    //==================================================================================================================
    // Field
    //==================================================================================================================
    Connection conn;
    //==================================================================================================================
    public Query(){
        try{ conn = DriverManager.getConnection(SQL.URL); }
        catch (SQLException ignored){}
    }

    public void end(){
        try { conn.close(); }
        catch (SQLException ignored){}
    }

    public void select(String table,String column,String field,Archivable arq){
        try{
            PreparedStatement pstt = conn.prepareStatement(("SELECT * FROM "+table+" WHERE "+column+" = ? ;"));
            pstt.setString(1, field );
            ResultSet result = pstt.executeQuery();
            if(result.next())arq.read(result);
        }
        catch( SQLException ignored){}
    }

    public boolean exist(String table,String column,String field){

        try {
            PreparedStatement pstt = conn.prepareStatement(("SELECT * FROM "+table+" WHERE "+column+" = ? ;"));
            pstt.setString(1, field);
            ResultSet resultSet = pstt.executeQuery();
            if (resultSet.next()) return true;
        }
        catch (SQLException ignored){ return false; }

        return false;
    }

    public void update(String table,String column,String field,Archivable arq){
        try{
            PreparedStatement pstt = conn.prepareStatement("UPDATE "+table+" SET "+arq.edit()+" WHERE "+column+" = ? ;");
            pstt.setString(1,field);
            pstt.executeUpdate();
        }catch (SQLException ignored){}
    }
    
    public void delete(String table, String column, String field){
        try{
            PreparedStatement pstt = conn.prepareStatement((" DELETE FROM "+table+" WHERE "+column+" = ? ;"));
            pstt.setString(1,field);
            pstt.executeUpdate();
        }catch (SQLException ignored){}
    }

    public void insert(String table,Archivable arq){
        try {
            String
                column = arq.write()[Archivable.COLUMN],
                value  = arq.write()[Archivable.VALUES];
            PreparedStatement pstt = conn.prepareStatement(("INSERT INTO "+table+" ("+column+") VALUES ("+value+");"));
            pstt.executeUpdate();
        }catch (SQLException ignored){}
    }

}
