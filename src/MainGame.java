import java.io.IOException;
import java.util.Scanner;

/**
 * This class file represents the entrance to a text based game.
 * When you run this program, the loop stops each time to read input
 * from the user as you will see below. 
 * 
 * Before the loop, initialize all variables and things you want to use
 * during the game. This includes story text if you want.
 * 
 * After the loop, the game has ended, so you should end the game as you
 * see fit here.
 */
public class MainGame {
    private static int NB = 15; // Amount of damage you can sustain and the amount of points you can spend on stats
    private static int DV = 0; // Your dodge
    private static int Gun = 0; // Your bonus to hit
    private static int Defence = 0; // Your armor
    private static int ENB = 15;  // Amount of damage enemy can sustain
    private static int EDV = 0; // enemy dodge value
    private static int EGun = 0; // enemy gun bonus
    private static int EDefence = 0; // enemy armor
	private static Scanner scanner;
	private static String userInput;
	private static boolean gameRunning = true;
	private static String[] userActionsArray;
	
	public static void main(String[] args) throws IOException {
		
		//Initializes input object for text input
		scanner = new Scanner(System.in);
//		userActionsArray = CharacterCommands.getCharacterCommands();
		userInput = "";
		
		//Define your game player here (health/name/description/etc...

		
		//Define your enemy here (health/name/description/etc...
		
		

		
		//Define startup value for game here such as number of turns played
		//number of turns remaining, and more.
		
		
		//This is where the game starts
		
		while(gameRunning)
		{
			System.out.println("Round Start");
			System.out.println("Set Your Stats");
			getStats(NB);
			System.out.println("COMBAT");
			userInput = getUserInput("Enter Input: ");
			if(userInput == "fire" || userInput == "Fire" || userInput == "FIRE")
				ENB = returnNB(EDV, Gun, EDefence);
			outputText(userInput, 50);
			
			
			if(NB <= 0 || ENB <= 0) gameRunning = false;
			//gameRunning = false; // <------- Ends the game when run
		}
		
		//Game is over
	}
	
	private static String getUserInput(String preText)
	{
		System.out.print(preText);
		return scanner.nextLine();
	}
	
	/**
	 * Outputs the text entered in at the speed specified in milliseconds
	 * @param output The string to print out at the given speed
	 * @param speed The milliseconds to wait before printing next character of input
	 */
	private static void outputText(String output, int speed)
	{
		try {
			
			int lineLength = 20; //The width of any line of text
			for(int i = 0; i < output.length(); i++)
			{
				System.out.print(output.charAt(i));  //Prints character in string at location i
				
				if(i < output.length() - 1 && output.charAt(i + 1) != ' ') //Only pauses if non whitespace is next
					Thread.sleep(speed); //Pauses the program for "speed" milliseconds
				
				//If the current character printed is a multiple of "lineLength", go to new line
				if(i % lineLength == 0 && i != 0)
					System.out.print("\n");
			}
			System.out.print("\n");
			
		} catch (InterruptedException e) {
			System.out.println("Error: Sleep thread crashed");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static int returnNB(int Dv, int Guns, int Armor)
	{
		int random = (int )(Math.random() * 20 + 1);
		if((random + Guns) > Dv) {
		int damage = (int )(Math.random() * 8 + 1);
		return (damage + Guns) - Armor; }
		else return 0;
	}
	public static void getStats(int EB)  // Where EB represents the number of crew.
	{
		boolean satisfied = false;
		while (!satisfied)  {
			int NewEB = EB;
			int NewDV = 0;
			int NewGun = 0;
			int NewDefence = 0;
		System.out.println("You have " + NewEB + " Crew left");
		
		System.out.println("Type in the amount of Crew you want to use for Dodge");
		userInput = getUserInput("Enter Input: ");
		if(NewEB >= 0) {
		NewEB = NewEB - Integer.parseInt(userInput);
		NewDV = Integer.parseInt(userInput);
		System.out.println("Your Dodge value is: " + DV);
		}
		else System.out.println("You don't have enough Crew for that");
		
		System.out.println("You have " + NewEB + " Crew left");
		System.out.println("Type in the amount of Crew you want to use for Guns");
		userInput = getUserInput("Enter Input: ");
		if(NewEB >= 0) {
		NewEB = NewEB - Integer.parseInt(userInput);
		NewGun = Integer.parseInt(userInput);
		System.out.println("Your Targeting bonus is: " + Gun);
		}
		else System.out.println("You don't have enough Crew for that");
		
		System.out.println("You have " + NewEB + " Crew left");
		System.out.println("Type in the amount of Crew you want to use for Armor");
		userInput = getUserInput("Enter Input: ");
		if(NewEB >= 0) {
		NewEB = NewEB - Integer.parseInt(userInput);
		NewDefence = Integer.parseInt(userInput);
		System.out.println("Your Targeting bonus is: " + Defence);
		}
		else System.out.println("You don't have enough Crew for that");
		
		System.out.println("Your Dodge: " + NewDV + " Your target bonus: " + NewGun + " Your Armor Bonus: " + NewDefence);
		System.out.println("====================================================================");
		System.out.println("Is this satisfactory?  Yes/No");
		userInput = getUserInput("Enter Input: ");
		
		DV = NewDV;
		Gun = NewGun;
		Defence = NewDefence;
		
		if((userInput == "Yes" )||( userInput == "yes")) {satisfied = true;}
	}
	}
	
}
