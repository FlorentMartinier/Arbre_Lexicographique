package arbreLexicographique;

public class ArbreLexicographique {
	NoeudAbstrait entree;

	public ArbreLexicographique() {
		entree = NoeudVide.getInstance();
	}

	public boolean isEmpty() {
		return entree instanceof NoeudVide;
	}

	public int size() {
		return entree.nbMots();
	}

	public boolean contient(String s) {
		return entree.contient(s);
	}

	public boolean prefixe(String s) {
		return entree.prefixe(s);
	}

	public boolean ajout(String s) {
		try {
			entree = entree.ajout(s);
			return true;
		} catch (ModificationImpossibleException mie) {
			return false;
		}
	}

	public boolean suppr(String s) {
		try {
			entree = entree.suppr(s);
			return true;
		} catch (ModificationImpossibleException mie) {
			return false;
		}
	}

	public String toString() { // �l�ments s�par�s par \n
		return entree.toString("");
	}
}
