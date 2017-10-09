import tc.TC;

public class Maillon {
	public final Candidat contenu;
	private Maillon suivant;

	public Maillon(Candidat c) {
		this.contenu = c;
		this.suivant = null;
	}

	public Maillon(Candidat c, Maillon reste) {
		this.contenu = c;
		this.suivant = reste;
	}

	public Maillon suivant() {
		return this.suivant;
	}

	public static Maillon intercaler(Maillon courant, Candidat c) {
		if(courant == null){
		    Maillon unmaillon = new Maillon(c);
		    return unmaillon;
		}
		Maillon unmaillon = new Maillon(c, courant.suivant);
		courant.suivant = unmaillon;
		return unmaillon;
	}

	public static Maillon enleverSuivant(Maillon courant) {
		if(courant != null && courant.suivant() !=null){
			courant.suivant = courant.suivant.suivant;
			return courant;
		}
		return courant;
	}

	public static int longueur(Maillon m) {
		int len = 0;
		Maillon tmp = m;
		while(tmp!=null){
			len +=1;
			tmp = tmp.suivant();
		}
		return len;
	}

	public static void afficher(Maillon m) {
		if(m!=null){
			TC.println(m.contenu);
			Maillon.afficher(m.suivant());
		}
	}
}
