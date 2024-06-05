package Pages;

import bank.Archivable;
import bank.Query;
import bank.SQL;
import dados.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PDV1 extends JDialog{
    private JTextField textField1 = new JTextField();
    private JRadioButton dinheiroRadioButton;
    private JRadioButton créditoRadioButton;
    private JRadioButton débitoRadioButton;
    private JRadioButton PIXRadioButton;
    private JButton finalizarCompraButton = new JButton();
    private JButton addButton;
    private JPanel PDV1;
    private float totalValue;
    private List<Product> cart;


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
        Product prd = new Product();
        Query.search(SQL.TABLE_PRODUCT,SQL.COLUNM_NAME,"Biscoito",prd);
        conexãoBanco();
        //PDV1.add(finalizarCompraButton);

        cart = new ArrayList<Product>();

        totalValue = 0.0f;

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProductToCart();
            }
        });

        finalizarCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizePurchase();

                    JOptionPane.showMessageDialog(null, "total" + totalValue);
            }
        });

    }

    private void addProductToCart() {

        conexãoBanco();
        Product prd = new Product();
        Product product =  Query.search(SQL.TABLE_PRODUCT,SQL.COLUNM_NAME,"Biscoito",prd);

        if(textField1.getText().equals(product)){

            JOptionPane.showMessageDialog(this,"produto encontrado no banco");
        }

        if (product != null) {
            cart.add(product);
            totalValue += product.get_value();
            updateTotalLabel();
            JOptionPane.showMessageDialog(this, "Produto adicionado: " + product.get_name());
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado!");
        }
    }

    public float trocoDinheiro(float textField1){

        float troco = (totalValue - textField1);

        return troco;

    }

    public void relatorioCompra(float dinheiro){

        if(créditoRadioButton.equals(true)){

            JOptionPane.showMessageDialog(this,"Pagamento cartão de crédito" + totalValue);
        }

        if(débitoRadioButton.equals(true)){

            JOptionPane.showMessageDialog(this, "Pagamento em cartão de débito" +totalValue);
        }

        if(dinheiroRadioButton.equals(true)){

            JOptionPane.showMessageDialog(this, "Pagamento feito com dinheiro" +totalValue);
            JOptionPane.showMessageDialog(this,"Troco" + trocoDinheiro(dinheiro));
        }

        if(PIXRadioButton.equals(true)){

            JOptionPane.showMessageDialog(this,"Pagmento feito com dinheiro"+ totalValue);
        }
    }

    public void impressaoRelatorio(){

    
    }

    private void updateTotalLabel() {

        Label totalLabel = null;
        totalLabel.setText(String.format("Total: R$ %.2f", totalValue));
    }

    private void finalizePurchase() {

        JOptionPane.showMessageDialog(this, "Compra finalizada! Total: R$ " + totalValue);
        cart.clear();
        totalValue = 0.0f;
        updateTotalLabel();
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
            System.err.println("Erro de conexão: " + e.getMessage());;
        }
    }
}
