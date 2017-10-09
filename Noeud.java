import java.util.Locale;

import tc.TC;

public class Noeud {
	
    public Noeud gauche;
    public Noeud droit;
    public Entree contenu;
    
    public Noeud(Entree e) {
        gauche = null;
        droit = null;
        contenu = e;
    }
    
    public Noeud(Noeud g, Noeud d, Entree e) {
        gauche = g;
        droit = d;
        contenu = e;
    }
    
	// Pour les trois méthodes "comparer" on exploite la surcharge de la 
	// méthode correspondant de la classe "Entree".
    public int comparer(Noeud x) {
        return contenu.comparer(x.contenu);
    }

    public int comparer(Entree e) {
        return contenu.comparer(e);
    }

    public int comparer(String m) {
        return contenu.comparer(m);
    }

    public String toString( ) {
        String str = "";
        if(gauche != null)
            str += "(" + gauche + ")";
        else
            str += "*";
        str += " <- " + contenu + " -> ";
        if(droit != null)
            str += "(" + droit + ")";
        else
            str += "*";
        return str;
    }
        
    // Les méthodes ci-dessus sont données, vous ne devez pas les modifier.
    
    // Exercice 1
    public int hauteur(){
    	//throw new Error("classe Noeud : compéter la méthode hauteur");
    	if(this.gauche==null&&this.droit==null){
    		return 0;
    	}
    	if(this.gauche==null){
    		return 1+this.droit.hauteur();
    	}
    	if(this.droit==null){
    		return 1+this.gauche.hauteur();
    	}
    	if(this.gauche.hauteur()<this.droit.hauteur()){
    		return 1+this.droit.hauteur();
    	}else{
    		return 1+this.gauche.hauteur();
    	}
    }

    // Exercice 2
    public ListeEntiers chercher(String w) {
    	//throw new Error("classe Noeud : compléter la méthode chercher");
    	int res = this.comparer(w);
    	if(res==0){
    		return this.contenu.occurrences;
    	}else if(res<0&&this.droit!=null){
    		return this.droit.chercher(w);
    	}else if(res>0&&this.gauche!=null){
    		return this.gauche.chercher(w);
    	}
    	ListeEntiers l = new ListeEntiers();
    	return l;
    }
    
    // Exercice 3
    
    public boolean estValide(String min, String max) {
    	// Vérifier que le nœud courant respecte les bornes
    	if ((min!=null) && this.contenu.comparer(min) <= 0
    			|| ((max!=null) && this.contenu.comparer(max) >= 0))
    		return false;
    	// Descente à gauche
    	if (this.gauche != null) {
    		if(!this.gauche.estValide(min, this.contenu.mot))
    			return false;
    	}
    	// Descente à droite
    	if (this.droit != null) {
    		if(!this.droit.estValide(this.contenu.mot,max))
    			return false;
    	}
    	return true;
    }
    
    // Exercice 4
    public void ajouterOccurrence(String w, int n) {
    	//throw new Error("classe Noeud : compléter la méthode ajouterOccurrence");
            int comp = this.comparer(w);
    		if(comp>0){
    			//ajouter dans gauche
    			if(this.gauche==null){
    				Noeud unNoeud = new Noeud(new Entree(w, n));
    				this.gauche = unNoeud;
    			}else{
    				this.gauche.ajouterOccurrence(w, n);
    			}
    		}else if(comp<0){
    			//ajouter dans droit
    			if(this.droit==null){
    				Noeud unNoeud = new Noeud(new Entree(w, n));
    				this.droit = unNoeud;
    			}else{
    				this.droit.ajouterOccurrence(w, n);
    			}
    		}else{
    			this.contenu.ajouter(n);
    		}  	
    }
    
    // Exercice 5
    public void imprimer() {
    	//throw new Error("classe Noeud : compléter la méthode imprimer");
    	if(this.gauche!=null){
    		this.gauche.imprimer();
    	}
    	TC.println(this.contenu);
    	if(this.droit!=null){
    		this.droit.imprimer();
    	}
    	
    }
    
    // Exercice 6
    public ListeEntrees liste( ) {
    	//throw new Error("classe Noeud : compléter la méthode liste");
    	ListeEntrees list = new ListeEntrees();
    	if(this.gauche!=null){
    		list.concatener(this.gauche.liste());   		
    	}
    	list.ajouterEnQueue(this.contenu);
    	if(this.droit!=null){
    		list.concatener(this.droit.liste());
    	}
    	return list;
    }

    // Exercice 7
	public static Noeud indexerTableauTrie(Entree[] entrees, int min, int max) {
		//throw new Error("classe Noeud : compléter la méthode indexerTableauTrie");
		if(max>=min){
		int middle = (min+max)/2;
		return new Noeud(Noeud.indexerTableauTrie(entrees, min, middle-1), Noeud.indexerTableauTrie(entrees, middle+1, max), entrees[middle]);}
		return null;
	}
	
}
