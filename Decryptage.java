public class Decryptage {

	public static String[] decodeToutes(String msg, int n){
		//A MODIFIER (exo 7b)
		//throw new RuntimeException("Erreur : Methode decodeToutes non implementee !");
		int[][] perms = Permutation.listePermutations(n);
		String[] str = new String[perms.length];
		for(int i = 0; i<perms.length; i+=1){
			str[i] = Permutation.decodeString(msg, perms[i]);
		}
		return str;
	}

}
