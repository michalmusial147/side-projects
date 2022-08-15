package com.consdata.visitor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
public class Warrior extends Person {

    private int stamina;

    public Warrior(int health, int morale, int stamina) {
        super(health, morale);
        this.stamina = stamina;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


}
