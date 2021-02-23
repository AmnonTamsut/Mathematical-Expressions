import java.util.List;
import java.util.Map;

public class Plus extends BinaryExpression implements Expression {

    public Plus(Expression expression, Expression expression2) {
        this.expression = expression;
        this.expression2 = expression2;
    }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return expression.evaluate(assignment) + expression2.evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        return expression.evaluate() + expression2.evaluate();
    }

    @Override
    public String toString() {
        return "(" + this.expression.toString() + " + " + this.expression2.toString() + ")";
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(this.expression.assign(var, expression), this.expression2.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(this.expression.differentiate(var), this.expression2.differentiate(var));
    }

    @Override
    public Expression simplify() {
        Expression e = this.expression.simplify();
        Expression e2 = this.expression2.simplify();
        Expression res = new Plus(e, e2);
        try {
            if (e.getVariables().isEmpty() && e2.getVariables().isEmpty()) {
                return new Num(res.evaluate());
            }
            if (!e2.getVariables().isEmpty()) {
                //0+x=-x
                if (e.getVariables().isEmpty()) {
                    if (Math.abs(e.evaluate()) < 0.00001) {
                        return e2;
                    }
                }
            }
            if (!e.getVariables().isEmpty()) {
                //x+0=x
                if (e2.getVariables().isEmpty()) {
                    if (Math.abs(e2.evaluate()) < 0.00001) {
                        return e;
                    }
                }
            }
        } catch (Exception k) {
            k.printStackTrace();
        }

        return res;
    }
}

