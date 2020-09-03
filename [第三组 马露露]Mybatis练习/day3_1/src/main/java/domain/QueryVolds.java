package domain;

import java.util.List;

/**
 * 查询类
 * 用于组成In操作符的查询集合
 */

public class QueryVolds {

    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "QueryVolds{" +
                "ids=" + ids +
                '}';
    }
}
