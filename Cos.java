import java.security.PublicKey;
import java.util.List;
import java.util.Map;

public class Cos extends UnaryExpression implements Expression {


    public Cos(Expression expression) {
        this.expression = expression;
    }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(expression.evaluate(assignment)));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians(expression.evaluate()));
    }


    @Override
    public String toString() {
        return "cos(" + this.expression.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(this.expression.assign(var, expression));

    }

    @Override
    public Expression differentiate(String var) {

        return new Neg(new Mult(new Sin(this.expression),this.expression.differentiate(var)));
    }

    @Override
    public Expression simplify() {
        if(this.expression.getVariables().isEmpty()) {
            try {
                return new Num(Math.cos(this.expression.evaluate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
        return new Cos(this.expression.simplify());

    }

}