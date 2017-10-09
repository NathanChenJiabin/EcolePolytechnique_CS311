public class MergeSort implements SortingAlgorithm {

	Candidat[] table; // elements to sort
	CandidatComparator comparator; // defines the order
	Candidat[] tmp;
	
	public MergeSort(Candidat[] table, CandidatComparator comparator) {
		this.table = table;
		this.comparator = comparator;
		this.tmp = new Candidat[table.length];
	}
	
	public void run() {
		// to complete
		if(this.table.length > 1){
			Candidat[] tableleft = new Candidat[table.length/2];
			for(int i = 0; i<table.length/2; i+=1){
				tableleft[i] = table[i];
			}
			Candidat[] tableright = new Candidat[table.length - (table.length/2)];
			for(int j = 0; j< table.length - (table.length/2); j+=1){
				tableright[j] = table[(table.length/2)+j];
			}
			MergeSort ml = new MergeSort(tableleft, this.comparator);
			MergeSort mr = new MergeSort(tableright, this.comparator);
			ml.run();mr.run();
			for(int n = 0; n<table.length/2; n+=1){
				table[n] = ml.table[n];
			}
			for(int n = 0; n<table.length-(table.length/2); n+=1){
				table[(table.length/2) +n] = mr.table[n];
			}
			merge(0, table.length/2, table.length);
		}

		

	}
	
	public void merge(int left, int middle, int right) {
		// to complete
		int head1 = left;
		int head2 = middle;
		for(int n = left; n<right; n+=1){
			if(head1<middle && head2<right){
			if(comparator.compare(table[head1], table[head2])<=0){
				tmp[n] = table[head1];
				head1 +=1;
			}else{
				tmp[n] = table[head2];
				head2 +=1;
			}}
			if(head1==middle){
				for(int i = head2; i<right; i+=1){
					tmp[i] = table[i];
				}
			}
			if(head2==right){
				for(int i = head1; i<middle; i+=1){
					tmp[right-(middle-i)]=table[i];		
				}
			}
		}
		for(int i = left; i< right; i+=1){
			table[i] = tmp[i];
		}

	}
	
}
