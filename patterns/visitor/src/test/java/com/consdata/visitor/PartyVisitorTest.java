package com.consdata.visitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PartyVisitorTest {

	private PlagueVisitor covid;
	private final int plagueStrength = 2;

	private final int defaultStatistic = 100;

	@BeforeEach
	void init(){
		covid = new PlagueVisitor(plagueStrength);
	}

	@Test
	void visitWarriorTest() {
		//given
		Warrior warrior = new Warrior(100, 100, 100);

		//when
		warrior.accept(covid);

		//then
		assertThat(warrior.getHealth()).isEqualTo(98);
		assertThat(warrior.getStamina()).isEqualTo(98);

	}


	@Test
	void visitWizardTest() {
		//given
		Wizard wizard = new Wizard(100, 100, 100);

		//when
		wizard.accept(covid);

		//then
		assertThat(wizard.getHealth()).isEqualTo(100);
		assertThat(wizard.getMana()).isEqualTo(100);
	}

	@Test
	void visitWorkerTest() {
		//given
		Worker worker = new Worker(100, 100, 100);

		//when
		worker.accept(covid);

		//then
		assertThat(worker.getHealth()).isEqualTo(98);
		assertThat(worker.getSpeed()).isEqualTo(98);

	}



}
