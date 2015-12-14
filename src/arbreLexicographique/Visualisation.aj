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

	// -----------------------------------pointcuts et advices :--------------------------------------

	// initialisation de la racine
	pointcut initArbreLexico(ArbreLexicographique a) : target(a) && execution(ArbreLexicographique.new());
	after(ArbreLexicographique a) : initArbreLexico(a){
		a.treeModel = new DefaultTreeModel(a.entree.treeNode);
	}
	
	//Modification de la racine lors de l'ajout et de la suppression
	pointcut modifArbreLexico(ArbreLexicographique a) : target(a) && (
		execution(boolean ArbreLexicographique.ajout(String)) || 
		execution(boolean ArbreLexicographique.suppr(String))
	);
	after(ArbreLexicographique a) : modifArbreLexico(a){
		a.treeModel.setRoot(a.entree.treeNode);
	}
	
	//initialisation d'un noeud abstrait
	pointcut initNoeudAbstrait(NoeudAbstrait n) : target(n) && execution(NoeudAbstrait.new(NoeudAbstrait));
	before(NoeudAbstrait n) : initNoeudAbstrait(n){
		n.treeNode = new DefaultMutableTreeNode();
	}
	
	//ajout de lettres
	pointcut ajoutLettre(Noeud n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : target(n) && args(frere, fils, val) && execution(Noeud.new(NoeudAbstrait, NoeudAbstrait, char));
	after(Noeud n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : ajoutLettre(n, frere,  fils,  val){
		n.treeNode = new DefaultMutableTreeNode(val);
		n.treeNode.add(fils.treeNode);		
	}
	
	
	
	after(Noeud n, String s) returning(NoeudAbstrait n1): target(n) && args(s) && execution(NoeudAbstrait Noeud.ajout(String)){
		System.out.println();
	}
	
	pointcut modifFils(Noeud n, NoeudAbstrait n1) : this(n) && target(n1) && set(NoeudAbstrait Noeud.fils);
	after(Noeud n, NoeudAbstrait n1) : modifFils(n, n1){
		System.out.println();
	}
	
	pointcut ajoutsurMarque(Marque m, String s) : target(m) && args(s) && execution(NoeudAbstrait Marque.ajout(String));
	after(Marque m, String s) : ajoutsurMarque(m, s){
		
	}
	
	private boolean contient(TreeNode treeNode, Noeud n){
		Enumeration e = treeNode.children();
		MutableTreeNode m;
		while(e.hasMoreElements()){
			m = (MutableTreeNode) e.nextElement();
			if(m.equals(n)) return true;
		}
		return false;
	}

	//------------------------------------ interface TreeModel :-----------------------------------------
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

	// ---------------------------------------interface TreeNode :--------------------------------------
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