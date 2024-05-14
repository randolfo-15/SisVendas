package dados.test;

import dados.User;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @org.junit.jupiter.api.Test
    void set_uname() {

    }

    @org.junit.jupiter.api.Test
    void set_phone() {
        User user = new User();
        try{
            user.set_phone("(31)98105-9465");
        }
        catch (User.Invalid_format e){
            System.out.println(e.msg());
            Assertions.fail();
        }
        catch (User.Existing_phone e){
            System.out.println(e.msg());
            Assertions.fail();
        }

        try{ user.set_phone("(31)8105-9465"); }
        catch (User.Invalid_format e){ Assertions.assertTrue(true); }
        catch (User.Existing_phone e){ Assertions.fail(); }

        try{ user.set_phone("(3B)A8105-9465"); }
        catch (User.Invalid_format e){ Assertions.assertTrue(true); }
        catch (User.Existing_phone e){ Assertions.fail(); }

    }

    @org.junit.jupiter.api.Test
    void set_email() {
        User user = new User();
        try{
            user.set_email("ragnifico@yahoo.com.br");
        }
        catch (User.Invalid_format e){
            System.out.println(e.msg());
            Assertions.fail();
        }
        catch (User.Existing_email e){
            System.out.println(e.msg());
            Assertions.fail();
        }

        try{
            user.set_email("ragnifico@hotmail.com");
        }
        catch (User.Invalid_format e){
            System.out.println(e.msg());
            Assertions.fail();
        }
        catch (User.Existing_email e){
            System.out.println(e.msg());
            Assertions.fail();
        }

        try{ user.set_email("ragnifico@@yahoo.com.br"); }
        catch (User.Invalid_format e){ Assertions.assertTrue(true); }
        catch (User.Existing_email e){ Assertions.fail(); }

        try{ user.set_email("ragnifico@yahoo."); }
        catch (User.Invalid_format e){ Assertions.assertTrue(true); }
        catch (User.Existing_email e){ Assertions.fail(); }


    }
}