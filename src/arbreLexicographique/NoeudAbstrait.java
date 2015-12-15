package arbreLexicographique;

public abstract class NoeudAbstrait {

	protected NoeudAbstrait frere;

	public NoeudAbstrait(NoeudAbstrait frere) {
		if (frere == null && !(this instanceof NoeudVide))
			throw new ArbreLexicographiqueException("frere null interdit");
		this.frere = frere;
	}

	public abstract boolean contient(String s);

	public abstract boolean prefixe(String s);

	public abstract int nbMots();

	public abstract NoeudAbstrait ajout(String s);

	public abstract NoeudAbstrait suppr(String s);

	public abstract String toString(String chemin);

	public static void main(String[] args) {	
	}

}
