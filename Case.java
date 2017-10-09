import java.util.LinkedList;

public class Case {
	
	private Labyrinthe laby;
	private boolean type;
	private boolean sortie;
	private LinkedList<Case> voisines;
	private int x, y;

	private static final boolean MUR = true;
	private static final boolean COULOIR = false;

	/* gestion du parcours */
	
	private char marqueParcours;

	private static final char INCONNUE = ' ';
	private static final char VUE = '?';
	private static final char IMPASSE = '@';
	private static final char CHEMIN = '*';

	
	/* constructeur */
	
	public Case(Labyrinthe laby, int x, int y) {
		this.laby = laby;
		this.x = x;
		this.y = y;
		this.marqueParcours = INCONNUE;
		this.sortie = false;
		this.voisines = new LinkedList<Case>();
	}

	/* setters */
	
	public void setMur() {
		type = MUR;
	}

	public void setCouloir() {
		type = COULOIR;
	}

	public void setSortie() {

		sortie = true;
	}

	/* getters */
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean estMur() {
		return type;
	}

	public boolean estCouloir() {
		return !type;
	}

	public boolean estSortie() {
		return sortie;
	}

	/* marquage pour le parcours */
	
	public void marqueVue() {
		marqueParcours = VUE;
		laby.poseUnCaillou(this);
	}

	public void marqueImpasse() {
		marqueParcours = IMPASSE;
		laby.poseUnCaillou(this);
	}

	public void marqueChemin() {
		marqueParcours = CHEMIN;
		laby.poseUnCaillou(this);
	}

	public void effaceMarque() {
		marqueParcours = INCONNUE;
		laby.poseUnCaillou(this);
	}

	public boolean dejaVue() {
		return marqueParcours != INCONNUE;
	}

	/*public boolean estInconnue() {
		return marqueParcours == INCONNUE;
	}*/

	public boolean estChemin() {
		return (marqueParcours == CHEMIN);
	}

	public boolean estImpasse() {
		return (marqueParcours == IMPASSE);
	}


	
	/* gestion des voisines */
	
	public void addVoisine(Case c) {
		voisines.add(c);
	}

	public Case[] voisines() {
		return voisines.toArray(new Case[voisines.size()]);
	}

	/* affichage */
	
	public String toString() {
		String res = "Case (" + x + ", " + y + "), " + voisines.size()
				+ " voisin(s) : ";
		for (Case c : voisines)
			res += "(" + c.x + ", " + c.y + ") ";
		return res;
	}

	public char etat() {
		return marqueParcours;
	}
}
