
public interface RadixSortAdapter {
	int getNumberOfDigits();
	
	CountingSortAdapter getCountingSortAdapter(int pos);
}
