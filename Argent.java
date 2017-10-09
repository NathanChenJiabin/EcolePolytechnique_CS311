import tc.TC;

public class Argent {

	// répresentation interne de l'argent:
	// Champs d'objet
	private final int valeur;
	private final Monnaie monnaie;

	public Argent(int v) {
		this.valeur = v;
		this.monnaie = new Monnaie("Euro", 1.0);
	}

	public Argent(int v, Monnaie monnaie) {
		if (monnaie == null)
			throw new IllegalArgumentException("le parametre monnaie est null");
		this.valeur = v;
		this.monnaie = monnaie;
	}
	
	public Argent(String str, Monnaie[] tab){
		String[] s1 = TC.motsDeChaine(str);
		Monnaie m = Monnaie.trouverMonnaie(s1[1], tab);
		String[] s2 = TC.decoupage(s1[0], '.');
		int n1 = Integer.parseInt(s2[0]);
		int n2 = Integer.parseInt(s2[1]);
		this.valeur = n1*100+n2;
		this.monnaie = m;
	}

	public Monnaie getMonnaie() {
		return this.monnaie;
	}

	public int valeurEntiere() {
		return this.valeur / 100;
	}

	public int valeurDecimale() {
		return this.valeur % 100;
	}

	public boolean estEgalA(Argent a) {
		
		return a != null && a.getMonnaie().estEgalA(this.monnaie) && a.valeur == this.valeur;
		
	}

	public String toString() {
		
		String v;
		if(this.valeurDecimale()<10){
			v = "0"+this.valeurDecimale();
		}else{
			v = ""+this.valeurDecimale();
		}
		return this.valeurEntiere() + "." + v + " " + this.getMonnaie().nom;
	}

	public Argent convertir(Monnaie autreMonnaie) {
		int valeur = (int)(this.valeur*(this.getMonnaie().getTaux()/autreMonnaie.getTaux()));
        Argent a = new Argent(valeur, autreMonnaie);
        return a;
	}

	public Argent plus(Argent x) {
		Argent a1 = x.convertir(this.getMonnaie());
		int somme = a1.valeur + this.valeur;
		Argent a = new Argent(somme, this.getMonnaie());
		return a;
	}

	public Argent moins(Argent x) {
		Argent a1 = x.convertir(this.getMonnaie());
		int xPrime = 0;
		if(this.valeur < a1.valeur){
			return null;
		}else{
			xPrime = this.valeur - a1.valeur;
		    Argent a = new Argent(xPrime, this.getMonnaie());
            return a;
        }
	}

}
