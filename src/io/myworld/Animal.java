package io.myworld;

import java.io.Serializable;

/**
 * Created by EricLi on 2017/1/6.
 * Email me : EricLi1235@gmail.com.
 */
public class Animal implements Serializable {
    private String name;
    private House preHouse;

    public Animal(String name, House preHouse) {
        this.name = name;
        this.preHouse = preHouse;
    }

    @Override
    public String toString() {
        return name + "[" + super.toString() + "]," + preHouse + "\n";
    }
}
