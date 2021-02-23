import java.util.List;

public abstract class BinaryExpression extends BaseExpression {

    protected Expression expression;
    protected Expression expression2;


    public List<String> getVariables() {

        List l1 = expression.getVariables();
        List l2 = expression2.getVariables();
        l1.addAll(l2);

        return l1;
    }

}
