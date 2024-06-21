/*****************************************************************************
 *   Program
 *  =========
 *  @file  : Program.java
 *  @author: Randolfo A Goncalves
 *  @since : 30/05/24
 ****************************************************************************/
package Manager;
import Pages.*;
import Pages.Menu;
import bank.Query;
import dados.User;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Program {
    //==================================================================================================================
    // Field
    //==================================================================================================================
    public static Query     query = new Query();
    public static LoginForm login = new LoginForm(null);
    private static User user = new User();
    private static boolean flag = true;

    private static final int
            MENU     = 0,
            ADD_USER = 1,
            ADD_PROD = 2,
            PDV      = 3,
            INFO     = 4,
            DATA     = 5;

    private static Sys sys = null;
    private static final ArrayList<Sys.Panel> page = new ArrayList<Sys.Panel>();

    //==================================================================================================================
    // Calls
    //==================================================================================================================
    public static void start(){
        if(login()){

            if(!flag) page.clear();

            if(flag){
                sys = new Sys();
                flag = false;
            }

            user = login.get_user();
            page.add( new Menu()   );
            page.add( new Person() );
            page.add( new Item()   );
            page.add( new PDV()   );
            page.add( new Info()   );
            page.add( new Data()   );

            for (var panel:page) sys.add(panel);
            sys.call(page.get(MENU).ID);
        }
    }

    private static boolean login(){
        login.start();
        return login.get_state();
    }

    public static void call_menu(){
        sys.call(page.get(MENU).ID);
    }

    public static void call_user(){
        page.get(ADD_USER).transform();
        sys.call(page.get(ADD_USER).ID);
    }
    public static void call_prod(){
        page.get(ADD_PROD).transform();
        sys.call(page.get(ADD_PROD).ID);
    }
    public static void call_pdv() {
        page.get(PDV).transform();
        sys.call(page.get(PDV).ID);
    }
    public static void call_info(){
        page.get(INFO).transform();
        sys.call(page.get(INFO).ID);
    }
    public static void call_date(){
        JOptionPane.showMessageDialog(null,"Bem Vindo ao SisVendas. :)");
        //page[DATA].transform();
        //sys.call(page[DATA].ID);
    }

    public  static String user_name(){ return ((user.get_adm())?"Ad":"Cx")+": "+user.get_name(); }

    public static void alert(String text){
        JOptionPane.showMessageDialog(null, text, (""), JOptionPane.PLAIN_MESSAGE, new ImageIcon((Graph.PATH_BTN + "coin.gif")));
    }
    public static boolean quest(String name){
        return (JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog((null),("Deseja excluir "+name),(""),JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,new ImageIcon((Graph.PATH_BTN+"coin.gif"))));
    }

    public static boolean quest_x(String name){
        return (JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog((null),(name),(""),JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,new ImageIcon((Graph.PATH_BTN+"coin.gif"))));
    }
    //==================================================================================================================
}
