import java.math.BigInteger;

public class PuissanceNaive implements Puissance {

	/**
	 * Calcule la puissance d'une matrice de maniere naive:
	 * M^n=M^(n-1)*M
	 */
	public Matrice2D puissance(Matrice2D matrix, int n) {
		//throw new Error("A completer");
		if(n==0){
			return Matrice2D.identite();
		}else if( n ==1){
			return matrix;
		}else{
			return matrix.multiplie(puissance(matrix,n-1));
		}
	}
	
	/**
	 * Renvoie le type d'algorithme implanté pour calculer M^n
	 * 
	 *  @return le nom de l'algorithme implanté
	 */
	public String name() {
		return "(Calcul de M^n par Puissance naive)";
	}


}
