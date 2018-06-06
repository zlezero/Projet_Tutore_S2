package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modele.Date;

class TestDate {

	@Test
	void testCompareTo() {
		assertEquals(0, new Date(1, 1, 2010).compareTo(new Date(1, 1, 2010)));
		assertEquals(-1, new Date(1, 1, 2010).compareTo(new Date(2, 1, 2010)));
		assertEquals(1, new Date(2, 1, 2010).compareTo(new Date(1, 1, 2010)));
	}

	@Test
	void testDateDuLendemain() {
		
		Date maDate = new Date(1, 1, 2010);
		
		if (maDate.dateDuLendemain().getJour() != maDate.getJour() + 1) {
			fail("Date du lendemain invalide !");
		}
		
		maDate = new Date(31, 1, 2010);
		
		if (maDate.dateDuLendemain().getJour() != 1 && maDate.dateDuLendemain().getMois() != 2) {
			fail("Date du lendemain : cas changement de mois invalide !");
		}
		
		maDate = new Date(31, 12, 2010);
		
		if (maDate.dateDuLendemain().getAnnee() != maDate.getAnnee() + 1 && maDate.dateDuLendemain().getJour() != 1 && maDate.dateDuLendemain().getMois() != 1) {
			fail("Date du lendemain : cas fin d'année invalide !");
		}
		
		maDate = new Date(28, 2, 2010);
		
		if (maDate.dateDuLendemain().getJour() != 1 && maDate.dateDuLendemain().getMois() != 3) {
			fail("Date du lendemain : cas de février non bissextile invalide !");
		}
		
		maDate = new Date(28, 2, 2016);
		
		if (maDate.dateDuLendemain().getJour() != 29 && maDate.dateDuLendemain().getMois() != 2) {
			fail("Date du lendemain : cas de février bissextile invalide !");
		}
	}

	@Test
	void testDateDeLaVeille() {
		
		Date maDate = new Date(2, 2, 2010);
		
		if (maDate.dateDeLaVeille().getJour() != maDate.getJour() - 1) {
			fail("Date de la veille invalide !");
		}
		
		maDate = new Date(1, 2, 2010);
		
		if (maDate.dateDeLaVeille().getJour() != 31 && maDate.dateDeLaVeille().getMois() != maDate.dateDeLaVeille().getMois() - 1) {
			fail("Date de la veille : cas du changement de mois invalide !");
		}
		
		maDate = new Date(31, 12, 2010);
		
		if (maDate.dateDeLaVeille().getAnnee() != maDate.getAnnee() - 1 && maDate.dateDeLaVeille().getJour() != 31 && maDate.dateDeLaVeille().getMois() != 12) {
			fail("Date de la veille : cas fin d'année invalide !");
		}
		
		maDate = new Date(1, 3, 2010);
		
		if (maDate.dateDeLaVeille().getJour() != 28 && maDate.dateDeLaVeille().getMois() != 2) {
			fail("Date de la veille : cas de février non bissextile invalide !");
		}
		
		maDate = new Date(1, 3, 2016);
		
		if (maDate.dateDeLaVeille().getJour() != 29 && maDate.dateDeLaVeille().getMois() != 2) {
			fail("Date de la veille : cas de février bissextile invalide !");
		}
	}

	@Test
	void testDernierJourDuMois() {
		assertEquals(31, Date.dernierJourDuMois(1, 2010));
		assertEquals(30, Date.dernierJourDuMois(4, 2010));
		assertEquals(28, Date.dernierJourDuMois(2, 2010));
		assertEquals(29, Date.dernierJourDuMois(2, 2016));
	}

	@Test
	void testGetNomMois() {
		assertEquals("Janvier", Date.getNomMois(1));
		assertEquals("Mois invalide", Date.getNomMois(13));
	}

	@Test
	void testDatePremierJourSemaine() {
		assertEquals(4, new Date(6, 6, 2018).datePremierJourSemaine().getJour());
		assertEquals(31, new Date(1, 1, 2019).datePremierJourSemaine().getJour());
		assertEquals(12, new Date(1, 1, 2019).datePremierJourSemaine().getMois());
		assertEquals(2018, new Date(1, 1, 2019).datePremierJourSemaine().getAnnee());
	}

	@Test
	void testIsToday() {
		assertEquals(true, new Date().isToday());
	}

	@Test
	void testDateFormatee() {
		assertEquals("01/01/2010", new Date(1, 1, 2010).dateFormatee());
		assertEquals("10/10/2010", new Date(10, 10, 2010).dateFormatee());
	}

}
