package arbreLexicographique;

public class Noeud extends NoeudAbstrait {

	private char valeur;
	private NoeudAbstrait fils;

	public Noeud(NoeudAbstrait frere, NoeudAbstrait fils, char valeur) {
		super(frere);
		if (fils == null)
			throw new ArbreLexicographiqueException("fils ne peut pas être null");
		this.fils = fils;
		this.valeur = valeur;
	}

	@Override
	public boolean contient(String s) {
		if (s.isEmpty())
			return false;
		char c = s.charAt(0);
		if (c < valeur)
			return false;
		if (c == valeur)
			return fils.contient(s.substring(1));
		return frere.contient(s);
	}

	@Override
	public boolean prefixe(String s) {
		if (s.isEmpty())
			return true;
		char c = s.charAt(0);
		if (c < valeur)
			return false;
		if (c == valeur)
			return fils.contient(s.substring(1));
		return frere.contient(s);
	}

	@Override
	public int nbMots() {
		return fils.nbMots() + frere.nbMots();
	}

	@Override
	public NoeudAbstrait ajout(String s) {
		if (s.isEmpty())
			return new Marque(this);
		char c = s.charAt(0);
		if (c < valeur) {
			NoeudAbstrait n = new Marque(NoeudVide.getInstance());
			for (int i = s.length() - 1; i >= 0; i--)
				n = new Noeud(NoeudVide.getInstance(), n, s.charAt(i));
			n.frere = this;
			return n;
		}
		if (c == valeur) {
			fils = fils.ajout(s.substring(1));
			return this;
		}
		frere = frere.ajout(s);
		return this;
	}

	@Override
	public NoeudAbstrait suppr(String s) {
		if (s.isEmpty())
			throw new ModificationImpossibleException(
					"Suppression impossible : mot absent");
		char c = s.charAt(0);
		if (c < valeur)
			throw new ModificationImpossibleException(
					"Suppression impossible : mot absent");
		if (c == valeur) {
			fils = fils.suppr(s.substring(1));
			if (fils instanceof NoeudVide) {
				NoeudAbstrait n = frere;
				frere = null;
				return n;
			}
			return this;
		}
		frere = frere.suppr(s);
		return this;
	}

	@Override
	public String toString(String chemin) {
		String res = fils.toString(chemin + valeur);
		return res + frere.toString(chemin);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
