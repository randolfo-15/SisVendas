package bank;

import java.sql.*;

public class Query{

    public static void select(String table,String column,String field,Archivable arq){
        try{
            String query = "SELECT * FROM "+table+" WHERE "+column+" = ? ;";
            Connection conn = DriverManager.getConnection(SQL.URL);
            PreparedStatement pstt = conn.prepareStatement(query);
            pstt.setString(1, field );
            ResultSet result = pstt.executeQuery();
            if(result.next())arq.read(result);

            conn.close();
        }
        catch( SQLException ignored){}
    }

    public static boolean exist(String table,String column,String field){

        try {
            String query = "SELECT * FROM "+table+" WHERE "+column+" = ? ;";
            Connection conn = DriverManager.getConnection(SQL.URL);
            PreparedStatement pstt = conn.prepareStatement(query);
            pstt.setString(1, field);
            ResultSet resultSet = pstt.executeQuery();

            conn.close();
            if (resultSet.next()) return true;
        }
        catch (SQLException ignored){}

        return false;
    }

    public static void update(String table,String column,String field,Archivable arq){
        try{
            String query = "UPDATE "+table+" SET "+arq.edit()+" WHERE "+column+" = ? ;";
            Connection conn = DriverManager.getConnection(SQL.URL);
            PreparedStatement pstt = conn.prepareStatement(query);
            pstt.setString(1,field);
            pstt.executeUpdate();
            conn.close();
        }catch (SQLException ignored){}
    }
    
    public static void delete(String table, String column, String field){
        try{
            String query = " DELETE FROM "+table+" WHERE "+column+" = ? ;";
            Connection conn = DriverManager.getConnection(SQL.URL);
            PreparedStatement pstt = conn.prepareStatement(query);
            pstt.setString(1,field);
            pstt.executeUpdate();
            conn.close();
        }catch (SQLException ignored){}
    }
}
