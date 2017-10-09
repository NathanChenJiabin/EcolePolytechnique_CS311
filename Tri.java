public class Tri {

	private static final int COUPURE = 5;
	private Dictionnaire dict;
	private PrefixComparator comparator;
	
	Tri (Dictionnaire dict, PrefixComparator comparator){
		this.dict = dict;
		this.comparator = comparator;
	}
		
	public void triInsertion(int lo, int hi, int d) {
		// A completer (Exo 6a)
		String v;
		int j;
		for(int i = lo+1; i<hi; i+=1){
			v = dict.get(i);
		    j = i-1;
		    while(j>=lo && comparator.precede(v, dict.get(j), d)){
		    	dict.echange(j+1, j);
		    	j-=1;
		    }
		}

	}

	public Bornes partition(int v, int lo, int hi, int d) {
		// A completer (Exo 6b)
		int countinf=0;
		int countsup=0;
		int n =lo-1;
		for(int i = lo ; i<hi; i+=1){
			if(Dictionnaire.caractereA(dict.get(i), d)<v){
		           countinf+=1;
		           n+=1;
		           dict.echange(n, i);
			}else if(Dictionnaire.caractereA(dict.get(i), d)==v){
				   countsup+=1;
			}else{
				
			}
		}
		
		int m = countinf-1;
		
		for(int j = lo+countinf;j<hi;j+=1) {

			if(Dictionnaire.caractereA(dict.get(j), d)==v){
				m+=1;
				dict.echange(lo+m, j);
				
			}

		}
		Bornes borne = new Bornes(lo+countinf, lo+countinf+countsup-1);
		return borne;
	}

	public void tri(int lo, int hi, int d) {
		// A completer (Exo 6b)
		if((hi-lo)<=COUPURE){
			triInsertion(lo, hi, d);
		}else{
			Bornes borne = partition(Dictionnaire.caractereA(dict.get(lo), d), lo, hi, d);
			tri(lo, borne.inf, d);
			tri(borne.inf, borne.sup+1, d+1);
			tri(borne.sup+1, hi, d);
		}
	}

	public void tri() {
		tri(0, dict.size(), 0);
	}

	/*private int rang(String s, int lo, int hi){
		if(hi<=lo){
			return -1;
		}
		if((hi-lo)==1){
			if(s.equals(dict.get(lo))){
				return lo;
			}else{
				return -1;
			}
		}
		else{
		int k = dict.compare(s,(hi+lo)/2);
		if(k==0){
			return (hi+lo)/2;
		}else if(k<0){
			return rang(s, lo, (hi+lo)/2);
		}else{
			return rang(s, (hi+lo)/2, hi);
		}}	
	}*/
	private int rang(String s, int lo, int hi){
		int mid;
		while(lo<hi){
			mid = (hi+lo)/2;
			int n = dict.compare(s, mid);
			if(n==0){return mid;}
			if(n>0){lo=mid+1;}
			else{hi= mid;}
		}
		return -1;
	}
	
	public int rang(String s) {
		return rang(s, 0, dict.size());
	}
}
