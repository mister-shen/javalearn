package entity;

import java.util.List;

/**
 * @author shenrs
 * @Description 分页返回统一对象
 * @create 2019-07-23 13:25
 **/
public class PageResult <T> {
    private long total;
    private List<T> rows;

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
