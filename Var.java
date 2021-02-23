

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Var implements Expression {
    private String var;

    public Var(String var) {
        this.var = var;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if(assignment == null)
        {
            throw new Exception();
        }
        if(assignment.get(var) != null)
            return assignment.get(var);
        throw new Exception();
    }

    @Override
    public double evaluate() throws Exception {
        if(this.var.equals("e"))
            return Math.E;
        if(this.var.equals("Pi"))
            return Math.PI;
        throw new Exception();
    }

    @Override
    public List<String> getVariables() {
        List l = new ArrayList<>();
        l.add(var);
        return l;
    }

    @Override
    public String toString() {
        return this.var;
    }


    @Override
    public Expression assign(String var, Expression expression) {
        if (this.var.equals(var)) {
            return expression;

        }
        return this;
    }

    @Override
    public Expression differentiate(String var) {
        if (this.var.equals(var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }


}
