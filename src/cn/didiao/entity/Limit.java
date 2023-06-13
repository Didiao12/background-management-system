package cn.didiao.entity;

import cn.didiao.entity.Vo.MenuInfoVo;

import java.util.List;
import java.util.Map;

public class Limit {
    private Integer count;
    private Integer pageCount;
    private List<Map<String, Object>> list;

    public Integer getCount() {
        return count;
    }

    public Limit(Integer count, Integer pageCount, List<Map<String, Object>> list) {
        this.count = count;
        this.pageCount = pageCount;
        this.list = list;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
}
