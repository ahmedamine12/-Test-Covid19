package ma.fstm.ilisi.projet.model.service;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

public class Historique implements Serializable {
	ObjectId idDioagno;
	String identifiant;
	double possi_covid;
	Date dateDia;
	
	public Historique(ObjectId idDioagno,String identifiant, double possi_covid, Date dateDia) {
		this.idDioagno= idDioagno;
		this.identifiant = identifiant;
		this.possi_covid = possi_covid;
		this.dateDia = dateDia;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public ObjectId getIdDioagno() {
		return idDioagno;
	}
	public void setIdDioagno(ObjectId idDioagno) {
		this.idDioagno = idDioagno;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public double getPossi_covid() {
		return possi_covid;
	}
	public void setPossi_covid(double possi_covid) {
		this.possi_covid = possi_covid;
	}
	public Date getDateDia() {
		return dateDia;
	}
	public void setDateDia(Date dateDia) {
		this.dateDia = dateDia;
	}
	@Override
	public String toString() {
		return "Historique [identifiant=" + identifiant + ", possi_covid=" + possi_covid + ", dateDia=" + dateDia + "]";
	}
	
}
