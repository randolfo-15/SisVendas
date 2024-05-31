package bank;

import java.sql.*;

public class Query1 <T> {


    Connection connection;

    public Query1() { try {

        connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD);

        } catch (Exception e) {

        } }

    //void desconnect(){ connection.close(); }

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

    //Método para criação da tabela no banco de dados
    public static void createTable(){

        try (Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD)) {
            // Verifica se a conexão foi bem-sucedida
            if (connection != null) {
                System.out.println("Conectado ao banco de dados!");

                // Criação da tabela
                String createTableSQL = "CREATE TABLE IF NOT EXISTS exemplo (" +
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
            System.err.println("Erro de conexão: " + e.getMessage());
        }
    }

    //Método de busca no banco por ID
    public static String buscaPorId(String a){
        int idToFetch = 1; // ID do elemento que você deseja buscar

        try (Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD)) {
            // Verifica se a conexão foi bem-sucedida
            if (connection != null) {
                System.out.println("Conectado ao banco de dados!");

                // Consulta para buscar o elemento pelo ID
                String selectSQL = "SELECT * FROM exemplo WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                    // Define o valor do ID na consulta
                    preparedStatement.setInt(1, idToFetch);

                    // Executa a consulta
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Processa o resultado
                        if (resultSet.next()) {
                            // Supondo que a tabela "exemplo" tem colunas: id, nome, email, data_criacao
                            int id = resultSet.getInt("id");
                            String nome = resultSet.getString("nome");
                            String email = resultSet.getString("email");
                            String dataCriacao = resultSet.getString("data_criacao");

                            System.out.println("ID: " + id);
                            System.out.println("Nome: " + nome);
                            System.out.println("Email: " + email);
                            System.out.println("Data de Criação: " + dataCriacao);
                        } else {
                            System.out.println("Elemento com ID " + idToFetch + " não encontrado.");
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao executar a consulta: " + e.getMessage());
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
        }
        return null;
    }

    //Método busrcar por uname
    public static void buscaPorUname(String Uname){
        //String Uname = "";

        try (Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD)) {
            // Verifica se a conexão foi bem-sucedida
            if (connection != null) {
                System.out.println("Conectado ao banco de dados!");

                // Consulta para buscar o elemento pelo ID
                String selectSQL = "SELECT * FROM exemplo WHERE uname = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                    // Define o valor do ID na consulta
                    preparedStatement.setString(1, Uname);

                    // Executa a consulta
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Processa o resultado
                        if (resultSet.next()) {
                            // Supondo que a tabela "exemplo" tem colunas: id, nome, email, data_criacao
                            int id = resultSet.getInt("id");
                            String nome = resultSet.getString("nome");
                            String uname = resultSet.getString("uname");
                            String email = resultSet.getString("email");
                            String phone = resultSet.getString("phone");
                            String dataCriacao = resultSet.getString("data_criacao");

                            System.out.println("ID: " + id);
                            System.out.println("Nome: " + nome);
                            System.out.println("Uname" + uname);
                            System.out.println("Email: " + email);
                            System.out.println("Phone" + phone);
                            System.out.println("Data de Criação: " + dataCriacao);
                        } else {
                            System.out.println("Elemento com Uname " + Uname + " não encontrado.");
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao executar a consulta: " + e.getMessage());
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
        }
    }

    //Método busrcar por phone
    public static void buscaPorPhone(){

        String Phone = "";

        try (Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD)) {
            // Verifica se a conexão foi bem-sucedida
            if (connection != null) {
                System.out.println("Conectado ao banco de dados!");

                // Consulta para buscar o elemento pelo ID
                String selectSQL = "SELECT * FROM exemplo WHERE phone = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                    // Define o valor do ID na consulta
                    preparedStatement.setString(1, Phone);

                    // Executa a consulta
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Processa o resultado
                        if (resultSet.next()) {
                            // Supondo que a tabela "exemplo" tem colunas: id, nome, email, data_criacao
                            int id = resultSet.getInt("id");
                            String nome = resultSet.getString("nome");
                            String uname = resultSet.getString("uname");
                            String email = resultSet.getString("email");
                            String phone = resultSet.getString("phone");
                            String dataCriacao = resultSet.getString("data_criacao");

                            System.out.println("ID: " + id);
                            System.out.println("Nome: " + nome);
                            System.out.println("Uname" + uname);
                            System.out.println("Email: " + email);
                            System.out.println("Phone" + phone);
                            System.out.println("Data de Criação: " + dataCriacao);
                        } else {
                            System.out.println("Elemento com Phone " + Phone + " não encontrado.");
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao executar a consulta: " + e.getMessage());
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
        }
    }

    //Método busrcar por Email
    public static void buscaPorEmail(){

        String Email = "";

        try (Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD)) {
            // Verifica se a conexão foi bem-sucedida
            if (connection != null) {
                System.out.println("Conectado ao banco de dados!");

                // Consulta para buscar o elemento pelo ID
                String selectSQL = "SELECT * FROM exemplo WHERE email = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                    // Define o valor do ID na consulta
                    preparedStatement.setString(1, Email);

                    // Executa a consulta
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Processa o resultado
                        if (resultSet.next()) {
                            // Supondo que a tabela "exemplo" tem colunas: id, nome, email, data_criacao
                            int id = resultSet.getInt("id");
                            String nome = resultSet.getString("nome");
                            String uname = resultSet.getString("uname");
                            String email = resultSet.getString("email");
                            String phone = resultSet.getString("phone");
                            String dataCriacao = resultSet.getString("data_criacao");

                            System.out.println("ID: " + id);
                            System.out.println("Nome: " + nome);
                            System.out.println("Uname" + uname);
                            System.out.println("Email: " + email);
                            System.out.println("Phone" + phone);
                            System.out.println("Data de Criação: " + dataCriacao);
                        } else {
                            System.out.println("Elemento com Email " + Email + " não encontrado.");
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao executar a consulta: " + e.getMessage());
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
        }
    }


    //Método para atualizar o elemento no Banco de dados
    public static void updateElemento(){
        int idToUpdate = 1; // ID do elemento que você deseja atualizar
        String newName = "Novo Nome";
        String newEmail = "novoemail@example.com";

        updateRecord(idToUpdate, newName, newEmail);
    }

    public static void updateRecord(int id, String nome, String email) {

        String updateSQL = "UPDATE exemplo SET nome = ?, email = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            // Define os valores dos parâmetros na consulta
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, id);

            // Executa a atualização
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Registro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado com o ID especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o registro: " + e.getMessage());
        }
    }

    //Método para deletar um elemento no banco de dados
    public static void deletarRegistro(){

        int idToDelete = 1; // ID do elemento que você deseja deletar

        deleteRecord(idToDelete);

    }
    public static void deleteRecord(int id) {
        String deleteSQL = "DELETE FROM exemplo WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            // Define o valor do ID no parâmetro da consulta
            preparedStatement.setInt(1, id);

            // Executa a exclusão
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Registro deletado com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado com o ID especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o registro: " + e.getMessage());
        }
    }

    //Método para contar quantidade de registros

    public static void quantidadeRegistros(){

        int count = getRecordCount();
        System.out.println("Quantidade de registros na tabela: " + count);
    }

    public static int getRecordCount() {
        String countSQL = "SELECT COUNT(*) FROM exemplo";
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
