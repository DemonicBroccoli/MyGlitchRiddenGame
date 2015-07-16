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

	private static Scanner scanner;
	private static String userInput;
	private static boolean gameRunning = true;
	private static String[] userActionsArray;
	
	public static void main(String[] args) throws IOException {
		
		//Initializes input object for text input
		scanner = new Scanner(System.in);
		userActionsArray = CharacterCommands.getCharacterCommands();
		userInput = "";
		
		//Define your game player here (health/name/description/etc...
		
		
		//Define your enemy here (health/name/description/etc...
		
		
		//Define startup value for game here such as number of turns played
		//number of turns remaining, and more.
		
		
		//This is where the game starts
		
		while(gameRunning)
		{
			
			userInput = getUserInput("Enter Input: ");
			outputText(userInput, 50);
			
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
	
}
