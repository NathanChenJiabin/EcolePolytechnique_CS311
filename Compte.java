import tc.TC;
public class Compte {
	public final String nom;
	public final long numero;
	private Argent solde;
		
	public Compte(String nom, long numero, Argent a){
		this.nom = nom;
		this.numero = numero;
		this.solde = a;
	}
	
	public Compte(String str, Monnaie[] tab){
		String[] str1 = TC.motsDeChaine(str);
		this.nom = str1[3];
		this.numero = Long.parseLong(str1[0]);
		this.solde = new Argent(str1[1]+" "+str1[2], tab); 
		
	}
			
	public Argent getSolde(){
		return this.solde;
	}
	
	public Monnaie getMonnaie(){
		return this.solde.getMonnaie();
	}
	
	public void setSolde(Argent a){
		if(!a.getMonnaie().estEgalA(this.getMonnaie())){
			throw new IllegalArgumentException("le solde n'est pas dans la monnaie du compte");
		}
		this.solde = a;
	}
	
	public String toString(){
		String s = "";
		s += this.numero + " " + this.solde + " " + this.nom;
		return s;
	}

}
