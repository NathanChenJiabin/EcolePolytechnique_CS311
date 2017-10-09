import java.util.LinkedList;

import tc.TC;

public class ABR {
    public Noeud racine;
    
    // construit l'arbre vide
    public ABR() {
        this.racine = null;
    }
    
    // construit un ABR équilibré à partir d'une liste d'entrées
    // que l'on suppose triée.
    public ABR(ListeEntrees liste) {
    	Entree[] entrees = liste.toArray();
    	int n = entrees.length - 1;
    	if(n==-1) // arbre vide
    		racine = null;
    	else // arbre non vide
    		this.racine = Noeud.indexerTableauTrie(entrees, 0, n);
    }
    
    public String toString( ) {
        return "Index de : " + racine;
    }
    
    public void dessiner( ) {
    	new Fenetre(this.racine);
    }

    // Ajouter toutes les occurrences de mots d'un texte.
    // La lecture se fait via la classe TC qui ignore la ponctuation.
    public void indexerTexte( ) {
		int nligne = 1;
		while(!TC.finEntree( )) {
			for(String mot: TC.lireLigne( ).split("[ .,:;!?()\\[\\]\"]+"))
				ajouterOccurrence(mot,nligne);
			nligne++;
		}
    }
    
    // Les méthodes ci-dessus sont données, vous ne devez pas les modifier.
    
    // Exercice 1

    public int hauteur(){
    	//throw new Error("classe ABR : compéter la méthode hauteur");
    	if(this.racine == null){
    		return -1;
    	}
    	
    	return racine.hauteur();
    }

    // Exercice 2
    
    public ListeEntiers chercher(String w) {
    	//throw new Error("classe ABR : compléter la méthode chercher");
    	if(racine==null){
    		ListeEntiers l = new ListeEntiers();
    		return l;
    	}
    	return racine.chercher(w);
    }

    // Exercice 3
    
    public boolean estValide() {
    	//throw new Error("classe ABR : compléter la méthode estValide");
    	if(racine==null){
    		return true;
    	}
    	return racine.estValide(null, null);
    }

    // Exercice 4

    public void ajouterOccurrence(String w, int n) {
        //throw new Error("classe ABR : compléter la méthode ajouterOccurrence");
    	if(racine==null){
    		racine = new Noeud(new Entree(w, n));
    	}else{
    		racine.ajouterOccurrence(w, n);
    	}
    }
    
    // Exercice 5
    
    public void imprimer(String nom) {
    	//La ligne qui suit indique dans quel fichier écrire.
        TC.ecritureDansNouveauFichier(nom + ".index");
        //
        //Compléter ici
        if(this.racine==null){
        	return;
        }else{
        	this.racine.imprimer();
        }
        //
        //Une fois les écritures dans le fichier terminées, 
        //on revient à la sortie standard, c'est-à-dire l'écran.
        TC.ecritureSortieStandard( );
    }
    
    // Exercice 6
    
    public ListeEntrees liste() {
    	//throw new Error("classe ABR: Écrire la méthode d'objet liste.");
    	if(this.racine==null){
    		return new ListeEntrees();
    	}else{
    		return racine.liste();
    	}
    }
    
    // Extension (facultative)
    public void filtrer(String w) {
    	throw new Error("classe ABR: Écrire la méthode d'objet filtrer.");
    }
}
