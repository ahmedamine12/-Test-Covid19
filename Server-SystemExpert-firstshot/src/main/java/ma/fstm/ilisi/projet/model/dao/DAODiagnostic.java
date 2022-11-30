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

import ma.fstm.ilisi.projet.model.bo.CronicDisease;
import ma.fstm.ilisi.projet.model.bo.Diagnostic;
import ma.fstm.ilisi.projet.model.bo.Patient;
import ma.fstm.ilisi.projet.model.bo.Symptom;
import ma.fstm.ilisi.projet.model.service.Historique;

public class DAODiagnostic implements IDAODiagnostic{
	DAOSymptom daosymp= new DAOSymptom();
	DAOMaladie daomal = new DAOMaladie();
	MongoCollection<Document> collection= ConnectionDB.getDb().getCollection("Diagnostique");
	@Override
	public void create(Diagnostic dia) {
		// TODO Auto-generated method stub
		//charger les symptomes dans un tableau
		ArrayList<Document> sym=new ArrayList<Document>();
		for(Symptom s : dia.getSymptomes()) {
			if(s.get_id()==null) {
				s.set_id(daosymp.findSymptomByName(s.getSymName()).get_id());
			}
			sym.add(new Document().append("idS", s.get_id()) );
		}
		//charger les maladies croniques dans un tableau
		ArrayList<Document> mal=new ArrayList<Document>();
		for(CronicDisease m : dia.getMaladiesC()){
		if(m.get_id()==null) {
			m.set_id(daomal.findSymptomByName(m.getCronName()).get_id());			
		}
			mal.add(new Document().append("idC", m.get_id()) );
		}
		//creation du docment diagnostic
		Document doc = new Document()
				.append("date", dia.getDate_diagnostic())
				.append("PossiPresence", dia.getPossi_presence())
				.append("contact", dia.isContact())
				.append("idPat",dia.getPatient().get_id())
				.append("symptomes", sym)
				.append("maladie", mal)
				.append("temperature", dia.getTemperature());
		collection.insertOne(doc);
		System.out.println("a diagnostic inserted successufully ! ");
	}

	@Override
	public Collection<Diagnostic> retreive() {
		// TODO Auto-generated method stub
		ArrayList<Diagnostic> diagnostics= new ArrayList<Diagnostic>();
		FindIterable<Document> iterable = collection.find();
		MongoCursor<Document> cursor = iterable.iterator();
		System.out.println("Patient list with cursor: ");
		while (cursor.hasNext()) {
			Document curr=cursor.next();
			Diagnostic dia=new Diagnostic(curr.getObjectId("_id"),
					       new DAOPatient().findPatientById(curr.getObjectId("idPat")));
			/***************Setting attributes***************/
			//chercher les symptomes figurent en diagnostic courant
			ArrayList<Document> symp = (ArrayList<Document>) curr.get("symptomes");
			DAOSymptom dao_symptom= new DAOSymptom();
			if(symp!=null)
			for(Document it:symp) {
				
				dia.addSymptom( dao_symptom.findSymptomById( (ObjectId)it.get("idS") ) );
			}
			//chercher les maladie figurent en diagnostic courant
			ArrayList<Document> mald =(ArrayList<Document>) curr.get("maladies");
			DAOMaladie dao_mal= new DAOMaladie();
			if(mald!=null)
			for(Document it:mald) {
				dia.addCronic(dao_mal.findMaladieById((ObjectId)it.get("idC")));
			}
			/***********************************************/
			dia.setPossi_presence(curr.getDouble("PossiPresence"));
			dia.setTemperature(curr.getDouble("temperature"));
			dia.setDate_diagnostic(curr.getDate("date"));
		    diagnostics.add(dia);
		    
		}
		return diagnostics;
	}
	@Override
	public Collection<Diagnostic> retreive(ObjectId id) {
		// TODO Auto-generated method stub
		ArrayList<Diagnostic> diagnostics= new ArrayList<Diagnostic>();
		Patient pat=new DAOPatient().findPatientById(id);
		FindIterable<Document> iterable = collection.find(Filters.eq("idPat",id));
		MongoCursor<Document> cursor = iterable.iterator();
		System.out.println("Patient list with cursor: ");
		while (cursor.hasNext()) {
			Document curr=cursor.next();
			Diagnostic dia=new Diagnostic(curr.getObjectId("_id"),pat);
			/***************Setting attributes***************/
			//chercher les symptomes figurent en diagnostic courant
			ArrayList<Document> symp = (ArrayList<Document>) curr.get("symptomes");
			DAOSymptom dao_symptom= new DAOSymptom();
			if(symp!=null)
			for(Document it:symp) {
				
				dia.addSymptom( dao_symptom.findSymptomById( (ObjectId)it.get("idS") ) );
			}
			//chercher les maladie figurent en diagnostic courant
			ArrayList<Document> mald =(ArrayList<Document>) curr.get("maladies");
			DAOMaladie dao_mal= new DAOMaladie();
			if(mald!=null)
			for(Document it:mald) {
				dia.addCronic(dao_mal.findMaladieById((ObjectId)it.get("idC")));
			}
			/***********************************************/
			dia.setPossi_presence(curr.getDouble("PossiPresence"));
			dia.setTemperature(curr.getDouble("temperature"));
			dia.setDate_diagnostic(curr.getDate("date"));
		    diagnostics.add(dia);
		    
		}
		return diagnostics;
	}
	@Override
	public Diagnostic AfficherDiagnostic(ObjectId idDia) {
		// TODO Auto-generated method stub
		
		Diagnostic dia = null;
		//Diagnostic diagnostics= new Diagnostic(idDia,pat);
		Document curr = collection.find(Filters.eq("_id",idDia)).first();
		//MongoCursor<Document> cursor = iterable.iterator();
		//if(iterab)
		System.out.println("Patient list with cursor: ");
		if (curr != null) {
			Patient pat= new DAOPatient().findPatientById(curr.getObjectId("idPat"));
			dia=new Diagnostic(curr.getObjectId("_id"),pat);
			/******Setting attributes******/
			//chercher les symptomes figurent en diagnostic courant
			ArrayList<Document> symp = (ArrayList<Document>) curr.get("symptomes");
			DAOSymptom dao_symptom= new DAOSymptom();
			if(symp!=null)
			for(Document it:symp) {
				
				dia.addSymptom( dao_symptom.findSymptomById( (ObjectId)it.get("idS") ) );
			}
			//chercher les maladie figurent en diagnostic courant
			ArrayList<Document> mald =(ArrayList<Document>) curr.get("maladies");
			DAOMaladie dao_mal= new DAOMaladie();
			if(mald!=null)
			for(Document it:mald) {
				dia.addCronic(dao_mal.findMaladieById((ObjectId)it.get("idC")));
			}
			/*****************/
			dia.setPossi_presence(curr.getDouble("PossiPresence"));
			dia.setTemperature(curr.getDouble("temperature"));
			dia.setDate_diagnostic(curr.getDate("date"));
				
			}
		return dia;
	}
	public List<Historique> histroriqueDiagno(ObjectId identif) {
		List<Historique> histo = new ArrayList<Historique>();
		List<Diagnostic> col = (List<Diagnostic>) this.retreive(identif);
		if(col.size()>0) {
			String ident = col.get(0).getPatient().getIdentifiant();
			for (int i = 0; i < col.size(); i++) {
				Historique h = new Historique(col.get(i).get_id(), ident, col.get(i).getPossi_presence(),
						col.get(i).getDate_diagnostic());
				histo.add(h);
			}
		}
		return histo;
	}

}
