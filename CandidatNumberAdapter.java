
public class CandidatNumberAdapter implements RadixSortAdapter {

	@Override
	public int getNumberOfDigits() {
		return 4; // toujours 4 character pour le nombre de dossier
	}

	@Override
	public CountingSortAdapter getCountingSortAdapter(int pos) {
		return new CandidatNumberCountingSortAdapter(pos);
	}

}
