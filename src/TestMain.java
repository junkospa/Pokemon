/*
Test class to test function for RetrieveData class
Before test, change the methods from private to public if necessary
and remove comment block for test

*/
public class TestMain {

	public static void main(String[] args) {
        System.out.println("Hello, World!");

        // To test getPokemon(String name)
        Pokemon temp = new Pokemon ();
        temp = RetrieveData.getPokemon("ditto");
        System.out.println("MAIN temp name:" + temp.getName());
        System.out.println("MAIN temp translation:" + temp.getDescription());

        
/*
 		// To test getDescription(String name)
        String description = new String ();
		try {
			description = RetrieveData.getDescription("ditto");
		} catch (Exception e) {
			e.printStackTrace();
		}

        System.out.println("MAIN description:" + description);

		// To test getTranslation(String description)
        String translation = new String();
        try {
			translation = RetrieveData.getTranslation(description);
		} catch (Exception e) {
			e.printStackTrace();
		}
        System.out.println("MAIN translation:" + translation);

      */  
	}

}
