package com.dhuangz.validate.pojo.vo;

import com.dhuangz.validate.annotation.StringAnnotation;

import java.io.Serializable;

/**
 * Created by zyf on 2017/9/15.
 */
public class UserVO implements Serializable{

    private static final long serialVersionUID = -1645857771248687430L;

    @StringAnnotation(code = "DHUANGZ_APPLICATION_ERROR_000001" , min = 2, max = 8)
    private String name;

    private String address;

    public UserVO() {
    }

    public UserVO(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
