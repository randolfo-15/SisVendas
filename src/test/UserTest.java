package test;

import dados.User;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @org.junit.jupiter.api.Test
    void set_uname() {

        try{

            User pablo = new User();
            pablo.set_uname("Pablo Rangel");

        }catch (User.Existing_name e){

            Assertions.assertTrue(false);
            System.out.println(e.msg());

        }
    }

    @org.junit.jupiter.api.Test
    void is_phone() {
        Assertions.assertTrue(User.is_phone("(31)98105-9465"),"Número correto.");
        Assertions.assertFalse(User.is_phone("(31)8105-9465"),"Número incompleto.");
        Assertions.assertFalse(User.is_phone("(3A)A105-9465"),"Número errado.");
    }

    @org.junit.jupiter.api.Test
    void is_email() {
        Assertions.assertTrue(User.is_email("ragnifico@yahoo.com.br"),"Email pessoal.");
        Assertions.assertTrue(User.is_email("mabtoy@gmail.com"),"email profissional");
        Assertions.assertFalse(User.is_email("ragnifico@@yahoo.com.br"),"Erro @@.");
        Assertions.assertFalse(User.is_email("ragnifico@yahoo."),"Erro de email incompleto.");
    }
}