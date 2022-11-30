package ma.fstm.ilisi.projet.model.service;

import java.util.Date;

public class Validation {

	public Errors validInscription(String nom,String prenom,String identifiant,Date ddn,String add) {
		if(nom.isEmpty() || prenom.isEmpty() || add.isEmpty() )return Errors.EMPTYCHAMPS;
		//if(ddn>2022 || ddn<1900) return Errors.DATEINVALID;
		return Errors.VALID;
	}
}
