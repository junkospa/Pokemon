
public class Pokemon {
	private String name;
	private String description;
	
	public Pokemon(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public Pokemon(){
	}
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
