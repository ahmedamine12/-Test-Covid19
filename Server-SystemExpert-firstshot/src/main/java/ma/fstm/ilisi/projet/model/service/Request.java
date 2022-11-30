package ma.fstm.ilisi.projet.model.service;

import java.io.Serializable;

public class Request implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -7108709848211884891L;
	private Object objet;
	private int type;

	public Request(Object objet, int type) {
		super();
		this.objet = objet;
		this.type = type;
	}

	public Object getObjet() {
		return objet;
	}

	public void setObjet(Object objet) {
		this.objet = objet;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
