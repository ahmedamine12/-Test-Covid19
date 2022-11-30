package ma.fstm.ilisi.projet.model.dao;

import java.util.Collection;

import ma.fstm.ilisi.projet.model.bo.*;

public interface IDAOPatient {
	void create(Patient dia);
	Collection<Patient> retreive();
	void update(String ide);
	void delete(String ide);
}
