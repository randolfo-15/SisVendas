/*****************************************************************************
 *   Person
 *  ========
 *  @file  : Person.java
 *  @author: Randolfo A Goncalves
 *  @since : 28/05/24
 ****************************************************************************/
package Pages;

import Manager.Program;
import Manager.Sys;
import bank.Query;
import dados.User;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Person extends Catalog {
    //==================================================================================================================
    // Field
    //==================================================================================================================
    private User user = new User();
    private JTextField field ;
    private JLabel info ;
    private ButtonGroup group = new ButtonGroup();
    //==================================================================================================================
    //  Build
    //==================================================================================================================
    public Person(){
        super("Usuários");
        init();
    }
    //==================================================================================================================
    //  Method
    //==================================================================================================================
    private void init(){
        user = Program.get_user_test();
        init_pages();
    }

    private void init_pages(){
        init_page_data();
        init_page_new();
        init_page_edit();
        init_page_del();
    }

    private void init_page_data(){
        field = make_textfield();
        info = Sys.make_text(make_table_data(user),30,Color.BLACK);
        page[DATA].setLayout(new BoxLayout(page[DATA],BoxLayout.Y_AXIS));

        // Campo de busca
        // ===============
        JPanel pnl_00    = new JPanel();
        pnl_00.add(new JLabel(new ImageIcon("src/imagens/lupa.png")));
        pnl_00.add(Sys.make_text("Buscar por: ",18,Color.BLACK));
        pnl_00.add(field);
        pnl_00.add(make_radio_search());
        pnl_00.setBorder(BorderFactory.createRaisedBevelBorder());

        // Campo de Resultado
        // ===================
        JPanel pnl_01    = new JPanel();
        pnl_01.setBorder(BorderFactory.createRaisedBevelBorder());
        pnl_01.add(info);

        // Plugs
        // =====
        page[DATA].add(pnl_00);
        page[DATA].add(pnl_01);

    }

    private void init_page_new(){

    }

    private void init_page_edit(){

    }

    private void init_page_del(){

    }

    //==================================================================================================================
    //  Functions
    //==================================================================================================================
    private JTextField make_textfield(){
        JTextField in = new JTextField(40);
        in.setFont(new Font("Serif",Font.BOLD,18));
        in.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) { group.clearSelection(); }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) { group.clearSelection(); }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {}
        });
        return in;
    }

    private JPanel make_radio_search(){
        JPanel pnl_00 = new JPanel();
        JRadioButton
                name  = new JRadioButton("Nome"),
                email = new JRadioButton("Email"),
                phone = new JRadioButton("Phone");


        Sys.make_component(name);
        Sys.make_component(email);
        Sys.make_component(phone);

        group.add(name);
        group.add(email);
        group.add(phone);

        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                /*user = Query.buscaPorUname(field.getText());*/
                user.name="Pedro Henrique";
                user.uname="PP";
                user.phone="(31) 98105-9111";
                user.email="pedro@gmail.com";
                user.set_adm(false);
                info.setText(make_table_data(user));
            }
        });

        email.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                /*user = Query.buscaPorEmail(field.getText());*/
                JOptionPane.showMessageDialog(null,"Busca por email");
            }
        });

        phone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                /*user = Query.buscaPorPhone(field.getText());*/
                JOptionPane.showMessageDialog(null,"Busca por phone");
            }
        });

        pnl_00.add(name);
        pnl_00.add(email);
        pnl_00.add(phone);

        return pnl_00;
    }

    private String make_table_data(User user){
        String html =
            Sys._html+
                    "<div align='center'>  <u>"+user.get_name()+"</u> <br> </div>"+
                    "<table border='2' style='padding:10px '>"+
                        "<tr style='background-color:#F80'><td> Username </td> <td>"+user.get_uname()+"</td></tr>"+
                        "<tr style='background-color:#FF0'><td> Email    </td> <td>"+user.get_email()+"</td></tr>"+
                        "<tr style='background-color:#F80'><td> Phone    </td> <td>"+user.get_phone()+"</td></tr>"+
                        "<tr style='background-color:#FF0'><td> Cargo    </td> <td>"+Sys.cargo(user.get_adm())+"</td></tr>"+
                        "<tr style='background-color:#F80'><td> Senha    </td> <td> ⬤ ⬤ ⬤ ⬤ ⬤ ⬤ ⬤ ⬤ </td></tr>"+
                    "</table>"+
            Sys.html_
        ;
        return html;
    }
}
