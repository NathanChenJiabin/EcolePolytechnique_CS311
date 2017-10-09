
public class CountingComparator extends CandidatNoteComparator {
	int counter = 0;
	
	@Override
	public int compare(Candidat c1, Candidat c2) {
		counter++;
		return super.compare(c1, c2);
	}
	
	public void reset() {
		counter = 0;
	}
	
	public int getCount() {
		return counter;
	}
	
}
