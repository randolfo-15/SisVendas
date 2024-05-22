import bank.Query;
import bank.SQL;
import dados.User;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        User user = new User();
        Query.select(SQL.TABLE_USER,SQL.COLUMN_NAME,"Randolfo",user);
        System.out.println(
                (
                    "Nome: "+user.get_name()+"\n"+
                    "Phone: "+user.get_phone()+"\n"+
                    "Email: "+user.get_email()+"\n"
                )
        );

        //Program.start();

    }
}