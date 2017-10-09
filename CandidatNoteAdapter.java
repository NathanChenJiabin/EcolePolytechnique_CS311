
public class CandidatNoteAdapter implements CountingSortAdapter {

	@Override
	public int getNumberOfSortableValues() {
		return 21;  // de 0 a 20
	}

	@Override
	public int getPositionInOrder(Candidat candidat) {
		return candidat.note;
	}
	
}
