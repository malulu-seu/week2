import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Generator {

    public static String parse(String expression) {
        Stack<Character> symbol = new Stack<Character>();
        Stack<String> element = new Stack<String>();
        expression = expression.toLowerCase();

        List<String> res = new LinkedList<String>();

        String subExpression = "";
        for (int i = 0;i<expression.length();i++) {
            if(expression.charAt(i) == '(') {//左括号->压进括号栈
                symbol.push(expression.charAt(i));
                if (!subExpression.equals("")) {
                    element.push(subExpression);
                    subExpression = "";
                }
            } else if(expression.charAt(i) == ')') {
                if (!subExpression.equals("")) {
                    element.push(subExpression);
                    subExpression = "";
                }
                symbol.pop();//and,or,not是一个子表达式最后弹出的
                String popValue = element.pop();
                check(popValue, res);
            } else {
                subExpression = subExpression + expression.charAt(i);
            }
        }

        if (res.size() == 0 && !subExpression.equals(""))//单个子表达式
            return addSelect(subExpression);

        if (!symbol.isEmpty())
            throw new IllegalArgumentException();

        while (!element.isEmpty()) {
            check(element.pop(), res);
        }
        return addSelect(res.get(0));
    }

    private static void check(String popValue, List<String> res) {
        if (popValue.replace(" ","").equals("not")) {
            //之前一定弹出过一个value(最近弹出的)
            Expression exp = new ExpressionContext(res.get(res.size()-1));
            Expression not = new NOTExpression(exp);
            res.remove(res.size()-1);
            res.add(not.interpreter());
        } else if (popValue.replace(" ","").equals("and")){
            Expression expLeft = new ExpressionContext(res.get(res.size()-2));
            Expression expRight = new ExpressionContext(res.get(res.size()-1));
            Expression and = new ANDExpression(expLeft,expRight);
            res.remove(res.size()-2);
            res.remove(res.size()-1);
            res.add(and.interpreter());
        } else if (popValue.replace(" ","").equals("or")){
            Expression expLeft = new ExpressionContext(res.get(res.size()-2));
            Expression expRight = new ExpressionContext(res.get(res.size()-1));
            Expression or = new ORExpression(expLeft,expRight);
            res.remove(res.size()-2);
            res.remove(res.size()-1);
            res.add(or.interpreter());
        } else {//是子表达式
            res.add(popValue);
        }

    }

    private static String addSelect(String res) {
        return String.format("select * from customers where %s",res);
    }
}
