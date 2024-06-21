package dados;

public class Cases_of_Error extends Exception{
    String text;
    Cases_of_Error(String text){ this.text=text;}
    public String msg(){ return text;}
}
