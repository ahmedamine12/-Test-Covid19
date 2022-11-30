package ma.fstm.ilisi.projet.model.dao;

import java.util.Collection;

import org.bson.types.ObjectId;

import ma.fstm.ilisi.projet.model.bo.*;

public interface IDAOMaladie {
	void create(CronicDisease dia);
	Collection<CronicDisease> retreive();
	CronicDisease findMaladieById(ObjectId id);
	CronicDisease findSymptomByName(String nom);
}
