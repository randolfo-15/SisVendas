package Pages;

import dados.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog {
    private JPanel loginPanel;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOK;
    private JButton btnCancel;

    public LoginForm(JFrame parent){
        super(parent);
        setTitle("login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setVisible(true);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String passw = String.valueOf(pfPassword.getPassword());

                user = getAuthenticatedUser(email, passw);

                if(user != null){
                    //dispose();
                    JOptionPane.showMessageDialog(LoginForm.this,"Usuario autenticado:" + user.name);
                }
                else{
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Email or password invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        setVisible(true);
    }

    private static User user;

    private User getAuthenticatedUser(String email, String password){

        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/mystore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM usuario WHERE email=? AND passw=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.passw = resultSet.getString("passw");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){

            e.printStackTrace();

        }

        return user;
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        User user = loginForm.user;

        if(user!= null){
            System.out.println("Sucessful Authentication: " + user.name);
            System.out.println("Email: " + user.email);
            System.out.println("Phone: " + user.phone);

        }
        else{

            System.out.println("Authentication canceled");
        }
    }

    public static User usuarioAtual(){

        return user;
    }
}
