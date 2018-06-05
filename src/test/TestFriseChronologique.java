package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import modele.Date;
import modele.Evenement;
import modele.FriseChronologique;

class TestFriseChronologique extends TestCase {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void testAjoutEvenement() {
		FriseChronologique maFrise = new FriseChronologique("Ma frise de test", new Date(1, 1, 2000), new Date(1, 1, 2018), 2, "Frise.ser");
		maFrise.ajoutEvenement(1, new Evenement(new Date(1, 1, 2010), "Mon evt !", "Ma desc !", ""));
		//if (assertEquals(maFrise.toString().equals("dd"))) {
			
		//}
	}
	
}
