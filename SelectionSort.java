public class SelectionSort implements SortingAlgorithm {

	Candidat[] table; // elements to sort
	CandidatComparator comparator; // defines the order
	
	public SelectionSort(Candidat[] table, CandidatComparator comparator) {
		this.table = table;
		this.comparator = comparator;
	}
	public void echange(int i, int j){
		Candidat uncandidat;
		uncandidat = this.table[i];
		this.table[i] = table[j];
		this.table[j] = uncandidat;
	}
	
	public void run() {
		// to complete
		int index = 0;
		for(int i = 0; i < table.length; i+=1){
			index = i;
			for(int j= i+1; j<table.length; j+=1){
				if(comparator.compare(table[index], table[j])>0){
					index = j ;
				}
			}
			echange(index,i);
		}
	}
	
}
