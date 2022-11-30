package ma.fstm.ilisi.projet.model.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import ma.fstm.ilisi.projet.model.bo.Diagnostic;

public class ServiceKie {

	public ServiceKie(Diagnostic dia) {

		KieServices ks ;
		KieContainer kContainer ;
		KieSession kSession ;
		ks = KieServices.Factory.get();
		kContainer = ks.getKieClasspathContainer();
		kSession = kContainer.newKieSession("ksession-rules");
		kSession.insert(dia);
	    kSession.fireAllRules();
	}
	

}
