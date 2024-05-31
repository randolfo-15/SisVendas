/*****************************************************************************
 *   Sys
 *  =====
 *  @file  : Sys.java
 *  @author: Randolfo A Goncalves
 *  @since : 28/05/24
 ****************************************************************************/
package Manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;



public class Sys extends JFrame {
    //==================================================================================================================
    // Field
    //==================================================================================================================
    //  Proportion
    //  ===========
    public static final int
            panel_width  = 800,  //< Largura paineis.
            panel_height = 600;  //< Altura paineis.

    private static final int
            width = 1650,      //< Largura janela.
            height = 1000,     //< Altura  janela.
            height_menu = 65;  //< Altura menu.

    // Style
    // ======
    public static  final Color
            frg1 = new Color(255,255,255),
            bkg = new Color(60,60,60),
            frg2 = new Color(200,200,200);


    // Components
    // ===========
    private final JMenuBar   menu    = new JMenuBar();                        //<  Menu
    private final CardLayout cards   = new CardLayout();                      //<  Manager
    private Container        buffer  = null;                                  //<  Panel buffer
    //private Map<String,Panel> display = new HashMap<String,Panel>();           //<  Display
    public static final int size_btn = 5;

    private final JButton[] btn = new JButton[size_btn];
    private final String[]  image_btn = new String[]{
            "add_user.png",
            "add_product.png",
            "pdv.png",
            "info.png",
            "graphic.gif"
    };

    public static final int ADD_USER    = 0;
    public static final int ADD_PRODUCT = 1;
    public static final int PDV         = 2;
    public static final int INFO        = 3;
    public static final int DATA        = 4;
    //==================================================================================================================
    // Startups
    //==================================================================================================================
    private void init(){
        init_frame();
        init_component();
        init_display();
        actions_btns();
        setVisible(true);
    }

    // Components
    // ===========
    private void init_frame(){
        setSize(width,height);
        setLocationRelativeTo(null);
        setJMenuBar(build_menu());
        setIconImage(new ImageIcon("src/imagens/coin.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void init_component(){
        int last = size_btn - 1;
        for(int i=0;i<size_btn;i++) {
            if(i==last) menu.add(Box.createHorizontalGlue());
            btn[i] = build_btn(image_btn[i]);
            menu.add(Box.createHorizontalStrut(20));
            menu.add(btn[i]);
            menu.add(Box.createHorizontalStrut(20));
        }

        // ...
    }

    private void init_display(){
        buffer = getContentPane();
        buffer.setLayout(cards);
        buffer.setBackground(bkg);
    }

    public static JButton build_btn(String path){
        JButton btn = new JButton();
        btn.setIcon(new ImageIcon("src/imagens/btns/"+path));

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBackground(frg2);
        btn.setBorder(BorderFactory.createCompoundBorder());

        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {}

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                btn.setBorder(BorderFactory.createTitledBorder("   "));
                //btn.setBackground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                btn.setBorder(BorderFactory.createCompoundBorder());
                //btn.setBackground(frg2);
            }
        });

        return btn;
    }

    // Setting Components
    // ==================
    private JMenuBar  build_menu(){
        menu.setPreferredSize(new Dimension(width,height_menu));
        menu.setBorder(BorderFactory.createRaisedBevelBorder());
        menu.setBackground(frg2);
        // ...
        return menu;
    }
    //==================================================================================================================
    // Function
    //==================================================================================================================
    public void actions_btns(){
        btn[ADD_USER].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { Program.call_user(); }
        });
        btn[ADD_PRODUCT].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { Program.call_prod(); }
        });
        btn[PDV].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { Program.call_pdv(); }
        });
        btn[INFO].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { Program.call_info(); }
        });
        btn[DATA].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { Program.call_date(); }
        });
    }

    //==================================================================================================================
    // Method
    //==================================================================================================================
    public  void call(String panel){ cards.show(buffer,panel); }

    public void add(Panel panel){ buffer.add(panel.ID,panel); }

    //==================================================================================================================
    // Build
    //==================================================================================================================

    public Sys(){ init(); }
    //==================================================================================================================
    // Panel
    //==================================================================================================================
    public static class Panel extends JPanel{
        //==============================================================================================================
        // Field
        //==============================================================================================================
        // Identified
        // ==========
        public final String ID;            //< Name class

        // Clock
        // =====
        static class Clock extends JLabel{
            Clock(){
                setText("00:00:00");
                setForeground(frg1);
                setFont(new Font("Serif",Font.BOLD,18));
                Timer time = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    }
                });
                time.start();
            }
        }

        Clock clock = new Clock();

        //  Proportion
        //  ===========
        public final int
                width  = Sys.panel_width , //< Largura
                height = Sys.panel_height; //< Altura
        //==============================================================================================================
        // Style
        // ======

        // Components
        // ===========
        BorderLayout        layout     = new BorderLayout();
        protected Graph main_panel = new Graph("bkg.jpg");
        public    JButton lock = new JButton();

        protected final int
                EAST  = 0,
                WEST  = 1,
                NORTH = 2,
                SOUTH = 3;

        protected JPanel[] space  = new JPanel[]{
                new JPanel(), //< EAST
                new JPanel(), //< WEST
                new JPanel(), //< NORTH
                new JPanel(new BorderLayout())  //< SOUTH
        };
        //==============================================================================================================
        // Build
        //==============================================================================================================
        protected Panel(String name){
            ID=name;
            setLayout(layout);
            setBackground(bkg);
            setForeground(frg2);
            setBorder(BorderFactory.createRaisedBevelBorder());
            border();

            lock.setIcon(new ImageIcon("src/imagens/btns/changer_user.png"));
            lock.setFocusPainted(false);
            lock.setBorderPainted(false);
            lock.setContentAreaFilled(false);
            lock.setOpaque(false);

            lock.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    JOptionPane.showMessageDialog(null,"LOCK");
                }
            });

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Clock();
                }
            });
        }

        JPanel init_display(){
            main_panel.setPreferredSize(new Dimension(width,height));
            main_panel.setBorder(BorderFactory.createLoweredBevelBorder());
            main_panel.setForeground(frg1);

            return main_panel;
        }

        private void border(){
            for(int i=0;i<4;i++) {
                int padding_width = 20;
                int padding_height = 30;
                space[i].setPreferredSize(new Dimension(padding_width, padding_height));
                space[i].setBackground(bkg);
            }

            space[SOUTH].add(lock  , BorderLayout.EAST);
            space[SOUTH].add(marca(),BorderLayout.WEST);
            space[NORTH].add(clock);
            add( space[NORTH],       BorderLayout.NORTH  );
            add( space[WEST],        BorderLayout.WEST   );
            add( init_display(),     BorderLayout.CENTER );
            add( space[EAST],        BorderLayout.EAST   );
            add( space[SOUTH],       BorderLayout.SOUTH  );
        }

        private JLabel marca(){
            JLabel label = new JLabel("    SisVendas");
            label.setForeground(frg1);
            label.setFont(new Font("Serif",Font.BOLD,20));
            return label;
        }


        //==============================================================================================================
        // Method
        //==============================================================================================================

        //==============================================================================================================
    }
}
