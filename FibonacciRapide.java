import java.math.BigInteger;

/**
 * @author Luca Castelli Aleardi (INF311, 2015)
 * 
 * Calcul rapide (avec multiplication matricielle) de la suite de Fibonacci
 * 
 */
public class FibonacciRapide implements Fibonacci {
	/** Choix de l'implantation pour le calcul de la puissance n-ième d'une matrice 2x2 */
	  Puissance algoPuissance;

	  public FibonacciRapide(Puissance algo) {
	    this.algoPuissance = algo;
	  }

	/**
	 * Calcul rapide,
	 * [1 1]^n   [F(n+1) F(n)  ]
	 * [1 0]   = [F(n)   F(n-1)]
	 * 
	 *  @param n le rang du nombre à calculer
	 *  @return F(n)
	 */
	public BigInteger fibo(int n) {
		//throw new Error("A completer");
		if(n==0){
			return BigInteger.ZERO;
		}else if(n==1){
			return BigInteger.ONE;
		}
	    else{
		BigInteger[] coeff= new BigInteger[4];
		coeff[0]=BigInteger.ONE;
		coeff[1]=BigInteger.ONE;
		coeff[2]=BigInteger.ONE;
		coeff[3]=BigInteger.ZERO;
		Matrice2D matrix = new Matrice2D(coeff);
		//PuissanceNaive p = new PuissanceNaive();
		Matrice2D resultat = algoPuissance.puissance(matrix, n-1);
		return resultat.getCoefficient(0, 0);}
	}
	
	/**
	 * Renvoie le type d'algorithme implanté pour calculer F(n)
	 * 
	 *  @return le nom de l'algorithme implanté
	 */
	public String name() {
		return "FibonacciRapide "+algoPuissance.name();
	}

}
