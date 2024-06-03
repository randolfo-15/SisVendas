package bank;

import java.sql.*;

public class Query<T> {


    private static Connection connection;
    //Connection connection;

    public Query() {

        try {

        connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD);

        } catch (Exception e) {

        }
    }

    //PESQUISAR - PRODUTO E USUÁRIO
    public static void search(String table,String column,String field,Archivable arq){
        try{
            Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.SELECT);
            preparedStatement.setString(1, column);
            preparedStatement.setString(2, field );
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.getRow() > 0) arq.read(resultSet);
            connection.close();
        }
        catch( SQLException ignored){}
    }

    //MÉTODO PARA INSERIR

    public static boolean exist(String table, String column, String field){
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

    //Método para criação da tabela no banco de dados
    public static void createTable(){

        try (Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD)) {
            // Verifica se a conexão foi bem-sucedida
            if (connection != null) {
                System.out.println("Conectado ao banco de dados!");

                // Criação da tabela
                String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(50) NOT NULL, " +
                        "email VARCHAR(50) NOT NULL UNIQUE, " +
                        "data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ");";

                try (Statement statement = connection.createStatement()) {
                    // Executa a declaração SQL para criar a tabela
                    statement.execute(createTableSQL);
                    System.out.println("Tabela criada com sucesso!");
                } catch (SQLException e) {
                    System.err.println("Erro ao criar a tabela: " + e.getMessage());
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados!");
            }
        } catch (SQLException e) {
            System.err.println("Erro de conexao: " + e.getMessage());
        }
    }

    //Método para inserir usuário no Banco de dados

    public static void insertUser(String name, String uname, String phone, String email, String passw, boolean adm) {
        String insertSQL = "INSERT INTO users (name, uname, phone, email, passw, adm) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD);
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



    public static void quantidadeRegistros(){

        int count = getRecordCount();
        System.out.println("Quantidade de registros na tabela: " + count);
    }

    public static int getRecordCount() {
        String countSQL = "SELECT COUNT(*) FROM users";
        int count = 0;

        try (Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(countSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Obtém o resultado da contagem
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter a contagem de registros: " + e.getMessage());
        }

        return count;
    }
}
