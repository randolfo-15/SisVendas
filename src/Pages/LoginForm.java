package Pages;

import Manager.Program;
import bank.SQL;
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
    private static User user = new User();
    private static boolean state = false;

    public LoginForm(JFrame parent){
        super(parent);
        setTitle("login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = getAuthenticatedUser(tfEmail.getText(), String.valueOf(pfPassword.getPassword()));

                if(!user.get_passw().isEmpty()){
                    state = true;
                    dispose();
                }
                else {
                    state = false;
                    Program.alert(("Senha ou username incorreto."));
                }

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void start(){ setVisible(true); }

    public void stop(){ setVisible(false); }

    public User get_user(){ return user;}

    public boolean get_state(){ return state; }

    private User getAuthenticatedUser(String uname, String password){
        user.clear();
        Program.query.select(SQL.TABLE_USER,SQL.COLUMN_UNAME,uname,user);
        return user;
    }


}
