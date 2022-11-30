package ma.fstm.ilisi.projet.model.bo;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class Symptom implements Serializable {
ObjectId _id;
String symName;

/*************************************************/
/**************Constructeur**********************/
/*************************************************/
public Symptom(ObjectId _id, String symName) {
	super();
	this._id = _id;
	this.symName = symName;
}
public Symptom( String symName) {
	super();
	this.symName = symName;
}

public Symptom() {}
/*************************************************/
/********Getters and setters**********************/
/*************************************************/
public ObjectId get_id() {
	return _id;
}
public void set_id(ObjectId _id) {
	this._id = _id;
}
public String getSymName() {
	return symName;
}
public void setSymName(String symName) {
	this.symName = symName;
}
@Override
public String toString() {
	return "Nom du symptome : " + symName + "\t";
}
}
