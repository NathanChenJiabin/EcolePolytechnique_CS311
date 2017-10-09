
public class Monnaie {
	public final String nom;
	private double taux;
	public Monnaie(String nom, double taux){
		this.nom = nom;
		this.taux = taux;
	}
	public double getTaux(){
		return taux;
	}
	public void setTaux(double autretaux){
		this.taux = autretaux;
	}
    public boolean estEgalA(Monnaie m){
    	return m != null && m.nom.equals(this.nom) && m.getTaux() == this.getTaux();
    }
    public static Monnaie trouverMonnaie(String s, Monnaie[] tab){
    	for(int i = 0 ; i < tab.length; i+=1){
    		if(tab[i].nom.equals(s)){
    			return tab[i];
    		}
    	}
    	return null;
    }
}
