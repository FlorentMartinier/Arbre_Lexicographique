package arbreLexicographique;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTree;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.TextArea;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Panel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class ArbreGraphique extends ArbreLexicographique {

	JFrame frame;
	private JTextField texteRecherche;
	private ArbreLexicographique arbre = new ArbreLexicographique();
	private JTree tree;
	private JLabel lblNombreDeMots;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArbreGraphique window = new ArbreGraphique();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ArbreGraphique() {
		initialize();
		arbre.setVue(tree);
		tree.setModel(arbre);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		TextArea textArea = new TextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);

		frame = new JFrame();
		frame.setBounds(100, 100, 736, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel boutons = new JPanel();
		frame.getContentPane().add(boutons, BorderLayout.SOUTH);
		boutons.setLayout(new GridLayout(0, 1, 0, 0));

		Panel panel = new Panel();
		boutons.add(panel);

		JButton btnAjouter = new JButton("Ajouter");
		panel.add(btnAjouter);

		JButton btnSupprimer = new JButton("Supprimer");
		panel.add(btnSupprimer);

		JButton btnChercher = new JButton("Chercher");
		panel.add(btnChercher);

		JButton btnPrefixe = new JButton("Prefixe");
		panel.add(btnPrefixe);

		JLabel lblNewLabel = new JLabel("Quoi ?");
		panel.add(lblNewLabel);
		

		texteRecherche = new JTextField();
		panel.add(texteRecherche);
		texteRecherche.setColumns(10);
		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arbre.contient(texteRecherche.getText())){
					lblNewLabel_1.setText("le mot " + texteRecherche.getText() + " existe");
				} else {
					lblNewLabel_1.setText("le mot " + texteRecherche.getText() + " n'existe pas");
				}
				
			}
		});
		btnPrefixe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arbre.prefixe(texteRecherche.getText())){
					lblNewLabel_1.setText("le mot "+texteRecherche.getText()+" est prefixe");
				} else {
					lblNewLabel_1.setText("le mot "+texteRecherche.getText()+" n'est pas prefixe");
				}
				texteRecherche.setText("");
				textArea.setText(arbre.toString());
			}
		});
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arbre.suppr(texteRecherche.getText());
				texteRecherche.setText("");
				textArea.setText(arbre.toString());
				lblNombreDeMots.setText("Nombre de mots : " + arbre.size());
				lblNewLabel_1.setText("Le mot a été correctement supprimé");
			}
		});
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arbre.ajout(texteRecherche.getText());
				texteRecherche.setText("");
				textArea.setText(arbre.toString());
				lblNombreDeMots.setText("Nombre de mots : " + arbre.size());
				lblNewLabel_1.setText("Le mot a été correctement ajouté");

			}
		});

		Panel panel_1 = new Panel();
		boutons.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		lblNombreDeMots = new JLabel("Nombre de mots :  0");
		panel_1.add(lblNombreDeMots);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		boutons.add(lblNewLabel_1);

		JTabbedPane choix = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(choix, BorderLayout.CENTER);

		JPanel Arbre = new JPanel();
		choix.addTab("Arbre", null, Arbre, null);
		Arbre.setLayout(new BorderLayout(0, 0));

		tree = new JTree();
		tree.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Arbre.add(tree);

		/*
		 * for (int i=0; i<4;i++){ tree.expandRow(i); }
		 */

		JPanel Liste = new JPanel();
		Liste.setToolTipText("");
		choix.addTab("Liste", null, Liste, null);
		Liste.setLayout(new BorderLayout(0, 0));

		textArea.setText(arbre.toString());
		Liste.add(textArea);

		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu Fichier = new JMenu("Fichier");
		menuBar.add(Fichier);

		JMenuItem mntmCharger = new JMenuItem("charger");
		mntmCharger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("choosertitle");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					arbre.charge(chooser.getSelectedFile().getAbsolutePath());
					textArea.setText(arbre.toString());
					lblNombreDeMots.setText("Nombre de mots : "+arbre.size());
					lblNewLabel_1.setText("Fichier chargé");
				}

			}
		});
		Fichier.add(mntmCharger);

		JMenuItem mntmSauver = new JMenuItem("Sauver");
		mntmSauver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("choosertitle");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					arbre.sauve(chooser.getSelectedFile().getAbsolutePath());
					lblNombreDeMots.setText("Nombre de mots : " + arbre.size());
					lblNewLabel_1.setText("Fichier correctement sauvegardé");

				}
			}
		});
		Fichier.add(mntmSauver);

		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		Fichier.add(mntmQuitter);

		JMenu Aide = new JMenu("Aide");
		menuBar.add(Aide);

		JMenuItem mntmGoogleEstTon = new JMenuItem("Google est votre ami !");
		mntmGoogleEstTon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Runtime r = Runtime.getRuntime();
				try {
					r.exec("start http://www.google.com");
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		});
		Aide.add(mntmGoogleEstTon);

		
		tree.revalidate();
	}
}
