public class NOTExpression implements Expression {

    private Expression subExpression;

    public NOTExpression(Expression subExpression) {
        this.subExpression = subExpression;
    }

    public String interpreter() {
        return String.format("not(%s)", subExpression.interpreter());
    }
}
