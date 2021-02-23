import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Neg extends UnaryExpression implements Expression {

    public Neg(Expression expression) {

        this.expression = expression;
    }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (expression.evaluate(assignment)) * (-1);
    }

    @Override
    public double evaluate() throws Exception {
        return (expression.evaluate()) * (-1);
    }

    @Override
    public String toString() {
        return "(-" + this.expression.toString()+")";
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(this.expression.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(this.expression.differentiate(var));
    }

    @Override
    public Expression simplify() {
        Expression e = new Neg(this.expression.simplify());
        if(e.getVariables().isEmpty()) {
            try {
                return new Num(e.evaluate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return new Neg(e);
    }

}
