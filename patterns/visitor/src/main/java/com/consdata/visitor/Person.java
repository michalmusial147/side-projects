package com.consdata.visitor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
