package packageTP2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class NouveauClient extends JDialog {

	private JLabel jlNom;
	private JLabel jlNumero;
	protected JTextField tfNumero;
	private JTextField tfNom;
	private JButton ok;
	private JButton annuler;
	
	private AppSuperCheapAuto a;
	
	private Ecouteur ec = new Ecouteur();
	

	/***********************************
	 *             Main                *
	 ***********************************/
	/*public static void main(String[] args) {
		try {
			NouveauClient dialog = new NouveauClient();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/************************************
	 *          Constructeur            *
	 ************************************/
	public NouveauClient(AppSuperCheapAuto app) {
		this.a = app;
		setTitle("Inscription d'un nouveau client");
		setBounds(100, 100, 459, 300);
		getContentPane().setLayout(null);
		
		
		jlNom = new JLabel("Nom du client:");
		jlNom.setFont(new Font("Dialog", Font.BOLD, 14));
		jlNom.setBounds(29, 45, 118, 16);
		getContentPane().add(jlNom);
		
		jlNumero = new JLabel("Numéro de carte de membre généré:");
		jlNumero.setFont(new Font("Dialog", Font.BOLD, 14));
		jlNumero.setBounds(29, 103, 273, 16);
		getContentPane().add(jlNumero);
		
		tfNumero = new JTextField();
		tfNumero.setBounds(333, 99, 100, 26);
		tfNumero.setEditable(false);
		getContentPane().add(tfNumero);
		tfNumero.setColumns(10);
		
		tfNom = new JTextField();
		tfNom.setColumns(10);
		tfNom.setBounds(288, 41, 145, 26);
		getContentPane().add(tfNom);
		
		ok = new JButton("OK");
		ok.setBounds(72, 180, 117, 71);
		getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		annuler.setBounds(264, 180, 117, 71);
		getContentPane().add(annuler);
		
		annuler.addActionListener(ec);
		ok.addActionListener(ec);
		
		
	}
	
	public class Ecouteur implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == annuler) {
				tfNumero.setText(null);
				tfNom.setText(null);
				NouveauClient.this.dispose();
			}
			else if (e.getSource() == ok) {
				if (tfNom.getText() != null && !(tfNom.getText().trim().isEmpty())) {
					Client c = new Client(tfNumero.getText(), tfNom.getText(), 0, 0);
					EnsembleClients.ajouterClient(c);
					a.tfNumMembre.setText(null);
					a.tfNomClient.setText(null);
					a.tfPointsBoni.setText(null);
					a.tfNumMembre.setText(tfNumero.getText());
					a.tfNomClient.setText(tfNom.getText());
					a.tfPointsBoni.setText("0");
					NouveauClient.this.dispose();
					System.out.println("ok!");
				}
				else {
					JOptionPane.showMessageDialog(NouveauClient.this, "Vous devez saisir un nom de client.");
				}
			}
			
		}
		
	}
}
