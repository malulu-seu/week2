public class ORExpression implements Expression {

    private Expression leftExpression;
    private Expression rightExpression;

    public ORExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public String interpreter() {
        return String.format("(%s) or (%s)", leftExpression.interpreter(), rightExpression.interpreter());
    }

}
