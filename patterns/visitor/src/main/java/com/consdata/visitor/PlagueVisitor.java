package com.consdata.visitor;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlagueVisitor implements Visitor {

    private int plagueStrength;

    @Override
    public void visit(Warrior person) {
        person.setHealth(person.getHealth() - plagueStrength);
        person.setStamina(person.getStamina() - plagueStrength);
    }

    @Override
    public void visit(Worker person) {
        person.setHealth(person.getHealth() - plagueStrength);
        person.setSpeed(person.getSpeed() - plagueStrength);

    }

    @Override
    public void visit(Wizard warrior) {
        // nothing Wizards have resistance to diseases
    }
}
