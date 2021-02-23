import java.util.List;
import java.util.Map;

public class Mult extends BinaryExpression implements Expression {


    public Mult(Expression expression, Expression expression2) {
        this.expression = expression;
        this.expression2 = expression2;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return expression.evaluate(assignment) * expression2.evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        return expression.evaluate() * expression2.evaluate();
    }

    @Override
    public String toString() {
        return "(" + this.expression.toString() + " * " + this.expression2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e = new Mult(this.expression.assign(var, expression), this.expression2.assign(var, expression));

        return e;
    }

    @Override
    public Expression differentiate(String var) {
        Expression f = this.expression;
        Expression fTag = this.expression.differentiate(var);

        Expression g = this.expression2;
        Expression gTag = this.expression2.differentiate(var);

        Expression product1 = new Mult(fTag, g);
        Expression product2 = new Mult(gTag, f);

        return new Plus(product1, product2);
    }

    @Override
    public Expression simplify() {
        Expression e = this.expression.simplify();
        Expression e2 = this.expression2.simplify();
        Expression res = new Mult(e, e2);
        try {
            if (e.getVariables().isEmpty() && e2.getVariables().isEmpty()) {
                return new Num(res.evaluate());
            }
            if (!e2.getVariables().isEmpty()) {

                if (e.getVariables().isEmpty()) {
                    //1*e=e
                    if(e.evaluate() == 1.0)
                        return e2;
                    //0*e=0
                    if(Math.abs(e.evaluate()) < 0.000001)
                        return new Num(0);
                }
            }
            if (!e.getVariables().isEmpty()) {

                if (e2.getVariables().isEmpty()) {
                    //e*1=e
                    if(e2.evaluate() == 1.0)
                        return e;
                    //e*0=0
                    if(Math.abs(e2.evaluate()) < 0.000001)
                        return new Num(0);
                }
            }
        } catch (Exception k) {
            k.printStackTrace();
        }
        return new Mult(e, e2);
    }
}
