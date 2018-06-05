package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modele.Date;
import modele.Evenement;
import modele.FriseChronologique;

class TestFriseChronologique {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void testAjoutEvenement() {
		FriseChronologique maFrise = new FriseChronologique();
		maFrise.ajoutEvenement(2010, new Evenement(new Date(1, 1, 2010), "", "", "", ""));
	}
	
}
