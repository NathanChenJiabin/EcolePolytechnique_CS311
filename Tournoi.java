import tc.TC;

public class Tournoi {

	public static void afficheTour(int[] tab){
		if(tab.length!=0){
			for(int i=0; i< tab.length; i+=2){
				TC.print("("+tab[i]+" "+"vs. "+tab[i+1]+")");
			}
		}
		TC.println();
	}
	
	public static int[] tableauOrdonne(int n){
		int[] tab = new int[n];
		for(int i = 0; i<tab.length; i++){
			tab[i]=i+1;
		}
		return tab;
	}
	public static void echange(int i, int j, int[] tab){
		  int tmp=tab[i];
		  tab[i]=tab[j];
		  tab[j]=tmp;
		}
	
	public static void melangeTableau(int[] tab){
		  for(int i=tab.length-1; i>0; i--){
		    int j=(int)(Math.random()*(i+1));
		    echange(i,j,tab);
		  }
		}
	
	public static boolean estUnePermutation(int[] tab){
	  if(tab.length==0){return true;}	
	  else{
		  int[] testtab = new int[tab.length+1];		
		for(int i = 0; i< tab.length ; i+=1){
			int tmp = tab[i];
			if(tmp<=0){
				return false;
			}else if(tmp>tab.length){
				return false;
			}else{
			    testtab[tmp]+=1;}
		}
		boolean b = true;
		for(int j = 1; j< testtab.length; j+=1){			
			if(testtab[j]!=1){				
				b=false;				
			}
		}
		return b;}
	}
	
	public static int[] tourSuivant(int[] tab, boolean[] result){
		int[] tabgagnant = new int[result.length];
		for(int i=0; i<result.length; i+=1){
			if(result[i]){
				tabgagnant[i]=tab[2*i];
			}else{
				tabgagnant[i]=tab[2*i+1];
			}
		}
		return tabgagnant;
	}
	
	public static int vainqueur(int[] tab, boolean[][] result){
		
		for(int i = 0; i<result.length; i+=1){
			afficheTour(tab);
			tab = tourSuivant(tab, result[i]);
		}
		return tab[0];
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
