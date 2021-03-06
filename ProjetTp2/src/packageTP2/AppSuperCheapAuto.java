package packageTP2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class AppSuperCheapAuto extends JFrame {
	private Ecouteur ec = new Ecouteur();
	private DefaultTableModel modele;
	//private Random rand = new Random();
	private Vector <String> nouvClients = new Vector();
	
	protected Commande cmd;
	DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	DataFormatter dataFormatter = new DataFormatter();
	
	DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	
	private JPanel panelClient;
	private JLabel jlNumMembre;
	private JLabel jlNomClient;
	private JLabel jlPointsBoni;
	protected JTextField tfNumMembre;
	protected JTextField tfNomClient;
	protected JTextField tfPointsBoni;
	private JPanel panelCommande;
	private JLabel jlCommande;
	private JLabel jlArticle;
	private JLabel jlPrixUni;
	private JLabel jlQte;
	private JTextField tfPrixUni;
	private JTextField tfQte;
	private JComboBox comboArticle;
	private JButton btnAchat;
	private JButton btnTerminer;
	private JPanel panelFacture;
	private JScrollPane scrollPane;
	private JTable table;
	private JRadioButton btnRadioComptant;
	private JRadioButton btnRadioCredit;
	private JButton btnPayer;
	private JLabel labelMontantDonne;
	private JLabel labelMontantRemis;
	private JTextField tfMontantDonne;
	private JTextField tfMontantRemis;
	private JButton btnAnnuNouvComm;
	
	private InputStream inp;
	private InputStream inp2;
	private XSSFWorkbook classeurClients;
	private XSSFSheet feuilleClients;
	private XSSFWorkbook classeurProduits;
	private XSSFSheet feuilleProduits;
	private JMenuBar menuBar;
	private JMenu options;
	private JMenuItem nouvClient;
	private JMenuItem fermeture;

	/*********************************
	 *             Main              *
	 *********************************/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppSuperCheapAuto frame = new AppSuperCheapAuto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/********************************
	 *        Constructeur          
	 * @throws IOException 
	 * @throws InvalidFormatException *
	 ********************************/
	public AppSuperCheapAuto() throws InvalidFormatException, IOException {
		setBounds(100, 100, 497, 569);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panelClient = new JPanel();
		panelClient.setBackground(Color.GREEN);
		panelClient.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelClient.setBounds(10, 11, 461, 92);
		getContentPane().add(panelClient);
		panelClient.setLayout(null);
		
		jlNumMembre = new JLabel("Num\u00E9ro de la carte de membre:");
		jlNumMembre.setFont(new Font("Dialog", Font.BOLD, 13));
		jlNumMembre.setBounds(10, 11, 246, 14);
		panelClient.add(jlNumMembre);
		
		jlNomClient = new JLabel("Nom du client:");
		jlNomClient.setFont(new Font("Dialog", Font.BOLD, 13));
		jlNomClient.setBounds(10, 34, 246, 14);
		panelClient.add(jlNomClient);
		
		jlPointsBoni = new JLabel("Nombre de points boni \u00E0 ce jour:");
		jlPointsBoni.setFont(new Font("Dialog", Font.BOLD, 13));
		jlPointsBoni.setBounds(10, 59, 246, 14);
		panelClient.add(jlPointsBoni);
		
		tfNumMembre = new JTextField();
		tfNumMembre.setBounds(365, 9, 86, 20);
		panelClient.add(tfNumMembre);
		tfNumMembre.setColumns(10);
		
		tfNomClient = new JTextField();
		tfNomClient.setColumns(10);
		tfNomClient.setBounds(268, 32, 183, 20);
		tfNomClient.setEditable(false);
		panelClient.add(tfNomClient);
		
		tfPointsBoni = new JTextField();
		tfPointsBoni.setColumns(10);
		tfPointsBoni.setBounds(365, 57, 86, 20);
		tfPointsBoni.setEditable(false);
		panelClient.add(tfPointsBoni);
		
		panelCommande = new JPanel();
		panelCommande.setBackground(Color.ORANGE);
		panelCommande.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelCommande.setBounds(10, 114, 304, 114);
		getContentPane().add(panelCommande);
		panelCommande.setLayout(null);
		
		jlCommande = new JLabel("Commande");
		jlCommande.setFont(new Font("Dialog", Font.BOLD, 13));
		jlCommande.setBounds(10, 11, 85, 14);
		panelCommande.add(jlCommande);
		
		jlArticle = new JLabel("Article");
		jlArticle.setFont(new Font("Dialog", Font.BOLD, 12));
		jlArticle.setBounds(10, 33, 58, 14);
		panelCommande.add(jlArticle);
		
		jlPrixUni = new JLabel("Prix unitaire:");
		jlPrixUni.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		jlPrixUni.setBounds(10, 59, 101, 16);
		panelCommande.add(jlPrixUni);
		
		jlQte = new JLabel("Qté en Stock:");
		jlQte.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		jlQte.setBounds(197, 59, 101, 16);
		panelCommande.add(jlQte);
		
		tfPrixUni = new JTextField();
		tfPrixUni.setBounds(6, 82, 85, 26);
		panelCommande.add(tfPrixUni);
		tfPrixUni.setColumns(10);
		
		tfQte = new JTextField();
		tfQte.setColumns(10);
		tfQte.setBounds(207, 82, 85, 26);
		panelCommande.add(tfQte);
		
		comboArticle = new JComboBox();
		comboArticle.setBounds(71, 28, 221, 27);
		panelCommande.add(comboArticle);
		
		btnAchat = new JButton("Achat");
		btnAchat.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnAchat.setBounds(324, 115, 147, 57);
		getContentPane().add(btnAchat);
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnTerminer.setBounds(326, 171, 147, 57);
		getContentPane().add(btnTerminer);
		
		panelFacture = new JPanel();
		panelFacture.setBackground(Color.RED);
		panelFacture.setBounds(10, 240, 461, 258);
		getContentPane().add(panelFacture);
		panelFacture.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 449, 114);
		panelFacture.add(scrollPane);
		
		modele = new DefaultTableModel();
		table = new JTable(modele);
		scrollPane.setViewportView(table);
		
		btnRadioComptant = new JRadioButton("Paiement comptant");
		btnRadioComptant.setBackground(Color.RED);
		btnRadioComptant.setForeground(Color.WHITE);
		btnRadioComptant.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnRadioComptant.setBounds(6, 145, 163, 23);
		btnRadioComptant.setSelected(true);
		panelFacture.add(btnRadioComptant);
		
		btnRadioCredit = new JRadioButton("Paiement crédit");
		btnRadioCredit.setBackground(Color.RED);
		btnRadioCredit.setForeground(Color.WHITE);
		btnRadioCredit.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnRadioCredit.setBounds(181, 145, 138, 23);
		panelFacture.add(btnRadioCredit);
		
		ButtonGroup paymentButtons = new ButtonGroup();
		paymentButtons.add(btnRadioComptant);
		paymentButtons.add(btnRadioCredit);
		
		btnPayer = new JButton("Payer");
		btnPayer.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnPayer.setBounds(322, 132, 133, 68);
		panelFacture.add(btnPayer);
		
		labelMontantDonne = new JLabel("Montant donnée:");
		labelMontantDonne.setForeground(Color.WHITE);
		labelMontantDonne.setFont(new Font("Dialog", Font.BOLD, 12));
		labelMontantDonne.setBounds(17, 196, 115, 14);
		panelFacture.add(labelMontantDonne);
		
		labelMontantRemis = new JLabel("Montant remis:");
		labelMontantRemis.setForeground(Color.WHITE);
		labelMontantRemis.setFont(new Font("Dialog", Font.BOLD, 12));
		labelMontantRemis.setBounds(17, 230, 115, 14);
		panelFacture.add(labelMontantRemis);
		
		tfMontantDonne = new JTextField();
		tfMontantDonne.setColumns(10);
		tfMontantDonne.setBounds(220, 190, 99, 26);
		panelFacture.add(tfMontantDonne);
		
		tfMontantRemis = new JTextField();
		tfMontantRemis.setColumns(10);
		tfMontantRemis.setBounds(220, 224, 99, 26);
		panelFacture.add(tfMontantRemis);
		
		btnAnnuNouvComm = new JButton("<html>\n<p>Annuler Commande /</p>\n<p>Nouvelle Commande</p>\n</html>");
		btnAnnuNouvComm.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		btnAnnuNouvComm.setBounds(322, 202, 133, 48);
		panelFacture.add(btnAnnuNouvComm);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		options = new JMenu("Options");
		menuBar.add(options);
		
		nouvClient = new JMenuItem("Nouveau client");
		options.add(nouvClient);
		
		fermeture = new JMenuItem("Fermeture de session");
		options.add(fermeture);
		
		modele.addColumn("Produit");
		modele.addColumn("Quantit�");
		modele.addColumn("Prix");
		renderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(1).setCellRenderer(renderer);
		table.getColumnModel().getColumn(2).setCellRenderer(renderer);
		
		//Ecouteurs
		tfNumMembre.addActionListener(ec);
		nouvClient.addActionListener(ec);
		comboArticle.addActionListener(ec);
		btnAchat.addActionListener(ec);
		btnTerminer.addActionListener(ec);
		btnRadioComptant.addActionListener(ec);
		btnRadioCredit.addActionListener(ec);
		btnPayer.addActionListener(ec);
		btnAnnuNouvComm.addActionListener(ec);
		fermeture.addActionListener(ec);
		
		//Lecture des fichiers Excel
		inp = new FileInputStream ( "Clients.xlsx");
        classeurClients = ( XSSFWorkbook ) WorkbookFactory.create(inp);
        feuilleClients = classeurClients.getSheetAt(0);
        
        inp2 = new FileInputStream ( "Produits.xlsx");
        classeurProduits = ( XSSFWorkbook ) WorkbookFactory.create(inp2);
        feuilleProduits = classeurProduits.getSheetAt(0);
        
        
        //Loop permettant de lire chaque cellule de chaque ligne
        //Fichier "Clients"
        for (Row rangee: feuilleClients) {
        	if (rangee.getRowNum() > 0) {
        		Cell cellule = rangee.getCell(0);
        		String numero = dataFormatter.formatCellValue(cellule);
        		
            	cellule = rangee.getCell(1);
            	String nom = dataFormatter.formatCellValue(cellule);
            	
            	cellule = rangee.getCell(2);
            	String p = dataFormatter.formatCellValue(cellule);
            	int points = Integer.parseInt(p);
            	
            	cellule = rangee.getCell(3);
            	double solde = cellule.getNumericCellValue();
   
            	Client c = new Client(numero, nom, points, solde);
            	EnsembleClients.ajouterClient(c);
            	
        	}
        }
        
        //Fichier "Produits"
        for (Row rangee: feuilleProduits) {
        	if (rangee.getRowNum() > 0) {
        		Cell cellule = rangee.getCell(0);
        		String c = dataFormatter.formatCellValue(cellule);
        		int code = Integer.parseInt(c);
        		
            	cellule = rangee.getCell(1);
            	String nom = dataFormatter.formatCellValue(cellule);
            	
            	cellule = rangee.getCell(2);
            	String q = dataFormatter.formatCellValue(cellule);
            	int quantite = Integer.parseInt(q);
            	
            	cellule = rangee.getCell(3);
            	double prix = cellule.getNumericCellValue();
            	

            	cellule = rangee.getCell(4);
            	String pts = dataFormatter.formatCellValue(cellule);
            	int points = Integer.parseInt(pts);
   
            	Produit p = new Produit(code, nom, quantite, prix, points);
            	Inventaire.ajouterProduit(p);
            	
        	}
        }
        
        //Parcours de la hashtable ListeProduits afin de remplir le JComboBox
        Set<String> NomProduits=Inventaire.getListe().keySet();
		for(String produitsClef:NomProduits)
		{ 
			comboArticle.addItem(produitsClef);
		}

	}
	
	public class Ecouteur implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//1- Recherche d'un client
			if (e.getSource() == tfNumMembre) {
				String num = tfNumMembre.getText();
				
				//Parcours de la HashTable. Si le client existe, on remplit les champs appropriés
				//Si le client n'existe pas, message d'erreur.
				if (EnsembleClients.getListe().containsKey(num)) {
					tfNomClient.setText(EnsembleClients.getListe().get(num).getNom());
					tfPointsBoni.setText(Integer.toString(EnsembleClients.getListe().get(num).getNbPointsAcc()));
					
					//Création d'un objet commande lorsque le client existe
					cmd = new Commande (tfNumMembre.getText());
				}
						
				else {
					JOptionPane.showMessageDialog(AppSuperCheapAuto.this, "Ce client n'existe pas.");
					tfNumMembre.setText("");
					tfNomClient.setText("");
					tfPointsBoni.setText("");
				}
			}
			
			//2- Création d'un nouveau client
			else if (e.getSource() == nouvClient) {
				//Création d'un nouvelle Fenêtre JDialog pour afin de saisir un nouveau client
				NouveauClient nc = new NouveauClient(AppSuperCheapAuto.this);
				nc.setVisible(true);
				
				//Générer un numéro aléatoire de 6 chiffres
				//Vérifier si ce numéro n'est pas déjà attribué à un client. 
				//Si ce n'est pas le cas, remplir le champ approprié de la fenêtre JDialog
				//Ajouter ce nouveau client au vecteur de nouveau client
				boolean numExiste = true;
				String numGen;
				while (numExiste) {
					int n = 100000 + new Random().nextInt(900000);
					numGen = Integer.toString(n);
		
					if (!EnsembleClients.getListe().containsKey(numGen)) {
						numExiste = false;
					}
					
					nc.tfNumero.setText(numGen);
					nouvClients.addElement(numGen);
				}
				
				
			}
			
			//3- Sélection d'un article du JComboBox
			else if (e.getSource() == comboArticle) {
				String nom = (String) comboArticle.getSelectedItem();
				
				tfPrixUni.setText(Double.toString(Inventaire.getListe().get(nom).getPrix()));
				tfQte.setText(Integer.toString(Inventaire.getListe().get(nom).getQteStock()));
			}
			
			//4- Ajout de l'article sélectionné à la JTable
			else if (e.getSource() == btnAchat) {
				//Vérifier s'il y a un client de sélectionné (S'il y a un objet commande, alors un client est sélectionné)
				//Le cas échéant, un objet item est créé. Sinon, message d'erreur
				if (cmd != null) {
					String nom = (String) comboArticle.getSelectedItem();
					Item i = new Item(nom, 1, cmd.getNumero());
					
					//Vérifier si le produit est en stock en quantité suffisante avec la méthode "modifieQteStock"
					//Le cas échéant, cette méthode ajuste le stock du produit. Sinon, message d'erreur
					//Ajouter une ligne à la JTable avec le produit et remplir le champ de texte approprié
					//Ajouter le produit à la commande
					if (Inventaire.getProduit(nom).modifierQteStock(1)) {
						
						double prix = Inventaire.getListe().get(nom).getPrix();
						
						modele.addRow(new Object[] {nom, 1, prix});
						tfQte.setText(Integer.toString(Inventaire.getListe().get(nom).getQteStock()));
						
						cmd.ajouterItem(i);
					}
					else {
						JOptionPane.showMessageDialog(AppSuperCheapAuto.this, "En rupture de stock!");
					}
				}
				else {
					JOptionPane.showMessageDialog(AppSuperCheapAuto.this, "Aucun client associé à cette commande.");
				}
				
			}
			
			// 5- Finaliser la commande
			else if (e.getSource() == btnTerminer) {
				//Calculer le total et les taxes de la commande 
				//Insérer de nouvelles lignes dans la JTable pour laffichage
				double st = cmd.calculerSousTotal();
				double tps = cmd.calculerTPS();
				double tvq = cmd.calculerTVQ();
				double total = cmd.calculerGrandTotal();
				
				modele.addRow(new Vector());
				modele.addRow(new Vector());
				modele.setValueAt("Sous-Total:", modele.getRowCount()-1, 1);
				modele.setValueAt(decimalFormat.format(st), modele.getRowCount()-1, 2);
				
				modele.addRow(new Vector());
				modele.setValueAt("TPS:", modele.getRowCount()-1, 1);
				modele.setValueAt(decimalFormat.format(tps), modele.getRowCount()-1, 2);
				
				modele.addRow(new Vector());
				modele.setValueAt("TVQ:", modele.getRowCount()-1, 1);
				modele.setValueAt(decimalFormat.format(tvq), modele.getRowCount()-1, 2);
				
				modele.addRow(new Vector());
				modele.setValueAt("Total:", modele.getRowCount()-1, 1);
				modele.setValueAt(decimalFormat.format(total), modele.getRowCount()-1, 2);
				
			}
			
			//6- Déterminer si le paiement est comptant ou crédit 
			else if (e.getSource() == btnRadioComptant || e.getSource() == btnRadioCredit) {
				//Si l'option "crédit" est sélectionnée, les champs de texte "montants" ne sont pas éditables.
				if (btnRadioCredit.isSelected()) {
					tfMontantDonne.setEditable(false);
					tfMontantRemis.setEditable(false);
				}
				else if (btnRadioComptant.isSelected()) {
					tfMontantDonne.setEditable(true);
					tfMontantRemis.setEditable(true);
				}
			}
			
			//7- Payer la commande
			else if(e.getSource() == btnPayer) {
				//Si le bouton "comptant" est s�lectionn�, on s'assure que le champ "montantDonne" est ad�quatement compl�t�
				//S'assurer que le montant donn� est suffisant gr�ce � la m�thode "assezArgent". La m�thode "paieCommandeComptant" calcule �galement les points boni et met la variable "estPaye" � true
				//Le cas �ch�ant, remplir le champ de texte "montantRemis". Sinon, message d'erreur
				if (btnRadioComptant.isSelected()) {
					if (tfMontantDonne.getText() != null && !(tfMontantDonne.getText().trim().isEmpty())) {
						
						double montantRecu = Double.parseDouble(tfMontantDonne.getText());
						
						if (EnsembleClients.getClient(tfNumMembre.getText()).assezArgent(cmd, montantRecu)) {
							
							double montantRemis = EnsembleClients.getClient(tfNumMembre.getText()).paieCommandeComptant(cmd, montantRecu);
							tfMontantRemis.setText(Double.toString(montantRemis));
							
						}
						else {
							JOptionPane.showMessageDialog(AppSuperCheapAuto.this, "Montant donné insuffisant.");
						}
					}
				}
				//Si le bouton radio "cr�dit" est s�lectionn�, v�rification du solde de cr�dit suffisant gr�ce � la m�thode "paieCommandeCredit"
				else if (btnRadioCredit.isSelected()) {
					//La m�thode "paieCommandeCredit" calcule �galement les points boni et le solde et met la variable "estPaye" � true
					//Faire un message "Cr�dit suffisant" si suffisament de cr�dit. Sinon, message d'erreur 
					if (EnsembleClients.getClient(tfNumMembre.getText()).paieCommandeCredit(cmd)) {
						JOptionPane.showMessageDialog(AppSuperCheapAuto.this, "Crédit suffisant. Merci!");
					}
					else {
						JOptionPane.showMessageDialog(AppSuperCheapAuto.this, "Crédit insuffisant.");
					}
				}
			}
			
			//8- Annuler commande / Nouvelle commande
			else if (e.getSource() == btnAnnuNouvComm) {
				//Pour "annuler commande" ou "nouvelle commande":
				//Effacer tous les champs concern�s de m�me que ce qu'il y a dans la Jtable
				//Remet, en s�lection par d�faut, le bouton "comptant"
				tfNumMembre.setText("");
				tfNomClient.setText("");
				tfPointsBoni.setText("");
				
				for( int i = modele.getRowCount() - 1; i >= 0; i-- ) {
			        modele.removeRow(i);
			    }
				
				btnRadioComptant.setSelected(true);
				
				tfMontantDonne.setText("");
				tfMontantRemis.setText("");
				tfMontantDonne.setEditable(true);
				tfMontantRemis.setEditable(true);
				
				//Pour "annuler commande" seulement:
				//Remet les quantit� achet�es (mais non pay�es) en stock
				//Pour ce faire, on utilise encore la m�thode "modifierQteStock". Or, on lui passe une quantit� n�gative en param�tre 
				//Comme cette m�thode soustrait la quantit� pass�e en param�tre des stocks, en passant une quantit� n�gative, on ajoute au stock 
				if (!cmd.estPayee()) {
					for (int i = 0; i < cmd.getItems().size(); i++) {
						Inventaire.getProduit(cmd.getItems().elementAt(i).getNomProduit()).modifierQteStock(-(cmd.getItems().elementAt(i).getQte()));
					}
					
				}
			}
			//9- Fermeture de la session
			else if (e.getSource() == fermeture) {
				
				//�criture des fichiers Excel
				//Fichier "Produits.xlsx"
				//Uniquement besoin de modifier les cellules de quantit� en stock
				for (Row rangee: feuilleProduits) {
		        	if (rangee.getRowNum() > 0) {
		        		Cell cellule = rangee.getCell(1);
		            	String nom = dataFormatter.formatCellValue(cellule);
		            	
		        		int qte = Inventaire.getProduit(nom).getQteStock();
		        		cellule = rangee.getCell(2);
		        		
		        		cellule.setCellValue(qte);
		        		
		        	}
				}
				
				//Fichier "Clients.xlsx"
				//Modification des cellules Points accumulul�s et solde
				for (Row rangee: feuilleClients) { 
				  
					if (rangee.getRowNum() > 0) { 
						Cell cellule= rangee.getCell(0); 
						String numero = dataFormatter.formatCellValue(cellule);
			  
						int pts = EnsembleClients.getClient(numero).getNbPointsAcc(); 
						cellule = rangee.getCell(2); cellule.setCellValue(pts);
			  
						double solde = EnsembleClients.getClient(numero).getSoldeCarteCredit();
						cellule = rangee.getCell(3); cellule.setCellValue(solde);
			  
					} 
				}
				//Ajout des nouveaux clients au fichier "Clients..xlsx"
				//Les nouveaux clients ont �t� ajout�s au fur et � mesure au vecteur "nouvClients"
				if (nouvClients.size() > 0) {
					for (int i = 0; i < nouvClients.size(); i++) {
						int rowNum = feuilleClients.getLastRowNum();
						Row  nouvelleRangee = feuilleClients.createRow(++rowNum);
						Cell nouvelleCellule0 = nouvelleRangee.createCell(0);
						Cell nouvelleCellule1 = nouvelleRangee.createCell(1);
						Cell nouvelleCellule2 = nouvelleRangee.createCell(2);
						Cell nouvelleCellule3 = nouvelleRangee.createCell(3);
						
						int num = Integer.parseInt(EnsembleClients.getClient(nouvClients.elementAt(i)).getNoCarte());
						nouvelleCellule0.setCellValue(num);
						
						String nom = EnsembleClients.getClient(nouvClients.elementAt(i)).getNom();
						nouvelleCellule1.setCellValue(nom);
						
						int pts = EnsembleClients.getClient(nouvClients.elementAt(i)).getNbPointsAcc();
						nouvelleCellule2.setCellValue(pts);
						
						double solde = EnsembleClients.getClient(nouvClients.elementAt(i)).getSoldeCarteCredit();
						nouvelleCellule3.setCellValue(solde);
					}
					
					
				}
				
				try {
					OutputStream out = new FileOutputStream ( "Clients.xlsx");
					classeurClients.write(out);
					
					OutputStream out2 = new FileOutputStream ( "Produits.xlsx");
					classeurProduits.write(out2);
					
					inp.close();
					inp2.close();
					
					out.close();
					out2.close();
				}
				catch(Exception f) {
					f.printStackTrace();
				}
				
				System.exit(0);
			}
				
		}
		
	}
}
