package packageTP2;

public class Client 
{

private String noCarteMembre;
private String nom;
private int nbPointsAcc;
private double soldeCarteCredit;

  public Client(String noCarteMembre, String nom, int nbPointsAcc, double soldeCarteCredit)
  {
  this.noCarteMembre=noCarteMembre;
  this.nbPointsAcc=nbPointsAcc;
  this.nom = nom;
  this.soldeCarteCredit = soldeCarteCredit;
  
  }

  public String getNoCarte()
  {
  return noCarteMembre;
  }
  
  public String getNom()
  {
  return nom;
  }
  
  public int getNbPointsAcc()
  {
  return nbPointsAcc;
  }
  
  public void modifierPoints ( int nbPointsSupp )
  {
  this.nbPointsAcc+= nbPointsSupp;
  }

  //Méthode booléenne qui détermine si le montant donné est supérieur ou égal à au montant de la commande
  public boolean assezArgent ( Commande c, double montant )
  {
   double total = c.calculerGrandTotal();
   if ( montant  >= total )
    return true;
  else 
    return false;
  }
  
  public int arrondirCent ( double montant )
  {
	  double montantEnCentsAvecPoussieres = montant * 100;
	  int montantEnCents = ( int ) Math.round(montantEnCentsAvecPoussieres);
	  return montantEnCents;
  }
  
  //La méthode "paieCommandeComptant" calcule également les points boni et met la variable "estPaye" à true
  public double paieCommandeComptant ( Commande c, double montant )
  {
  double total = c.calculerGrandTotal();
  System.out.println(total);
  double change = montant- total;
  int cents=0;
  System.out.println ( change);
  //multiplie par 100 pour avoir cents 
  /*
  change = change*100;
  System.out.println ( change);
  //arrondit
  cents = (int)Math.round(change);
  */
  cents = arrondirCent(change);
  System.out.println ( cents);
  int reste = cents % 5;
  System.out.println(reste);
  if ( reste >=3)
	  cents = cents + ( 5-reste );
  else
	  cents = cents - reste;
  System.out.println(cents);
  //remettre en dollars
  change = cents/100.0;
  System.out.println(change);
  int nbPoints = c.calculerPointsBonis();
  if ( change > 0)
  {
      modifierPoints(nbPoints);
      c.devientPayee(true);
  }

  return change;
  }
  
  //vérification du solde de crédit suffisant grâce à la méthode "paieCommandeCredit"
  //La méthode "paieCommandeCredit" calcule également les points boni et le solde et met la variable "estPaye" à true
  public boolean paieCommandeCredit ( Commande c )
  {
  double total = c.calculerGrandTotal();
  System.out.println(total);
  if ( soldeCarteCredit + total <= 2000)
  	{
	  int totalEnCents = arrondirCent(total);
	//remettre en dollars
	 
	  soldeCarteCredit += totalEnCents/100.0;
	  int nbPoints = c.calculerPointsBonis();
	  modifierPoints(nbPoints);
	  c.devientPayee(true);
	  return true;
  	}
  else
	  return false;
  }

public double getSoldeCarteCredit() {
	return soldeCarteCredit;
}

public void setSoldeCarteCredit(double soldeCarteCredit) {
	this.soldeCarteCredit = soldeCarteCredit;
}
  
}