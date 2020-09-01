public class ANDExpression implements Expression {

    private Expression leftExpression;
    private Expression rightExpression;

    public ANDExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public String interpreter() {
        return String.format("(%s) and (%s)", leftExpression.interpreter(), rightExpression.interpreter());
    }
}
