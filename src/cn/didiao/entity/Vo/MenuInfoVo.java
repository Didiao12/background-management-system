package cn.didiao.entity.Vo;

import cn.didiao.entity.MenuInfo;

import java.awt.*;
import java.util.List;

public class MenuInfoVo extends MenuInfo {
    private List<MenuInfo> list;

    public List<MenuInfo> getList() {
        return list;
    }

    public void setList(List<MenuInfo> list) {
        this.list = list;
    }
}