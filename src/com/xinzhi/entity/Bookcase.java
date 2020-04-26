package com.xinzhi.entity;

import java.util.List;

public class Bookcase {
    private Integer id;
    private String name;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bookcase(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Bookcase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

