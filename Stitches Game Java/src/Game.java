import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
//Dan Swezey
//Software Development One: CMPT220L-111
//"Stitches"
import java.util.Random;
public class Game {

    //
    // Public
    //

    // Globals
    private static Locale currentLocale = null;      // Player starts in locale 0.
    private static String command;                   // What the player types as he or she plays the game.
    private static boolean stillPlaying = true;      // Controls the game loop.
    private static int[][]  nav;                     // An uninitialized array of type int int.
    private static double moves = 0;                 // Counter of the player's moves.
    private static double score = 0;                 // Tracker of the player's score.
    private static double achievement = 0;           // gives the ratio of points/number of moves.
    private static Item[] bag;                       // Inventory
    private static int janitor = 0;
    private static String map0 = "";   
    private static String map1 = ""; 
    private static String map2 = ""; 
    private static String map3 = ""; 
    private static String map4 = "";                //All these maps are for individual room locations
    private static String map5 = ""; 
    private static String map6 = ""; 
    private static String map7 = ""; 
    private static String map8 = ""; 
    private static String map9 = ""; 
    private static String map10 = ""; 
    private static int key = 0;                      //This is to make sure you can't leave unless you have the appropriate keys
    private static Currency money = new Currency( "cash"); 
    private static Random coins = new Random();				
    private static ListMan magicItems  = new ListMan();
    private static ListItem purchase = new ListItem();
    private static Scanner transactionDone = new Scanner(System.in);
    public static int num = 9999;
    static ArrayList<String> newBag = new ArrayList<String>();
    private static boolean unlock = false;
    private static boolean unlock1 = false;
    private static boolean mapFound = false;    
    private static ListItem[] storeInventory = new ListItem[666];
    private static Stack lookUpDown = new Stack();
    private static Queue lookDownUp = new Queue();
    
    
    public static void main(String[] args) {
        
        // Get the game started.
        init();
        try {
        lookUpDown.push(currentLocale);
        lookDownUp.enqueue(currentLocale);
    } catch (Exception e){
    	e.printStackTrace();
        updateDisplay();
    }
        // Game Loop
        while (stillPlaying) {
            getCommand();
            navigate();
            updateDisplay();
        }

        // We're done. Thank the player and exit.
        System.out.println("Thank you for playing.");
    }

    //
    // Private
    //
    private static void mapAscii(){//player map with individual locations indicated
    	if(mapFound == true){
    		if (currentLocale.getId()==0){
		    	System.out.println("The '*' indicates your current location");
		    	map0=    " ____________________________________________________________________________________________________________                \n"+
		    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
		    			"|      |                     |                       |                ^         ^             Emergency Exit |        ^       \n"+
		    			"|      |                     |                       |                |         |                     |      |        |       \n"+
		    			"|      |                     |                       |                v         |                     |      |  W <---+---> E \n"+
		    			"|*Operation Room A --- Operation Room B -------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
		    			"|                                                    |                                                       |        v       \n"+
		    			"|                                                Entrance                                                    |        S       \n"+
		    			"|____________________________________________________________________________________________________________|                  ";
		    	System.out.println(map0);
    		}else if (currentLocale.getId()==1){
		    	System.out.println("The '*' indicates your current location");
		    	map1=   " ____________________________________________________________________________________________________________                 \n"+
		    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
		    			"|      |                     |                       |                ^         ^             Emergency Exit |        ^       \n"+
		    			"|      |                     |                       |                |         |                     |      |        |       \n"+
		    			"|      |                     |                       |                v         |                     |      |  W <---+---> E \n"+
		    			"|Operation Room A --- *Operation Room B -------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
		    			"|                                                    |                                                       |        v       \n"+
		    			"|                                                Entrance                                                    |        S       \n"+
		    			"|____________________________________________________________________________________________________________|                  ";
		    	System.out.println(map1);
    		}else if (currentLocale.getId()==2){
    			System.out.println("The '*' indicates your current location");
    	    	map2=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                ^         ^             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                v         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                *Entrance                                                   |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map2);
    		}else if (currentLocale.getId()==3){
    			System.out.println("The '*' indicates your current location");
    	    	map3=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                ^         ^             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                v         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|--------*Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map3);
    		}else if (currentLocale.getId()==4){
    			System.out.println("The '*' indicates your current location");
    	    	map4=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                ^         ^             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                v         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|--------Waiting Room --- *Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map4);
    		}else if (currentLocale.getId()==5){
    			System.out.println("The '*' indicates your current location");
    	    	map5=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                ^         ^             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                v         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|--------Waiting Room --- Bathroom --- *Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map5);
    		}else if (currentLocale.getId()==6){
    			System.out.println("The '*' indicates your current location");
    	    	map6=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                ^         ^            *Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                v         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|--------Waiting Room --- Bathroom --- Janitor's Closet |        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map6);
    		}else if (currentLocale.getId()==7){
    			System.out.println("The '*' indicates your current location");
    	    	map7=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|*Recovery Room A ---- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                ^         ^             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                v         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map7);
    		}else if (currentLocale.getId()==8){
    			System.out.println("The '*' indicates your current location");
    	    	map8=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ---- *Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                ^         ^             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                v         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map8);
    		}else if (currentLocale.getId()==9){
    			System.out.println("The '*' indicates your current location");
    	    	map9=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ---- Recovery Room B ----- *Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                ^         ^             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                v         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map9);
    		}else if (currentLocale.getId()==10){
    			System.out.println("The '*' indicates your current location");
    	    	map10=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ---- Recovery Room B ----- Medical Supply Room ------ *Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                ^         ^             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                v         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map10);
    		} 
    		
    		
    	} else {
			System.out.println("You have not found the map.");
		}
    }

    private static void init() {
        // Initialize any uninitialized globals.
        command = new String();
        stillPlaying = true; 

        // Set up the location instances of the Locale class.
        Operation loc0 = new Operation(0);
        loc0.setName("Operation Room A");
        loc0.setDesc("Looks like there is a map on the floor.");
        loc0.setOperationTable("This is the table you woke up on. You still feel uneasy about that.");
        loc0.setNav("North:Available, South:N/A, East:Available, West:N/A");

        Operation loc1 = new Operation(1);
        loc1.setName("Operation Room B");
        loc1.setDesc("You can take that scalpel over there.");
        loc1.setOperationTable("The table is covered in filthy operating tools.");
        loc1.setNav("North:Available, South:N/A, East:Available, West:Available");
        
        Locale loc2 = new Locale(2);
        loc2.setName("Entrance");
        loc2.setDesc("Seems to be locked. There must be a master key somewhere!");
        loc2.setNav("North:Available, South:N/A, East:Available, West:Available");
        
        Locale loc3 = new Locale(3);
        loc3.setName("Waiting Room");
        loc3.setDesc("So empty...");
        loc3.setNav("North:Available, South:N/A, East:Available, West:Available");
        
        Locale loc4 = new Locale(4);
        loc4.setName("Bathroom");
        loc4.setDesc("Look at all the available toilet paper!");
        loc4.setNav("North:Available, South:N/A, East:Available, West:Available");
        
        Locale loc5 = new Locale(5);
        loc5.setName("Janitor's Closet");//"Magick Shoppe"
        loc5.setDesc("I wonder what is in here. And it smells like old man in here...");
        loc5.setNav("North:Available, South:N/A, East:N/A, West:Available");
        
        Locale loc6 = new Locale(6);
        loc6.setName("Emergency Exit");
        loc6.setDesc("Seems to be locked. There has to be a master key somewhere!");
        loc6.setNav("North:N/A, South:Available, East:N/A, West:Available");
        
        Recovery loc7 = new Recovery(7);
        loc7.setName("Recovery Room A");
        loc7.setDesc("That bed looked like it was comfortable");
        loc7.setRecoveryBed("Upright and tidy, looks comfortable!");
        loc7.setNav("North:N/A, South:Available, East:Available, West:N/A");
        
        Recovery loc8 = new Recovery(8);
        loc8.setName("Recovery Room B");
        loc8.setDesc("There's dust everywhere");
        loc8.setRecoveryBed("Flipped over and broken!");
        loc8.setNav("North:N/A, SouthAvailable:, East:Available, West:Available");
        
        Locale loc9 = new Locale(9);
        loc9.setName("Medical Supply Room");
        loc9.setDesc("The door is locked, but you can see a key ring inside.");
        loc9.setNav("North:N/A, South:Available, East:Available, West:Available");
        
        Locale loc10 = new Locale(10);
        loc10.setName("Cafeteria");
        loc10.setDesc("What a mess... There is something shiny over there!");
        loc10.setNav("North:N/A, South:Available, East:N/A, West:Available");
        
        // Item array
        Item item0 = new Item(0);
        item0.setName("Map");
        item0.setDesc("A blueprint of this place!");
        
        Item item1 = new Item(1);
        item1.setName("Scalpel");
        item1.setDesc("Slathered in blood");
        
        Item item2 = new Item(2);
        item2.setName("Toilet Paper");
        item2.setDesc("Never know when this could come in handy!");
        
        Item item3 = new Item(3);
        item3.setName("Medical Supply Key");
        item3.setDesc("I hope this can open the Medical Supply Closet!");
        
        Item item4 = new Item(4);
        item4.setName("Master Key");
        item4.setDesc("Can this be the key out of here?!?!?");
        
        bag = new Item[5];
        bag[0] = item0;
        bag[1] = item1;
        bag[2] = item2;
        bag[3] = item3;
        bag[4] = item4;

        // Linking up the Locales5
        loc0.setNorth(loc7);
        loc0.setSouth(null);
        loc0.setEast(loc1);
        loc0.setWest(null);
        
        loc1.setNorth(loc8);
        loc1.setSouth(null);
        loc1.setEast(loc2);
        loc1.setWest(loc0);
        
        loc2.setNorth(loc9);
        loc2.setSouth(null);
        loc2.setEast(loc3);
        loc2.setWest(loc1);
        
        loc3.setNorth(loc10);
        loc3.setSouth(null);
        loc3.setEast(loc4);
        loc3.setWest(loc2);
        
        loc4.setNorth(loc10);
        loc4.setSouth(null);
        loc4.setEast(loc5);
        loc4.setWest(loc3);
        
        loc5.setNorth(loc6);
        loc5.setSouth(null);
        loc5.setEast(null);
        loc5.setWest(loc4);
        
        loc6.setNorth(null);
        loc6.setSouth(loc5);
        loc6.setEast(null);
        loc6.setWest(null);
        
        loc7.setNorth(null);
        loc7.setSouth(loc0);
        loc7.setEast(loc8);
        loc7.setWest(null);
        
        loc8.setNorth(null);
        loc8.setSouth(loc1);
        loc8.setEast(loc9);
        loc8.setWest(loc7);
        
        loc9.setNorth(null);
        loc9.setSouth(loc2);
        loc9.setEast(loc10);
        loc9.setWest(loc8);
        
        loc10.setNorth(null);
        loc10.setSouth(loc3);
        loc10.setEast(null);
        loc10.setWest(loc9);
     

       System.out.println("---STICHES---" + "\n\n" + "It's cold and dark." + "\n" +"You look around you quickly" + "\n" +"The room wreaks of isolation and abandonment." + "\n" +"You find yourself on an operating table" + "\n" +"with several rusty tools" + "\n" +"scattered across the adjacent table." + "\n" +"Desperately, you call out a bold 'hello?'," + "\n" +"but there is no response." + "\n" +"You know what this means." + "\n" +"There is nobody here." + "\n" +"And there hasn't been anybody here" + "\n" +"in a very long time." + "\n" +"You must get out...and fast!" + "\n" +"You are now in" + "\n");
       currentLocale=loc0;
    }

    private static void updateDisplay() {
        System.out.println(currentLocale.getText());
    }

    private static void getCommand() {
    	if(moves != 0){
    		achievement = score/moves;
    		//makes the achievement ratio rounded to the nearest two decimal places for neatness
    		long l = (int)Math.round(achievement * 100);  
    		achievement = score/moves;
    		score = score + 5;
        	
        }
    
        System.out.print("[" + moves + " moves, score " + score +"," + " Money "+ money.getAmount()+","+" achievement ratio " + achievement + "] " + "\n");
        
        Scanner inputReader = new Scanner(System.in);
        command = inputReader.nextLine();  // command is global.
    }

    private static void navigate() { 
        int INVALID=-1;
        Locale newLocation=new Locale();
        newLocation=currentLocale;
        
        try {
        if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n") ) {
        	if(currentLocale.getNorth()!=null){
                currentLocale = currentLocale.getNorth();
                moves+=1;
                lookUpDown.push(currentLocale);
                lookDownUp.enqueue(currentLocale);
                money.add(coins.nextInt(16));
                
            }else{
                System.out.println("You can't go that way");
            }
        } else if ( command.equalsIgnoreCase("south") || command.equalsIgnoreCase("s") ) {
        	if(currentLocale.getSouth()!=null){
                currentLocale = currentLocale.getSouth();
                moves+=1;
                lookUpDown.push(currentLocale);
                lookDownUp.enqueue(currentLocale);
                money.add(coins.nextInt(16));
            }else{
                System.out.println("You can't go that way");
            }
        } else if ( command.equalsIgnoreCase("east")  || command.equalsIgnoreCase("e") ) {
        	if(currentLocale.getEast()!=null){
                currentLocale = currentLocale.getEast();
                moves+=1;
                lookUpDown.push(currentLocale);
                lookDownUp.enqueue(currentLocale);
                money.add(coins.nextInt(16));
            }else{
                System.out.println("You can't go that way");
            }
        } else if ( command.equalsIgnoreCase("west")  || command.equalsIgnoreCase("w") ) {
        	if(currentLocale.getWest()!=null){
                currentLocale = currentLocale.getWest();
                moves+=1;
                lookUpDown.push(currentLocale);
                lookDownUp.enqueue(currentLocale);
                money.add(coins.nextInt(16));
            }else{
                System.out.println("You can't go that way");
            }
        	
        } else if ( command.equalsIgnoreCase("quit")  || command.equalsIgnoreCase("q")) {
            quit();
        } else if ( command.equalsIgnoreCase("help")  || command.equalsIgnoreCase("h")) {
            help();
        } else if ( command.equalsIgnoreCase("shop")  || command.equalsIgnoreCase("os")) {
        	//this is where the magic shop is. the first time you open the shop there is a dialogue.
        	//every other time you open the shop there is the same dialogue each time thats different from the first time.
        	if (currentLocale.getId()==5){
        		janitor++;
        		if (janitor == 1){
        			ShopSetup(magicItems);
        		}else if (janitor >= 2){
        			ShopBuy();
        		}
        		
        	}else{
        		if (janitor == 0){
        			System.out.println("*This is no place for a shop... ya dingus!!*" + "\n");
        		}
        		else{
        			System.out.println("*You must go to the Janitor's closet of wonders!*" + "\n");
        		}
        	}
        } else if ( command.equalsIgnoreCase("take") || command.equalsIgnoreCase("t")) {
        	Take();
        } else if ( command.equalsIgnoreCase("map") || command.equalsIgnoreCase("m")) {
        	mapAscii();
        } else if ( command.equalsIgnoreCase("inventory") || command.equalsIgnoreCase("i")) {
        	bag();
        } else if ( command.equalsIgnoreCase("leave") || command.equalsIgnoreCase("l")) {
        	if (((currentLocale.getId() == 2 || currentLocale.getId()==6)) && unlock1 == true){
        		System.out.println("You thrust open the door to finally taste the fresh,");
        		System.out.println("outdoor air. Looking around, you search to find someone");
        		System.out.println("to explain the strange, empty hospital.");
        		System.out.println("However, all you can see are empty cars strewn throughout the road.");
                System.out.println("Not a soul is nearby. There is no sign of human life.");	
                System.out.println("You are still alone!");
                System.out.println("TO BE CONTINUED...");
        		quit();
        	}else{
        		System.out.println("*Shadowy voice*: YOU CANNOT LEAVE PUNY HUMAN!!"+"\n");
        	}
    	} else if ( command.equalsIgnoreCase("shit")) {
        	System.out.println("This kind of language is not going to get you anywhere!" + "\n");
        } else if ( command.equalsIgnoreCase("fuck")) {
        	System.out.println("You can't do that here." + "\n");
        } else if ( command.equalsIgnoreCase("poop")) {
        	System.out.println("You stop what you are doing, drop your pants and take a duce." + "\n");
        } else if ( command.equalsIgnoreCase("unlock") || command.equalsIgnoreCase("u")){
        	unlock();
        }else {//if you input an invalid text command you get this error message.
        	System.out.println("Error: Invalid text command");
        	System.out.println("To see a list of commands enter: help");
        }
        
        }catch(Exception ex){
        	ex.getMessage();
        }
        if(currentLocale.getHasVisited()==true){
          	
          	
          }
          else{
          	currentLocale.setHasVisited(true);
          	score=score+5;
          	
          } 
    }
	private static void help() {
        System.out.println("The commands are as follows:");
        System.out.println("Help = h/help");
        System.out.println("North = n/north");
        System.out.println("South = s/south");
        System.out.println("East = e/east");
        System.out.println("West = w/west");
        System.out.println("Take Item = t/take");
        System.out.println("Check Inventory = i/inventory");
        System.out.println("Leave building = l/leave");
        System.out.println("Unlock door = u/unlock");
        System.out.println("Open Map = m/map");
        System.out.println("Open Shop = os/shop");
        System.out.println("Quit = q/quit"+"\n");
    }

    private static void quit() {
    	System.out.println("Want to see the paths you took? \n Press 'f' to view from the beginning \n Press 'r' to view from the end");
    	String walkthrough = transactionDone.nextLine();
    		try{
    		if(walkthrough.equalsIgnoreCase("f")){
    			while(moves>=0){
    				System.out.println(lookDownUp.dequeue());
    				moves--;
    			}
    		}
    		else if(walkthrough.equalsIgnoreCase("r")){
    			while(moves>=0){
    				System.out.println(lookUpDown.pop());
    				moves--;
    			}
    		}
    		}catch(Exception ex){
    			System.out.println(ex.getMessage());
    		}
    	System.out.println("");
        stillPlaying = false;
    }
    private static void Take(){
    	if (currentLocale.getId() == 0){
    		mapFound = true;
    		bag[0].setFound(true);
    		newBag.add(bag[0].getName());
    		System.out.println("You found " + bag[0].getName() + ", it is in your bag.");
    	}
    	else if (currentLocale.getId() == 1){
    		bag[1].setFound(true);
    		newBag.add(bag[1].getName());
    		System.out.println("You found " + bag[1].getName() + ", it is in your bag.");
    	}
    	else if (currentLocale.getId() == 4){
    		bag[2].setFound(true);
    		newBag.add(bag[2].getName());
    		System.out.println("You found " + bag[2].getName() + ", it is in your bag.");
    	}
    	else if (currentLocale.getId() == 10){
    		bag[3].setFound(true);
    		newBag.add(bag[3].getName());
    		System.out.println("You found " + bag[3].getName() + ", it is in your bag.");
    	}
    	else if (currentLocale.getId() == 9 && unlock == true){
    		bag[4].setFound(true);
    		newBag.add(bag[4].getName());
    		System.out.println("You found " + bag[4].getName() + ", it is in your bag.");
    	}else
    		System.out.println("There is nothing to take here.");
 
    }
    private static void unlock() {
    	if(bag[3].itemFound() && currentLocale.getId()==9){
    		currentLocale.setDesc("You unlock the door and see a key ring on the shelf.");
    		unlock = true;
    	} else if (bag[4].itemFound() && (currentLocale.getId()==2 || currentLocale.getId()==6)){
    		currentLocale.setDesc("You unlock the door! Now it is time to leave.");
    		unlock1 = true;
    	} else if (currentLocale.getId()==0 || currentLocale.getId()==1 || currentLocale.getId()==3 || currentLocale.getId()==4 || currentLocale.getId()==5 || currentLocale.getId()==10 || currentLocale.getId()==8 || currentLocale.getId()==7){
    		System.out.println("There is no need to unlock anything here."+"\n");
    	}else {
    		System.out.println("You don't have the correct key for this door."+"\n");
    	}
		
	}
  
    private static void bag(){//inventory
    	String inventory = "Inventory:";
    	System.out.println(inventory + newBag);
    }
  
    private static boolean binarySearchArray(ListItem[] items, String target) {

        ListItem retVal = null;
        System.out.println("Binary Searching for " + target + ".");
        ListItem currentItem = new ListItem();
        boolean isFound = false;
        int counter = 0;
        int low  = 0;
        int high = items.length-1; // because 0-based arrays
        while ( (!isFound) && (low <= high)) {
            int mid = Math.round((high + low) / 2);
            currentItem = items[mid];
            if (currentItem.getName().equalsIgnoreCase(target)) {
                // We found it!
                isFound = true;
                retVal = currentItem;
            } else {
                // Keep looking.
                counter++;
                if (currentItem.getName().compareToIgnoreCase(target) > 0) {
                    // target is higher in the list than the currentItem (at mid)
                    high = mid - 1;
                } else {
                    // target is lower in the list than the currentItem (at mid)
                    low = mid + 1;
                }
            }
        }
    	if (isFound) {
    		System.out.println("Here is the "+ target +" you asked for.");
    		System.out.println("this will be "+ currentItem.getCost()+" dollars");
    		System.out.println("Are you ready to purchase this item? Enter yes to buy or no to not buy.");
    		String buy = transactionDone.nextLine();
    		if(buy.equalsIgnoreCase("yes")&&money.getAmount()>=currentItem.getCost()){
    			
    			
    		}else if (buy.equalsIgnoreCase("yes")&&money.getAmount()<currentItem.getCost()){
    			System.out.println("Uhmmm... I'm sorry but I can't help you.");
    			System.out.println("Either you didn't clearly say 'Yes' or 'No'");
    			System.out.println("or you simply don'y have enough money.");
    			return false;
    		}	
    			if (buy.equalsIgnoreCase("Yes")) {
    			purchase=currentItem;
    			newBag.add(currentItem.getName());
    			return true;
    			
    		}
    		else if(buy.equalsIgnoreCase("No")){
    			System.out.println("Goodbye :)");
    			
    		} 		
    	} else {
    		System.out.println("Sorry, I couldn't find " + target + " in my stock. \nCheck your inventory to see if you have already purchased the item. \nYou also could have asked for an item I don't have. \n");
    		return false;
}
    	return false;
    	}  
    private static void selectionSort(ListItem[] items) {
        for (int pass = 0; pass < items.length-1; pass++) {
            // System.out.println(pass + "-" + items[pass]);
            int indexOfTarget = pass;
            int indexOfSmallest = indexOfTarget;
            for (int j = indexOfTarget+1; j < items.length; j++) {
                if (items[j].getName().compareToIgnoreCase(items[indexOfSmallest].getName()) < 0) {
                    indexOfSmallest = j;
                }
            }
            ListItem temp = items[indexOfTarget];
            items[indexOfTarget] = items[indexOfSmallest];
            items[indexOfSmallest] = temp;
        }
    }
    private static void readMagicItemsFromFileToArray(String fileName, ListItem[] items) {
		File myFile = new File(fileName);
		try {
		int itemCount = 0;
		Scanner input = new Scanner(myFile);
		
		while (input.hasNext() && itemCount < items.length) {
		// Read a line from the file.
		String itemName = input.nextLine();
		
		// Construct a new list item and set its attributes.
		ListItem fileItem = new ListItem();
		fileItem.setName(itemName);
		fileItem.setCost((int) (Math.random() * 100));
		fileItem.setNext(null); // Still redundant. Still safe.
		
		// Add the newly constructed item to the array.
		items[itemCount] = fileItem;
		itemCount = itemCount + 1;
		}
		// Close the file.
		input.close();
		} catch (FileNotFoundException ex) {
		System.out.println("File not found. " + ex.toString());
		}
    }

    private static void ShopSetup(ListMan lm){
    	System.out.println("Strange voice: Well howdy! I can hardly believe my eyes." + "\n" +"Strange voice: Not every day you see another critter" + "\n" +"Strange voice: strolling around this here place! " + "\n" +"Strange voice: My name's Old Craig." + "\n" +"You: How long have you been here?!" + "\n" +"You: How are you still alive?" + "\n" +"Old Craig: About 62 years, it's been! " + "\n" +"Old Craig: Got everything I need to survive in this here closet." + "\n" +"Old Craig: I got some stuff here if y'all need anythang!" + "\n" +"Old Craig: Just open my shop whenever you are here." + "\n" +"You: Well thanks for that I'll keep it in mind" + "\n");
    	readMagicItemsFromFileToList("magic_item_list.txt", lm);
    	readMagicItemsFromFileToArray("magic_item_list.txt", storeInventory);
    	selectionSort(storeInventory);
    	janitor = 1;
    }
    private static void readMagicItemsFromFileToList(String fileName, ListMan lm) {
    	File myFile = new File(fileName);
        try {
            Scanner input = new Scanner(myFile);
            while (input.hasNext()) {
                // Read a line from the file.
                String itemName = input.nextLine();

                // Construct a new list item and set its attributes.
                ListItem fileItem = new ListItem();
                fileItem.setName(itemName);
                fileItem.setCost((int) (Math.random() * 100));
                fileItem.setNext(null); // Still redundant. Still safe.

                // Add the newly constructed item to the list.
                lm.add(fileItem);
            }
            // Close the file.
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. " + ex.toString());
        }
		
	}

	private static void ShopBuy(){
    	System.out.println("Old Craig: Welcome back! Looking for something?" + "\n");
    	String selection = transactionDone.nextLine();
    	if (binarySearchArray(storeInventory, selection) == true){
    		money.subtract(purchase.getCost());
    		num++;
    		System.out.println("Item bought");
    	
    	}
    	}
}