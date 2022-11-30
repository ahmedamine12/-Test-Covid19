package ma.fstm.ilisi.projet.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.processing.Filer;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import ma.fstm.ilisi.projet.model.bo.Symptom;

public class DAOSymptom implements IDAOSymptom {
	MongoCollection<Document> collection= ConnectionDB.getDb().getCollection("Symptome");

	@Override
	public void create(Symptom spt) {
		// TODO Auto-generated method stub
		Document document = new Document(
				 "name", spt.getSymName());
				//Inserting document into the collection
				collection.insertOne(document);
				System.out.println("Document of symptome inserted successfully");
	}

	@Override
	public Collection<Symptom> retreive() {
		// TODO Auto-generated method stub
		Collection<Symptom> symptomes= new ArrayList<Symptom>();
		FindIterable<Document> it=collection.find();
		MongoCursor<Document> cursor = it.iterator();
		while (cursor.hasNext()) {
			Document curr=cursor.next();
			symptomes.add(new Symptom(curr.getObjectId("_id"),curr.getString("name")));
		}
		return symptomes;
	}
	@Override
	public Symptom findSymptomById(ObjectId id) {
		Document doc=collection.find(Filters.eq("_id", id)).first();
		if(doc==null)return null;
		return new Symptom(id,doc.getString("name"));
	}
	@Override
	public Symptom findSymptomByName(String nom) {
		Document doc=collection.find(Filters.eq("name", nom)).first();
		if(doc==null)return null;
		return new Symptom(doc.getObjectId("_id"),nom);
	}

}
