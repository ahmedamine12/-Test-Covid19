package ma.fstm.ilisi.projet.model.bo;

import java.io.Serializable;
import java.util.Date;
import org.bson.types.ObjectId;

public class Patient implements Serializable {
ObjectId _id;
private String nom;
private String prenom;
private String identifiant;																																																									
private Date dateNaissance;
private String adresse;
private int age;
private Ville ville;
/*************************************************/
/***********Constructeur**************************/
/*************************************************/
public Patient(ObjectId _id, String nom, String prenom, String identifiant, Date dateNaissance, String adresse,
		Ville ville) {
	super();
	this._id = _id;
	this.nom = nom;
	this.prenom = prenom;
	this.identifiant = identifiant;
	this.dateNaissance = dateNaissance;
	this.adresse = adresse;
	this.ville = ville;
}
public Patient( String nom, String prenom, String identifiant, Date dateNaissance, String adresse,
		Ville ville) {
	this.nom = nom;
	this.prenom = prenom;
	this.identifiant = identifiant;
	this.dateNaissance = dateNaissance;
	this.adresse = adresse;
	this.ville = ville;
}
public Patient() {}
/*************************************************/
/********Getters and setters**********************/
/*************************************************/
public ObjectId get_id() {
	return _id;
}

public void set_id(ObjectId _id) {
	this._id = _id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getIdentifiant() {
	return identifiant;
}
public void setIdentifiant(String identifiant) {
	this.identifiant = identifiant;
}
public Date getDateNaissance() {
	return dateNaissance;
}
public void setDateNaissance(Date dateNaissance) {
	this.dateNaissance = dateNaissance;
}
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public Ville getVille() {
	return ville;
}
public void setVille(Ville ville) {
	this.ville = ville;
}
@Override
public String toString() {
	return "Patient [nom=" + nom + ", prenom=" + prenom + ", identifiant=" + identifiant + ", dateNaissance="
			+ dateNaissance + ", adresse=" + adresse + ", age=" + age + ", ville=" + ville + "]\n";
}

}
