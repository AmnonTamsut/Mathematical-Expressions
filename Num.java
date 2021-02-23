import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Num implements Expression {

    private double number;

    public Num(double number) {
        this.number = number;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return number;
    }

    @Override
    public double evaluate() throws Exception {
        return number;
    }


    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return Double.toString(number);
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }

}
