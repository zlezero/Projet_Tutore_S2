package test;

import java.io.IOException;
import java.util.ArrayList;
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
		
		fail("L'ajout de l'événement a échoué !");
	}
	
	@Test
	void testSuppressionEvenement() {
		
		FriseChronologique maFrise = new FriseChronologique("Ma frise de test", new Date(1, 1, 2000), new Date(1, 1, 2018), 2, "Frise.ser");

		Evenement monEvt = new Evenement(new Date(1, 1, 2010), "Mon evt !", "Ma desc !", "");
		Evenement monEvt2 = new Evenement(new Date(1, 1, 2010), "Mon evt !", "Ma desc !", "");
		
		maFrise.ajoutEvenement(0, monEvt);
		maFrise.ajoutEvenement(1, monEvt2);
		
		maFrise.supprimerEvenement(monEvt2);
		
		for (HashMap<Integer, Evenement> maHashMap : maFrise.getHashMapEvts().values()) {
			if (maHashMap.containsValue(monEvt2)) {
				fail("La suppression de l'événement a échoué !");
			}
		}
				
	}
	
	@Test
	void getPoidsEvenement() {
		
		FriseChronologique maFrise = new FriseChronologique("Ma frise de test", new Date(1, 1, 2000), new Date(1, 1, 2018), 2, "Frise.ser");

		Evenement monEvt = new Evenement(new Date(1, 1, 2010), "Mon evt !", "Ma desc !", "");
		Evenement monEvt2 = new Evenement(new Date(1, 1, 2010), "Mon evt 2 !", "Ma desc 2 !", "");
		Evenement monEvt3 = new Evenement(new Date(1, 1, 2012), "Mon evt 3 !", "Ma desc 3 !", "");

		maFrise.ajoutEvenement(0, monEvt);
		maFrise.ajoutEvenement(1, monEvt2);
		maFrise.ajoutEvenement(3, monEvt3);
		
		assertEquals(0, maFrise.getPoidsEvenement(monEvt));
		assertEquals(1, maFrise.getPoidsEvenement(monEvt2));
		assertEquals(3, maFrise.getPoidsEvenement(monEvt3));
		
	}
	
	@Test
	void testGetListeEvenements() {
		
		FriseChronologique maFrise = new FriseChronologique("Ma frise de test", new Date(1, 1, 2000), new Date(1, 1, 2018), 2, "Frise.ser");

		Evenement monEvt = new Evenement(new Date(1, 1, 2010), "Mon evt !", "Ma desc !", "");
		Evenement monEvt2 = new Evenement(new Date(1, 1, 2010), "Mon evt 2 !", "Ma desc 2 !", "");
		Evenement monEvt3 = new Evenement(new Date(1, 1, 2012), "Mon evt 3 !", "Ma desc 3 !", "");
		
		maFrise.ajoutEvenement(0, monEvt);
		maFrise.ajoutEvenement(1, monEvt2);
		maFrise.ajoutEvenement(0, monEvt3);
				
		ArrayList<Evenement> maListe = new ArrayList<Evenement>();
		
		maListe.add(monEvt);
		maListe.add(monEvt2);
		maListe.add(monEvt3);
		
		assertEquals(maListe, maFrise.getListeEvenements());
	}
	
	@Test
	void testSuppressionEvtHorsPeriode() {
		
		FriseChronologique maFrise = new FriseChronologique("Ma frise de test", new Date(1, 1, 2000), new Date(1, 1, 2018), 2, "Frise.ser");

		Evenement monEvt = new Evenement(new Date(1, 1, 2010), "Mon evt !", "Ma desc !", "");
		Evenement monEvt2 = new Evenement(new Date(1, 1, 2010), "Mon evt 2 !", "Ma desc 2 !", "");
		Evenement monEvt3 = new Evenement(new Date(1, 1, 2012), "Mon evt 3 !", "Ma desc 3 !", "");
		
		maFrise.ajoutEvenement(0, monEvt);
		maFrise.ajoutEvenement(1, monEvt2);
		maFrise.ajoutEvenement(0, monEvt3);
		
		maFrise.setDateFin(new Date(1, 1, 2010));
		
		maFrise.supprimerEvenementsHorsPeriode();
		
		ArrayList<Evenement> evts = new ArrayList<Evenement>();
		
		evts.add(monEvt);
		evts.add(monEvt2);

		assertEquals(evts, maFrise.getListeEvenements());
		
	}
	
	@Test
	void testSauvegarderFrise() {
		
		FriseChronologique maFrise = new FriseChronologique("Ma frise de test", new Date(1, 1, 2000), new Date(1, 1, 2018), 2, "Frise.ser");

		Evenement monEvt = new Evenement(new Date(1, 1, 2010), "Mon evt !", "Ma desc !", "");
		Evenement monEvt2 = new Evenement(new Date(1, 1, 2010), "Mon evt 2 !", "Ma desc 2 !", "");
		Evenement monEvt3 = new Evenement(new Date(1, 1, 2012), "Mon evt 3 !", "Ma desc 3 !", "");
		
		maFrise.ajoutEvenement(0, monEvt);
		maFrise.ajoutEvenement(1, monEvt2);
		maFrise.ajoutEvenement(0, monEvt3);
		
		try {
			maFrise.sauvegarderFrise();
		} catch (IOException e) {
			fail("La sauvegarde de la frise a échoué !");
		}
	}
	
}
