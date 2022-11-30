package ma.fstm.ilisi.projet.model.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConnectionDB {

	
	public static MongoDatabase getDb() {

		MongoClient mongo = new MongoClient("localhost", 27017);
		return mongo.getDatabase("SystemeExpertCovid19");
	}

}
