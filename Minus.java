import java.util.List;
import java.util.Map;

public class Minus extends BinaryExpression implements Expression {


    public Minus(Expression expression, Expression expression2) {
        this.expression = expression;
        this.expression2 = expression2;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return expression.evaluate(assignment) - expression2.evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        return expression.evaluate() - expression2.evaluate();
    }

    @Override
    public String toString() {
        return "(" + this.expression.toString() + " - " + this.expression2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e = new Minus(this.expression.assign(var, expression), this.expression2.assign(var, expression));

        return e;
    }

    @Override
    public Expression differentiate(String var) {
        return new Minus(this.expression.differentiate(var), this.expression2.differentiate(var));
    }

    @Override
    public Expression simplify() {
        Expression e = this.expression.simplify();
        Expression e2 = this.expression2.simplify();
        Expression res = new Minus(e, e2);
        try {
            if (e.getVariables().isEmpty() && e2.getVariables().isEmpty()) {
                return new Num(res.evaluate());
            }
            if (!e2.getVariables().isEmpty()) {
                //x-x=0
                if (e.toString().equals(e2.toString())) {
                    return new Num(0);
                }

                //0-x=-x
                if (e.getVariables().isEmpty()) {
                        if (Math.abs(e.evaluate()) < 0.00001) {
                            return new Neg(e2);
                        }
                }
            }
            if (e2.getVariables().isEmpty()) {
                //x-0=x
                    if (e2.evaluate() == 0)
                        return e;
            }
        } catch (Exception k) {
            k.printStackTrace();
        }

        return new Minus(e, e2);
    }
}
