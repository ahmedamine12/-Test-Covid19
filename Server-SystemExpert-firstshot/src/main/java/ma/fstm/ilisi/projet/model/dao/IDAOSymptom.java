package ma.fstm.ilisi.projet.model.dao;

import java.util.Collection;

import org.bson.types.ObjectId;

import ma.fstm.ilisi.projet.model.bo.*;

public interface IDAOSymptom {
	void create(Symptom dia);
	Collection<Symptom> retreive();
	Symptom findSymptomById(ObjectId id);
	Symptom findSymptomByName(String nom);
}
