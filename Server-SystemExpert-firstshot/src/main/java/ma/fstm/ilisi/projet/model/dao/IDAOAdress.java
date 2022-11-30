package ma.fstm.ilisi.projet.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import ma.fstm.ilisi.projet.model.bo.Region;
import ma.fstm.ilisi.projet.model.bo.Ville;


public interface IDAOAdress {
	void createVille(Ville v);
	Collection<Ville> retreiveVille() ;
	Collection<Ville> VilleOfRegion(String name);
	void createRegion(Region rg);
	Collection<Region> retreiveRegion();
	Ville findVilleById(ObjectId _id);
	Region findRegionByName(String name);
	Ville findVilleByName(String name);
	Region findRegionByID(ObjectId idR);

}
