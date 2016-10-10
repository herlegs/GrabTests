package question3;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RESTApi {
	private final String API_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";

	public Location getLocation(String address) {
		String json = getJsonResponse(address);
		Location location = getLocationInfoFromJson(json);
		return location;
	}
	
	private String getJsonResponse(String address){
		if (address == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(API_URL + address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			conn.disconnect();
//			System.out.println(sb.toString());
		} catch (Exception e) {
			return null;
		}
		return sb.toString();
	}
	
	private Location getLocationInfoFromJson(String json){
		if(json == null){
			return null;
		}
		Location location = new Location();
		JsonObject response = new JsonParser().parse(json).getAsJsonObject();
		String status = response.getAsJsonPrimitive("status").getAsString();
		if(status.equals("OK")){
			JsonObject result = response.getAsJsonArray("results").get(0).getAsJsonObject();
			JsonObject locationJsonObject = result.getAsJsonObject("geometry").getAsJsonObject("location");
			double lat = locationJsonObject.getAsJsonPrimitive("lat").getAsDouble();
			double lng = locationJsonObject.getAsJsonPrimitive("lng").getAsDouble();
			location.setLatitute(lat);
			location.setLongitute(lng);
		}
		else{
			return null;
		}
		return location;
	}
}
