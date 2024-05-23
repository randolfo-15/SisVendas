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
    private final String
        NAME  = "Nome",
        PHONE = "Phone",
        EMAIL = "Email";

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
        page[DATA].setLayout(new BorderLayout());

        JLabel info = Sys.make_text("",30,Color.BLACK);
        ButtonGroup group = new ButtonGroup();
        JTextField field = search_field(group,info);


        // Campo de busca
        // ===============
        JPanel pnl_00 = new JPanel();
        pnl_00.setBorder(BorderFactory.createRaisedBevelBorder());

        pnl_00.add(new JLabel(new ImageIcon((Graph.PATH_IMG+"lupa.png"))));
        pnl_00.add(Sys.make_text("Buscar por: ",18,Color.BLACK));
        pnl_00.add(field);
        pnl_00.add(make_radio_search(group,field,info));

        // Campo de Resultado
        // ===================
        JPanel pnl_01 = new Graph(BKG_00);
        pnl_01.setBorder(BorderFactory.createRaisedBevelBorder());

        pnl_01.add(info);

        // Plugs
        // =====
        page[DATA].add(pnl_00,BorderLayout.NORTH);
        page[DATA].add(pnl_01,BorderLayout.CENTER);
    }

    private JTextField search_field(ButtonGroup group,JLabel info){
        JTextField in = new JTextField(30);
        in.setFont(new Font("Serif",Font.BOLD,18));
        in.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                group.clearSelection();
                info.setText("");
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                group.clearSelection();
                info.setText("");
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {}
        });
        return in;
    }

    private JPanel make_radio_search(ButtonGroup group,JTextField field,JLabel info){
        JPanel pnl = new JPanel();
        JRadioButton[] radio = new JRadioButton[]{
            new JRadioButton(NAME),
            new JRadioButton(EMAIL),
            new JRadioButton(PHONE)
        };
        for(var btn : radio) {
            Sys.make_component(btn);
            pnl.add(btn);
            group.add(btn);

            switch (btn.getText()){
                case NAME:  action_radio_search(btn, SQL.COLUNM_NAME ,field,info);  break;
                case EMAIL: action_radio_search(btn, SQL.COLUNM_EMAIL,field,info);  break;
                case PHONE: action_radio_search(btn, SQL.COLUNM_PHONE,field,info);  break;
            }
        }
        return pnl;
    }

    void action_radio_search(JRadioButton btn,String column,JTextField field,JLabel info){
        User user =new User();
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Query.search(SQL.TABLE_USER,column,field.getText(),user);
                info.setText(make_table_data(user));
            }
        });
    }
    //==================================================================================================================
    //  Register user
    //==================================================================================================================
    private boolean
            flag_00 = true,
            flag_01 = true;

    private void init_page_new(){
        JTextField
            nome = make_textfield(),
            username = make_textfield(),
            email = make_textfield(),
            phone = make_textfield();
        JPasswordField
            senha1 = make_text_passw(36),
            senha2 = make_text_passw(36);
        JButton
            eye_00 = make_eye_btn(),
            eye_01 = make_eye_btn();

        ButtonGroup func = new ButtonGroup();
        JRadioButton
            adm = new JRadioButton("Administrador"),
            cx  = new JRadioButton("Caixa");

        adm.setFont(new Font("Serif",Font.BOLD,16));
        cx.setFont(new Font("Serif",Font.BOLD,16));
        func.add(adm);
        func.add(cx);

        JPanel pnl_00 = new JPanel(new GridLayout(9,1));
        pnl_00.setBorder(BorderFactory.createRaisedBevelBorder());

        pnl_00.add(make_text("Novo Usuário"));

        JPanel pnl_10 = create_panel();
        pnl_10.add(adm);
        pnl_10.add(Box.createHorizontalStrut(30));
        pnl_10.add(cx);
        pnl_00.add(pnl_10);

        JPanel pnl_01 = create_panel();
        pnl_01.add(make_text("Nome"));
        pnl_01.add(nome);
        pnl_00.add(pnl_01);

        JPanel pnl_02 = create_panel();
        pnl_02.add(make_text("Username"));
        pnl_02.add(username);
        pnl_00.add(pnl_02);

        JPanel pnl_03 = create_panel();
        pnl_03.add(make_text("Email"));
        pnl_03.add(email);
        pnl_00.add(pnl_03);

        JPanel pnl_04 = create_panel();
        pnl_04.add(make_text("Phone"));
        pnl_04.add(phone);
        pnl_00.add(pnl_04);

        JPanel pnl_05 = create_panel();
        pnl_05.add(make_text("Senha"));
        pnl_05.add(senha1);
        pnl_05.add(eye_00);
        pnl_00.add(pnl_05);

        JPanel pnl_06 = create_panel();
        pnl_06.add(make_text("Confirma"));
        pnl_06.add(senha2);
        pnl_06.add(eye_01);
        pnl_00.add(pnl_06);


        JPanel pnl_07 = create_panel();
        JButton salvar = new JButton("Salvar"),
                cancelar = new JButton("Limpar");

        pnl_07.add(salvar);
        pnl_07.add(Box.createHorizontalStrut(60));
        pnl_07.add(cancelar);
        pnl_00.add(pnl_07);


        eye_00.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (flag_00){
                    senha1.setEchoChar((char) 0);
                    eye_00.setIcon(new ImageIcon((Graph.PATH_IMG+"close_eye.png")));
                }else {
                    senha1.setEchoChar('*');
                    eye_00.setIcon(new ImageIcon((Graph.PATH_IMG+"eye.png")));
                }
                flag_00= !flag_00;
            }
        });

        eye_01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(flag_01) {
                    senha2.setEchoChar((char)0);
                    eye_01.setIcon(new ImageIcon((Graph.PATH_IMG+"close_eye.png")));
                } else {
                    senha2.setEchoChar('*');
                    eye_01.setIcon(new ImageIcon((Graph.PATH_IMG+"eye.png")));
                }
                flag_01= !flag_01;
            }
        });

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

        page[NEW].setLayout(new BorderLayout());
        page[NEW].add(Box.createVerticalStrut(150),BorderLayout.NORTH);
        page[NEW].add(Box.createHorizontalStrut(300),BorderLayout.WEST);
        page[NEW].add(pnl_00,BorderLayout.CENTER);
        page[NEW].add(Box.createHorizontalStrut(300),BorderLayout.EAST);
        page[NEW].add(Box.createVerticalStrut(200),BorderLayout.SOUTH);

    }

    private JButton make_eye_btn(){
        JButton btn = new JButton(new ImageIcon(Graph.PATH_IMG+"eye.png"));
        btn.setPreferredSize(new Dimension(30,30));
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        return btn;
    }

    private JTextField make_textfield(){
        JTextField in = new JTextField(38);
        in.setFont(new Font("Serif",Font.BOLD,18));
        in.setBorder(BorderFactory.createLoweredBevelBorder());
        return in;
    }

    private JPasswordField make_text_passw(int value){
        JPasswordField in = new JPasswordField(value);
        in.setFont(new Font("Serif",Font.BOLD,18));
        in.setBorder(BorderFactory.createLoweredBevelBorder());
        in.setEchoChar('*');
        return in;
    }

    private JPanel create_panel(){
        JPanel pnl = new JPanel();
        pnl.setBorder(BorderFactory.createRaisedBevelBorder());
        return pnl;
    }

    private JPanel make_text(String text){
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Serif",Font.BOLD,18));
        JPanel pnl = new JPanel();
        pnl.setPreferredSize(new Dimension(120,32));
        pnl.setBorder(BorderFactory.createLoweredBevelBorder());
        pnl.add(label);
        return pnl;
    }
    //==================================================================================================================
    //  Edit User
    //==================================================================================================================
    private boolean
            flag_02 = true,
            flag_03 = true;

    private void init_page_edit(){
        page[EDIT].setLayout(new BorderLayout());

        JLabel info = Sys.make_text("",30,Color.BLACK);
        ButtonGroup group = new ButtonGroup();
        JTextField field = search_field(group,info);


        // Campo de busca
        // ===============
        JPanel pnl_00_00 = new JPanel();
        pnl_00_00.setBorder(BorderFactory.createRaisedBevelBorder());

        pnl_00_00.add(new JLabel(new ImageIcon((Graph.PATH_IMG+"lupa.png"))));
        pnl_00_00.add(Sys.make_text("Buscar por: ",18,Color.BLACK));
        pnl_00_00.add(field);
        pnl_00_00.add(make_radio_update(group,field,info));

        JTextField
                nome = make_textfield(),
                username = make_textfield(),
                email = make_textfield(),
                phone = make_textfield();
        JPasswordField
                senha1 = make_text_passw(36),
                senha2 = make_text_passw(36);
        JButton
                eye_00 = make_eye_btn(),
                eye_01 = make_eye_btn();

        ButtonGroup func = new ButtonGroup();
        JRadioButton
                adm = new JRadioButton("Administrador"),
                cx  = new JRadioButton("Caixa");

        adm.setFont(new Font("Serif",Font.BOLD,16));
        cx.setFont(new Font("Serif",Font.BOLD,16));
        func.add(adm);
        func.add(cx);

        JPanel pnl_00 = new JPanel(new GridLayout(9,1));
        pnl_00.setBorder(BorderFactory.createRaisedBevelBorder());
        pnl_00.add(make_text("Editar Usuário"));

        JPanel pnl_10 = create_panel();
        pnl_10.add(adm);
        pnl_10.add(Box.createHorizontalStrut(30));
        pnl_10.add(cx);
        pnl_00.add(pnl_10);


        JPanel pnl_01 = create_panel();
        pnl_01.add(make_text("Nome"));
        pnl_01.add(nome);
        pnl_00.add(pnl_01);

        JPanel pnl_02 = create_panel();
        pnl_02.add(make_text("Username"));
        pnl_02.add(username);
        pnl_00.add(pnl_02);

        JPanel pnl_03 = create_panel();
        pnl_03.add(make_text("Email"));
        pnl_03.add(email);
        pnl_00.add(pnl_03);

        JPanel pnl_04 = create_panel();
        pnl_04.add(make_text("Phone"));
        pnl_04.add(phone);
        pnl_00.add(pnl_04);

        JPanel pnl_05 = create_panel();
        pnl_05.add(make_text("Senha"));
        pnl_05.add(senha1);
        pnl_05.add(eye_00);
        pnl_00.add(pnl_05);

        JPanel pnl_06 = create_panel();
        pnl_06.add(make_text("Confirma"));
        pnl_06.add(senha2);
        pnl_06.add(eye_01);
        pnl_00.add(pnl_06);


        JPanel pnl_07 = create_panel();
        JButton salvar = new JButton("Salvar"),
                cancelar = new JButton("Limpar");

        pnl_07.add(salvar);
        pnl_07.add(Box.createHorizontalStrut(60));
        pnl_07.add(cancelar);
        pnl_00.add(pnl_07);


        eye_00.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (flag_02){
                    senha1.setEchoChar((char) 0);
                    eye_00.setIcon(new ImageIcon((Graph.PATH_IMG+"close_eye.png")));
                }else {
                    senha1.setEchoChar('*');
                    eye_00.setIcon(new ImageIcon((Graph.PATH_IMG+"eye.png")));
                }
                flag_02= !flag_02;
            }
        });

        eye_01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(flag_03) {
                    senha2.setEchoChar((char)0);
                    eye_01.setIcon(new ImageIcon((Graph.PATH_IMG+"close_eye.png")));
                } else {
                    senha2.setEchoChar('*');
                    eye_01.setIcon(new ImageIcon((Graph.PATH_IMG+"eye.png")));
                }
                flag_03= !flag_03;
            }
        });

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

        JPanel edition = new JPanel();
        edition.setBackground(new Color(0,0,0,0));
        edition.setLayout(new BoxLayout(edition,BoxLayout.Y_AXIS));

        edition.add(Box.createHorizontalStrut(90));
        edition.add(pnl_00);

        // Campo de Resultado
        // ===================
        //JPanel pnl_01 = new Graph(BKG_00);
        //pnl_01.setBorder(BorderFactory.createRaisedBevelBorder());

        //pnl_01.add(info);

        // Plugs
        // =====
        page[EDIT].add(pnl_00_00,BorderLayout.NORTH);
        page[EDIT].add(Box.createHorizontalStrut(300),BorderLayout.WEST);
        page[EDIT].add(edition,BorderLayout.CENTER);
        page[EDIT].add(Box.createHorizontalStrut(300),BorderLayout.EAST);
        page[EDIT].add(Box.createVerticalStrut(150),BorderLayout.SOUTH);

    }



    private JPanel make_radio_update(ButtonGroup group,JTextField field,JLabel info){
        JPanel pnl = new JPanel();
        JRadioButton[] radio = new JRadioButton[]{
                new JRadioButton(NAME),
                new JRadioButton(EMAIL),
                new JRadioButton(PHONE)
        };
        for(var btn : radio) {
            Sys.make_component(btn);
            pnl.add(btn);
            group.add(btn);

            switch (btn.getText()){
                case NAME:  action_radio_update(btn, SQL.COLUNM_NAME ,field,info);  break;
                case EMAIL: action_radio_update(btn, SQL.COLUNM_EMAIL,field,info);  break;
                case PHONE: action_radio_update(btn, SQL.COLUNM_PHONE,field,info);  break;
            }
        }
        return pnl;
    }

    void action_radio_update(JRadioButton btn,String column,JTextField field,JLabel info){
        User user =new User();
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Query.search(SQL.TABLE_USER,column,field.getText(),user);
                info.setText(make_table_data(user));
            }
        });
    }

    //==================================================================================================================
    // Deletar User
    //==================================================================================================================

    private void init_page_del(){
        page[DEL].setLayout(new BorderLayout());

        JLabel info = Sys.make_text("",30,Color.BLACK);
        ButtonGroup group = new ButtonGroup();
        JTextField field = search_field(group,info);


        // Campo de busca
        // ===============
        JPanel pnl_00 = new JPanel();
        pnl_00.setBorder(BorderFactory.createRaisedBevelBorder());

        pnl_00.add(new JLabel(new ImageIcon((Graph.PATH_IMG+"lupa.png"))));
        pnl_00.add(Sys.make_text("Buscar por: ",18,Color.BLACK));
        pnl_00.add(field);
        pnl_00.add(make_radio_delete(group,field,info));

        // Campo de Resultado
        // ===================
        JPanel pnl_01 = new Graph(BKG_00);
        pnl_01.setBorder(BorderFactory.createRaisedBevelBorder());

        pnl_01.add(info);

        // Plugs
        // =====
        page[DEL].add(pnl_00,BorderLayout.NORTH);
        page[DEL].add(pnl_01,BorderLayout.CENTER);
    }

    private JPanel make_radio_delete(ButtonGroup group,JTextField field,JLabel info){
        JPanel pnl = new JPanel();
        JRadioButton[] radio = new JRadioButton[]{
                new JRadioButton(NAME),
                new JRadioButton(EMAIL),
                new JRadioButton(PHONE)
        };
        for(var btn : radio) {
            Sys.make_component(btn);
            pnl.add(btn);
            group.add(btn);

            switch (btn.getText()){
                case NAME:  action_radio_delete(btn, SQL.COLUNM_NAME ,field,info);  break;
                case EMAIL: action_radio_delete(btn, SQL.COLUNM_EMAIL,field,info);  break;
                case PHONE: action_radio_delete(btn, SQL.COLUNM_PHONE,field,info);  break;
            }
        }
        return pnl;
    }

    void action_radio_delete(JRadioButton btn,String column,JTextField field,JLabel info){
        User user =new User();
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Query.search(SQL.TABLE_USER,column,field.getText(),user);

                info.setText(make_table_data(user));
                if(!user.get_passw().isEmpty()){
                    int option = JOptionPane.showConfirmDialog((null),("Deseja excluir"+user.get_name()),(""),JOptionPane.YES_NO_OPTION);
                    if(option==JOptionPane.YES_OPTION) Query.deletarRegistro();
                }
            }
        });
    }


    //==================================================================================================================
    //  Functions
    //==================================================================================================================
    private String make_table_data(User user){
        boolean unavilable = user.get_passw().isEmpty();
        if(unavilable) return Sys._html
                +"<br> <br> <br> " +
                    "<div align='center'> Não localizado " + "</div> " +
                    "<div align='center'> <img src='https://i.pinimg.com/originals/b1/03/3b/b1033bc996c69d3a6003c2fa07281aaf.gif'>  </div> " +
                "<br>"+Sys.html_;

        else return Sys._html+
                    "<br><div align='center'>  <u>"+(user.get_name())+"</u> </div> <br>"+
                    "<table border='2' style='padding:10px '>"+
                        "<tr style='background-color:#F80'><td> Username </td> <td>"+(user.get_uname())+"</td></tr>"+
                        "<tr style='background-color:#FF0'><td> Email    </td> <td>"+(user.get_email())+"</td></tr>"+
                        "<tr style='background-color:#F80'><td> Phone    </td> <td>"+(user.get_phone())+"</td></tr>"+
                        "<tr style='background-color:#FF0'><td> Cargo    </td> <td>"+(Sys.cargo(user.get_adm()))+"</td></tr>"+
                        "<tr style='background-color:#F80'><td> Senha    </td> <td> (⬤ ⬤ ⬤ ⬤ ⬤ ⬤ ⬤ ⬤ )</td></tr>"+
                    "</table>"+
            Sys.html_
        ;

    }
}
