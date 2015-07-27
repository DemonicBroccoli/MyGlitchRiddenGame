import java.io.IOException;
import java.util.Scanner;

/**
 * This class file represents the entrance to a text based game. When you run
 * this program, the loop stops each time to read input from the user as you
 * will see below.
 * 
 * Before the loop, initialize all variables and things you want to use during
 * the game. This includes story text if you want.
 * 
 * After the loop, the game has ended, so you should end the game as you see fit
 * here.
 */
public class MainGame {
	
	private static int numofitems = 0;
	
	private static int[] items = new int [10]; // This tells whether or not you have an item.
	
	private static String[] itemDescrStrings = new String [10]; // This describes the items name and discription.
	
	private static int damage = 0;
	private static int Crew = 15; // Amount of damage you can sustain and the
	// amount of points you can spend on stats
	
	private static int Gold = 30;
	private static int Scrap = 30;
	
	private static int DV = 0; // Your dodge
	private static int Gun = 0; // Your bonus to hit
	private static int Defence = 0; // Your armor
	
	private static int upgradeDV = 0;
	private static int upgradeGun = 0; 
	private static int upgradeDefence = 0;
	
	
	
	private static int enemyCrew = 15; // Amount of damage enemy can sustain
	private static int EDV = 10; // enemy dodge value
	private static int EGun = 3; // enemy gun bonus
	private static int EDefence = 4; // enemy armor
	private static int MISS = 2; // Where 0 = a miss and 1 = is a glance and 2 =
									// nil

	private static int NewEB = 0;
	private static int OldEB = 0;

	private static Scanner scanner;
	private static String userInput;
	private static boolean gameRunning = true;

	// private static String[] userActionsArray;

	public static void main(String[] args) throws IOException {
		// Initializes input object for text input
		scanner = new Scanner(System.in);
		// userActionsArray = CharacterCommands.getCharacterCommands();
		userInput = "";

		int round = 0;

		preGameQuestions();

		while (gameRunning) {
			int numOfCrewKilled = Crew;
			round += 1;

			outputText("Round Start", 50);
			outputText("Assign your crew to stations.", 50);

			getStats(Crew);

			System.out.println("Round  " + round);
			System.out.println("COMBAT");

			userInput = getUserInput("Enter Input: ");

			if (userInput.toLowerCase().equals("fire")) {
				returnDAMAGE(EDV, Gun, EDefence);

				damageOutput("the enemy");
				enemyCrew -= damage;
			} else
				outputText("What?", 50);
			if (enemyCrew > 0) {
				outputText("The Sulphoric gun turns toward you,",50);
				returnDAMAGE(DV, EGun, Defence);

				damageOutput("your");

				Crew -= damage;

				numOfCrewKilled -= Crew;
				if (numOfCrewKilled > 0)
					System.out.println("On no! " + numOfCrewKilled
							+ " Were killed in the blast!");
			}

			if (Crew <= 0 || enemyCrew <= 0)
				gameRunning = false;
			// gameRunning = false; // <------- Ends the game when run
		}
	endMessage(round);
		// Game is over
	}
	


	private static String getUserInput(String preText) {
		System.out.print(preText);
		return scanner.nextLine();
	}

	/**
	 * Outputs the text entered in at the speed specified in milliseconds
	 * 
	 * @param output
	 *            The string to print out at the given speed
	 * @param speed
	 *            The milliseconds to wait before printing next character of
	 *            input
	 */
	private static void outputText(String output, int speed) {
		try {

			int lineLength = 150; // The width of any line of text
			for (int i = 0; i < output.length(); i++) {
				System.out.print(output.charAt(i)); // Prints character in
													// string at location i

				if (i < output.length() - 1 && output.charAt(i + 1) != ' ') // Only
																			// pauses
																			// if
																			// non
																			// whitespace
																			// is
																			// next
					Thread.sleep(speed); // Pauses the program for "speed"
											// milliseconds

				// If the current character printed is a multiple of
				// "lineLength", go to new line
				if (i % lineLength == 0 && i != 0)
					System.out.print("\n");
			}
			System.out.print("\n");

		} catch (InterruptedException e) {
			System.out.println("Error: Sleep thread crashed");
			e.printStackTrace();
		}
	}

	public static void returnDAMAGE(int Dv, int Guns, int Armor) // calculates
																	// the
																	// damage,
																	// not
																	// actually
																	// returning
																	// anything,
																	// just
																	// setting a
																	// variable.
	{
		int random = (int) (Math.random() * 20 + 1);

		if ((random + Guns) > Dv) {
			int damage1 = (int) (Math.random() * 8 + 1);

			if (((damage1 + Guns) - Armor) <= 0)
				MISS = 1;

			else {
				MISS = 2;
				damage = ((damage1 + Guns) - Armor);
			}
		} else {
			MISS = 0;
			damage = 0;
		}
	}
	private static void damageOutput(String name) // Tells you how much damage was done.
	{
		if (MISS == 0)
			outputText(
					"BOOM! The gun fires, but the shell goes wide, missing " + name + " entirely!",
					50);
		else if (MISS == 1)
			outputText(
					"KABLAM! The gun fires, but the shell merely glances off " + name + " armor!",
					50);
		else if (damage < 5 && damage > 0)
			outputText(
					"BLAM! The gun fires, and the shell puts a good sized dent in " + name + " armor!",
					50);
		else if (damage < 7 && damage > 4)
			outputText(
					"GOOBOOMM! The gun fires, and the shell puts a hole in " + name + " hull!",
					50);
		else if (damage < 10 && damage > 6)
			outputText(
					"KABLOOM! The gun fires, tearing a large chunk out of " + name + " vessel!",
					50);
		else if (damage < 30 && damage > 9)
			outputText(
					"SHABLAMO! The gun fires, smashing straight through " + name + " hull!",
					50);

	}
	
	
private static void preGameQuestions()  // Just moved this down here for the sake of a simple main function.
{
	outputText(
			"Type 'story' if you want to read the story, (its pretty long) type 'ministory' if you want the small version.",
			50);
	outputText(" otherwise just slam enter", 50);

	userInput = getUserInput("Enter Input: ");
	if (userInput.toLowerCase().equals("story"))
		ReadStory(true);

	else if (userInput.toLowerCase().equals("ministory"))
		ReadStory(false);

	outputText(
			"Type 'help' if you want to read how to play otherwise just slam enter.",
			50);
	userInput = getUserInput("Enter Input: ");
	if (userInput.toLowerCase().equals("help")) {

		outputText(
				"Hi! You should just type a number then press enter, or type fire and then press enter.",
				50);
		outputText("Hope that clears up your problems! *Evil chuckle*", 50);
		
		
	}
	outputText("Type 'Shop' if you want to visit the store, otherwise, slam enter.",50);
	userInput = getUserInput("Enter Input: ");
	if(userInput.toLowerCase().equals("shop")) Shop(Gold, Scrap);

}


private static void endMessage(int round) // once again, just clearing the main function
{
	if (Crew <= 0)
		outputText(
				"One horrendous BANG and your tank crumples like wet paper, trapping you inside as it slowly burns.",
				50);
	else if (enemyCrew <= 0 && round <= 2)
		outputText(
				"A distant explosion, and the Sulphoric xclass explodes in a ball of bright light, you must have hit the fuel tank!",
				50);
	else if (enemyCrew <= 0 && round <= 4)
		outputText(
				"The Sulphoric Xclass crumples, guess they don't make em' like the used to.",
				50);
	else if (enemyCrew <= 0 && round <= 6)
		outputText(
				"The Sulphoric Xclass, under the weight of steady shell fire, collapses in a heap of steel and burning machinery.",
				50);
	else if (enemyCrew <= 0)
		outputText(
				"The Sulphoric tank finaly falls, hammered by many blows, on its side, Victory at Last!",
				50);
}
	
	private static void getStats(int Crew) { // This Function gets the stats for
												// your tank
		int originalCrew = Crew;
		boolean satisfied = false;

		while (!satisfied) {
			Crew = originalCrew;

			outputText(("Your Upgrades: Dodge: " + upgradeDV + ". Guns: " + upgradeGun + ". Armor: " + upgradeDefence + "."),50);
			
			DV = getStat(Crew, "Dodge") +upgradeDV;
			Crew -= DV;

			Gun = getStat(Crew, "Gun") +upgradeGun;
			Crew -= Gun;

			Defence = getStat(Crew, "Armor") +upgradeDefence;
			Crew -= Defence;

			System.out.println("Your Dodge: " + DV + " Your target bonus: "
					+ Gun + " Your Armor Bonus: " + Defence);
			System.out
					.println("====================================================================");
			System.out.println("Is this satisfactory?  Yes/No");
			userInput = getUserInput("Enter Input: ");

			if ((userInput.equals("Yes")) || (userInput.equals("yes")))
				satisfied = true;
		}

	}

	private static int getStat(int crewLeft, String stat) { // This one gets an
															// individual stat.
		NewEB = crewLeft;
		OldEB = crewLeft;
		int newStat = 0;

		System.out.println("You have " + NewEB + " Crew left");

		outputText(("Type in the amount of Crew you want to use for " + stat),
				20);

		userInput = getUserInput("Enter Input: ");

		NewEB = NewEB - Integer.parseInt(userInput);

		if (NewEB >= 0) {

			newStat = Integer.parseInt(userInput);

			System.out.println(("Your " + stat + " value is: ") + newStat);
			System.out.println("-----------------------------------------");
		} else {
			System.out.println("You don't have enough Crew for that");
			NewEB = OldEB;
			return 0;
		}
		OldEB = NewEB;
		return newStat;
	}

	private static void ReadStory(boolean story) { // ahem, this one gets the
													// *HUGE* story, and reads
													// it to you.

		if (story) {
			outputText(
					"The dull roar of the engine, The steady grind of the treads, ",
					50);
			outputText(
					"The sea of grass around us, The sky above our heads. - Nepher Poem",
					50);
			outputText(
					"'Nepher', a word meaning simply 'The People', a race of magicly geneticly altered rabbits bred only for war.",
					50);
			outputText(
					"You are one of these Rabbits, a Tank commander in a quiet, fallen world that has forgotten what a tank is.",
					50);
			outputText(
					"In the years since their creation by a mad wizard, the Nepher have risen above their violent nature.",
					50);
			outputText(
					"Nepher now roam the landscape in huge tanks, making fuel and alchohol from a secret grass fermentaion process",
					50);
			outputText(
					"The land of the Nepher is divided up into several sections, first, there are the plains,",
					50);
			outputText(
					"These cover most of the land, the plains themselves are cut apart by many slow moving rivers.",
					50);
			outputText(
					"This land you share with the 'Grarn' a race of nomadic, donkey riding, goblin folk.",
					50);
			outputText(
					"Second there are the citys, one on the northeastern shore, Borgle,",
					50);
			outputText(
					"second is the southeastern city of Selenneckia , and lastly, near the central mountains, is the Hold of Gresh.",
					50);
			outputText(
					"In the west are the great mountains, and from them most rivers emerge, the foothils are dotted with forests.",
					50);
			outputText(
					"And in the southwest lied the Nepher's most hated enemy, 'Sulphor'...",
					50);
			outputText(
					"For years the Nepher moved across this world in peace, selling fuel and alchohol to everybody.",
					50);
			outputText(
					"However, that was before Sulphor started a war with the Nepher that was to bring both races to ruin.",
					50);
			outputText(
					"The Sulphor are a human race, they lived in a squalid, dirty city with a great palace/temple in the center.",
					50);
			outputText(
					"In this Palace lived their godking, an insane overlord, who, thanks to a dream he had, started the war.",
					50);
			outputText(
					"The dream showed a massive robot, with a tower 'top its back, made out of steel gleaming like the sun.",
					50);
			outputText(
					"The godking decided that the dream was prophetic, and that they were supposed to build the robot -",
					50);
			outputText("To ensure the future of Sulphor", 50);
			outputText(
					"The art of steel making was lost to Sulphor long ago, and the only ready supply was in great rusty hulks -",
					50);
			outputText(
					"That had lain derelict for centurys, but this steel was of terrible quality.",
					50);
			outputText(
					"The only good steel for hundreds of miles was in the hands of the Nepher, in their tanks, specificly",
					50);
			outputText(
					"Outfitting a huge, barely working ancient Xclass tank with a magic engine, they set out to hunt the Nepher.",
					50);
			outputText(
					"They suprised the first Nepher they came across, seizing their tank, and soon they had a fleet of hunters.",
					50);
			outputText(
					"Captain Greneer Lok, of the Nepher, summoned all the Nepher to fight together to crush the Sulphors.",
					50);
			outputText(
					"Things did NOT go well, the city of Sulphor was turned to ash, and the tanks of the Nepher were destroyed.",
					50);
			outputText(
					"Well, most of the tanks, the few that were left returned to a much more empty world.",
					50);
			outputText(
					"The last of the Sulphor hunter fleet vowed to avenge their people, and hunted what was left of the Nepher",
					50);
		}
		// This is the MEGA story.

		// This is the mini story.
		outputText(
				"Thats the world you come into, a tank commander in a world that no longer has tank commanders.",
				50);
		outputText(
				"Still, you hold to the traditions of your people, roaming and making fuel and alchohol out of grass.",
				50);
		outputText(
				"You move from town to town, village to village, nomad camp to nomad camp,",
				50);
		outputText(
				"And each place it is the same, the people stare open mouthed, at the first tank they have seen in years.",
				50);
		outputText(
				"Things continue like this for a while, but one day you gaze around at the ever green landcape -",
				50);
		outputText(
				"Only to detect a darker shade on the horizon, it grows steadily larger.",
				50);
		outputText(
				"You grab a pair of binoculars, its a Sulphoric Xclass, perhaps the last one, and its heading right for you!",
				50);
		outputText(
				"Its magic engine and powerful treads are much faster than yours, you have no choice but to fight or die...",
				50);
		outputText(
				"It comes withing firing distance... (don't type anything but fire in combat pweasee)",
				50);
	}
	
	
	
	
	private static void addItem(int itemSlot, int itemNum, int stringSlot, String describeItem)
	{
		items[itemSlot] = itemNum;
		itemDescrStrings[stringSlot] = describeItem;
	}
		private static void listItems()
		{
			for(int I = 0;I  <	items.length;I++)
			{
				if(itemDescrStrings[I] != null && items[I] != 0)
					System.out.println(("Item " + items[I] + ": " + itemDescrStrings[I]));
			}
		}
		
		
		private static void Shop(int gold, int scrap)
		{
			boolean end = false;
			while(!end)
			{
				gold = Gold;
				scrap = Scrap;
			int scrapprice = 0;
			int price = 0;
			
			outputText("Hi, welcome to the shop! Here you can buy upgrades, items, fuel, and party hats!",50);
			System.out.println("===============================================================================");
			System.out.println("Items:");
			System.out.println("1. Binoculars == 10 gold coins");
			System.out.println("2. 10 gold coins == 20 scrap");
	        System.out.println("3. 10 scrap == 20 gold coins");
	        System.out.println("4. 10 scrap == 1 upgrade to your tank");
	        System.out.println("5. 1 new crew member == 10 gold coins");
	        System.out.println("6. 1 semi automatic sniper rifle == 5 gold coins");
	        System.out.println("7. Rabit Fewd for one week == 5 gold coins");
	        System.out.println("8. Party hats == 4 gold coins" );
	        System.out.println("9. Leave Shop Without Buying anything.");
	        System.out.println("================================================================================");
	        System.out.println("You have " + Gold + " gold coins, You have " + Scrap + " scrap" );
	        System.out.println("================================================================================");
	        System.out.println("Please Enter the number of your choice.");
	    	userInput = getUserInput("Enter Input: ");
		
	    	
	    
	    	  
	    	   if(userInput.equals("4") || userInput.equals("2")) scrapprice =	getItemPrice(userInput,0);
	    else price = getItemPrice(userInput,0);
	    	
	   
	  
	    	   if(Gold < price || Scrap < scrapprice)
			System.out.println("You don't have enough stuff for that");
	   
	    else if(userInput.equals("4"))
		    UpgradeTank();
	    
	    else if(userInput.equals("2")) {Gold += 10;
	    scrap -= 20;}
	    
	    else if(userInput.equals("3")) {Gold -= 20;
	    scrap += 10;}
	    
	    else if(userInput.equals("5")) {Gold -= 10;
	    Crew += 1;
	    System.out.println("You now have " + Crew + " Crew.");}
	    
	    else if(userInput.equals("9")) end = true;
	    	   
	    else { addItem(numofitems,1,numofitems,getItemDescr(userInput) );
	    
       listItems();
       Gold -= price;
		numofitems++;}

		}
			} 
		public static void UpgradeTank()
		{
			System.out.println("Which stat would you like to upgrade?");
			System.out.println("1 = Dodge. 2 = Guns. 3 = Armor.");
			
			userInput = getUserInput("Enter Input: ");
			if(userInput.equals("1")) {upgradeDV += 1;
			Scrap = Scrap - 10;}
			else if(userInput.equals("2")) {upgradeGun += 1;
			Scrap = Scrap - 10;}
			else if(userInput.equals("3")) {upgradeDefence += 1;
			Scrap = Scrap - 10;
			}
			else outputText("Nah, I have no idea what you just said.",50);
		}
		
		
		
		public static String getItemDescr(String item)
		{
			String description = "";
			
			if (item.equals ("1")) {
				description = "Binculars, useful for looking at things.";
			}  else if (item.equals ("6")) {
				description = "Semi automatic sniper rifle, shame there's no amunition.";
			} else if (item.equals ("7")) {
				description = "Fewd, maybe you should have a party?";
			} else if (item.equals ("8")) {
				description = "Party Hats! wop. wop. wop. wop. wop. wop. wop. maybe you should have a party?";
			} 
			return description;
		}
		public static int getItemPrice(String item,int price) {
			if (item.equals ("1")) {
				price = 10;
			} else if (item.equals ("2")) {
				price = 20;
			} else if (item.equals ("3")) {
				price = 20;
			} else if (item.equals ("4")) {
				price = 10;
			} else if (item.equals ("5")) {
				price = 10;
			} else if (item.equals ("6")) {
				price = 5;
			} else if (item.equals ("7")) {
				price = 5;
			} else if (item.equals ("8")) {
				price = 4;
			} else if (item.equals ("9")) {
				price = 0;
			}
			return price;
		}
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
