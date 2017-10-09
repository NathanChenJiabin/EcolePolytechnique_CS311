import java.math.BigInteger;

/**
 * @author Luca Castelli Aleardi (INF311, 2015)
 * 
 * Calcul naif (lent) de la suite de Fibonacci
 * 
 */
public class FibonacciNaif implements Fibonacci {

	/**
	 * Calcul naif (itératif),
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
			BigInteger s = BigInteger.ZERO;
			BigInteger s1 = BigInteger.ZERO;
			BigInteger s2 = BigInteger.ONE;
			for(int i =1; i<n; i+=1){
				s = s1.add(s2);
				s1 = s2;
				s2 = s;
			}
			return s;
		}
	}
	
	/**
	 * Renvoie le type d'algorithme implanté pour calculer F(n)
	 * 
	 *  @return le nom de l'algorithme implanté
	 */
	public String name() {
		return "Fibonacci naif";
	}
	
}
