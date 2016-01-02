package arbreLexicographique;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

privileged aspect Serialisation {
	declare parents: ArbreLexicographique implements java.io.Serializable;

	public void ArbreLexicographique.sauve(String nomFichier) {
		File monFichier = new File(nomFichier);

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(monFichier));

			// Ecriture dans le flux de sortie
			out.write(this.toString());

			// Vide le tampon
			out.flush();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ArbreLexicographique.charge(String nomFichier) {
		try {
			// Ouverture du flux d'entrée sur le fichier
			BufferedReader in = new BufferedReader(new FileReader(nomFichier));
			Scanner s = new Scanner (in);
			while (s.hasNext()){
				this.ajout(s.nextLine());
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) {
	}
}
