
public class RadixSort implements SortingAlgorithm {

	private RadixSortAdapter adapter;
	private Candidat[] data;
	
	RadixSort(Candidat[] data, RadixSortAdapter adapter) {
		this.adapter = adapter;
		this.data = data;
	}
	
	@Override
	public void run() {
		// to complete
		CountingSort unCountingSort;
		int i = adapter.getNumberOfDigits()-1;
		while(i>=0){
			CountingSortAdapter unCountingSortAdapter = adapter.getCountingSortAdapter(i);
			unCountingSort = new CountingSort(data, unCountingSortAdapter);
			unCountingSort.run();
			i-=1;
		}	
	}
}
