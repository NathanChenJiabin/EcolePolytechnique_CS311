import java.awt.Color;

import maclib.*;

/**
 * @author Luca Castelli Aleardi, Philippe Chassignet (INF311, 2014)
 * 
 * Classe permettant de représenter et mettre a' jour l'etat de l'echiquier
 * 
 * L'echiquier est decrit par le tableau int[] positions:
 * la reine d'indice i ( 0<= i < n) est placee a' la position suivante:
 *  ligne d'indice i;
 *  colonne d'indice positions[i]
 * 
 */
public class DrawEchiquier extends Echiquier {
	
	public Draw d; // graphical frame
	
	/**
	 * Initialise un nouvel echiquier
	 * 
	 * @param taille la taille de l'echiquier
	 * @param affichage specifie si l'echiquier doit etre affiche'
	 */
	public DrawEchiquier(int taille, boolean affichage) {
		super(taille, affichage);
		d = new Draw("8 reines", 200, 200, taille);
		d.drawGrid();
	}
	
  /**
	 * Teste si une reine est en conflit avec les reines 0..reine-1
	 * 
	 *  @param reine indice de la reine dont il faut tester le conflit
	 *  @return true si la reine est en conflit avec les autres reines, ayant indices de 0..reine-1
	 */
	public boolean estEnConflit( int reine) {
		for (int i = 0; i < reine; i++) {
			if (this.getPositions()[i] == this.getPositions()[reine]) {
				d.drawQueen(reine, this.getPositions()[reine], Color.red);
				d.pause();
				d.eraseQueen(reine, this.getPositions()[reine]);
				return true;   // meme colonne
			}
			if ((this.getPositions()[i] - this.getPositions()[reine]) == (reine - i)) {
				d.drawQueen(reine, this.getPositions()[reine], Color.red);
				d.pause();
				d.eraseQueen(reine, this.getPositions()[reine]);
				return true;   // meme diagonale
			}
			if ((this.getPositions()[reine] - this.getPositions()[i]) == (reine - i)) {
				d.drawQueen(reine, this.getPositions()[reine], Color.red);
				d.pause();
				d.eraseQueen(reine, this.getPositions()[reine]);
				return true;   // meme diagonale
			}
		}
		return false;
	}

	/**
	 * Place une reine à une position donnee, par definition: la case [reine, colonne] de l'echiquier
	 * 
	 *  @param reine indice de la reine a' placer
	 *  @param colonne indice de la colonne ou' la reine sera placee
	 */
	public void placeReine( int reine, int colonne) {
		this.getPositions()[reine]=colonne;
		d.reset();
		d.drawEchiquier(this, reine, Color.black);
		//d.drawQueen(reine, colonne, Color.black);
	}

	/**
	 * Incremente le nombre de solutions trouvees et affiche une solution (si affichage==true)
	 */
	public void estUneSolution() {
		d.drawEchiquier(this, this.taille, Color.green);
		d.pause();
		d.pause();
		d.pause();
	}	

}

/**
 * @author Luca Castelli Aleardi (Ecole Polytechnique, INF311, 2014)
 * 
 * A class for painting the 8 queens problem
 */
class Draw {

	GrafPort g;
	int width;
	int height;
	int n;

	static int sleepTime = 300;
	static int framePosition = 100; // x coordinate of the frame

	public Draw(String title, int width, int height, int n) {
		this.g = new GrafPort(title, width, height);
		this.g.getPanel().getParent().setLocation(framePosition, 50);
		this.width = width;
		this.height = height;
		framePosition = framePosition + width + 20;
		this.n=n;
	}

	public void reset() {
		g.foreColor(Color.white);
		g.paintRect(new Rect(0, 0, height, height));
		g.foreColor(Color.black);
		this.drawGrid();
	}

	public void eraseQueen(int reine, int column) {
		//g.foreColor(Color.WHITE);
		drawQueen(reine, column, Color.white);
		g.foreColor(Color.BLACK);
	}

	/*public void blink(int i, Element e) {
		g.foreColor(Color.GREEN);
		draw(i, e);
		pause();
		g.foreColor(Color.BLACK);
		draw(i, e);
	}*/
	
	public void pause() {
		try {
			Thread.sleep(sleepTime);
		} catch (Exception ex) {
			// System.out.println(e);
		}
	}

	/*public void drawComp(int i, Element e, int j, Element f) {
		g.foreColor(Color.GREEN);
		draw(i, e);
		draw(j, f);
		pause();
		g.foreColor(Color.BLACK);
		draw(i, e);
		draw(j, f);
	}

	public void drawSwap(int i, Element e, int j, Element f) {
		erase(i, e);
		erase(j, f);
		draw(j, e);
		draw(i, f);
		pause();
	}*/

	public void drawGrid() {
		//g.foreColor(color);
		int hStep=this.width/n;
		int vStep=this.height/n;
		for (int i = 0; i <= this.width; i=i+hStep) {
			g.drawLine(i, 0, i, this.height);
		}
		for (int i = 0; i <= this.height; i=i+vStep) {
			g.drawLine(0, i, this.width, i);
		}
	}
	
	public void drawQueen(int row, int column, Color color) {
		if(row<0 || column<0) return;
		g.foreColor(color);
		int hStep=this.width/n;
		int vStep=this.height/n;
		int x=column*hStep+(hStep/2);
		int y=row*vStep+(vStep/2);
		g.paintCircle(x, y, hStep/2);
		//pause();
	}
	
	public void drawEchiquier(DrawEchiquier echiquier, int indiceReine, Color color) {
		reset();
		g.foreColor(color);
		for(int i=0;i<indiceReine;i++)
			drawQueen(i, echiquier.getPositions()[i], color);
		pause();
	}

}
