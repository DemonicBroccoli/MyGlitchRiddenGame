/**
 * Simple class meant to be used statically to access
 * an array of character commands in String form.
 */
public class CharacterCommands {

	//This is an array of character commands
	private static final String[] characterCommands = {
		"write",
		"some",
		"commands",
		"here"
	};
	
	public static String[] getCharacterCommands()
	{
		return characterCommands;
	}
}
