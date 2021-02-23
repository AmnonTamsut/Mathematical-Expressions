import java.util.List;

public abstract class UnaryExpression extends BaseExpression{
    protected Expression expression;

    public List<String> getVariables() {
        return expression.getVariables();
    }
}
