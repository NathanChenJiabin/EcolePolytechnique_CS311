
public class CandidatNumberCountingSortAdapter implements CountingSortAdapter {

	int pos;
	
	public CandidatNumberCountingSortAdapter(int pos) {
		this.pos = pos;
	}
	
	@Override
	public int getNumberOfSortableValues() {
		return 36; // 26 lettres + 10 chiffre
	}

	@Override
	public int getPositionInOrder(Candidat element) {
		char letter = element.numeroDeDossier.toUpperCase().charAt(pos);
		// we assume that we have an ascii character here, even though this might not be true in general
		// (well, it is for our input, anyway...)
		if (Character.isDigit(letter)) {
			return (int) letter - 48;
		}
		else { 
			return (int) letter - 55;
		}
	}

}
