import tc.TC;
public class Banque {
	public final String nom;
	private Compte[] comptes;
	final static int TAILLE_INITIALE = 10;
	private int nombreDeComptes;
	
	public Banque(String nom){
		this.nom = nom;
		comptes = new Compte[TAILLE_INITIALE];
		nombreDeComptes = 0;
	}
	
	public int getNombreDeComptes(){
		return this.nombreDeComptes;
	}
    
	public String toString(){
		return "BANQUE "+this.nom;
	}
	
	public void afficher(){
		TC.println(nom);
		TC.println(this.nombreDeComptes);
		for (int i=0; i < this.nombreDeComptes;++i){
			TC.println(comptes[i]);
		}	
	}
	
	 public Compte trouverCompte(long numero){
		 for(int i = 0; i< nombreDeComptes; i+=1){
			 if(this.comptes[i].numero == numero){
				 return comptes[i];
			 }
		 }
		 return null;
	 }
	 
	 public boolean ajouterCompte(Compte c){
		 if( c == null || this.trouverCompte(c.numero) != null){
			 return false;
		 }else if(this.nombreDeComptes < this.comptes.length){
			 comptes[nombreDeComptes] = c;
			 this.nombreDeComptes +=1;
			 return true;
		 }else{
			 Compte[] nouveau = new Compte[2*this.nombreDeComptes];
			 for(int i =0; i<this.nombreDeComptes;i+=1){
				 nouveau[i] = this.comptes[i];
			 }
			 nouveau[this.nombreDeComptes]= c;
			 this.nombreDeComptes +=1;
			 this.comptes = nouveau;
			 return true;
		 }
	 }
	 public static Banque lireBanque(Monnaie[] tab){
		 String str1 = TC.lireLigne();
		 Banque unebanque = new Banque(str1);
		 String str2 = TC.lireLigne();
		 int nb = Integer.parseInt(str2);
		 for(int i = 1; i<=nb; i+=1 ){
			 String str = TC.lireLigne();
			 Compte uncompte = new Compte(str, tab);
			 unebanque.ajouterCompte(uncompte);
		 }
		 return unebanque;
	 }
	 
	 public boolean deposer(long numero, Argent argent){
		 if(this.trouverCompte(numero)!=null){
			 this.trouverCompte(numero).setSolde(this.trouverCompte(numero).getSolde().plus(argent));
			 return true;
		 }else{
			 return false;
		 }
	 }
	 
	 public boolean prelever(long numero, Argent argent){
		 if(this.trouverCompte(numero)!=null){
			 Argent a = this.trouverCompte(numero).getSolde().moins(argent);
			 if(a == null){
				 return false;
			 }else{
				 this.trouverCompte(numero).setSolde(a);
				 return true;
			 }
		 }else{
			 return false;
		 }
	 }
	 
	 /*public boolean deposer(long numero, Liquide l){
		 Argent a = l.montant();
		 return this.deposer(numero, a);
	 }*/
	 
	 public Liquide retirer(long numero, Argent a){
		 if(this.prelever(numero, a)){
			 Liquide l = new Liquide(a);
			 return l;
		 }else{
			 return null;
		 }
	 }
	 
	 /*public boolean deposer(long numero, Cheque ch){
		 return this.deposer(numero, ch.montant());
	 } */
		
		 
     /*public boolean deposer(long numero, TIP t){
	      return this.deposer(numero, t.montant());	 
	 } */
	 
	 public boolean deposer(long numero, ArgentPhysique a){
		 return this.deposer(numero, a.montant());
	 }
}
