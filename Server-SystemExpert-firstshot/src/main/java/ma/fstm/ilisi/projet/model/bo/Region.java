package ma.fstm.ilisi.projet.model.bo;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class Region implements Serializable {

	private ObjectId _id;
	private String regionName;
	private boolean estHautRisque=false;
	private String capital;
	private int population;
	private int populationPositif=0;
	private int death=0;
	private int recovery=0;
	private int totalPositif=0;
	private int totDeath=0;
	private int totRecovery=0;
	/*************************************************/
	/*******Constructor with and without parameters***/
	/*************************************************/
	public Region(ObjectId _id, String region, String capital, int population) {
		super();
		this._id = _id;
		this.regionName = region;
		this.capital = capital;
		this.population = population;
		
	}
	public Region( String region,String capital, int population) {
		super();
		this.regionName = region;
		this.capital = capital;
		this.population = population;
	}
	public Region() {}
	
	/*************************************************/
	/********Getters and setters**********************/
	/*************************************************/
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public boolean isEstHautRisque() {
		return estHautRisque;
	}
	public void setEstHautRisque(boolean estHautRisque) {
		this.estHautRisque = estHautRisque;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getPopulationPositif() {
		return populationPositif;
	}
	public void setPopulationPositif(int populationPositif) {
		this.populationPositif = populationPositif;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public int getDeath() {
		return death;
	}
	public void setDeath(int death) {
		this.death = death;
	}
	public int getRecovery() {
		return recovery;
	}
	public void setRecovery(int recovery) {
		this.recovery = recovery;
	}
	public int getTotalPositif() {
		return totalPositif;
	}
	public void setTotalPositif(int totalPositif) {
		this.totalPositif = totalPositif;
	}
	public int getTotDeath() {
		return totDeath;
	}
	public void setTotDeath(int totDeath) {
		this.totDeath = totDeath;
	}
	public int getTotRecovery() {
		return totRecovery;
	}
	public void setTotRecovery(int totRecovery) {
		this.totRecovery = totRecovery;
	}
	@Override
	public String toString() {
		return regionName ;
	}
	public boolean estDangeureuse()
	{
		//if(this.population==0)return false;
		return ((this.populationPositif+this.death)/this.population)>=0.3;
	}
	
}
