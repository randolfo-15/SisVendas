package bank;

import java.sql.*;

public class Query {

    private static final String URL = "jdbc:mysql://localhost/mystore?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static boolean exist(String table, String colunm, String field){

        return true;
    }

    //Método para inserir elemento no banco de dados

    public static void insertUser(String name, String uname, String phone, String email, String password) {
        String insertSQL = "INSERT INTO users (name, uname, phone, email, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, name);
            pstmt.setString(2, uname);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.setString(5,password);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User inserted successfully.");
            } else {
                System.out.println("User insertion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Método para criação da tabela no banco de dados
    public static void createTable(){

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
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
    public static void buscaPorId(){
        int idToFetch = 1; // ID do elemento que você deseja buscar

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Verifica se a conexão foi bem-sucedida
            if (connection != null) {
                System.out.println("Conectado ao banco de dados!");

                // Consulta para buscar o elemento pelo ID
                String selectSQL = "SELECT * FROM users WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                    // Define o valor do ID na consulta
                    preparedStatement.setInt(1, idToFetch);

                    // Executa a consulta
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Processa o resultado
                        if (resultSet.next()) {
                            // Supondo que a tabela "exemplo" tem colunas: id, nome, email, data_criacao
                            int id = resultSet.getInt("id");
                            String name = resultSet.getString("name");
                            String uname = resultSet.getString("uname");
                            String email = resultSet.getString("email");
                            String dataCriacao = resultSet.getString("phone");
                            String password = resultSet.getString("password");

                            System.out.println("ID: " + id);
                            System.out.println("Nome: " + name);
                            System.out.println("Nome: " + uname);
                            System.out.println("Email: " + email);
                            System.out.println("Password: " + password);
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
    }

    //Método busrcar por uname
    public static void buscaPorUname(){
        String Uname = "Rangel";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Verifica se a conexão foi bem-sucedida
            if (connection != null) {
                System.out.println("Conectado ao banco de dados!");

                // Consulta para buscar o elemento pelo ID
                String selectSQL = "SELECT * FROM users WHERE uname = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                    // Define o valor do ID na consulta
                    preparedStatement.setString(1, Uname);

                    // Executa a consulta
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Processa o resultado
                        if (resultSet.next()) {
                            // Supondo que a tabela "exemplo" tem colunas: id, nome, email, data_criacao
                            int id = resultSet.getInt("id");
                            String name = resultSet.getString("name");
                            String uname = resultSet.getString("uname");
                            String email = resultSet.getString("email");
                            String dataCriacao = resultSet.getString("phone");
                            String password = resultSet.getString("password");

                            System.out.println("ID: " + id);
                            System.out.println("Nome: " + name);
                            System.out.println("Uname" + uname);
                            System.out.println("Email: " + email);
                            System.out.println("Password" + password);
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

        String Phone = "31991919086";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Verifica se a conexão foi bem-sucedida
            if (connection != null) {
                System.out.println("Conectado ao banco de dados!");

                // Consulta para buscar o elemento pelo ID
                String selectSQL = "SELECT * FROM users WHERE phone = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                    // Define o valor do ID na consulta
                    preparedStatement.setString(1, Phone);

                    // Executa a consulta
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Processa o resultado
                        if (resultSet.next()) {
                            // Supondo que a tabela "exemplo" tem colunas: id, nome, email, data_criacao
                            int id = resultSet.getInt("id");
                            String name = resultSet.getString("name");
                            String uname = resultSet.getString("uname");
                            String email = resultSet.getString("email");
                            String dataCriacao = resultSet.getString("phone");
                            String password = resultSet.getString("password");

                            System.out.println("ID: " + id);
                            System.out.println("Nome: " + name);
                            System.out.println("Uname" + uname);
                            System.out.println("Email: " + email);
                            System.out.println("Password" + password);
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

        String Email = "pablo.rangel2506@gmail.com";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Verifica se a conexão foi bem-sucedida
            if (connection != null) {
                System.out.println("Conectado ao banco de dados!");

                // Consulta para buscar o elemento pelo ID
                String selectSQL = "SELECT * FROM users WHERE email = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                    // Define o valor do ID na consulta
                    preparedStatement.setString(1, Email);

                    // Executa a consulta
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Processa o resultado
                        if (resultSet.next()) {
                            // Supondo que a tabela "exemplo" tem colunas: id, nome, email, data_criacao
                            int id = resultSet.getInt("id");
                            String name = resultSet.getString("name");
                            String uname = resultSet.getString("uname");
                            String email = resultSet.getString("email");
                            String dataCriacao = resultSet.getString("phone");
                            String password = resultSet.getString("password");


                            System.out.println("ID: " + id);
                            System.out.println("Nome: " + name);
                            System.out.println("Uname" + uname);
                            System.out.println("Email: " + email);
                            System.out.println("Password" + password);
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
//    public static void updateElemento(){
//        int idToUpdate = 1; // ID do elemento que você deseja atualizar
//        String newName = "Pablo Rangel Abreu Andrade";
//        String newUname = "Rangel";
//        String newEmail = "pabloraa@hotmail.com";
//        String newPhone = "31991919086";
//        String newPassword = "5555555";
//
//        updateRecord(idToUpdate, newName, newUname, newEmail, newPhone, newPassword);
//    }
//
//    public static void updateRecord(int id, String name,String uname, String email, String phone, String password) {
//
//        //String updateSQL = "UPDATE users SET name = ?, email = ? WHERE id = ?";
//        String updateSQL = "UPDATE users SET name = ?, uname = ?, email = ?, phone = ?, password = ? WHERE id = ?";
//
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
//
//            // Define os valores dos parâmetros na consulta
//              preparedStatement.setInt(1, id);
//            preparedStatement.setString(2, name);
//            preparedStatement.setString(3, uname);
//            preparedStatement.setString(4, email);
//            preparedStatement.setString(5, phone);
//            preparedStatement.setString(6, password);
//
//
//            // Executa a atualização
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Registro atualizado com sucesso!");
//            } else {
//                System.out.println("Nenhum registro encontrado com o ID especificado.");
//            }
//        } catch (SQLException e) {
//            System.err.println("Erro ao atualizar o registro: " + e.getMessage());
//        }
//    }

    //Método para atualizar uma chave no banco de dados

    public static void updateUser(int id, String name, String uname, String email, String phone, String password) {
        String updateSQL = "UPDATE users SET name = ?, uname = ?, email = ?, phone = ?, password = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setString(1, name);
            pstmt.setString(2, uname);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, password);
            pstmt.setInt(6, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User update failed. No matching record found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Método para deletar um elemento no banco de dados
    public static void deletarRegistro(){

        int idToDelete = 1; // ID do elemento que você deseja deletar

        deleteRecord(idToDelete);

    }
    public static void deleteRecord(int id) {
        String deleteSQL = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
        String countSQL = "SELECT COUNT(*) FROM users";
        int count = 0;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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