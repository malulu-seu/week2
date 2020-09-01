public class ExpressionContext implements Expression{

    private String context;

    public ExpressionContext(String context) {
        this.context = context;
    }


    public String interpreter() {
        return context;
    }
}
