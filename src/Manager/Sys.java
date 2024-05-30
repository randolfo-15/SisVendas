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
            bkg1 = new Color(100,100,100),
            frg1 = new Color(255,255,255),
            bkg2 = new Color(60,60,60),
            frg2 = new Color(255,255,255);


    // Components
    // ===========
    private final JMenuBar   menu    = new JMenuBar();                        //<  Menu
    private final CardLayout cards   = new CardLayout();                      //<  Manager
    private Container        buffer  = null;                                  //<  Panel buffer
    //private Map<String,Panel> display = new HashMap<String,Panel>();           //<  Display
    public static final int size_btn = 5;

    private final JMenu[] btn = new JMenu[size_btn];
    private final String[]  image_btn = new String[]{
            "add_user.png",
            "add_product.png",
            "pdv.png",
            "info.png",
            "grafic2.gif"
    };
    //==================================================================================================================
    // Startups
    //==================================================================================================================
    private void init(){
        init_frame();
        init_component();
        init_display();

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
            menu.add(Box.createHorizontalStrut(10));
            menu.add(btn[i]);
            menu.add(Box.createHorizontalStrut(10));
        }

        // ...
    }

    private void init_display(){
        buffer = getContentPane();
        buffer.setLayout(cards);
    }

    public static JMenu build_btn(String path){
        JMenu btn = new JMenu();
        btn.setIcon(new ImageIcon("src/imagens/btns/"+path));
        return btn;
    }

    // Setting Components
    // ==================
    private JMenuBar  build_menu(){
        menu.setPreferredSize(new Dimension(width,height_menu));
        menu.setBorder(BorderFactory.createRaisedBevelBorder());
        // ...
        return menu;
    }

    //==================================================================================================================
    // Method
    //==================================================================================================================
    public void call(String panel){ cards.show(buffer,panel); }

    public void add(Panel panel){ buffer.add(panel.ID,panel); }

    public void remove(Panel panel){ buffer.remove(panel); }
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

        private int
                padding_width  = 20,
                padding_height = 30;

        // Components
        // ===========
        BorderLayout        layout     = new BorderLayout();
        protected Graph main_panel = new Graph("bkg.jpg");
        public    JButton changer_user = new JButton();

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
            setBackground(bkg2);
            setForeground(frg2);
            setBorder(BorderFactory.createRaisedBevelBorder());
            border();

            changer_user.setIcon(new ImageIcon("src/imagens/btns/changer_user.png"));
            changer_user.setFocusPainted(false);
            changer_user.setBorderPainted(false);
            changer_user.setContentAreaFilled(false);
            changer_user.setOpaque(false);

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
                space[i].setPreferredSize(new Dimension(padding_width, padding_height));
                space[i].setBackground(bkg2);
            }

            space[SOUTH].add(changer_user,BorderLayout.EAST);
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
        // Padding
        // =======
        void set_padding_width(int value){  padding_width  = value; }
        void set_padding_height(int value){ padding_height = value; }
        int get_padding_width(){  return padding_width;  }
        int get_padding_height(){ return padding_height; }

        // Background Image
        // ================
        public void set_bkg_image(String path){ main_panel= new Graph(path); }
        //==============================================================================================================
    }
}
