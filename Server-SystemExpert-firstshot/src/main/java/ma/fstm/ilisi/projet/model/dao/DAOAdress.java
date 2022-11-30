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

public class DAOAdress implements IDAOAdress {

	MongoCollection<Document> collectionV = ConnectionDB.getDb().getCollection("Ville");
	MongoCollection<Document> collectionR = ConnectionDB.getDb().getCollection("Region");
	
	public void createVille(Ville v) {
		Document document = new Document(
				 "name", v.getVilleName())
				.append("region", v.getRegion().get_id());
				//Inserting document into the collection
				collectionV.insertOne(document);
				System.out.println("Document of Ville inserted successfully");
	
	}
	public Collection<Ville> retreiveVille() {
		Collection<Ville> villes= new ArrayList<Ville>();
		FindIterable<Document> it=collectionV.find();
		MongoCursor<Document> cursor = it.iterator();
		while (cursor.hasNext()) {
		Document curr=cursor.next();
		Region rg= findRegionByID((ObjectId)curr.getObjectId("region"));
		System.out.println(curr.toJson());
			villes.add(new Ville(curr.getObjectId("_id"),curr.getString("name"),rg));
		}
		return villes;
		
	}
	public Collection<Ville> VilleOfRegion(String name) {
		
		Collection<Ville> villes= new ArrayList<Ville>();
		Region rg= this.findRegionByName(name);
		FindIterable<Document> it=collectionV.find(Filters.eq("region", rg.get_id()));
		MongoCursor<Document> cursor = it.iterator();
		while (cursor.hasNext()) {
		Document curr=cursor.next();
		System.out.println(curr.toJson());
			villes.add(new Ville(curr.getObjectId("_id"),curr.getString("name"),rg));
		}
		return villes;
		
	}
	public void createRegion(Region rg) {
		Document document = new Document(
				 "name", rg.getRegionName())
				.append("capital", rg.getCapital())
				.append("population", rg.getPopulation())
				.append("populationPositif", rg.getPopulationPositif())
				.append("estHautRisque", rg.isEstHautRisque())
				.append("recovery",rg.getRecovery())
				.append("death", rg.getDeath())
				.append("totPositif", rg.getTotalPositif())
				.append("totRecovery", rg.getTotRecovery())
				.append("totDeath", rg.getTotDeath())
				;
				//Inserting document into the collection
				collectionR.insertOne(document);
				System.out.println("Document of region inserted successfully");
	}
	public Collection<Region> retreiveRegion() {
		Collection<Region> regions= new ArrayList<Region>();
		FindIterable<Document> it=collectionR.find();
		MongoCursor<Document> cursor = it.iterator();
		while (cursor.hasNext()) {
			Document curr=cursor.next();
			Region rg=new Region(curr.getObjectId("_id"),curr.getString("name"),curr.getString("capital"),curr.getInteger("population"));
			/*
			 * 
			 * .append("populationPositif", rg.getPopulationPositif())
				.append("estHautRisque", rg.isEstHautRisque())
				.append("recovery",rg.getRecovery())
				.append("death", rg.getDeath())
				.append("totPositif", rg.getTotalPositif())
				.append("totRecovery", rg.getTotRecovery())
				.append("totDeath", rg.getTotDeath())
			 */
			rg.setDeath(curr.getInteger("death"));
			rg.setTotDeath(curr.getInteger("totDeath"));
			rg.setRecovery(curr.getInteger("recovery"));
			rg.setTotRecovery(curr.getInteger("totRecovery"));
			rg.setPopulationPositif(curr.getInteger("populationPositif"));
			rg.setTotalPositif(curr.getInteger("totPositif"));
			regions.add(rg);
		}
		return regions;
		
	}
	public Ville findVilleById(ObjectId _id) {
		
		Document ville = collectionV.find(Filters.eq("_id", _id)).first();
		Ville vl= new Ville(_id,ville.getString("name"),this.findRegionByID(ville.getObjectId("region")));
		return vl;

	}
	public Region findRegionByName(String name) {
		System.out.println(name);
		Document region = collectionR.find(Filters.eq("name", name)).first();
		//if(region!=null)System.out.println(region.getString("name")+region.getString("capital")+region.getObjectId("_id").toString());
		Region rg=new Region();
		//region.getObjectId("_id"),region.getString("name"),region.getString("capital"),region.getInteger("population")
		if(region!=null) {
			/***
			 * 
			 * .append("populationPositif", rg.getPopulationPositif())
				.append("estHautRisque", rg.isEstHautRisque())
				.append("recovery",rg.getRecovery())
				.append("death", rg.getDeath())
				.append("totPositif", rg.getTotalPositif())
				.append("totRecovery", rg.getTotRecovery())
				.append("totDeath", rg.getTotDeath())
			 */
			System.out.println(region.getString("name")+region.getString("capital")+region.getObjectId("_id").toString());
			rg.set_id(region.getObjectId("_id"));
			rg.setCapital(region.getString("capital"));
			rg.setRegionName(region.getString("name"));
			rg.setPopulation(region.getInteger("population"));
			rg.setDeath(region.getInteger("death"));
			rg.setPopulationPositif(region.getInteger("populationPositif"));
		}
		return rg;
		

	}
	public Ville findVilleByName(String name) {
		
		Document ville = collectionV.find(Filters.eq("name", name)).first();
		System.out.println(ville.getObjectId("region"));
		Region rg=this.findRegionByID(ville.getObjectId("region"));
		System.out.println(rg.getRegionName());
		Ville vl= new Ville(ville.getObjectId("_id"),ville.getString("name"),rg);
		return vl;
		

	}
	public Region findRegionByID(ObjectId idR) {
		
		Document region = collectionR.find(Filters.eq("_id", idR)).first();
		Region rg= new Region(region.getObjectId("_id"),region.getString("name"),region.getString("capital"),region.getInteger("population"));
		return rg;
		

	}
	public void update(Region rg) {
		//Region rg=this.findRegionByName(name);
		Document query = new Document();
		query.append("_id", rg.get_id());
		Document setdata = new Document()
				.append("populationPositif", rg.getPopulationPositif())
				.append("estHautRisque", rg.isEstHautRisque())
				.append("recovery",rg.getRecovery())
				.append("death", rg.getDeath())
				.append("totPositif", rg.getTotalPositif())
				.append("totRecovery", rg.getTotRecovery())
				.append("totDeath", rg.getTotDeath())
				;
		Document update=new Document();
		update.append("$set", setdata);
		collectionR.updateOne(query, update);
		System.out.println("a doc updated !");
	}
}
