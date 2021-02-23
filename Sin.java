import java.util.List;
import java.util.Map;

public class Sin extends UnaryExpression implements Expression {

    public Sin(Expression expression) {
        this.expression = expression;
    }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(Math.toRadians(expression.evaluate(assignment)));
    }

    @Override
    public double evaluate() throws Exception {
        double calc = Math.sin(Math.toRadians(expression.evaluate()));

        if (Math.abs(calc) < 0.00001) {
            return 0;
        }
        return calc;
    }

    @Override
    public String toString() {
        return "sin(" + this.expression.toString() + ")";
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(this.expression.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Cos(this.expression), this.expression.differentiate(var));
    }

    @Override
    public Expression simplify() {
        if (this.expression.getVariables().isEmpty()) {
            try {
                return new Num(Math.sin(Math.toRadians(this.expression.evaluate())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Sin(this.expression.simplify());

    }

}
