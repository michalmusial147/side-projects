package com.consdata.visitor;

public class PartyVisitor implements Visitor {

    @Override
    public void visit(Warrior person) {
        person.setMorale(person.getMorale() + 1);
        person.setStamina(person.getStamina() - 1);
    }

    @Override
    public void visit(Worker person) {
        person.setMorale(person.getMorale() + 1);
        person.setSpeed(person.getSpeed() - 1);
    }

    @Override
    public void visit(Wizard person) {
        //nothing
    }
}
