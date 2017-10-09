import tc.TC;

public class CandidatUtil {

	public static Candidat[] read(String nomDuFichier) {
		// to complete
		TC.lectureDansFichier(nomDuFichier);
		int nb = Integer.parseInt(TC.lireLigne());
		Candidat[] candidats = new Candidat[nb];
		for(int i = 0; i < nb ; i+=1){
			candidats[i] = new Candidat(TC.lireLigne());
		}
		return candidats;
	}

	public static void printCandidatTable(Candidat[] data) {
		TC.println(data.length);

		for (int i = 0; i < data.length; i++) {
			TC.println(data[i]);
		}
	}

}
