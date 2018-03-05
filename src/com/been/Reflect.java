package com.been;
import java.sql.Array;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/2 0002.
 */
public class Reflect {
    private String name;
    private Integer age;
    public String phone;
    public Map<String,Array> map;
    public Reflect() {
    }

    public Reflect(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
