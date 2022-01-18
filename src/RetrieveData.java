
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONObject;
import org.json.JSONArray;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.*;

/**
 * Retrieve Pokemon description from name from PokeAPI
 * Then translate the description using Shakespere translater API
 */
public class RetrieveData {

    static String speciesBaseURL = "https://pokeapi.co/api/v2/pokemon-species/";
    static String shakespeareURL = "https://api.funtranslations.com/translate/shakespeare.json";

	
    //Return Pokemon object from name
	public static Pokemon getPokemon(String name) {
        Pokemon poke = new Pokemon();
        poke.setName(name);
        System.out.println("name in getPokemon: " + name);
        
        try{
        // Retrieve Pokemon description from name
		String description = getDescription(name);

        // Retrieve translation from description
        String translation = getTranslation(description);
        System.out.println("translation: " + translation);
        poke.setDescription(translation);
        
        }catch (Exception e) {
            e.printStackTrace();
        }
        return poke;
	}

	

    //Get the Pokemon description from species API endpoint
    //Return: description
    private static String getDescription(String pokename) throws Exception{
        String description = new String();

        try{
            URL url = new URL(speciesBaseURL +  pokename);
            System.out.println (url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Mozilla");
            
            //print the status code from the response
            int responsecode = con.getResponseCode();
            System.out.println (responsecode);

            if (responsecode > 299) {
                //throw new RuntimeException("HttpResponseCode: " + responsecode);
            	description = "";
            } else {
            	 
            	//InputStream is = url.openStream();
            	InputStream is = con.getInputStream();
            	
            	try {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                    System.out.println (rd);
                    String jsonText;
                    StringBuilder sb = new StringBuilder();
                    int cp;
                    while ((cp = rd.read()) != -1) {
                        sb.append((char) cp);
                    }
                    jsonText = sb.toString();
                    System.out.println (jsonText);
                    JSONObject jobj = new JSONObject(jsonText);

                    if (jobj.getJSONArray("flavor_text_entries").length() > 0) {
                        //flavor_text_entries
                        JSONArray flaver = jobj.getJSONArray("flavor_text_entries");
                        JSONObject flaverObj = flaver.getJSONObject(0);
                        description = flaverObj.getString("flavor_text");
                        
                        System.out.println ("description:" + description);
                    }
                            
                } finally {
                    is.close();
                }
            }
            con.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return description;
    }

    //Get the Shakespeare translation from description
    //Return: translation
    private static String getTranslation(String description) throws Exception{
            String translation = new String();
            System.out.println("Description IN:" + description);

            try{          
                //CloseableHttpClient client = HttpClientBuilder.create().build();
                CloseableHttpClient client = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(shakespeareURL);
               
                // set the POST request 
                //String urlParameters = "text=" + description;
                //byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
                //int    postDataLength = postData.length;
                StringEntity params = new StringEntity("text=" + description);
                httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
                httpPost.setEntity(params);
    
                try {
                    //closeablehttp response
                    CloseableHttpResponse response = client.execute(httpPost);
    
                    // print the status code from the response
                    int responsecode = response.getStatusLine().getStatusCode();
                    System.out.println(responsecode);
    
                    // take the response body as a json formatted string 
                    String responseJSON = EntityUtils.toString(response.getEntity());
                    
                    JSONObject jobj = new JSONObject(responseJSON);
                    System.out.println("jobj:" + jobj);

                    if (jobj.getJSONObject("contents") != null) {
                        //Get translation
                        JSONObject g = jobj.getJSONObject("contents");
                        translation = g.getString ("translated");
                    }
    
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            return translation;
        } 

}