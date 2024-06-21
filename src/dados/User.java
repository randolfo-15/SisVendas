package dados;

import Manager.Program;
import bank.Archivable;
import bank.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Archivable {
    //==================================================================================================================
    // Field
    //==================================================================================================================
    public String
            name  = "",
            uname = "",
            phone = "",
            email = "",
            passw = "";

    boolean adm = false;

    private static final String
            regex_phone =
                "\\(\\d{2}\\)\\s9\\d{4}-\\d{4}|"+
                "\\(\\d{2}\\)\\s9\\d{4}\\d{4}|"+
                "\\(\\d{2}\\)9\\d{4}\\d{4}|"+
                "\\(\\d{2}\\)9\\d{4}-\\d{4}|"+
                "\\d{2}\\s9\\d{4}-\\d{4}|"+               //< Se há espaço e traço.
                "\\d{2}9\\d{4}-\\d{4}|"+                  //< Se há traço.
                "\\d{2}\\s9\\d{4}\\d{4}|"+                //< Se há espaço.
                "\\d{2}9\\d{4}\\d{4}",                    //< Se não há nem espaço ou traço.,
            regex_email =
                "\\w+@\\w+\\.\\w+\\.*\\w*" ;

    //==================================================================================================================
    // Build
    //==================================================================================================================
    public User(){ }

    @Override
    public void read(ResultSet result) throws SQLException {
        name  = result.getString  (  SQL.COLUMN_NAME);
        uname = result.getString  (  SQL.COLUMN_UNAME);
        phone = result.getString  (  SQL.COLUMN_PHONE);
        email = result.getString  (  SQL.COLUMN_EMAIL);
        passw = result.getString  (  SQL.COLUMN_PASSW);
        adm   = result.getBoolean (  SQL.COLUMN_ADM   );
    }

    @Override
    public String edit() {
        return
            SQL.COLUMN_NAME +"='"+name+"',"+
            SQL.COLUMN_UNAME +"='"+uname+"',"+
            SQL.COLUMN_PHONE +"='"+phone+"',"+
            SQL.COLUMN_EMAIL +"='"+email+"',"+
            SQL.COLUMN_PASSW +"='"+passw+"',"+
            SQL.COLUMN_ADM+"="+((adm)?1:0);
    }

    @Override
    public String[] write() {
        String column =
            SQL.COLUMN_NAME +","+
            SQL.COLUMN_UNAME +","+
            SQL.COLUMN_PHONE+","+
            SQL.COLUMN_EMAIL +","+
            SQL.COLUMN_PASSW+","+
            SQL.COLUMN_ADM;

        String values =
            "'"+name+"',"+
            "'"+uname+"',"+
            "'"+phone+"',"+
            "'"+email+"',"+
            "'"+passw+"',"+
            "'"+((adm)?1:0)+"'";

        return new String[]{column,values};
    }

    //==================================================================================================================
    // Exceptions class
    //==================================================================================================================

    public static class Existing_name  extends Cases_of_Error { Existing_name(String name){  super((name+" já esta cadastrado."));  }}
    public static class Existing_phone extends Cases_of_Error { Existing_phone(){ super(("Este número já se encontra em uso."));    }}
    public static class Existing_email extends Cases_of_Error { Existing_email(){ super(("Este email já se encontra em uso."));     }}
    public static class Invalid_format extends Cases_of_Error { Invalid_format(String number){ super(("Formato invalido: "+number));}}
    public static class Insufficient   extends Cases_of_Error { Insufficient(){ super(("Senha com menos de 6 dígitos"));            }}
    public static class Incompatible   extends Cases_of_Error { Incompatible(){ super(("Senhas diferentes"));                       }}

    //==================================================================================================================
    // Getting
    //==================================================================================================================
    public String get_name() { return name;  }
    public String get_uname(){ return uname; }
    public String get_phone(){ return phone; }
    public String get_email(){ return email; }
    public String get_passw(){ return passw; }
    public boolean get_adm() { return adm;   }
    //==================================================================================================================
    // Setting
    //==================================================================================================================
    public void changer_name(String name){ this.name = name; }
    public void set_name(String name) throws Empty_field {
        if( name.isEmpty()) throw new Empty_field("Nome");
        this.name=name.toUpperCase();
    }

    public void set_adm(boolean adm){ this.adm=adm; }


    public void set_passw(String passw1,String passw2) throws Insufficient,Empty_field,Incompatible {
        if     (    passw1.isEmpty()    ) throw new Empty_field("Senha");
        else if(    passw2.isEmpty()    ) throw new Empty_field("Confirma");
        else if( !passw1.equals(passw2) ) throw  new Incompatible();
        else if(    passw1.length()<6   ) throw new Insufficient();
        this.passw = passw1;
    }

    public void changer_uname(String uname){ this.uname=uname; }
    public void set_uname(String uname) throws Existing_name,Empty_field {
        if     (                      uname.isEmpty()                       ) throw  new Empty_field("Username");
        else if( Program.query.exist(SQL.TABLE_USER,SQL.COLUMN_UNAME,uname) ) throw  new Existing_name(uname);

        this.uname=uname;
    }

    public void changer_phone(String phone){ this.phone=phone; }
    public void set_phone(String phone) throws Existing_phone,Invalid_format,Empty_field{
        if      (                    phone.isEmpty()                         ) throw new Empty_field("Phone");
        else if ( Program.query.exist(SQL.TABLE_USER,SQL.COLUMN_PHONE,phone) ) throw new Existing_phone();
        else if (                     !is_phone(phone)                       ) throw new Invalid_format(phone);

        this.phone=phone;
    }

    public void changer_email(String email){ this.email=email; }
    public void set_email(String email) throws Existing_email,Invalid_format,Empty_field{
        if     (                  email.isEmpty()                          ) throw new Empty_field("Email");
        else if( Program.query.exist(SQL.TABLE_USER,SQL.COLUMN_EMAIL,email)) throw  new Existing_email();
        else if(                  !is_email(email)                         ) throw new Invalid_format(email);

        this.email=email;
    }
    //==================================================================================================================
    // Tools
    //==================================================================================================================
    public static boolean is_phone(String phone){ return phone.matches(regex_phone); }

    public static boolean is_email(String email){ return email.matches(regex_email); }


    public void clear(){
        name = "";
        uname = "";
        phone = "";
        email = "";
        passw = "";
    }

    public static User fill(ResultSet resultSet){
        User user = new User();
        try{
            user.name = resultSet.getString("nome");
            user.uname = resultSet.getString("uname");
            user.email = resultSet.getString("email");
            user.passw = resultSet.getString("passw");

        } catch (Exception e){

        }
        return user;
    }


}
