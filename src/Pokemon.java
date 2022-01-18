/*
 * Pokemon data class
 * Store name and description
 * 
 */
public class Pokemon {
	private String name;
	private String description;
	

	public void setName (String n){
        	name = n;
    	}
	public void setDescription (String d){
        	description = d;
    	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
}
