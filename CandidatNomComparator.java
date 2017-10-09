public class CandidatNomComparator implements CandidatComparator {

	@Override
	public int compare(Candidat a, Candidat b) {
		// to complete
          return a.nom.compareTo(b.nom);
	}

}
