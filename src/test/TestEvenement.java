package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modele.Date;
import modele.Evenement;

class TestEvenement {

	@Test
	void testCompareTo() {
		
		Evenement evt1 = new Evenement(new Date(1, 1, 2010), "Mon super événement !", "Ma super description !", "");
		Evenement evt2 = new Evenement(new Date(1, 1, 2010), "Mon super événement !", "Ma super description !", "");
		Evenement evt3 = new Evenement(new Date(1, 1, 2010), "Mon super événement 3 !", "Ma super description 3 !", "");
		
		assertEquals(0, evt1.compareTo(evt2));
		assertEquals(0, evt2.compareTo(evt1));
		assertEquals(-18, evt1.compareTo(evt3));
		assertEquals(18, evt3.compareTo(evt1));
		
	}

}
