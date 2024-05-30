import Manager.Sys;
import Pages.Menu;


public class Main {
    public static void main(String[] args) {

        Sys sys = new Sys();
        Menu menu = new Menu();

        sys.add(menu);
        sys.call(menu.ID);
    }
}