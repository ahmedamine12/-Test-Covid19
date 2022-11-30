package ma.fstm.ilisi.projet.model.bo;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class CronicDisease implements Serializable {
ObjectId _id;
String cronName;
/*************************************************/
/*******constructeur avec et sans parametre*******/
/*************************************************/
public CronicDisease(ObjectId _id, String cronName) {
	super();
	this._id = _id;
	this.cronName = cronName;
}
public CronicDisease(String cronName) {
	super();
	this.cronName = cronName;
}
public CronicDisease() {}
/*************************************************/
/*************Getters and setters*****************/
/*************************************************/
public ObjectId get_id() {
	return _id;
}
public void set_id(ObjectId _id) {
	this._id = _id;
}
public String getCronName() {
	return cronName;
}
public void setCronName(String cronName) {
	this.cronName = cronName;
}
@Override
public String toString() {
	return "Nom de la maadie cronique : "+cronName+"\t" ;
}

}
