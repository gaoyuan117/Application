package com.jzbwlkj.application.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by admin on 2017/5/11.
 */

@Entity
public class HistoryBean {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "json")
    @NotNull
    private String json;

    @Generated(hash = 245605076)
    public HistoryBean(Long id, @NotNull String json) {
        this.id = id;
        this.json = json;
    }

    @Generated(hash = 48590348)
    public HistoryBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }

}
