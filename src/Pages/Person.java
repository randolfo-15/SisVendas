/*****************************************************************************
 *   Person
 *  ========
 *  @file  : Person.java
 *  @author: Randolfo A Goncalves
 *  @since : 28/05/24
 ****************************************************************************/
package Pages;

import Manager.Graph;
import Manager.Program;
import Manager.Sys;
import bank.Query;
import bank.SQL;
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

    //==================================================================================================================
    //  User search
    //==================================================================================================================
    private void init_page_data(){
        ButtonGroup group = new ButtonGroup();
        JTextField field = search_field(group);
        JLabel info = Sys.make_text(make_table_data(user),30,Color.BLACK);
        page[DATA].setLayout(new BoxLayout(page[DATA],BoxLayout.Y_AXIS));

        // Campo de busca
        // ===============
        JPanel pnl_00    = new JPanel();
        pnl_00.add(new JLabel(new ImageIcon("src/imagens/lupa.png")));
        pnl_00.add(Sys.make_text("Buscar por: ",18,Color.BLACK));
        pnl_00.add(field);
        pnl_00.add(make_radio_search(group,field));
        pnl_00.setBorder(BorderFactory.createRaisedBevelBorder());

        // Campo de Resultado
        // ===================
        JPanel pnl_01    = new Graph("bkg2.jpg")    ;
        pnl_01.setBorder(BorderFactory.createRaisedBevelBorder());
        pnl_01.add(info);

        // Plugs
        // =====
        page[DATA].add(pnl_00);
        page[DATA].add(pnl_01);

    }

    private JTextField search_field(ButtonGroup group){
        JTextField in = new JTextField(30);
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

    private JPanel make_radio_search(ButtonGroup group,JTextField field){
        JPanel pnl = new JPanel();
        JRadioButton[] radio = new JRadioButton[]{
            new JRadioButton("Nome"),
            new JRadioButton("Email"),
            new JRadioButton("Phone")
        };

        for(var btn : radio) {
            Sys.make_component(btn);
            group.add(btn);
            switch (btn.getText()){
                case "Nome": { action_radio(btn, SQL.COLUNM_NAME,field.getText()); }; break;
                case "Email":{ /* ? */ }; break;
                case "Phone":{ /* ? */ }; break;
            }

            pnl.add(btn);
        }

        return pnl;
    }

    void action_radio(JRadioButton btn,String column,String field){
        User user =new User();
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Query.search(SQL.TABLE_USER,column,field,user);
            }
        });
    }
    //==================================================================================================================
    //  Register user
    //==================================================================================================================

    private void init_page_new(){

        JTextField
            nome = make_textfield(40) ,
            username = make_textfield(40),
            email = make_textfield(40),
            phone = make_textfield(40);
        JPasswordField
            senha1 = make_text_passw(),
            senha2 = make_text_passw();


        JPanel pnl_00 = new JPanel(new GridLayout(8,2));
        pnl_00.setPreferredSize(new Dimension(700,300));
        pnl_00.setBorder(BorderFactory.createRaisedBevelBorder());


        pnl_00.add(make_text("Nome"));
        pnl_00.add(nome);

        pnl_00.add(make_text("Username"));
        pnl_00.add(username);

        pnl_00.add(make_text("Email"));
        pnl_00.add(email);

        pnl_00.add(make_text("Phone"));
        pnl_00.add(phone);

        pnl_00.add(make_text("Senha"));
        pnl_00.add(senha1);

        pnl_00.add(make_text("Confirma"));
        pnl_00.add(senha2);
        pnl_00.add(new Label());
        pnl_00.add(new Label());

        JPanel pnl_02 = new JPanel(),pnl_03 = new JPanel();
        JButton salvar = new JButton("Salvar"),
                cancelar = new JButton("Limpar");

        pnl_02.add(salvar);
        pnl_03.add(cancelar);

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //...
            }
        });

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nome.setText("");
                username.setText("");
                email.setText("");
                phone.setText("");
                senha1.setText("");
                senha2.setText("");
            }
        });

        pnl_00.add(pnl_02);
        pnl_00.add(pnl_03);

        page[NEW].add(pnl_00);

    }

    private void init_page_edit(){
        JTextField field = make_textfield(40);
        //page[EDIT].setLayout(new BoxLayout(page[EDIT],BoxLayout.Y_AXIS));

        // Campo de busca
        // ===============
        JPanel pnl_02    = new JPanel();
        pnl_02.add(new JLabel(new ImageIcon("src/imagens/lupa.png")));
        pnl_02.add(Sys.make_text("Buscar por: ",18,Color.BLACK));
        pnl_02.add(field);
        //pnl_02.add(make_radio_search());
        pnl_02.setBorder(BorderFactory.createRaisedBevelBorder());

        // Campo de Resultado
        // ===================
        JPanel pnl_03    = new Graph("bkg2.jpg")    ;
        pnl_03.setBorder(BorderFactory.createRaisedBevelBorder());
        //----------------------------------------------------------------
        JTextField
                nome = make_textfield(40) ,
                username = make_textfield(40),
                email = make_textfield(40),
                phone = make_textfield(40);
        JPasswordField
                senha1 = make_text_passw(),
                senha2 = make_text_passw();


        JPanel pnl_00 = new JPanel(new GridLayout(8,2));
        pnl_00.setPreferredSize(new Dimension(700,300));
        pnl_00.setBorder(BorderFactory.createRaisedBevelBorder());


        pnl_00.add(make_text("Nome"));
        pnl_00.add(nome);

        pnl_00.add(make_text("Username"));
        pnl_00.add(username);

        pnl_00.add(make_text("Email"));
        pnl_00.add(email);

        pnl_00.add(make_text("Phone"));
        pnl_00.add(phone);

        pnl_00.add(make_text("Senha"));
        pnl_00.add(senha1);

        pnl_00.add(make_text("Confirma"));
        pnl_00.add(senha2);
        pnl_00.add(new Label());
        pnl_00.add(new Label());

        JPanel pnl_04 = new JPanel(),pnl_05 = new JPanel();
        JButton salvar = new JButton("Salvar"),
                cancelar = new JButton("Limpar");

        pnl_04.add(salvar);
        pnl_05.add(cancelar);

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //...
            }
        });

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nome.setText("");
                username.setText("");
                email.setText("");
                phone.setText("");
                senha1.setText("");
                senha2.setText("");
            }
        });

        pnl_00.add(pnl_04);
        pnl_00.add(pnl_05);

        //-----------------------------------------------------------------
        // Plugs
        // =====
        page[EDIT].add(pnl_02);
        page[EDIT].add(pnl_03);
        page[EDIT].add(pnl_00);
    }

    private void init_page_del(){
        JTextField field = make_textfield(40);
        JLabel info = Sys.make_text(make_table_data(user),30,Color.BLACK);
        page[DEL].setLayout(new BoxLayout(page[DEL],BoxLayout.Y_AXIS));

        // Campo de busca
        // ===============
        JPanel pnl_00    = new JPanel();
        pnl_00.add(new JLabel(new ImageIcon("src/imagens/lupa.png")));
        pnl_00.add(Sys.make_text("Buscar por: ",18,Color.BLACK));
        pnl_00.add(field);
        //pnl_00.add(make_radio_search());

        pnl_00.setBorder(BorderFactory.createRaisedBevelBorder());

        // Campo de Resultado
        // ===================
        JPanel pnl_01    = new Graph("bkg2.jpg")    ;
        pnl_01.setBorder(BorderFactory.createRaisedBevelBorder());
        pnl_01.add(info);
        pnl_01.add(new JButton("Delete"));

        // Plugs
        // =====
        page[DEL].add(pnl_00);
        page[DEL].add(pnl_01);
    }

    //==================================================================================================================
    //  Functions
    //==================================================================================================================


    private JPanel make_text(String text){
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Serif",Font.BOLD,18));
        JPanel pnl = new JPanel();
        pnl.setBorder(BorderFactory.createRaisedBevelBorder());
        pnl.add(label);
        return pnl;
    }

    private JTextField make_textfield(int value){
        JTextField in = new JTextField(value);
        in.setFont(new Font("Serif",Font.BOLD,18));
        return in;
    }

    private JPasswordField make_text_passw(){
        JPasswordField in = new JPasswordField(40);
        in.setFont(new Font("Serif",Font.BOLD,18));
        return in;
    }



    private String make_table_data(User user){
        String html =
            Sys._html+
                    "<br><div align='center'>  <u>"+user.get_name()+"</u> </div> <br> <br>"+
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
