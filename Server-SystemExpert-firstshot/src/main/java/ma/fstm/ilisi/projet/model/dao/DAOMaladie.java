package ma.fstm.ilisi.projet.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import ma.fstm.ilisi.projet.model.bo.CronicDisease;

public class DAOMaladie implements IDAOMaladie {
	MongoCollection<Document> collection= ConnectionDB.getDb().getCollection("Maladie");
	@Override
	public void create(CronicDisease mld) {
		// TODO Auto-generated method stub
		Document document = new Document(
				 "name", mld.getCronName());
				//Inserting document into the collection
				collection.insertOne(document);
				System.out.println("Document of maladie inserted successfully");
	}

	@Override
	public Collection<CronicDisease> retreive() {
		// TODO Auto-generated method stub
		Collection<CronicDisease> cronic= new ArrayList<CronicDisease>();
		FindIterable<Document> it=collection.find();
		MongoCursor<Document> cursor = it.iterator();
		while (cursor.hasNext()) {
			Document curr=cursor.next();
			cronic.add(new CronicDisease(curr.getObjectId("_id"),curr.getString("name")));
		}
		return cronic;
	}
	@Override
	public CronicDisease findMaladieById(ObjectId id) {
		Document doc =collection.find(Filters.eq("_id", id)).first();
		if(doc==null)return null;
		return new CronicDisease(id,doc.getString("name"));
	}

	@Override
	public CronicDisease findSymptomByName(String nom) {
		// TODO Auto-generated method stub
		Document doc =collection.find(Filters.eq("name", nom)).first();
		if(doc==null)return null;
		return new CronicDisease(doc.getObjectId("_id"),nom);
		
	}

}
