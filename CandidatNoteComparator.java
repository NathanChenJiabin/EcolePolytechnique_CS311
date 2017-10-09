public class CandidatNoteComparator implements CandidatComparator {

	@Override
	public int compare(Candidat c1, Candidat c2) {
		// to complete
		int n;
		if(c1.note < c2.note){
			n = -1;
		}else if(c1.note == c2.note){
			n = 0;
		}else{
			n = 1;
		}
		return n;
	}

}
