package com.consdata.visitor;

public interface Visitor {
    void visit(Warrior person);

    void visit(Worker person);

    void visit(Wizard person);
}
