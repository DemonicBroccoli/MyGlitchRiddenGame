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
    private static int damage = 0;
	private static int NB = 15; // Amount of damage you can sustain and the amount of points you can spend on stats
    private static int DV = 0; // Your dodge
    private static int Gun = 0; // Your bonus to hit
    private static int Defence = 0; // Your armor
    private static int ENB = 15;  // Amount of damage enemy can sustain
    private static int EDV = 0; // enemy dodge value
    private static int EGun = 0; // enemy gun bonus
    private static int EDefence = 5; // enemy armor
    private static int MISS = 2; // Where 0 = a miss and 1 = is a glance and 2 = nil.
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
		
		for(;gameRunning;)
		{
		    outputText("Round Start",50);
			outputText("Assign your crew to stations.",50);
			
			getStats(NB);
			System.out.println("COMBAT");
			
			userInput = getUserInput("Enter Input: ");
			
			if(userInput == "fire" || userInput == "Fire" || userInput == "FIRE") {
			 returnDAMAGE(EDV, Gun, EDefence); }
			
			if(MISS == 0) outputText("BOOM! The gun fires, but the shell goes wide, missing the enemy entirely!" , 50);
			else if(MISS == 1) outputText("KABLAM! The gun fires, but the shell merely glances off the enemy armor!",50);
			else if(damage < 5 && damage > 0) outputText("BLAM! The gun fires, and the shell puts a good sized dent in the enemy's armor!",50);
			else if(damage < 7 && damage > 4) outputText("GOOBOOMM! The gun fires, and the shell puts a hole in the enemy hull!",50);
			else if(damage < 10 && damage > 6) outputText("KABLOOM! The gun fires, tearing a large chunk out of the enemy vessel!",50);
			else if(damage < 30 && damage > 9) outputText("SHABLAMO! The gun fires, smashing straight through the enemy hull!",50);
			
			ENB = ENB - damage;
			
			if(NB <= 0 || ENB <= 0) gameRunning = false;
			//gameRunning = false; // <------- Ends the game when run
		}
		if(NB <= 0) System.out.println("One horrendous BANG and your tank crumples like wet paper, trapping you inside as it slowly burns.");
		if(ENB <= 0) System.out.println("A distant explosion, and the Sulphoric xclass explodes in a ball of bright light, you must have hit the fuel tank!");
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
			
			int lineLength = 100; //The width of any line of text
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
	
	
	
	
	
	public static void returnDAMAGE(int Dv, int Guns, int Armor) // calculates the damage, not actually returning anything, just setting a variable.
	{
		int random = (int )(Math.random() * 20 + 1);
		
		if((random + Guns) > Dv) {
		int damage1 = (int )(Math.random() * 8 + 1);
		
		if(((damage1 + Guns) - Armor) <= 0) MISS = 1;
		
		else MISS = 2;
		damage = ((damage1 + Guns) - Armor);
		}
		else {MISS = 0;
	    damage = 0;}
	}
	public static void getStats(int EB)  // Where EB represents the number of crew.
	{
		boolean satisfied = false;
		while (!satisfied)  {
			int NewEB = EB;
			int OldEb = EB;
			int NewDV = 0;
			int NewGun = 0;
			int NewDefence = 0;
		System.out.println("You have " + NewEB + " Crew left");
		
		outputText("Type in the amount of Crew you want to use for Dodge",20);
		
		userInput = getUserInput("Enter Input: ");
		
		NewEB = NewEB - Integer.parseInt(userInput);
		
		if(NewEB >= 0) {
		
		NewDV = Integer.parseInt(userInput);
		
		System.out.println("Your Dodge value is: " + NewDV);
		System.out.println("-----------------------------------------");
		}
		else  {System.out.println("You don't have enough Crew for that");
		NewEB = OldEb; }
		OldEb = NewEB;
		
		System.out.println("You have " + NewEB + " Crew left");
		
		outputText("Type in the amount of Crew you want to use for Guns",20);
		
		userInput = getUserInput("Enter Input: ");
		
		NewEB = NewEB - Integer.parseInt(userInput);
		
		if(NewEB >= 0) {
		
		NewGun = Integer.parseInt(userInput);
		
		System.out.println("Your Targeting bonus is: " + NewGun);
		System.out.println("-----------------------------------------");
		}
		else {System.out.println("You don't have enough Crew for that");
		NewEB = OldEb; }
		OldEb = NewEB;
		
		System.out.println("You have " + NewEB + " Crew left");
		
		outputText("Type in the amount of Crew you want to use for Armor",20);
		
		userInput = getUserInput("Enter Input: ");
		
		NewEB = NewEB - Integer.parseInt(userInput);
		
		if(NewEB >= 0) {
		
		NewDefence = Integer.parseInt(userInput);
		
		System.out.println("Your Targeting bonus is: " + NewDefence);
		System.out.println("-----------------------------------------");
		}
		else {System.out.println("You don't have enough Crew for that");
		NewEB = OldEb;}
		OldEb = NewEB;
		
		System.out.println("Your Dodge: " + NewDV + " Your target bonus: " + NewGun + " Your Armor Bonus: " + NewDefence);
		System.out.println("====================================================================");
		System.out.println("Is this satisfactory?  Yes/No");
		userInput = getUserInput("Enter Input: ");
		
		DV = NewDV;
		Gun = NewGun;
		Defence = NewDefence;
		
		if((userInput.equals("Yes") )||( userInput.equals("yes"))) satisfied = true;
	}
	}
	
}
