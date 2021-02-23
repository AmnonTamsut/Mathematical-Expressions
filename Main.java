import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        Expression e2 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));

        try {
            Expression e = new Div(new Pow(new Var("x"),new Num(2.0)),new Var("x"));
            System.out.println(e.differentiate("x").simplify().simplify());



        } catch (Exception e) {
            System.out.println("we did it!!");
            System.out.println("amnon the king");
        }
    }
}
