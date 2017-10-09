import tc.TC;

public class Permutation {
	
	public static void afficher(int[] t){
		for(int i=0;i<t.length-1;++i){
			TC.print(t[i]+" ");
		}
		if (t.length>0)
			TC.println(t[t.length-1]);
	}
	
	public static void afficherListePermutations(int n){
		int[][] t=listePermutations(n);
		for(int i=0;i<t.length;++i){
			afficher(t[i]);
		}
	}

	public static String decodeString(String msg, int[] perm){
		int[] invperm=new int[perm.length];
		for(int i=0;i<perm.length;++i) invperm[perm[i]-1]=i+1;
		int l=msg.length();
		int n=perm.length;
		int k=l/n;
		char[][] mat=new char[k][n];
		int pos=0;
		for(int j=0;j<n;j++)
		for(int i=0;i<k;i++)
				mat[i][invperm[j]-1]=msg.charAt(pos++);	
		String res="";
		for (int i = 0; i < k; i++) 
		for (int j = 0; j < n; j++)		
				res += mat[i][j];
		return res;
	}
		
	public static int[] inserer(int[] t, int i){
		//A MODIFIER (exo 7)
		//throw(new RuntimeException("Fonction inserer non-implémentée"));
		int[] t1 = new int[t.length+1];
		for(int j =0; j<i;j+=1){
			t1[j] = t[j];
		}
		t1[i] = t.length+1;
		for(int j = i+1; j<t.length+1; j+=1){
			t1[j] = t[j-1];
		}
		return t1;
	}
	
	public static int[][] listePermutations(int n){
		//A MODIFIER (exo 7)
		//throw(new RuntimeException("Fonction listePermutations non-implémentée"));
		int[][] t;
		if(n==0){t = new int[][]{{}}; return t;}
		if(n==1){t = new int[][]{{1}}; return t;}
		int[][] t1 = listePermutations(n-1);
		t = new int[n*t1.length][n];
		for(int i = 0; i<t1.length; i+=1){
			for(int j = 0; j<n;j+=1){
				t[i*n+j]=inserer(t1[i],j);
			}
		}
		return t;
	}
}

























