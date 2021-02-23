import java.util.Map;

public class Div extends BinaryExpression implements Expression {

    public Div(Expression expression, Expression expression2) {
        this.expression = expression;
        this.expression2 = expression2;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {

        double den = expression2.evaluate(assignment);
        if (den != 0) {
            return expression.evaluate(assignment) / den;
        }
        throw new ArithmeticException();
    }

    @Override
    public double evaluate() throws Exception {
        double den = expression2.evaluate();
        if (den != 0) {
            return expression.evaluate() / den;
        }
        throw new ArithmeticException();
    }

    @Override
    public String toString() {
        return "(" + this.expression.toString() + " / " + this.expression2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e = new Div(this.expression.assign(var, expression), this.expression2.assign(var, expression));

        return e;
    }

    @Override
    public Expression differentiate(String var) {
        try {
            double d = Double.parseDouble(this.expression2.toString());
            if (Math.abs(d) < 0.000001) {
                System.out.println("Error the derivative of the denominator is 0 - Can't divide by 0");
            } else {
                Expression e;
                e = new Div(
                        new Minus(new Mult(this.expression.differentiate(var), this.expression2),
                                new Mult(this.expression2.differentiate(var), this.expression)),
                        new Pow(this.expression2, new Num(2)));

                return e;
            }

            //with lack of info i'm forced to return null
            //Instructors weren't clear about a non-defined derivative
            return null;
        } catch (NumberFormatException k) {
            Expression e;
            e = new Div(
                    new Minus(new Mult(this.expression.differentiate(var), this.expression2),
                            new Mult(this.expression2.differentiate(var), this.expression)),
                    new Pow(this.expression2, new Num(2)));

            return e;
        }


    }

    @Override
    public Expression simplify() {

        //simplifying the num and denom
        Expression e1 = this.expression.simplify();
        Expression e2 = this.expression2.simplify();

        if (e1.getVariables().isEmpty() && e2.getVariables().isEmpty()) {
            try {
                if (e2.evaluate() != 0){
                    return new Num(e1.evaluate()/e2.evaluate());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        if(!e2.getVariables().isEmpty()) {
            // x/x=1
            if (e1.toString().equals(e2.toString())) {
                return new Num(1);
            }

            // 0/x=0
            if (e1.getVariables().isEmpty()) {
                try {
                    if (Math.abs((e1).evaluate()) < 0.00001) {
                        return new Num(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // x/1=x
        if (e2.getVariables().isEmpty()) {
            try {
                if ((e2).evaluate() == 1) {
                    return e1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new Div(e1, e2);
    }
}
