
package Pages;

import Manager.Graph;
import Manager.Program;
import Manager.Sys;
import bank.Query;
import bank.SQL;
import dados.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PDV extends Sys.Panel {
    //==================================================================================================================
    // Fields
    //==================================================================================================================
    //  Head
    // ======
    private JLabel     placa = new JLabel("Total: R$ 0.00");   //< Mostrar Total
    private JTextField codes = new JTextField(45);        //< Campo para codes
    private float      total = 0.0F;

    private final int   padding = 100;
    private final Color bkg =  new Color(0,0,0,0);

    //  Body
    // ======
    private String[] names = new String[]{
            "Dinheiro",
            "Débito",
            "Crédito",
            "Pix"
    };
    private JTable table;
    DefaultTableModel model = new DefaultTableModel();
    private List<Product> cart = new ArrayList<>();
    private Graph pnl = new Graph();

    //  Base
    // ======
    private ButtonGroup group = new ButtonGroup();      //< Grupo d botões

    //==================================================================================================================
    // Build Panel
    //==================================================================================================================
    private Component set_font(Component cmp ){
        cmp.setFont(new Font("SERIF",Font.BOLD,20));
        return cmp;
    }

    private JRadioButton transform_radio(String name){
        JRadioButton btn = new JRadioButton(name);
        set_font(btn);
        group.add(btn);
        return btn;
    }

    private JPanel init_radius_pay(){
        JPanel panel = new JPanel(new GridLayout((1),(4)));

        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.setBackground(new Color(255,150,0,150));
        for(var btn: names)  panel.add(transform_radio(btn));

        return panel;
    }

    private JButton search_btn(){
        JButton btn = new JButton(new ImageIcon((Graph.PATH_IMG+"lupa.png")));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
                codes.setText("");
            }
        });
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setPreferredSize(new Dimension(50,50));
        return btn;
    }

    private JButton finalize_btn(){
        JButton btn = new JButton("Finalizar compra");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(group.getSelection()==null) {
                    Program.alert("Selecione uma forma de pagamento.");
                    return;
                }
                if(Program.quest_x("Total da compra: R$ "+total)) {
                    model.setRowCount(0);
                    total=0;
                    group.clearSelection();
                }
            }
        });
        return btn;
    }

    private void init_panel(){

        pnl.setLayout(new BorderLayout());
    }

    private JPanel head(){
        Graph   space_00 = new Graph();
        JPanel  space_01 = new JPanel(),
                space_02 = new JPanel(),
                space_03 = new JPanel();

        space_00.setImg(new ImageIcon((Graph.PATH_IMG+"fundo.jpg")));
        space_01.setBackground(bkg);
        space_02.setBackground(bkg);

        space_00.setLayout(new BoxLayout(space_00,BoxLayout.X_AXIS));
        space_01.setLayout(new BoxLayout(space_01,BoxLayout.Y_AXIS));

        space_01.add(set_font(new JLabel(("Código:"))));
        space_03.add(set_font(codes));
        space_03.add(search_btn());
        space_01.add(space_03);
        space_01.add(Box.createVerticalStrut(70));

        space_02.add(Box.createHorizontalStrut((padding)));
        space_02.add(new JLabel(new ImageIcon((Graph.PATH_IMG+"money.png"))));
        space_02.add(Box.createHorizontalStrut((padding)));

        space_00.add(Box.createHorizontalStrut(padding));
        space_00.add(space_01);
        space_00.add(space_02);

        return space_00;
    }

    private JPanel body(){
        JPanel space_00 = new JPanel(new BorderLayout());


        model.addColumn(("Código"));
        model.addColumn(("Nome"));
        model.addColumn(("Categoria"));
        model.addColumn(("Valor"));

        table = new JTable(model);
        set_font(table);

        space_00.add(Box.createHorizontalStrut(padding),BorderLayout.EAST);
        space_00.add(table);
        space_00.add(Box.createHorizontalStrut(padding),BorderLayout.WEST);


        return space_00;
    }

    private JPanel tail(){
        JPanel space_00 = new JPanel(),
               space_01 = new JPanel(),
               space_02 = new JPanel();

        space_00.setLayout(new BoxLayout(space_00,BoxLayout.X_AXIS));
        space_00.setBorder(BorderFactory.createRaisedBevelBorder());

        space_01.add(init_radius_pay());
        space_02.add(finalize_btn());

        space_00.add(space_01);
        space_00.add(Box.createHorizontalBox());
        space_00.add(space_02);

        return space_00;
    }


    private void init(){
        init_panel();
        pnl.add(head(),BorderLayout.NORTH);
        pnl.add(body(),BorderLayout.CENTER);
        pnl.add(tail(),BorderLayout.SOUTH);
        work_space.add(pnl);
    }



    private void search(){
        Product product = new Product();
        Program.query.select(SQL.TABLE_PRODUCT,SQL.COLUMN_CODE,codes.getText(),product);
        if(!product.get_code().isEmpty()) {
            model.addRow(new Object[]{product.get_code(), product.get_name(), product.get_category(), product.get_value()});
            total += product.get_value();
        }
    }

    public PDV() {
        super("Ponto de venda");
        init();
    }
}
