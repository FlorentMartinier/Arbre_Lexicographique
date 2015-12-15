package arbreLexicographique;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class Marque extends NoeudAbstrait {

	public Marque(NoeudAbstrait frere) {
		super(frere);
	}

	@Override
	public boolean contient(String s) {
		return (s.isEmpty() || frere.contient(s));
	}

	@Override
	public boolean prefixe(String s) {
		return (s.isEmpty() || frere.prefixe(s));
	}

	@Override
	public int nbMots() {
		return 1 + frere.nbMots();
	}

	@Override
	public NoeudAbstrait ajout(String s) {
		if (s.isEmpty())
			throw new ModificationImpossibleException("Ajout impossible : mot deja present");
		frere = frere.ajout(s);
		return this;
	}

	@Override
	public NoeudAbstrait suppr(String s) {
		if (s.isEmpty()) {
			NoeudAbstrait n = frere;
			frere = null;
			return n;
		}
		frere = frere.suppr(s);
		return this;
	}

	@Override
	public String toString(String chemin) {
		return chemin + "\n" + frere.toString(chemin);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
