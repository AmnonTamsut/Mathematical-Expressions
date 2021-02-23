import java.util.List;
import java.util.Map;

public class Log extends BinaryExpression implements Expression {


    public Log(Expression expression, Expression expression2) {
        this.expression = expression;
        this.expression2 = expression2;
    }


    //this method uses the identity:
    //Logx(y) = Logc(y)/Logc(x)
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double y = expression.evaluate(assignment);
        double x = expression2.evaluate(assignment);
        if (x > 0 && y > 0 && x != 1) {
            return Math.log(y) / Math.log(x);
        }

        throw new ArithmeticException();
    }

    @Override
    public double evaluate() throws Exception {
        double x = expression.evaluate();
        double y = expression2.evaluate();
        if (x > 0 && y > 0 && x != 1) {
            return Math.log(y) / Math.log(x);
        }

        throw new ArithmeticException();

    }

    @Override
    public String toString() {
        return "log(" + this.expression.toString() + ", " + this.expression2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e = new Log(this.expression.assign(var, expression), this.expression2.assign(var, expression));

        return e;
    }

    @Override
    public Expression differentiate(String var) {
        Expression e = new Var("e");
        Expression f = this.expression;
        Expression fTag = this.expression.differentiate(var);

        Expression g = this.expression2;
        Expression gTag = this.expression2.differentiate(var);


        Expression product1 = new Mult(new Log(e, f), new Mult(gTag, f));
        Expression product2 = new Mult(new Log(e, g), new Mult(fTag, g));
        Expression numerator = new Minus(product1, product2);
        Expression denominator = new Mult(new Pow(new Log(e, f),new Num(2)), new Mult(g, f));

        return new Div(numerator, denominator);


    }

    @Override
    public Expression simplify() {
        Expression e = this.expression.simplify();
        Expression e2 = this.expression2.simplify();
        Expression res = new Log(e,e2);
        try {
            if(e.getVariables().isEmpty() && e2.getVariables().isEmpty()) {
                return new Num(res.evaluate());
            }
        } catch (Exception k) {
            k.printStackTrace();
        }
        //log(x,x)=1
        if (e.toString().equals(e2.toString())) {
            return new Num(1);
        }
        return res;
    }
}
