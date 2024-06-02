package Pages;

import Manager.Graph;
import Manager.Program;
import Manager.Sys;
import dados.Product;
import dados.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Item extends Catalog{
    Product product = new Product();
    User user = new User();
    private JTextField field ;
    private JLabel info ;
    private ButtonGroup group = new ButtonGroup();

    public Item(){
        super("Produtos");
        init();
    }

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
        info = Sys.make_text(make_table_data(user),30, Color.BLACK);
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
        JPanel pnl_01    = new Graph("bkg2.jpg")    ;
        pnl_01.setBorder(BorderFactory.createRaisedBevelBorder());
        pnl_01.add(info);

        // Plugs
        // =====
        page[DATA].add(pnl_00);
        page[DATA].add(pnl_01);

    }

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
        JTextField field = make_textfield();
        //page[EDIT].setLayout(new BoxLayout(page[EDIT],BoxLayout.Y_AXIS));

        // Campo de busca
        // ===============
        JPanel pnl_02    = new JPanel();
        pnl_02.add(new JLabel(new ImageIcon("src/imagens/lupa.png")));
        pnl_02.add(Sys.make_text("Buscar por: ",18,Color.BLACK));
        pnl_02.add(field);
        pnl_02.add(make_radio_search());
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
        JTextField field = make_textfield();
        JLabel info = Sys.make_text(make_table_data(user),30,Color.BLACK);
        page[DEL].setLayout(new BoxLayout(page[DEL],BoxLayout.Y_AXIS));

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
    private JTextField make_textfield(){
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
