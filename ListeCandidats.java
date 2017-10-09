import tc.TC;

public class ListeCandidats implements EnsembleCandidats {

	private Maillon tete;
	private Maillon queue;

	public ListeCandidats() {
		this.tete = null;
		this.queue = null;
	}

	public boolean estVide() {
		return tete==null && queue == null;
	}

	public void ajouterEnTete(Candidat c) {
		if(estVide()){
			Maillon unmaillon = new Maillon(c);
			tete = unmaillon;
			queue = unmaillon;
		}else{
		    Maillon unmaillon = new Maillon(c, tete);
		    tete = unmaillon;
		}
	}

	public void ajouterEnQueue(Candidat c) {
		if(estVide()){
			queue = Maillon.intercaler(queue, c);
			tete = queue;
		}else{
		queue = Maillon.intercaler(queue, c);}
		

	}

	public int nombreCandidats() {
		if(estVide()){
			return 0;
		}else{
		return Maillon.longueur(tete);}
	}

	public void afficher() {
		if(estVide()){
			TC.println("<liste vide>");
		}else{
			Maillon.afficher(tete);
		}
	}

	public void ajouterFichierEnQueue(String nomFichier) {
		TC.lectureDansFichier(nomFichier);
		String nomregion = TC.lireLigne();
		String ligne ="";
		while(!TC.finEntree()){
			ligne = TC.lireLigne();
			Candidat uncandidat = new Candidat(ligne);
			ajouterEnQueue(uncandidat);
		}
		TC.println("Candidats de la region "+nomregion+" ajoutes. Il y a maintenant "+nombreCandidats()+" candidats");
	}

	public void enleverSuivant(Maillon precedent) {
	  if(estVide()){
		  if(precedent!=null){
			  Maillon.enleverSuivant(precedent);
		  }
	  }else{
		if(precedent==null){
		  if(tete==queue){
			  tete=null;
			  queue=null;
		  }else{
			tete = tete.suivant();
		  }
		}else{
			if(precedent.suivant()==queue){
				queue=Maillon.enleverSuivant(precedent);
			}else{
				Maillon.enleverSuivant(precedent);
			}
		}
	  }
	}
	


	public void desistement(Candidat c) {
		if(tete!=null){
			if(tete.contenu.equals(c)){
				enleverSuivant(null);
			}else{
				Maillon tmp1 = tete;
				Maillon tmp2 = tete.suivant();
				while(tmp2!=null){
					if(tmp2.contenu.equals(c)){
						enleverSuivant(tmp1);
						break;
					}
					tmp1 = tmp2;
					tmp2 = tmp1.suivant();
				}
			}
		}

	}
	

	public void desistementFichier(String nomFichier) {	
		if(!estVide()){
		TC.lectureDansFichier(nomFichier);
		String ligne = "";
		String[] mots;
		Candidat ref = new Candidat("a", "b", 0);
		Maillon mark1 = new Maillon(ref, tete);
		Maillon mark2 = mark1.suivant();
		while(!TC.finEntree()){
			ligne = TC.lireLigne();
			mots = TC.motsDeChaine(ligne);
			Candidat c = new Candidat(mots[0], mots[1], 0);
			
			Maillon mark3 = mark1;
			int len = nombreCandidats();
			while(mark2!=null){
				if(mark2.contenu.equals(c)){
					if(mark2==tete){
						tete = tete.suivant();
						mark1 = new Maillon(ref, tete);
						mark2 = mark1.suivant();
						break;
						
					}else{
					    enleverSuivant(mark1);
					    mark2 = mark1.suivant();
					    break;
					}
				}else{
					mark1 = mark2;
					mark2 = mark1.suivant();
				}

			}
			if(nombreCandidats()==len && mark2==null){
				mark1 = mark3;
				mark2 = mark1.suivant();
			}
			if(mark2==null){
				break;
			}
			

		}}
	}
	/*public void desistementFichier(String nomFichier){
	 //ce methode n'est pas efficace.
		TC.lectureDansFichier(nomFichier);
		while(!TC.finEntree()&&tete!=null){
			String ligne = TC.lireLigne();
			String[] mots = TC.motsDeChaine(ligne);
			Candidat c = new Candidat(mots[0], mots[1], 0);
			desistement(c);
		}
	}*/

	public void selection() {
		if(nombreCandidats()>=2){
			Maillon m = tete;
			Maillon ms = m.suivant();
			while(ms!=null){
				if(ms.contenu.note<m.contenu.note){
					enleverSuivant(m);
					
				}else if(ms.contenu.note==m.contenu.note){
					m = ms;
					
				}else{
					tete = ms;
					m = tete;
				   
				}
				ms = m.suivant();
			}
		}
	}
	


	public void ajouterFichierTrie(String nomFichier) {
		TC.lectureDansFichier(nomFichier);
		String nomDeRegion = TC.lireLigne();
		String ligne = "";
		Candidat c;
		Maillon mark = tete;
		while(!TC.finEntree()){
			ligne = TC.lireLigne();
			c = new Candidat(ligne);
            if(tete==null){
            	ajouterEnTete(c);
            	mark = tete;
            }
            else if(mark==tete&&c.ordreAlphabetique(tete.contenu)<0){
            	ajouterEnTete(c);
            	mark = tete;
            }else if(mark==queue){
            	ajouterEnQueue(c);
            	mark = queue;
            }else{
            	while(mark.suivant()!=null){
            	   if(mark.suivant().contenu.ordreAlphabetique(c)>=0){
                     mark = Maillon.intercaler(mark, c);
                     break;
            	   }else{
            		  mark = mark.suivant();
            		  if(mark.suivant()==null){
            			ajouterEnQueue(c);
            			mark = queue;
            			break;
            		  }
            	    }
            	}	
            }
		}
		TC.println("Candidats de la region "+nomDeRegion+" ajoutes. Il y a maintenant "+nombreCandidats()+" candidats");
	}

}

