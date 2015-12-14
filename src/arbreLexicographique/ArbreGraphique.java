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
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import java.awt.TextArea;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArbreGraphique extends ArbreLexicographique {

	JFrame frame;
	private JTextField textField;
	private ArbreLexicographique arbre;
	private JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArbreGraphique window = new ArbreGraphique(new ArbreLexicographique());
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
	public ArbreGraphique(ArbreLexicographique arbre) {
		initialize(arbre);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArbreLexicographique arbre) {
		this.arbre = arbre;
		
		TextArea textArea = new TextArea();

		frame = new JFrame();
		frame.setBounds(100, 100, 636, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel boutons = new JPanel();
		frame.getContentPane().add(boutons, BorderLayout.SOUTH);
		boutons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arbre.ajout(textField.getText());
				textField.setText("");
				textArea.setText(arbre.toString());
			}
		});
		boutons.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arbre.suppr(textField.getText());
				textField.setText("");
				textArea.setText(arbre.toString());
			}
		});
		boutons.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Chercher");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arbre.contient(textField.getText());
				textField.setText("");
				textArea.setText(arbre.toString());
			}
		});
		boutons.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Prefixe");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arbre.prefixe(textField.getText());
				textField.setText("");
				textArea.setText(arbre.toString());
			}
		});
		boutons.add(btnNewButton_3);

		JLabel lblNewLabel = new JLabel("Quoi ?");
		boutons.add(lblNewLabel);

		textField = new JTextField();
		boutons.add(textField);
		textField.setColumns(10);

		JTabbedPane choix = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(choix, BorderLayout.CENTER);

		JPanel Arbre = new JPanel();
		choix.addTab("Arbre", null, Arbre, null);

		tree = new JTree();
		Arbre.add(tree);

		JPanel Liste = new JPanel();
		Liste.setToolTipText("");
		choix.addTab("Liste", null, Liste, null);

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
		
		this.arbre = arbre;
		arbre.setVue(tree);
		tree.setModel(arbre);
	}
}
