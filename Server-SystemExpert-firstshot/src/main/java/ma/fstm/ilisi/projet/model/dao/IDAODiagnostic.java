package ma.fstm.ilisi.projet.model.dao;

import java.util.Collection;

import org.bson.types.ObjectId;

import ma.fstm.ilisi.projet.model.bo.Diagnostic;

public interface IDAODiagnostic {
	void create(Diagnostic dia);
	Collection<Diagnostic> retreive();
	Collection<Diagnostic> retreive(ObjectId id);
	Diagnostic AfficherDiagnostic(ObjectId idDia);
}
