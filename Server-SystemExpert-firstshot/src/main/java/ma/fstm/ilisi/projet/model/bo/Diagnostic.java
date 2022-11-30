package ma.fstm.ilisi.projet.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import ma.fstm.ilisi.projet.model.service.SendEmail1;
public class Diagnostic implements Serializable{
	
private ObjectId _id;
private Patient patient;
private List<Symptom> symptomes=new ArrayList<Symptom>();
private List<CronicDisease> maladiesC=new ArrayList<CronicDisease>();
private Date date_diagnostic;
private double possi_presence=0.0;
private boolean contact=false;
private double temperature=37;
private boolean sent=false;
/*************************************************/
/**************Constructeur**********************/
/*************************************************/
public Diagnostic(ObjectId _id, Patient patient) {
	super();
	this._id = _id;
	this.patient = patient;
}

public List<Symptom> getSymptomes() {
	return symptomes;
}

public void setSymptomes(List<Symptom> symptomes) {
	this.symptomes = symptomes;
}

public List<CronicDisease> getMaladiesC() {
	return maladiesC;
}

public void setMaladiesC(List<CronicDisease> maladiesC) {
	this.maladiesC = maladiesC;
}

public double getTemperature() {
	return temperature;
}

public void setTemperature(double temperature) {
	this.temperature = temperature;
}

public Diagnostic( Patient patient, Date d) {
	this.patient = patient;
	this.date_diagnostic=d;
}

public Diagnostic(){}
/*************************************************/
/**************Getters setters********************/
/*************************************************/
public ObjectId get_id() {
	return _id;
}
public void set_id(ObjectId _id) {
	this._id = _id;
}
public Patient getPatient() {
	return patient;
}
public void setPatient(Patient patient) {
	this.patient = patient;
}
public Date getDate_diagnostic() {
	return date_diagnostic;
}
public void setDate_diagnostic(Date date_diagnostic) {
	this.date_diagnostic = date_diagnostic;
}
public double getPossi_presence() {
	return possi_presence;
}
public void setPossi_presence(double double1) {
	BigDecimal bd = new BigDecimal(double1).setScale(2, RoundingMode.HALF_UP);
    double val2 = bd.doubleValue();
	this.possi_presence = val2;
}

 public void  addSymptom(Symptom sys) {
	 this.symptomes.add(sys);
 }
 public void addCronic(CronicDisease cro) {
	 this.maladiesC.add(cro);
 }
 public boolean isContact() {
		return contact;
}
public void setContact(boolean contact) {
	this.contact = contact;
}
@Override
public String toString() {
	return "Diagnostic [patient=" + patient + "\nsymptomes=" + symptomes + "\n maladiesC=" + maladiesC
			+ "\n date_diagnostic=" + date_diagnostic + "\npossi_presence=" + possi_presence + "]\n";
}
public boolean isDiagnostic(String s) {
	for(Symptom sym : this.symptomes) {
		if(sym.getSymName().equalsIgnoreCase(s))
			return true;
	}
	return false;
}
public boolean isMaladieCronique(String mal) {
	for(CronicDisease m : this.maladiesC) {
		if(m.getCronName().equalsIgnoreCase(mal))
			return true;
	}
	return false;
	
}
public void fireAll() {
	 new ma.fstm.ilisi.projet.model.service.ServiceKie(this);
}
public void sendEmail() {
	if(this.sent==true) return;
	this.sent=true;
	new SendEmail1(this).EnvoiEmail("kamassafi19@gmail.com");
}
}
