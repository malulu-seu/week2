import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

public class GeneratorTest {

    @Test
    public void testOneExpression() {//只包含一个子表达式
        String expected = String.format("select * from customers where %s", getOneExpression());
        Assert.assertEquals(expected, Generator.parse(getOneExpression()));
    }

    @Test
    public void testAND() {//只包含and连接符
        String expected = String.format("select * from customers where %s", getAndExpression());
        Assert.assertEquals(expected, Generator.parse(getAndExpression()));
    }

    @Test
    public void testOR() {//只包含or连接符
        String expected = String.format("select * from customers where %s", getOrExpression());
        Assert.assertEquals(expected, Generator.parse(getOrExpression()));
    }

    @Test
    public void testNOT() {//只包含not连接符
        String expected = String.format("select * from customers where %s", getNotExpression());
        Assert.assertEquals(expected, Generator.parse(getNotExpression()));
    }

    @Test
    public void tesyNestAND_OR() {//嵌套and和or连接符
        String expected = String.format("select * from customers where %s", getNestedAND_OR());
        Assert.assertEquals(expected, Generator.parse(getNestedAND_OR()));
    }

    @Test
    public void tesyNestAND_NOT() {//嵌套and和not连接符
        String expected = String.format("select * from customers where %s", getNestedAND_NOT());
        Assert.assertEquals(expected, Generator.parse(getNestedAND_NOT()));
    }

    @Test
    public void tesyNestOR_NOT() {//嵌套or和not连接符
        String expected = String.format("select * from customers where %s", getNestedOR_NOT());
        Assert.assertEquals(expected, Generator.parse(getNestedOR_NOT()));
    }

    @Test
    public void testNestAND_OR_NOT() {//嵌套and,or,not连接符
        String expected = String.format("select * from customers where %s", getNestedExpression());
        Assert.assertEquals(expected, Generator.parse(getNestedExpression()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLeftException() {//括号不匹配的情况-左括号更多
         Generator.parse(getException(true));
    }

    @Test(expected = EmptyStackException.class)
    public void testRightException() {//括号不匹配的情况-右括号更多
        Generator.parse(getException(false));
    }

    @Test
    public void testSpace() { //输入字符串存在多余空格的情况
        String expected = String.format("select * from customers where %s", getNestedExpression());
        Assert.assertEquals(expected, Generator.parse(getSpaceExpression()));
    }

    private String getOneExpression() {
        return "companyname = \"htsc\"";
    }

    private String getAndExpression() {
        return "((companyname = \"htsc\") and (age<30)) and (sex != \"male\")";
    }

    private String getOrExpression() {
        return "(companyname = \"htsc\") or ((age<30) or (sex != \"male\"))";
    }

    private String getNotExpression() {
        return "not(companyname = \"htsc\")";
    }

    private String getNestedAND_OR() {
        return "(companyname = \"htsc\") or ((age<30) and (sex != \"male\"))";
    }

    private String getNestedAND_NOT() {
        return "(not(companyname = \"htsc\")) and (sex != \"male\")";
    }

    private String getNestedOR_NOT() {
        return "(companyname = \"htsc\") or (not(sex != \"male\"))";
    }

    private String getNestedExpression() {
        return "(companyname = \"htsc\") and ((age<30) or (not(sex != \"male\")))";
    }

    private String getException(boolean moreLeft) {
        if(moreLeft)    return "(companyname = \"htsc\") and ((age<30)";
        return "(companyname = \"htsc\") and (age<30))";
    }

    private String getSpaceExpression() {
        return " (companyname = \"htsc\") and  ((age<30) or (not  (sex != \"male\")))";
    }

}
