package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import modele.Date;
import modele.Evenement;
import modele.LectureEcriture;

class TestLectureEcriture {
	
	@Test
	void testEcriture() {
		Evenement monEvt = new Evenement(new Date(1, 2, 2010), "Mon evt !", "Ma description !", "");
		try {
			LectureEcriture.ecriture(new File("Test_JUnit.ser"), monEvt);
			if (new File("Test_JUnit.ser").length() == 0) {
				fail("Erreur d'écriture !");
			}
		} catch (IOException e) {
			fail("Erreur d'écriture : " + e.getMessage());
		}
	}

	@Test
	void testLecture() {
		try {
			
			testEcriture();
			
			Evenement monEvt = (Evenement)LectureEcriture.lecture(new File("Test_JUnit.ser"));

			assertEquals(1, monEvt.getDate().getJour());
			assertEquals(2, monEvt.getDate().getMois());
			assertEquals(2010, monEvt.getDate().getAnnee());
			assertEquals("Mon evt !", monEvt.getTitre());
			assertEquals("Ma description !", monEvt.getChDescription());
			assertEquals("", monEvt.getChPhoto());
			
		} catch (FileNotFoundException e) {
			fail("Erreur de lecture : fichier non trouvé !");
		} catch (IOException e) {
			fail("Erreur de lecture : " + e.getMessage());
		}
	}

}
