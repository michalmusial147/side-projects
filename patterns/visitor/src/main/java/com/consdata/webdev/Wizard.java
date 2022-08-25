package com.consdata.webdev;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wizard extends Person {

    private int mana;

    public Wizard(int health, int morale, int mana) {
        super(health, morale);
        this.mana = mana;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
