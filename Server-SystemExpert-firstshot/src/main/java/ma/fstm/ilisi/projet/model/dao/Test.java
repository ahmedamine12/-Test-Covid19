package ma.fstm.ilisi.projet.model.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import ma.fstm.ilisi.projet.model.bo.CronicDisease;
import ma.fstm.ilisi.projet.model.bo.Diagnostic;
import ma.fstm.ilisi.projet.model.bo.Patient;
import ma.fstm.ilisi.projet.model.bo.Symptom;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*List<Patient> villesofR=(List<Patient>) new DAOPatient().retreive();
		for(Patient v : villesofR)
			System.out.println(v+"\n");*/
		/*String[] values = new String[] { "fievre", "fatigue", "toux seche", "diarrhe", "essouflement", "chute",
				"frisson", "congestion nasal", "gorge seche", "ecoulement nasal", "dyspnee", "douleurs musculaires",
				"maux de tete", "perte de gout", "perte de l'odorat", "confusion", "nause", "vomissement",
				"conjonctivite" };
		String[] values = new String[] {"Diabete","cardiaque","Hypertendu"};*/
		/*for(Symptom s :new DAOSymptom().retreive())
			System.out.println(s);*/
		//Diagnostic dia =new Diagnostic();
		Patient patient=new DAOPatient().findPatientByIdentifier("bj444970");
		/*dia.setPatient(patient);
		dia.setTemperature(39);
		dia.getPatient().setAge(75);
		dia.setDate_diagnostic(new Date(0));
		dia.addSymptom(new DAOSymptom().findSymptomByName("fievre"));
		dia.addSymptom(new DAOSymptom().findSymptomByName("fatigue"));
		dia.addSymptom(new DAOSymptom().findSymptomByName("toux seche"));
		dia.addSymptom(new DAOSymptom().findSymptomByName("dyspnee"));
		dia.fireAll();
		System.out.println(dia.getPossi_presence());*/
		//new DAODiagnostic().create(dia);
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(2000, 8, 24);

		Period period = Period.between(birthday, today);

		//Now accessaccess the values as below
		//System.out.println(period.getDays());
		//System.out.println(period.getMonths());
		System.out.println(period.getYears());
		for(Diagnostic s :new DAODiagnostic().retreive(patient.get_id())) {
			s.fireAll();
//			birthday=LocalDate.of(s.getDate_diagnostic().getYear(),1,1);
			int year=Integer.parseInt(s.getDate_diagnostic().toString().substring(24, 28));
			int age=LocalDate.now().getYear()-year;
			System.out.println("you born in "+s.getDate_diagnostic().toString().substring(24, 28)+" "+age+" is your age "+s.getPatient().getNom());
//			 period = Period.between(birthday, today);		
//			 System.out.println("years ! "+period.getYears());

		}
	}

}
