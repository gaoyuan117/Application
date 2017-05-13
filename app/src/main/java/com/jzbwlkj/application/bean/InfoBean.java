package com.jzbwlkj.application.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/5/2.
 */

public class InfoBean implements Serializable{
    public String time;
    public String money;
    public List<String> list;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
