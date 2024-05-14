package dados;

public class User {
    //==================================================================================================================
    // Field
    //==================================================================================================================
    private String
            name  = "",
            uname = "",
            phone = "",
            email = "";

    //==================================================================================================================
    // Build
    //==================================================================================================================
    public User(){ }

    //==================================================================================================================
    // Exceptions class
    //==================================================================================================================
    public static class Existing_name  extends Exception{ public String msg(){ return "Este nome já se encontra em uso.";   }}
    public static class Existing_phone extends Exception{ public String msg(){ return "Este número já se encontra em uso."; }}
    public static class Existing_email extends Exception{ public String msg(){ return "Este email já se encontra em uso.";  }}
    public static class Invalid_format extends Exception{ public String msg(){ return "Formato de dado invalido";           }}

    //==================================================================================================================
    // Getting
    //==================================================================================================================
    public String get_name() { return name;  }
    public String get_uname(){ return uname; }
    public String get_phone(){ return phone; }
    public String get_email(){ return email; }

    //==================================================================================================================
    // Setting
    //==================================================================================================================
    public void set_name(String name) { this.name=name.toUpperCase(); }

    public void set_uname(String uname) throws Existing_name {
        if(/*Query.exist(SQL.TABLE_USER,SQL.COLUMN_UNAME,uname)*/false ) throw new Existing_name();

        this.uname=uname;
    }

    public void set_phone(String phone) throws Existing_phone,Invalid_format{
        if(/*Query.exist(SQL.TABLE_USER,SQL.COLUMN_PHONE,phone)*/false ) throw new Existing_phone();
        else if(!phone.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) throw new Invalid_format();

        this.phone=phone;
    }

    public void set_email(String email) throws Existing_email,Invalid_format{
        if(/*Query.exist(SQL.TABLE_USER,SQL.COLUMN_EMAIL,email)*/false ) throw new Existing_email();
        else if(!email.matches("\\w+@\\w+\\.\\w+\\.*\\w*")) throw new Invalid_format();

        this.email=email;
    }

}
