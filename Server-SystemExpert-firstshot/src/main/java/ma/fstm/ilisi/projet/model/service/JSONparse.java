package ma.fstm.ilisi.projet.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeType;
import com.sbix.jnotify.NoticeWindow;

import ma.fstm.ilisi.projet.model.bo.Region;
import ma.fstm.ilisi.projet.model.dao.DAOAdress;

public class JSONparse implements Runnable {

	private Thread ref;

	public Thread getRef() {
		return ref;
	}

	public void setRef(Thread ref) {
		this.ref = ref;
	}

	public JSONparse() {
		ref = new Thread(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		int m = 0;
		while (true) {
			++m;
			String url = "https://services3.arcgis.com/hjUMsSJ87zgoicvl/arcgis/rest/services/Covid_19/FeatureServer/0/query?where=1%3D1&outFields=RegionFr,Cases,Deaths,Recoveries&returnGeometry=false&outSR=4326&f=json";
			
			try {
				JSONObject jr = JSONparse.parser(url);
				JSONArray firstT = jr.getJSONArray("features");
				for (int i = 0; i != firstT.length(); i++) {
					JSONObject atb = firstT.getJSONObject(i);
					JSONObject info = atb.getJSONObject("attributes");
					String nomReg = info.getString("RegionFr");
					int nbmort = info.getInt("Deaths");
					int nbRecov = info.getInt("Recoveries");
					int nbCase = info.getInt("Cases");
					Region rg = new DAOAdress().findRegionByName(nomReg);
					/// updates of data
					//rg.setPopulationPositif(-rg.getTotalPositif());
					rg.setTotalPositif(nbCase);
					rg.setRecovery(nbRecov);
					rg.setDeath(nbmort);
					rg.setTotDeath(rg.getTotDeath() + nbmort);
					rg.setTotRecovery(nbRecov + rg.getTotRecovery());

					new DAOAdress().update(rg);

					System.out.println("nom de la region " + nomReg);
					System.out.println("nombre de mort " + nbmort);
					System.out.println("nombre des gens retablis " + nbRecov);
					System.out.println("nombre totale des cas positifs " + nbCase);
					System.out.println(rg.getPopulation()+" le nombre des cas positif  "+rg.getPopulationPositif());
					if(rg.getPopulationPositif()>17150)
						new NoticeWindow(NoticeType.ERROR_NOTIFICATION,rg.getRegionName()+" est dangereuse !",NoticeWindow.LONG_DELAY,NPosition.BOTTOM_LEFT);
					else 
						new NoticeWindow(NoticeType.SUCCESS_NOTIFICATION,rg.getRegionName()+" n'est pas dangereuse !",NoticeWindow.NORMAL_DELAY,NPosition.BOTTOM_RIGHT);

				}
				System.out.println(m + " times!");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				new NoticeWindow(NoticeType.DEFAULT_NOTIFICATION," vous n'aves pas de connexion !",NoticeWindow.NORMAL_DELAY,NPosition.BOTTOM_RIGHT);

			}

			// System.out.println(jr);
			try {
				Thread.sleep(10000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public JSONObject readJsonFromUrl(String link) throws IOException, JSONException {
		InputStream input = new URL(link).openStream();
		// Input Stream Object To Start Streaming.
		try { // try catch for checked exception
			BufferedReader re = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			// Buffer Reading In UTF-8
			String Text = Read(re); // Handy Method To Read Data From BufferReader
			// System.out.println("hhhhhh"+Text);
			// JSONArray jr=new JSONArray(Text);
			JSONObject json = new JSONObject(Text); // Creating A JSON
			// System.out.println("kkkkk"+json);
			return json; // Returning JSON
		} catch (Exception e) {
			return null;
		} finally {
			input.close();
		}
	}

	/**
	 * 
	 */

	public String Read(Reader re) throws IOException { // class Declaration
		StringBuilder str = new StringBuilder(); // To Store Url Data In String.
		int temp;
		do {

			temp = re.read(); // reading Charcter By Chracter.
			str.append((char) temp);

		} while (temp != -1);
		// re.read() return -1 when there is end of buffer , data or end of file.

		return str.toString();

	}

	public static JSONObject parser(String url) {
		JSONparse reader = new JSONparse();
		JSONObject jr = null;
		try {
			jr = reader.readJsonFromUrl(url);
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jr;
	}

	/**
	 * 
	 */
	public static void main(String[] args) throws IOException, JSONException {
		// String url =
		// "https://services3.arcgis.com/hjUMsSJ87zgoicvl/arcgis/rest/services/Covid_19/FeatureServer/0/query?where=1%3D1&outFields=RegionFr,Cases,Deaths,Recoveries&returnGeometry=false&outSR=4326&f=json";
		// // example url which return json data
		// JSONObject jr=JSONparse.parser(url);
		// System.out.println(jr);

		JSONparse info = new JSONparse();
		info.getRef().start();

	}
}
