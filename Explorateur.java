import tc.TC;

public class Explorateur {

	public Labyrinthe laby;

	public Explorateur(Labyrinthe laby) {
		this.laby = laby;
	}

	public void exploreCase(Case c) {
		// A COMPLETER
		Case[] voisines = c.voisines();
		if(voisines.length==0){
			c.marqueVue();
		}
		for(int i = 0; i<voisines.length; i+=1){
			if(!voisines[i].dejaVue()){
				voisines[i].marqueVue();
				exploreCase(voisines[i]);
			}
		}
	}

	public void explore() {
		// A COMPLETER
		exploreCase(laby.entree());
	}

	public boolean exploreCasePourSortir(Case c) {
		// A COMPLETER
		//exploreCase(c);
		//c.marqueVue();
		Case[] voisines = c.voisines();
		/*if(voisines.length==0){
			//c.marqueVue();
			return true;
		}*/
		if(c.estSortie()){
			c.marqueVue();
			c.marqueChemin();
			return true;
		}
		else{
			if(!c.dejaVue())c.marqueVue();
			for(int i=0; i<voisines.length; i+=1){	
				if(!voisines[i].dejaVue()){
				
				//if(voisines[i].estImpasse()){}
				//else if(voisines[i].estChemin()){c.marqueChemin();return true;}
				if(exploreCasePourSortir(voisines[i])){
					
					c.marqueChemin();
					return true;}
				
				}
			}
			//c.marqueVue();
			c.marqueImpasse();
			return false;
		}
	}

	public boolean explorePourSortir() {
		// A COMPLETER
		return exploreCasePourSortir(laby.entree());
	}


}
