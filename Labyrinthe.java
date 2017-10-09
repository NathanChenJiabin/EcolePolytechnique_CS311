import java.awt.Color;
import java.util.LinkedList;

import tc.TC;
import maclib.GrafPort;
import maclib.MacLib;
import maclib.Rect;

public class Labyrinthe {

	private Case entree;
	private Case sortie;
	private LinkedList<Case> cases;

	/* graphismes */
	private int tailleX, tailleY;
	private GrafPort g;
	private int u, d;
	// unite de la grille et delai entre deux marquages.
	// adaptes selon la taille du labyrinthe

	private static final Color VERT = new Color((float) 0, (float) 0.8,
			(float) 0.1);
	private static final Color BLEU = Color.blue;
	private static final Color GRIS = Color.LIGHT_GRAY;
	private static final Color BLANC = Color.white;

	/* conventions de representation par char[][] (ou dans un fichier texte) */
	private static final char ENTREE = 'E';
	private static final char SORTIE = 'S';
	private static final char MUR = '#';
	private static final char COULOIR = ' ';
	private static final char NONTRAITEE = '?';

	/*
	 * constructeurs
	 */

	private Labyrinthe(char[][] tab) {
		/* attention, tab decrit le labyrinthe ligne par ligne */
		tailleX = tab[0].length;
		tailleY = tab.length;

		cases = new LinkedList<Case>();

		Case[][] tabCases = new Case[tailleX][tailleY];
		for (int i = 0; i < tailleX; i++)
			for (int j = 0; j < tailleY; j++) {
				tabCases[i][j] = new Case(this, i, j);
				cases.add(tabCases[i][j]);
				switch (tab[j][i]) {
				case COULOIR:
					tabCases[i][j].setCouloir();
					break;
				case MUR:
					tabCases[i][j].setMur();
					break;
				case ENTREE:
					entree = tabCases[i][j];
					break;
				case SORTIE:
					sortie = tabCases[i][j];
					sortie.setSortie();
					break;
				default:
					throw new RuntimeException(
							"Le tableau contient un caractere invalide");
				}
			}

		for (int i = 0; i < tailleX; i++)
			for (int j = 0; j < tailleY; j++) {
				Case c = tabCases[i][j];
				if (c.estCouloir()) {
					if (i < tailleX - 1 && tabCases[i + 1][j].estCouloir())
						c.addVoisine(tabCases[i + 1][j]);
					if (j < tailleY - 1 && tabCases[i][j + 1].estCouloir())
						c.addVoisine(tabCases[i][j + 1]);
					if (i > 0 && tabCases[i - 1][j].estCouloir())
						c.addVoisine(tabCases[i - 1][j]);
					if (j > 0 && tabCases[i][j - 1].estCouloir())
						c.addVoisine(tabCases[i][j - 1]);
				}
			}

		/* choix de l'unite de la grille et du delai */
		if (tailleX > 150 || tailleY > 90) {
			u = 1;
			d = 0;
		} else if (tailleX > 100 || tailleY > 60) {
			u = 2;
			d = 1;
		} else if (tailleX > 70 || tailleY > 40) {
			u = 3;
			d = 1;
		} else {
			u = 4;
			d = 2;
		}
		g = new GrafPort("Labyrinthe", 4 * tailleX * u, 4 * tailleY * u, 4
				* tailleX * u, 4 * tailleY * u);
		g.showDrawing();
		g.foreColor(GRIS);
	}

	public Labyrinthe(int tX, int tY) {
		this(initTab(tX, tY));
	}

	public Labyrinthe(int tX, int tY, double p) {
		this(initTab(tX, tY, p));
	}

	public Labyrinthe(String fic) {
		this(initTab(fic));
	}

	private static char[][] initTab(String fic) {
		TC.lectureDansFichier(fic);
		String[] mots = TC.motsDeChaine(TC.lireLigne());
		int tailleX = Integer.parseInt(mots[0]);
		int tailleY = Integer.parseInt(mots[1]);
		char[][] tab = new char[tailleY][tailleX];
		for (int i = 0; i < tailleY; i++) {
			String ligne = TC.lireLigne();
			for (int j = 0; j < tailleX; j++)
				tab[i][j] = ligne.charAt(j);
		}
		return tab;
	}

	private static char[][] initTab(int tX, int tY, double p) {
		char[][] tab = new char[2 * tY + 1][2 * tX + 1];
		/* cadre */
		for (int j = 0; j < tab[0].length; j++) {
			tab[0][j] = MUR;
			tab[2 * tY][j] = MUR;
		}
		for (int i = 0; i < tab.length; i++) {
			tab[i][0] = MUR;
			tab[i][2 * tX] = MUR;
		}
		/* entree et sortie */
		tab[tY - 1][0] = ENTREE;
		tab[tY - 1][2 * tX] = SORTIE;
		/* interieur */
		for (int i = 1; i < 2 * tY; i++)
			for (int j = 1; j < 2 * tX; j++)
				if (i % 2 == 1 && j % 2 == 1) tab[i][j] = COULOIR;
				else if (i % 2 == 0 && j % 2 == 0) tab[i][j] = MUR;
				else if (Math.random() < p) tab[i][j] = MUR;
				else tab[i][j] = COULOIR;
		return tab;
	}

	public static char[][] initTab(int tX, int tY) {
		char[][] tab = new char[2 * tY + 1][2 * tX + 1];
		for (int i = 0; i < tab.length; i++)
			for (int j = 0; j < tab[0].length; j++)
				if (i % 2 == 1 && j % 2 == 1) tab[i][j] = NONTRAITEE;
				else tab[i][j] = MUR;
		int cpt = tX * tY - 1;
		int x = 2 * tX - 1, y = 2 * tY - 1, xx, yy;
		tab[y][x] = COULOIR;
		int alea;
		while (cpt > 0) {
			xx = x;
			yy = y;
			alea = (int) (4 * Math.random());
			switch (alea) {
			case 0:
				if (x > 1) x -= 2;
				break;
			case 1:
				if (y > 1) y -= 2;
				break;
			case 2:
				if (x < 2 * tX - 1) x += 2;
				break;
			default:
				if (y < 2 * tY - 1) y += 2;
			}
			if (tab[y][x] == NONTRAITEE) {
				tab[y][x] = tab[(y + yy) / 2][(x + xx) / 2] = COULOIR;
				cpt--;
			}
		}
		/* entree et sortie */
		tab[1][0] = ENTREE;
		tab[2 * tY - 1][2 * tX] = SORTIE;

		return tab;
	}

	/*
	 * accesseurs
	 */

	public Case entree() {
		return entree;
	}

	public Case sortie() {
		return sortie;
	}

	/*
	 * affichage
	 */

	public void afficher() {
		Rect r = new Rect();
		for (Case c : cases) {
			int x = c.getX(), y = c.getY();
			if (c.estMur()) {
				r.setRect(4 * x * u, 4 * y * u, 4 * (x + 1) * u, 4 * (y + 1)
						* u);
				g.paintRect(r);
			}
		}
	}

	private void entoureCase(int x, int y) {
		g.penSize(2, 2);
		Rect r = new Rect();
		r.setRect(4 * x * u, 4 * y * u, 4 * (x + 1) * u, 4 * (y + 1) * u);
		g.frameRect(r);
		g.penNormal();
	}

	public void poseUnCaillou(Case c) {
		int x = c.getX(), y = c.getY();
		Rect r = new Rect();
		r.setRect((4 * x + 1) * u, (4 * y + 1) * u, (4 * x + 3) * u,
				(4 * y + 3) * u);
		if (c.estChemin()) g.foreColor(VERT);
		else if (c.estImpasse()) g.foreColor(GRIS);
		else if (c.dejaVue()) g.foreColor(BLEU);
		else g.foreColor(BLANC);

		g.paintRect(r);
		if (c.equals(entree) || c.equals(sortie)) entoureCase(x, y);
		g.foreColor(GRIS);
		MacLib.delay(d);
	}

	public void ramasseLesCailloux() {
		int aux = d;
		d = 0;
		for (Case c : cases)
			if (c.estCouloir()) c.effaceMarque();
		d = aux;
	}

}
