package modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LectureEcriture {
	
	/**
	* Méthode qui permet de lire un fichier en prenant en paramètre un fichier
	* @param un fichier 
	* @return un Objet 
	*/
	
	public static Object lecture(File parFichier) throws FileNotFoundException, IOException {
		
		ObjectInputStream flux;
		Object objetLu = null;
		
		//Ouverture du fichier en mode lecture
		try {
			flux = new ObjectInputStream(new FileInputStream(parFichier));
			objetLu = (Object)flux.readObject();
			flux.close();
		} 
		catch (ClassNotFoundException parException) {
			System.err.println("Erreur lecture du fichier : " + parException.toString());
		}
		
		return objetLu;
		
	}

	/**
	* Méthode qui permet d'écrire dans un fichier en indiquant ce même fichier en paramètre
	* @param un fichier 
	*/
	
	public static void ecriture(File parFichier, Object parObjet) throws IOException
	{
		ObjectOutputStream flux = null;
		//Ouverture du fichier en mode écriture
		try {
			flux = new ObjectOutputStream(new FileOutputStream(parFichier));
			flux.writeObject(parObjet);
			flux.flush();
			flux.close();
		}
		catch (IOException parException) {
			System.err.println("Erreur écriture du fichier : " + parException.toString());
			System.exit(1);
		}
	}
}
