package com.consdata.webdev;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Person {

    private int id;
    private int health;
    private int morale;

    public abstract void accept(Visitor visitor);

    public Person(int health, int morale){
        this.health = health;
        this.morale = morale;
    }
}
