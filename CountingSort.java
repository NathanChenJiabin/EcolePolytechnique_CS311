import java.util.Arrays;

public class CountingSort implements SortingAlgorithm {

	Candidat[] a;
	Candidat[]	b;
	int[] count;
	CountingSortAdapter adapter;
	
	
	public CountingSort(Candidat[] data, CountingSortAdapter adapter) {
		this.a = data;
		this.adapter = adapter;
		this.b = Arrays.copyOf(data, data.length);
		this.count = new int[adapter.getNumberOfSortableValues()];
	}
	
	@Override
	public void run() {
		// to complete
		//CountingComparator comparator = new CountingComparator();
		for(int i = 0; i<count.length; i+=1){
			count[i] = 0;
		}
		for(int j = 0; j< b.length; j+=1){
			count[adapter.getPositionInOrder(b[j])]+=1;
		}
		for(int j=1; j<count.length; j+=1){
			count[j] = count[j] + count[j-1];
		}
		for(int n = 1; n<=(b.length); n+=1){
			int pos = adapter.getPositionInOrder(b[(b.length)-n]);
			int nb = count[pos]-1;
			a[nb] = b[(b.length-n)];
			count[pos]-=1;
		}
	}

}
