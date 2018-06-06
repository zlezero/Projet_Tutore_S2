package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import modele.Date;
import modele.Evenement;
import modele.FriseChronologique;

class TestFriseChronologique extends TestCase {
	
	@Test
	void testAjoutEvenement() {
		Evenement monEvt = new Evenement(new Date(1, 1, 2010), "Mon evt !", "Ma desc !", "");
		FriseChronologique maFrise = new FriseChronologique("Ma frise de test", new Date(1, 1, 2000), new Date(1, 1, 2018), 2, "Frise.ser");
		maFrise.ajoutEvenement(1, monEvt);
		
		for (HashMap<Integer, Evenement> maHashMap : maFrise.getHashMapEvts().values()) {
			if (maHashMap.containsValue(monEvt)) {
				return;
			}
		}
		
		fail();
	}
	
}
