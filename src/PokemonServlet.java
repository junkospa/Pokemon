import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet ("/pokemon/*")
public class PokemonServlet extends HttpServlet {


	/**
	 * Servlet class to be called from a browser
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String requestUrl = request.getRequestURI();
		String name = requestUrl.substring("/Poke/pokemon/".length());
		
		System.out.println("Name:" + name);
		Pokemon poke = new Pokemon ();
		try {
			//RetrieveData rd = new RetrieveData();
			poke = RetrieveData.getPokemon(name);
		} catch (Exception e) {
			System.out.println("error:" + e);
			e.printStackTrace();
		}
		
		if(poke != null){
			String json = "{\n";
			json += "\"name\": " + JSONObject.quote(poke.getName()) + ",\n";
			json += "\"description\": " + JSONObject.quote(poke.getDescription()) + ",\n";
			json += "}";
			response.getOutputStream().println(json);
		}
		else{
			//That pokemon wasn't found, so return an empty JSON object. We could also return an error.
			response.getOutputStream().println("{}");
		}
	}
	

}
