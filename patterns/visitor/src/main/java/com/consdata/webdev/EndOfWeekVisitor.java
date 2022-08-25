package com.consdata.webdev;

public class EndOfWeekVisitor implements Visitor{

    private static final int DEFAULT_STATISTIC = 100;
    
    @Override
    public void visit(Warrior person) {
        person.setMorale(DEFAULT_STATISTIC);
        person.setHealth(DEFAULT_STATISTIC);
        person.setStamina(DEFAULT_STATISTIC);
    }

    @Override
    public void visit(Worker person) {
        person.setMorale(DEFAULT_STATISTIC);
        person.setHealth(DEFAULT_STATISTIC);
        person.setSpeed(DEFAULT_STATISTIC);
    }

    @Override
    public void visit(Wizard person) {
        person.setMorale(DEFAULT_STATISTIC);
        person.setHealth(DEFAULT_STATISTIC);
        person.setMana(DEFAULT_STATISTIC);
    }
}
