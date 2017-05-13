package com.jzbwlkj.application.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/5/11.
 */

public class NumberBean implements Serializable{

    /**
     * code : 1
     * message : 操作成功
     * data : [{"id":"2","name":"??","createtime":"1494467836"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 2
         * name : ??
         * createtime : 1494467836
         */

        private String id;
        private String name;
        private String createtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
