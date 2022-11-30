package ma.fstm.ilisi.projet.conroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;

import ma.fstm.ilisi.projet.model.bo.Diagnostic;
import ma.fstm.ilisi.projet.model.dao.DAODiagnostic;
import ma.fstm.ilisi.projet.model.dao.DAOPatient;
import ma.fstm.ilisi.projet.model.service.Historique;

public class HistoryController {
DAOPatient pat;
DAODiagnostic diagno;
public HistoryController() {
	super();
	this.pat = new DAOPatient();
	this.diagno = new DAODiagnostic();
}
//charger les diagnostiques
public List<Historique> histroriqueDiagno(ObjectId identif) {
	List<Historique> histo =new ArrayList<Historique>();
	List<Diagnostic> col=(List<Diagnostic>) diagno.retreive(identif);
	String ident=col.get(0).getPatient().getIdentifiant();
	for(int i=0;i<col.size();i++) {
		Historique h=new Historique(col.get(i).get_id(),ident, col.get(i).getPossi_presence(),col.get(i).getDate_diagnostic());
		histo.add(h);
	}
	return histo;
}
}
//charger un diagnostic pour l'imprimer 
/*
 * on va prendre l'id du table des diagnostics apres on 
 * 
 */

