package arbreLexicographique;

import java.util.Enumeration;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;

public aspect Visualisation {
	declare parents : ArbreLexicographique implements TreeModel;
	declare parents : NoeudAbstrait implements TreeNode;

	private DefaultTreeModel ArbreLexicographique.treeModel;
	private DefaultMutableTreeNode NoeudAbstrait.treeNode;
	private JTree ArbreLexicographique.vue;

	// setVue
	public void ArbreLexicographique.setVue(JTree jt) {
		this.vue = jt;
	}

	//-------------------------------pointcuts -------------------------------

	// modification des attributs
	// dans un call, this est le this qui fait appel à la méthode
	// dans un execution, this est le this qui exécute le code

	// construction d'un l'arbre
	pointcut initArbre(ArbreLexicographique a) : target(a) && execution(ArbreLexicographique.new()); 

	// ajout ou suppression dans l'arbre
	pointcut modifArbre(ArbreLexicographique a) : target(a) && ( 
			execution(boolean ArbreLexicographique.ajout(String)) || execution(boolean ArbreLexicographique.suppr(String)));

	// construction d'un NoeudAbstrait
	pointcut initNoeudAbstrait(NoeudAbstrait n) : target(n) && execution(NoeudAbstrait.new(NoeudAbstrait)); 

	// construction d'un Noeud
	pointcut initNoeud(Noeud n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : target(n) && args(frere, fils, val) && execution(Noeud.new(NoeudAbstrait, NoeudAbstrait, char));

	// modification de l'attribut fils
	pointcut modifFils(Noeud n, NoeudAbstrait n1) : this(n) && target(n1) && set(NoeudAbstrait Noeud.fils);
		
	// modification d'un frere
	pointcut modifFrere(Noeud n, NoeudAbstrait n1) : this(n) && target(n1) && set(NoeudAbstrait Noeud.frere);
	
	// ----------------------------advices--------------------------------------
	
	// le treeModel est initialisé après l'arbre
	after(ArbreLexicographique a) : initArbre(a){
		a.treeModel = new DefaultTreeModel(a.entree.treeNode);
	}

	// modification de la racine après ajout ou suppression
	after(ArbreLexicographique a) : modifArbre(a){
		a.treeModel.setRoot(a.entree.treeNode);
	}

	// création d'un treeNode après l'instanciation d'un noeud abstrait
	after(NoeudAbstrait n) : initNoeudAbstrait(n){
		n.treeNode = new DefaultMutableTreeNode();
	}

	// création d'un treeNode pour chaque Noeud instancié
	after(Noeud n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : initNoeud(n, frere,  fils,  val){
		n.treeNode = new DefaultMutableTreeNode(val);
		n.treeNode.add(fils.treeNode);
		n.treeNode.add(frere.treeNode);
	}

	// ajout des fils aux treeNodes
	after(Noeud n, NoeudAbstrait n1) : modifFils(n, n1){
		n.treeNode.add(n.fils.treeNode);
	}
	
	// ajout des freres aux treeNodes
	after(Noeud n, NoeudAbstrait n1) : modifFrere(n, n1){
		n.treeNode.add(n.frere.treeNode);
		System.out.println("coucou");
	}

	// ------------------------------------ interface TreeModel
	// :-----------------------------------------
	public Object ArbreLexicographique.getRoot() {
		return treeModel.getRoot();
	}

	public int ArbreLexicographique.getChildCount(Object parent) {
		return treeModel.getChildCount(parent);
	}

	public boolean ArbreLexicographique.isLeaf(Object node) {
		return treeModel.getChildCount(node) < 1;
	}

	public Object ArbreLexicographique.getChild(Object parent, int index) {
		return treeModel.getChild(parent, index);
	}

	public int ArbreLexicographique.getIndexOfChild(Object parent, Object child) {
		String text = child.toString();
		char lastCharacter = text.charAt(text.length() - 1);
		return lastCharacter - '0';
	}

	public void ArbreLexicographique.valueForPathChanged(TreePath path, Object newValue) {
		treeModel.valueForPathChanged(path, newValue);
	}

	public void ArbreLexicographique.addTreeModelListener(TreeModelListener l) {
		treeModel.addTreeModelListener(l);
	}

	public void ArbreLexicographique.removeTreeModelListener(TreeModelListener l) {
		treeModel.addTreeModelListener(l);
	}

	// ---------------------------------------interface TreeNode
	// :--------------------------------------
	public Enumeration NoeudAbstrait.children() {
		return treeNode.children();
	}

	public boolean NoeudAbstrait.getAllowsChildren() {
		return treeNode.getAllowsChildren();
	}

	public TreeNode NoeudAbstrait.getChildAt(int index) {
		return treeNode.getChildAt(index);
	}

	public int NoeudAbstrait.getChildCount() {
		return treeNode.getChildCount();
	}

	public int NoeudAbstrait.getIndex(TreeNode child) {
		return treeNode.getIndex(child);
	}

	public TreeNode NoeudAbstrait.getParent() {
		return treeNode.getParent();
	}

	public boolean NoeudAbstrait.isLeaf() {
		return treeNode.isLeaf();
	}
}