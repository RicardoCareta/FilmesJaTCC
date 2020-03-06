package util;
import java.io.FileWriter;

import org.json.simple.JSONObject;

public class CreateJSON {

	private static final String KEY_MAGNET = "mTorrent";

	private String magnet_link = "";
	
	public void create(String Magnet) {
		this.magnet_link = Magnet;
		mainCreate();
	}

	@SuppressWarnings("unchecked")
	private void mainCreate() {
		JSONObject objectJson = new JSONObject();

		FileWriter wf = null;

		objectJson.put(KEY_MAGNET, magnet_link);
		try {
			wf = new FileWriter(System.getProperty("user.dir")+ "\\TorrentServer\\torrent.json");
			wf.write(objectJson.toJSONString());
			wf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
