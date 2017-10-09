public class LexicographicComparator implements PrefixComparator {
	
	public boolean precede(String v, String w, int d) {
		// A completer (Exo 6a)
		int l1 = v.length();
        int l2 = w.length();
        int l = 0;
        if(l1<=l2){
        	l = l2;
        }else{
        	l = l1;
        }
		for(int j = d; j<l; j+=1){
			int a = Dictionnaire.caractereA(v, j);
			int b = Dictionnaire.caractereA(w, j);
			if(a<b){
				return  true;}
			else if(a==b){}
			else{
				return false;
			}
				
		}
		return false;

	}
}
