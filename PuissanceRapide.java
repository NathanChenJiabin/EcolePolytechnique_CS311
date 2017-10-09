import java.math.BigInteger;

public class PuissanceRapide implements Puissance {

	/**
	 * Calcule la puissance M^n avec la strategie dite: "square-and-multiply"
	 * 
	 * 	@param matrix
	 * 			matrice 2x2 en entrée 
	 *  @param n 
	 *  		exposant
	 *  @return M^n
	 */
	public Matrice2D puissance(Matrice2D matrix, int n) {
		//throw new Error("A completer");
		if(n==0){
			return Matrice2D.identite();
		}else if(n==1){
			return matrix;
		}
		else if( n%2==0){
			Matrice2D m1 = puissance(matrix, n/2);
			return m1.multiplie(m1);
		}else{
			Matrice2D m =  puissance(matrix,(n-1)/2);
			return matrix.multiplie(m.multiplie(m));
		}

	}
	
	/**
	 * Renvoie le type d'algorithme implanté pour calculer M^n
	 * 
	 *  @return le nom de l'algorithme implanté
	 */
	public String name() {
		return "(Calcul de M^n par Puissance rapide, 'square-and-multiply')";
	}

	
}
