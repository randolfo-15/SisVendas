package bank;

import java.sql.*;

import static bank.SQL.PASSWORD;
import static bank.SQL.USER;

public class Query<T> {


    Connection connection;

    public Query() {

        try {

        connection = DriverManager.getConnection(SQL.URL, USER, PASSWORD);

        } catch (Exception e) {

        }
    }

    //void desconnect(){ connection.close(); }

    public static void search(String table,String column,String field,Archivable arq){
        try{
            Connection connection = DriverManager.getConnection(SQL.URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.SELECT);
            preparedStatement.setString(1, column);
            preparedStatement.setString(2, field );
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.getRow() > 0) arq.read(resultSet);
            connection.close();
        }
        catch( SQLException ignored){}
    }


    //MÉTODO PARA INSERIR USUÁRIO NO BANCO DE DADOS
    public static void insertUser(String name, String uname, String phone, String email, String passw, boolean adm){

        String insertSQL = "INSERT INTO users (name, uname, phone, email, passw, adm) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(SQL.URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, uname);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, passw);
            preparedStatement.setBoolean(6, adm);

            // Executando a consulta de inserção
            preparedStatement.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        }
    }

    //MÉTODO PARA ATUALIZAR USUÁRIO
    public static void updateUser(String name, String uname, String phone, String email, String passw, boolean adm) {
        String updateSQL = "UPDATE users SET name = ?, uname = ?, phone = ?, email = ?, passw = ?, adm = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(SQL.URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, uname);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, passw);
            preparedStatement.setBoolean(6, adm);

            // Executando a consulta de atualização
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dados atualizados com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dados: " + e.getMessage());
        }
    }

    //MÉTODO PARA DELETAR USUÁRIO
    public static void deleteUserByName(String name) {
        String deleteSQL = "DELETE FROM users WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(SQL.URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setString(1, name);

            // Executando a consulta de deleção
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário deletado com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado com o nome fornecido.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    //MÉTODO PARA INSERIR PRODUTOS NO BANCO DE DADOS.
    public static void insertProduct(String name, String ctry, String codigo, float valor, int amount) {
        String insertSQL = "INSERT INTO produtos (name, ctry, codigo, valor, amount) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(SQL.URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, ctry);
            preparedStatement.setString(3, codigo);
            preparedStatement.setDouble(4, valor);
            preparedStatement.setInt(5, amount);

            // Executando a consulta de inserção
            preparedStatement.executeUpdate();
            System.out.println("Produto inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
        }
    }

    //MÉTODO PARA ATUALIZAR PRODUTO
    public static void updateProduct(String name, String ctry, String codigo, float valor, int amount) {
        String updateSQL = "UPDATE produtos SET name = ?, ctry = ?, codigo = ?, valor = ?, amount = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(SQL.URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, ctry);
            preparedStatement.setString(3, codigo);
            preparedStatement.setDouble(4, valor);
            preparedStatement.setInt(5, amount);

            // Executando a consulta de atualização
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    //MÉTODO PARA DELETAR
    public static void deleteProductByName(String name) {
        String deleteSQL = "DELETE FROM produtos WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(SQL.URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setString(1, name);

            // Executando a consulta de exclusão
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto deletado com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado com o nome fornecido.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar produto: " + e.getMessage());
        }
    }

    private boolean exist(String table,String column,String field){
        String query = "SELECT * FROM "+table+" WHERE "+column+" = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, field);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) return false;
        }
        catch (Exception e){ e.printStackTrace(); }

        return true;
    }

    public boolean exist_name_user(String field){

        return exist(SQL.TABLE_USER,SQL.COLUNM_NAME, field);
    }
}
