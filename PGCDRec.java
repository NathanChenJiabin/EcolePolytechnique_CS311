import java.math.BigInteger;

import tc.TC;

public class PGCDRec {
	public static int pgcd(int a, int b) {
		// __COMPLETER__ (Exo 1a)
		if(b == 0){
			return a;
		}else if( a >= b){
			int r = a%b;
			return pgcd(b, r);
		}else{
			return pgcd(b,a);
		}
		
	}
	
	public static BigInteger pgcd(BigInteger a, BigInteger b){
		// __COMPLETER__ (Exo 1b)
		if(b.equals(BigInteger.ZERO)){
			return a;
		}else if(a.compareTo(b)>=0){
			BigInteger r = a.mod(b);
			return pgcd(b, r);
		}else{
			return pgcd(b,a);
		}
		
	}
}
