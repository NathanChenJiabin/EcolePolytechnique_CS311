import java.math.BigInteger;

/**
 * @author Luca Castelli Aleardi (INF311, 2015)
 * 
 * Calcul recursif (très lent) de la suite de Fibonacci
 * 
 */
public class FibonacciRec implements Fibonacci {

	/**
	 * Calcul basé sur la définition récursive de Fibonacci
	 * F(n):=F(n-1)+F(n-2), pour n>1
	 * 
	 *  @param n le rang du nombre à calculer
	 *  @return F(n)
	 */
	public BigInteger fibo(int n) {
		//throw new Error("A completer");
		if(n==0){
			return BigInteger.ZERO;
		}else if( n == 1){
			return BigInteger.ONE;
		}else{
			return fibo(n-1).add(fibo(n-2));
		}
	}
	
	/**
	 * Renvoie le type d'algorithme implanté pour calculer F(n)
	 * 
	 *  @return le nom de l'algorithme implanté
	 */
	public String name() {
		return "Fibonacci recursif";
	}

}
