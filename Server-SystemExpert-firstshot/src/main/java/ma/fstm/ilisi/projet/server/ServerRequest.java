package ma.fstm.ilisi.projet.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;

import ma.fstm.ilisi.projet.model.bo.*;
import ma.fstm.ilisi.projet.model.dao.*;
import ma.fstm.ilisi.projet.model.service.Historique;
import ma.fstm.ilisi.projet.model.service.Request;

public class ServerRequest extends Thread {
	int nbClients;

	@Override
	public void run() {
		try {
			@SuppressWarnings("resource")
			ServerSocket ss = new ServerSocket(234);
			while (true) {

				Socket s = ss.accept();
				++nbClients;
				new Conversation(s, nbClients).start();
			}
		} catch (IOException e) {
			e.getMessage();
		}
	}

	class Conversation extends Thread {
		private Socket socket;
		private int numeroClient;

		Conversation(Socket socket, int num) {
			this.socket = socket;
			this.numeroClient = num;
		}

		public void run() {
			try {
				// pour la lecture des objets
				InputStream is = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(is);
				// pour envoyer la reponse
				OutputStream os = socket.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
				PrintWriter pw = new PrintWriter(os, true);
				// avoir l'adreese ip du client
				String IP = socket.getRemoteSocketAddress().toString();
				System.out.println(IP + " connected / nu-mero  : " + numeroClient);
				// reception d'objet

				Request req = (Request) objectInputStream.readObject();
				switch (((Request) req).getType()) {
				case 1:
					String cne = (String) req.getObjet();
					Patient p = new DAOPatient().findPatientByIdentifier((String) cne);
					objectOutputStream.writeObject(p);

					break;
				case 2:
					System.out.println("Requet de type 2");
					Patient pat = (Patient) req.getObjet();
					// envoie liste des diagnostics
					List<Diagnostic> dia = (List<Diagnostic>) new DAODiagnostic()
							.retreive(new DAOPatient().findPatientByIdentifier(pat.getIdentifiant()).get_id());
					objectOutputStream.writeObject(dia);
					break;
				case 3:
					System.out.println("Requet de type 3");
					// envoie liste des symptomes
					Diagnostic diaog = (Diagnostic) new DAODiagnostic().AfficherDiagnostic((ObjectId) req.getObjet());
					objectOutputStream.writeObject(diaog);
					break;
				case 4:
					Patient pa = (Patient) req.getObjet();
					new DAOPatient().create(pa);
					Patient p1= new DAOPatient().findPatientByIdentifier(pa.getIdentifiant());
					objectOutputStream.writeObject(p1);
					break;
				case 5:
					String region = (String) req.getObjet();
					Collection<Ville> villes = new DAOAdress().VilleOfRegion(region);
					objectOutputStream.writeObject(villes);

					break;
				case 6:
					Collection<Region> regions = new DAOAdress().retreiveRegion();
					objectOutputStream.writeObject(regions);

					break;
				// Cas ou l'utilisateur demande un diagnostique
				case 7:
					System.out.println("Requet de type 7");
					// envoie liste des symptomes
					List<Symptom> sympt = (List<Symptom>) new DAOSymptom().retreive();
					objectOutputStream.writeObject(sympt);
					break;
				case 8:
					System.out.println("Requet de type 8");
					// envoie liste des symptomes
					ObjectId id = (ObjectId) req.getObjet();
					List<Historique> lh = (List<Historique>) new DAODiagnostic().histroriqueDiagno(id);
					objectOutputStream.writeObject(lh);
					break;
				case 9:
					System.out.println("Requet de type 9");
					Diagnostic d = (Diagnostic) req.getObjet();
					System.out.println(d);
					d.fireAll();
					objectOutputStream.writeObject(d);
					new DAODiagnostic().create(d);
					break;
				case 10:
					System.out.println("Requet de type 10");
					// envoie liste des maladies
					List<CronicDisease> x = (List<CronicDisease>) new DAOMaladie().retreive();
					objectOutputStream.writeObject(x);
					break;
				case 11:
					System.out.println("request de type 11 : find ville by name");
					String villename=(String) req.getObjet();
					Ville vl= new DAOAdress().findVilleByName(villename);
					objectOutputStream.writeObject(vl); break;
				default:
					break;
				}

			} catch (IOException e) {
				e.getMessage();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("En attente d'une requete ...");

		new ServerRequest().start();

	}

}
