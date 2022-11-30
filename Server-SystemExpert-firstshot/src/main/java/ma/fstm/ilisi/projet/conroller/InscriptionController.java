package ma.fstm.ilisi.projet.conroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.fstm.ilisi.projet.model.bo.Patient;
import ma.fstm.ilisi.projet.model.bo.Region;
import ma.fstm.ilisi.projet.model.bo.Ville;
import ma.fstm.ilisi.projet.model.dao.DAOAdress;
import ma.fstm.ilisi.projet.model.dao.DAOPatient;
import ma.fstm.ilisi.projet.model.service.Errors;
import ma.fstm.ilisi.projet.model.service.Validation;

public class InscriptionController {

	DAOPatient pat;
	DAOAdress address;
	Validation val;
	public InscriptionController() {
		this.pat = new DAOPatient();
		this.address= new DAOAdress();
		this.val= new Validation();
	}
	//
	public Errors create(String nom,String prenom,String identifiant,Date ddn,String add,String ville) {
		Errors code;
		
		Ville vi= address.findVilleByName(ville);
		if(vi==null) return Errors.NOVILLE;
		
		code= val.validInscription( nom, prenom, identifiant, ddn, add);
		if(code == Errors.VALID) {
			pat.create(new Patient(nom,prenom,identifiant,ddn,add,vi));
			System.out.println("le petient "+identifiant+" a ete cree avec succees !");
			return Errors.VALID;
		}
		return code;
	}
	//retreiving data for the insciption view
	//get regions
	public List<String> retreiveRegion(){
		List<String> regions=new ArrayList<String>();
		List<Region> rgs=(List<Region>) address.retreiveRegion();
		for(int i=0;i<rgs.size();i++)regions.add(rgs.get(i).getRegionName());
		return regions;
	}
	//get cities of regions
	public List<String> retreiveVille(String reg){
		List<String> villes=new ArrayList<String>();
		List<Ville> vlls=(List<Ville>) address.VilleOfRegion(reg);
		for(int i=0;i<vlls.size();i++)villes.add(vlls.get(i).getVilleName());
		return villes;
	}
	//get symptomes
	
	
}
