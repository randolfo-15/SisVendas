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
        login();
        sys.call(page[MENU].ID);
    }

    public static void call_user(){
        JOptionPane.showMessageDialog(null,"User");
        sys.call(page[ADD_USER].ID);
    }
    public static void call_prod(){
        JOptionPane.showMessageDialog(null,"Products");
        sys.call(page[ADD_PROD].ID);
    }
    public static void call_pdv() {
        JOptionPane.showMessageDialog(null,"PDV");
        sys.call(page[PDV].ID);
    }
    public static void call_info(){
        JOptionPane.showMessageDialog(null,"Info");
        sys.call(page[INFO].ID);
    }
    public static void call_date(){
        JOptionPane.showMessageDialog(null,"Data's");
        sys.call(page[DATA].ID);
    }

    public static void login(){

    }

    public  static String user_name(){ return ((user.get_adm())?"Ad":"Cx")+user.get_name()+": "; }
}
