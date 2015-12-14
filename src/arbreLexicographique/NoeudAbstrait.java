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

	public abstract int nbMots(); // nombre d'éléments

	public abstract NoeudAbstrait ajout(String s);

	public abstract NoeudAbstrait suppr(String s);

	public abstract String toString(String chemin); // éléments séparés par \n

	public static void main(String[] args) {
		
		NoeudAbstrait arbre = new Marque(null);
		arbre = arbre.ajout("mot");
		arbre = arbre.suppr("");
		
		arbre = arbre.ajout("wic");
		arbre = arbre.ajout("wax");
		arbre = arbre.ajout("wifi");
		arbre = arbre.ajout("mode");
		arbre = arbre.ajout("motus");
		arbre = arbre.ajout("motul");
		arbre = arbre.ajout("wic");
		
		System.out.print(">>>>>>" + arbre + "<<<<<<<<");
		
		arbre = arbre.suppr("motul");
		arbre = arbre.suppr("mot");
		arbre = arbre.suppr("motul");
		arbre = arbre.suppr("motif");

		System.out.println();
		System.out.print(">>>>>>" + arbre + "<<<<<<<<");

	
	}

}
