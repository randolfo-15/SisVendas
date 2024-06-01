/*****************************************************************************
 *   Program
 *  =========
 *  @file  : Program.java
 *  @author: Randolfo A Goncalves
 *  @since : 30/05/24
 ****************************************************************************/
package Manager;
import Pages.*;
import dados.User;
import javax.swing.*;


public class Program {
    public static final int
        MENU     = 0,
        ADD_USER = 1,
        ADD_PROD = 2,
        PDV      = 3,
        INFO     = 4,
        DATA     = 5;

    private static final Sys sys =new Sys();
    private static User user = new User();


    private static final Sys.Panel[] page = new Sys.Panel[]{
            new Menu(),
            new Person(),
            new Item(),
            new Menu(),
            new Info(),
            new Datas()
    };


    public static void start(){
        for (var panel:page) sys.add(panel);
        //login();
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
        page[DATA].transform();
        sys.call(page[DATA].ID);
    }

    public static void login(){
        new LoginForm(null);
        user = LoginForm.usuarioAtual();
    }

    public  static String user_name(){
        return ((user.get_adm())?"Ad":"Cx")+": "+user.get_name();
    }
}
