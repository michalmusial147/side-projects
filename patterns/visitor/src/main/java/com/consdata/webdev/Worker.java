package com.consdata.webdev;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Worker extends Person {

    private int speed;

    public Worker(int health, int morale, int speed) {
        super(health, morale);
        this.speed = speed;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
