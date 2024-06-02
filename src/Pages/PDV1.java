package Pages;

import bank.Query;
import bank.SQL;

import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class PDV1 extends JDialog{
    private JTextField textField1;
    private JRadioButton dinheiroRadioButton;
    private JRadioButton créditoRadioButton;
    private JRadioButton débitoRadioButton;
    private JRadioButton PIXRadioButton;
    private JButton finalizarCompraButton;
    private JButton addButton;
    private JPanel PDV1;


    public PDV1(JFrame parent){

        super(parent);
        setTitle("PDV");
        setSize(800,800);
        setContentPane(PDV1);
        setLocale(parent);
        setMinimumSize(new Dimension(450,474));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        conexãoBanco();

        String a = textField1.getText();

        String resultado = Query.buscaPorId(a);

        if(resultado != null){

            System.out.println("Objeto encontrado");
        }
        else{

            System.out.println("Objeto não encontrado encontrado");
        }

    }

    private void setLocale(JFrame parent) {

    }

    public void conexãoBanco(){

        String URL = "jdbc:mysql://localhost/produtos?serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "";

        try (Connection connection = DriverManager.getConnection(SQL.URL, SQL.USER, SQL.PASSWORD)) {
            // Verifica se a conexão foi bem-sucedida
            if (connection != null) {
                System.out.println("Conectado ao banco de dados!");
            } else {
                System.out.println("Falha ao conectar ao banco de dados!");
            }
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
        }
    }
}
