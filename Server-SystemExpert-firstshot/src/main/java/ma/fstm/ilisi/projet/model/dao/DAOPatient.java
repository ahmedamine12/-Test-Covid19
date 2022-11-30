package ma.fstm.ilisi.projet.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import ma.fstm.ilisi.projet.model.bo.Patient;
import ma.fstm.ilisi.projet.model.bo.Ville;

public class DAOPatient implements IDAOPatient {

	
	MongoCollection<Document> collection = ConnectionDB.getDb().getCollection("Patient");

	@Override
	public void create(Patient pt) {
		// TODO Auto-generated method stub
		if(this.findPatientByIdentifier(pt.getIdentifiant())!=null) {
			System.out.println("ce patient deja existe");
			return;
		}
		Document document = new Document("nom",pt.getNom())
				.append("prenom", pt.getPrenom())
				.append("dateNaissance", pt.getDateNaissance())
				.append("identifier", pt.getIdentifiant())
				.append("adress", pt.getAdresse())
				.append("idVille", pt.getVille().get_id());
				
				//Inserting document into the collection
				collection.insertOne(document);
				System.out.println("Document inserted successfully");
	}

	@Override
	public void delete(String ide) {
		// TODO Auto-generated method stub
		 collection.deleteOne(Filters.eq("identifier",ide)); 
	     System.out.println("Document deleted successfully...");
		
	}

	@Override
	public List<Patient> retreive() {
		
		List<Patient> patients= new ArrayList<Patient>();
		FindIterable<Document> iterable = collection.find();
		MongoCursor<Document> cursor = iterable.iterator();
		System.out.println("Patient list with cursor: ");
		while (cursor.hasNext()) {
		Document curr=cursor.next();
	    Ville v=new DAOAdress().findVilleById((ObjectId)curr.getObjectId("idVille"));
	    System.out.println(v+curr.getObjectId("idVille").toString());
			patients.add(new Patient(curr.getObjectId("_Id"), curr.getString("nom"), curr.getString("prenom"), 
									curr.getString("identifier"), curr.getDate("dateNaissance"), curr.getString("adress"),v));
		}
		
		return patients;
	}

	@Override
	public void update(String ide) {
		
		
	}
/*
 * 
 * public Patient(ObjectId _id, String nom,
String prenom, String login, String password, Date dateNaissance,
			String adresse, Ville ville) {
	
 */
	public Patient findPatientByIdentifier(String ide) {
		Patient pt=null;
		DAOAdress dao= new DAOAdress();
		Document patient = collection.find(Filters.eq("identifier", ide)).first();
		if(patient==null)return null;
		pt=new Patient( patient.getObjectId("_id"),patient.getString("nom"),patient.getString("prenom"),
						patient.getString("identifier"),patient.getDate("dateNaissance"),
						patient.getString("adresse"),dao.findVilleById(patient.getObjectId("idVille"))
						);
		return pt;
	}
	public Patient findPatientById(ObjectId ide) {
		Patient pt=null;
		DAOAdress dao= new DAOAdress();
		Document patient = collection.find(Filters.eq("_id", ide)).first();
		if(patient==null)return null;
		pt=new Patient( patient.getObjectId("_id"),patient.getString("nom"),patient.getString("prenom"),
						patient.getString("identifier"),patient.getDate("dateNaissance"),
						patient.getString("adresse"),dao.findVilleById(patient.getObjectId("idVille"))
						);
		return pt;
	}


}
