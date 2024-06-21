/*****************************************************************************
 *   Item
 *  ========
 *  @file  : Item.java
 *  @author: Randolfo A Goncalves
 *  @since : 28/05/24
 ****************************************************************************/
package Pages;

import Manager.Graph;
import Manager.Program;
import Manager.Sys;
import bank.Query;
import bank.SQL;
import dados.Cases_of_Error;
import dados.Product;
import dados.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Item extends Catalog{
    private final String
        NAME = "Nome",
        CODE = "Code";

    private ButtonGroup group = new ButtonGroup();

    public Item(){
        super("Produtos");
        init();
    }

    private void init(){ init_pages(); }

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
                new JRadioButton(CODE),
        };
        for(var btn : radio) {
            Sys.make_component(btn);
            pnl.add(btn);
            group.add(btn);

            switch (btn.getText()){
                case NAME: action_radio_search(btn, SQL.COLUMN_NAME,field,info);  break;
                case CODE: action_radio_search(btn, SQL.COLUMN_CODE,field,info);  break;

            }
        }
        return pnl;
    }

    void action_radio_search(JRadioButton btn,String column,JTextField field,JLabel info){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Product prd = new Product();
                Program.query.select(SQL.TABLE_PRODUCT,column,field.getText(),prd);
                info.setText(make_table_data(prd));
            }
        });
    }

    //==================================================================================================================
    //  Register user
    //==================================================================================================================
    private void init_page_new(){
        JTextField
                nome = make_textfield(37),
                code = make_textfield(15),
                ctry = make_textfield(15),
                value = make_textfield(20),
                amount = make_textfield(10);

        JPanel pnl_00 = new JPanel(new GridLayout(5,1));
        pnl_00.setBorder(BorderFactory.createRaisedBevelBorder());

        pnl_00.add(make_text("Novo Produto"));

        JPanel pnl_01 = create_panel();
        pnl_01.add(make_text("Nome"));
        pnl_01.add(nome);
        pnl_00.add(pnl_01);

        JPanel pnl_02 = create_panel();
        pnl_02.add(make_text("Code"));
        pnl_02.add(code);
        pnl_02.add(make_text("Categoria"));
        pnl_02.add(ctry);
        pnl_00.add(pnl_02);

        JPanel pnl_03 = create_panel();
        pnl_03.add(make_text("Valor"));
        pnl_03.add(value);
        pnl_03.add(make_text("Qtd"));
        pnl_03.add(amount);
        pnl_00.add(pnl_03);

        JPanel pnl_07 = create_panel();
        JButton salvar = new JButton("Salvar"),
                cancelar = new JButton("Limpar");

        pnl_07.add(salvar);
        pnl_07.add(Box.createHorizontalStrut(60));
        pnl_07.add(cancelar);
        pnl_00.add(pnl_07);

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nome.setText("");
                ctry.setText("");
                code.setText("");
                value.setText("");
                amount.setText("");
                group.clearSelection();
            }

        });

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Product prd = new Product();
                    prd.set_name(nome.getText());
                    prd.set_code(code.getText());
                    prd.set_category(ctry.getText());
                    float price = (value.getText().isEmpty())?0.00F:Float.parseFloat(value.getText());
                    prd.set_value(price);
                    int qtd = (amount.getText().isEmpty())?0:Integer.parseInt(amount.getText());
                    prd.set_amount(qtd);

                    Program.query.insert(SQL.TABLE_PRODUCT,prd);
                    cancelar.doClick();
                    Program.alert("Produto cadastrado com sucesso.");
                }
                catch (Cases_of_Error e)  { Program.alert(e.msg()); }
            }
        });

        page[NEW].setLayout(new BorderLayout());
        page[NEW].add(Box.createVerticalStrut(150),BorderLayout.NORTH);
        page[NEW].add(Box.createHorizontalStrut(300),BorderLayout.WEST);
        page[NEW].add(pnl_00,BorderLayout.CENTER);
        page[NEW].add(Box.createHorizontalStrut(300),BorderLayout.EAST);
        page[NEW].add(Box.createVerticalStrut(200),BorderLayout.SOUTH);
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

    private JTextField make_textfield(int value){
        JTextField in = new JTextField(value);
        in.setFont(new Font("Serif",Font.BOLD,18));
        in.setBorder(BorderFactory.createLoweredBevelBorder());
        return in;
    }

    //==================================================================================================================
    // Editar
    //==================================================================================================================
    JTextField
            nome = make_textfield(37),
            code = make_textfield(15),
            ctry = make_textfield(15),
            value = make_textfield(20),
            amount = make_textfield(10);

    Product old =new Product();
    JPanel pnl_01_00 = new JPanel();
    JPanel pnl_00 = new JPanel(new GridLayout(5,1));

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

        pnl_00.setBorder(BorderFactory.createRaisedBevelBorder());
        pnl_00.add(make_text("Editar Produto"));

        JPanel pnl_01 = create_panel();
        pnl_01.add(make_text("Nome"));
        pnl_01.add(nome);
        pnl_00.add(pnl_01);

        JPanel pnl_02 = create_panel();
        pnl_02.add(make_text("Code"));
        pnl_02.add(code);
        pnl_02.add(make_text("Categoria"));
        pnl_02.add(ctry);
        pnl_00.add(pnl_02);

        JPanel pnl_03 = create_panel();
        pnl_03.add(make_text("Valor"));
        pnl_03.add(value);
        pnl_03.add(make_text("Qtd"));
        pnl_03.add(amount);
        pnl_00.add(pnl_03);

        JPanel pnl_07 = create_panel();
        JButton salvar = new JButton("Salvar"),
                cancelar = new JButton("Limpar");

        pnl_07.add(salvar);
        pnl_07.add(Box.createHorizontalStrut(60));
        pnl_07.add(cancelar);
        pnl_00.add(pnl_07);


        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Product prd = new Product();
                    prd.set_name(nome.getText());
                    prd.set_code(code.getText());
                    prd.set_category(ctry.getText());
                    prd.set_value(Float.parseFloat(value.getText()));
                    prd.set_amount(Integer.parseInt(amount.getText()));

                    Program.query.update(SQL.TABLE_PRODUCT,SQL.COLUMN_CODE,old.get_code(),prd);
                    cancelar.doClick();
                    Program.alert(("Produto modificado com sucesso."));
                }
                catch (Cases_of_Error e)  { Program.alert(e.msg()); }
            }
        });

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nome.setText("");
                ctry.setText("");
                code.setText("");
                value.setText("");
                amount.setText("");
                group.clearSelection();
            }
        });


        // Campo de Resultado
        // ===================

        pnl_01_00.setBackground(new Color(0,0,0,0));
        pnl_01_00.add(info);

        pnl_00.setVisible(false);
        pnl_01_00.setVisible(false);

        JPanel edition = new JPanel();
        edition.setBackground(new Color(0,0,0,0));
        edition.setLayout(new BoxLayout(edition,BoxLayout.Y_AXIS));
        edition.add(Box.createHorizontalStrut(110));
        edition.add(pnl_00);
        edition.add(pnl_01_00);

        // Plugs
        // =====
        page[EDIT].add(pnl_00_00,BorderLayout.NORTH);
        page[EDIT].add(Box.createHorizontalStrut(300),BorderLayout.WEST);
        page[EDIT].add(edition,BorderLayout.CENTER);
        page[EDIT].add(Box.createHorizontalStrut(300),BorderLayout.EAST);
        page[EDIT].add(Box.createVerticalStrut(200),BorderLayout.SOUTH);
    }

    private JPanel make_radio_update(ButtonGroup group,JTextField field,JLabel info){
        JPanel pnl = new JPanel();
        JRadioButton[] radio = new JRadioButton[]{
                new JRadioButton(NAME),
                new JRadioButton(CODE),
        };
        for(var btn : radio) {
            Sys.make_component(btn);
            pnl.add(btn);
            group.add(btn);

            switch (btn.getText()){
                case NAME: action_radio_update(btn, SQL.COLUMN_NAME,field,info);  break;
                case CODE: action_radio_update(btn, SQL.COLUMN_CODE,field,info);  break;

            }
        }
        return pnl;
    }
    void action_radio_update(JRadioButton btn,String column,JTextField field,JLabel info){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                old.clear();
                Program.query.select(SQL.TABLE_PRODUCT,column,field.getText(),old);


                pnl_00.setVisible(!old.get_code().isEmpty());
                pnl_01_00.setVisible(old.get_code().isEmpty());

                nome.setText(old.get_name());
                code.setText(old.get_code());
                ctry.setText(old.get_category());
                value.setText(old.get_value()+"");
                amount.setText(old.get_amount()+"");

                info.setText(make_table_data(old));
            }
        });
    }

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
                new JRadioButton(CODE),
        };
        for(var btn : radio) {
            Sys.make_component(btn);
            pnl.add(btn);
            group.add(btn);

            switch (btn.getText()){
                case NAME: action_radio_delete(btn, SQL.COLUMN_NAME,field,info);  break;
                case CODE: action_radio_delete(btn, SQL.COLUMN_EMAIL,field,info);  break;
            }
        }
        return pnl;
    }

    void action_radio_delete(JRadioButton btn,String column,JTextField field,JLabel info){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Product prd = new Product();
                String value = field.getText();
                Program.query.select(SQL.TABLE_PRODUCT,column,value,prd);
                info.setText(make_table_data(prd));

                if(!prd.get_code().isEmpty())
                    if(Program.quest(prd.get_name())){
                        field.setText("");
                        btn.setSelected(false);
                        Program.query.delete(SQL.TABLE_PRODUCT,column,value);
                    }
            }
        });
    }

    //==================================================================================================================
    //  Functions
    //==================================================================================================================
    private String make_table_data(Product prd){
        boolean unavilable = prd.get_code().isEmpty();
        if(unavilable) return Sys._html
                +"<br> <br> <br> " +
                "<div align='center'> Não localizado " + "</div> " +
                "<br><div align='center'>(｡•́︿•̀｡)</div>"+
                "<br>"+Sys.html_;

        else return Sys._html+
                "<br><div align='center'>  <u>"+(prd.get_name())+"</u> </div> <br>"+
                "<table border='1'>"+
                "<tr style='background-color:#EEE'><td> Code </td> <td>"+(prd.get_code())+"</td></tr>"+
                "<tr style='background-color:#AAA'><td> Categoria </td> <td>"+(prd.get_category())+"</td></tr>"+
                "<tr style='background-color:#EEE'><td> Qtd </td> <td>"+(prd.get_amount())+"</td></tr>"+
                "<tr style='background-color:#AAA'><td> Valor </td> <td> R$ "+(prd.get_value())+"</td></tr>"+
                "</table>"+
                Sys.html_
                ;

    }

}
