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


public class Program {
    //==================================================================================================================
    // Field
    //==================================================================================================================
    public static Query query = new Query();
    public static User user = new User();
    public static final int
            MENU     = 0,
            ADD_USER = 1,
            ADD_PROD = 2,
            PDV      = 3,
            INFO     = 4,
            DATA     = 5;

    private static final Sys sys =new Sys();
    private static final Sys.Panel[] page = new Sys.Panel[]{
            new Menu(),
            new Person(),
            new Item(),
            new Menu(),
            new Info(),
            new Data()
    };

    //==================================================================================================================
    // Calls
    //==================================================================================================================
    public static void start(){
        for (var panel:page) sys.add(panel);
        //login();
        //user = get_user_test();
        sys.call(page[MENU].ID);
    }

    public static void call_menu(){
        sys.call(page[MENU].ID);
    }

    public static void call_user(){
        page[ADD_USER].transform();
        sys.call(page[ADD_USER].ID);
    }
    public static void call_prod(){
        page[ADD_PROD].transform();
        sys.call(page[ADD_PROD].ID);
    }
    public static void call_pdv() {
        page[PDV].transform();
        sys.call(page[PDV].ID);
    }
    public static void call_info(){
        page[INFO].transform();
        sys.call(page[INFO].ID);
    }
    public static void call_date(){
        JOptionPane.showMessageDialog(null,"Bem Vindo ao SisVendas. :)");
        //page[DATA].transform();
        //sys.call(page[DATA].ID);
    }

    public static void login(){
        new LoginForm(null);
        user = LoginForm.usuarioAtual();
    }

    public  static String user_name(){
        return ((user.get_adm())?"Ad":"Cx")+": "+user.get_name();
    }

    public static User get_user_test(){

        user.name="Randolfo A Goncalves";
        user.uname="Rag";
        user.phone="(31) 98105-9465";
        user.email="randolfo@gmail.com";
        user.set_adm(true);

        return user;
    }

    public static void alert(String text){
        JOptionPane.showMessageDialog(null, text, (""), JOptionPane.PLAIN_MESSAGE, new ImageIcon((Graph.PATH_BTN + "coin.gif")));
    }
    public static boolean quest(String name){
        return (JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog((null),("Deseja excluir "+name),(""),JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,new ImageIcon((Graph.PATH_BTN+"coin.gif"))));
    }
    //==================================================================================================================
}
