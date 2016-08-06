package com.example.fcode14;

import javax.inject.Inject;

/**
 * Created by wuzhuojun on 2016/8/4 0004.
 */
public class UserModel {

    private String Name;

    @Inject
    public UserModel() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
