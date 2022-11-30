package ma.fstm.ilisi.projet.model.bo;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class Ville implements Serializable {
	private ObjectId _id;
	private String villeName; 
	private Region region;
	
	
	/*************************************************/
	/*******constructeur avec et sans parametre*******/
	/*************************************************/
	public Ville(ObjectId _id, String villeName, Region region) {
		super();
		this._id = _id;
		this.villeName = villeName;
		this.region = region;
	}


	public Ville(String villeName, Region region) {
		super();
		this.villeName = villeName;
		this.region = region;
	}
	
	public Ville() {}
	/*************************************************/
	/********Getters and setters**********************/
	/*************************************************/


	public ObjectId get_id() {
		return _id;
	}


	public void set_id(ObjectId _id) {
		this._id = _id;
	}


	public String getVilleName() {
		return villeName;
	}


	public void setVilleName(String villeName) {
		this.villeName = villeName;
	}


	public Region getRegion() {
		return region;
	}


	public void setRegion(Region region) {
		this.region = region;
	}


	@Override
	public String toString() {
		return  villeName ;
	}
	
	
	
}
