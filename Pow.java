import java.util.List;
import java.util.Map;

public class Pow extends BinaryExpression implements Expression {


    public Pow(Expression expression, Expression expression2) {
        this.expression = expression;
        this.expression2 = expression2;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.pow(expression.evaluate(assignment), expression2.evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.pow(expression.evaluate(), expression2.evaluate());
    }

    @Override
    public String toString() {
        return "(" + this.expression.toString() + "^" + this.expression2.toString() + ")";
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(this.expression.assign(var, expression), this.expression2.assign(var, expression));
    }


    //calculating the d by the formula - (f^g)':
    //f^g(ln(f)g'+f'g/f
    @Override
    public Expression differentiate(String var) {
        Expression e = new Var("e");
        Expression f = this.expression;
        Expression g = this.expression2;
        Expression fTag = this.expression.differentiate(var);
        Expression gTag = this.expression2.differentiate(var);

        Expression numerator = new Mult(fTag, g);
        Expression lefSideSum = new Mult(new Log(e, f), gTag);
        Expression commonFactor = new Pow(f, g);
        Expression parenthesis = new Plus(lefSideSum, new Div(numerator, f));
        Expression sum = new Mult(commonFactor, parenthesis);

        return sum;
    }

    @Override
    public Expression simplify() {
        Expression e = this.expression.simplify();
        Expression e2 = this.expression2.simplify();
        Expression res = new Pow(e, e2);
        try {
            if (e.getVariables().isEmpty() && e2.getVariables().isEmpty()) {
                return new Num(res.evaluate());
            }
            if (!e2.getVariables().isEmpty()) {

                if (e.getVariables().isEmpty()) {
                    ////0^e=0
                    if (Math.abs(e.evaluate()) < 0.00001) {
                        return new Num(0);
                    }
                    //1^e=1
                    if (e.evaluate() == 1.0) {
                        return new Num(1);
                    }
                }
            }
            if (!e.getVariables().isEmpty()) {

                if (e2.getVariables().isEmpty()) {
                    ////e^0=1
                    if (Math.abs(e2.evaluate()) < 0.00001) {
                        return new Num(1);
                    }
                    //e^1=e
                    if (e2.evaluate() == 1.0) {
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



