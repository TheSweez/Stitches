import java.util.Scanner;
//Dan Swezey
//Software Development One: CMPT220L-111
//Project 2 "STITCHES"
public class Game {

    //
    // Public
    //

    // Globals
	private static final boolean DEBUGGING  = false; // Debugging flag.
    private static final int MAX_LOCALES = 2;        // Total number of rooms/locations we have in the game.
    private static int currentLocale = 0;            // Player starts in locale 0.
    private static String command;                   // What the player types as he or she plays the game.
    private static boolean stillPlaying = true;      // Controls the game loop.
    private static Locale[] locations;               // An uninitialized array of type Locale. See init() for initialization.
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
    private static int key = 0;                      //This is to make sure you cant leave unless you have they appropriate keys

    public static void main(String[] args) {
        if (DEBUGGING) {
            // Display the command line args.
            System.out.println("Starting with args:");
            for (int i = 0; i < args.length; i++) {
                System.out.println(i + ":" + args[i]);
            }
        }

        // Set starting locale, if it was provided as a command line parameter.
        if (args.length > 0) {
            try {
                int startLocation = Integer.parseInt(args[0]);
                // Check that the passed-in value for startLocation is within the range of actual locations.
                if ( startLocation >= 0 && startLocation <= MAX_LOCALES) {
                    currentLocale = startLocation;
                } else {
                    System.out.println("WARNING: passed-in starting location (" + args[0] + ") is out of range.");
                }
            } catch(NumberFormatException ex) {
                System.out.println("WARNING: Invalid command line arg: " + args[0]);
                if (DEBUGGING) {
                    System.out.println(ex.toString());
                }
            }
        }

        // Get the game started.
        init();
        updateDisplay();

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
    	if(bag[0].itemFound()){
    		if (currentLocale==0){
		    	System.out.println("The '*' indicates your current location");
		    	map0=    " ____________________________________________________________________________________________________________                 \n"+
		    			"|Recovey Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                             |        N       \n"+
		    			"|      |                     |                       |                |         |             Emergency Exit |        ^       \n"+
		    			"|      |                     |                       |                |         |                     |      |        |       \n"+
		    			"|      |                     |                       |                |         |                     |      |  W <---+---> E \n"+
		    			"|*Operation Room A --- Operation Room B -------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
		    			"|                                                    |                                                       |        v       \n"+
		    			"|                                                Entrance                                                    |        S       \n"+
		    			"|____________________________________________________________________________________________________________|                  ";
		    	System.out.println(map0);
    		}else if (currentLocale==1){
		    	System.out.println("The '*' indicates your current location");
		    	map1=   " ____________________________________________________________________________________________________________                 \n"+
		    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
		    			"|      |                     |                       |                |         |             Emergency Exit |        ^       \n"+
		    			"|      |                     |                       |                |         |                     |      |        |       \n"+
		    			"|      |                     |                       |                |         |                     |      |  W <---+---> E \n"+
		    			"|Operation Room A --- *Operation Room B -------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
		    			"|                                                    |                                                       |        v       \n"+
		    			"|                                                Entrance                                                    |        S       \n"+
		    			"|____________________________________________________________________________________________________________|                  ";
		    	System.out.println(map1);
    		}else if (currentLocale==2){
    			System.out.println("The '*' indicates your current location");
    	    	map2=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                |         |             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                |         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                *Entrance                                                   |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map2);
    		}else if (currentLocale==3){
    			System.out.println("The '*' indicates your current location");
    	    	map3=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                |         |             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                |         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|--------*Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map3);
    		}else if (currentLocale==4){
    			System.out.println("The '*' indicates your current location");
    	    	map4=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                |         |             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                |         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|--------Waiting Room --- *Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map4);
    		}else if (currentLocale==5){
    			System.out.println("The '*' indicates your current location");
    	    	map5=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                |         |             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                |         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|--------Waiting Room --- Bathroom --- *Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map5);
    		}else if (currentLocale==6){
    			System.out.println("The '*' indicates your current location");
    	    	map6=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ----- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                |         |            *Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                |         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|--------Waiting Room --- Bathroom --- Janitor's Closet |        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map6);
    		}else if (currentLocale==7){
    			System.out.println("The '*' indicates your current location");
    	    	map7=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|*Recovery Room A ---- Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                |         |             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                |         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map7);
    		}else if (currentLocale==8){
    			System.out.println("The '*' indicates your current location");
    	    	map8=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ---- *Recovery Room B ----- Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                |         |             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                |         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map8);
    		}else if (currentLocale==9){
    			System.out.println("The '*' indicates your current location");
    	    	map9=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ---- Recovery Room B ----- *Medical Supply Room ------ Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                |         |             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                |         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map9);
    		}else if (currentLocale==10){
    			System.out.println("The '*' indicates your current location");
    	    	map10=   " ____________________________________________________________________________________________________________                 \n"+
    	    			"|Recovery Room A ---- Recovery Room B ----- Medical Supply Room ------ *Cafeteria                            |        N       \n"+
    	    			"|      |                     |                       |                |         |             Emergency Exit |        ^       \n"+
    	    			"|      |                     |                       |                |         |                     |      |        |       \n"+
    	    			"|      |                     |                       |                |         |                     |      |  W <---+---> E \n"+
    	    			"|Operation Room A --- Operation Room B --------------|---------Waiting Room --- Bathroom --- Janitor's Closet|        |       \n"+
    	    			"|                                                    |                                                       |        v       \n"+
    	    			"|                                                Entrance                                                    |        S       \n"+
    	    			"|____________________________________________________________________________________________________________|                  ";
    	    	System.out.println(map10);
    		}
    		
    		
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
        loc5.setDesc("No way there is a key in here!");
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
        loc9.setDesc("There has to be something important in here, but how to access it?");
        loc9.setNav("North:N/A, South:Available, East:Available, West:Available");
        
        Locale loc10 = new Locale(10);
        loc10.setName("Cafeteria");
        loc10.setDesc("What a mess");
        loc10.setNav("North:N/A, South:Available, East:N/A, West:Available");

        // Set up the location array.
        locations = new Locale[11];
        locations[0] = loc0; // "Operation Room A";
        locations[1] = loc1; // "Operation Room B";  
        locations[2] = loc2; // "Entrance";           
        locations[3] = loc3; // "Waiting Room";
        locations[4] = loc4; // "Bathroom";
        locations[5] = loc5; // "Janitor's Closet/Shop";
        locations[6] = loc6; // "Emergency Exit";
        locations[7] = loc7; // "Recovery Room A";
        locations[8] = loc8; // "Recovery Room B";
        locations[9] = loc9; // "Medical Supply Room";
        locations[10] = loc10; // "Cafeteria";
        
        
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

        if (DEBUGGING) {
            System.out.println("All game locations:");
            for (int i = 0; i < locations.length; ++i) {
                System.out.println(i + ":" + locations[i].toString());
            }
        }
        // Set up the navigation matrix.
        nav = new int[][] {
                                /* N   S   E   W */
                                /* 0   1   2   3 */
        	 /*loc0 for map 0*/  { 7 ,-1 , 1 ,-1},
       		 /*loc1 for map 1*/  { 8 ,-1 , 2 , 0},
       		 /*loc2 for map 2*/  { 9 ,-1 , 3 , 1},
       		 /*loc3 for map 3*/  { 10,-1 , 4 , 2},
       		 /*loc4 for map 4*/  { 10,-1 , 5 , 3},
       		 /*loc5 for map 5*/  { 6 ,-1 ,-1 , 4},
       		 /*loc6 for map 6*/  {-1 , 5 ,-1 ,10},
       		 /*loc7 for map 7*/  {-1 , 0 , 8 ,-1},
       		 /*loc8 for map 8*/  {-1 , 1 , 9 , 7},
       		 /*loc9 for map 9*/  {-1 , 2 , 10, 8},
       		 /*loc10 for map 10*/ {-1 , 3 ,-1 , 9}
        };

       System.out.println("---STICHES---" + "\n\n" + "It's cold and dark." + "\n" +"You look around you quickly" + "\n" +"The room wreaks of isolation and abandonment." + "\n" +"You find yourself on an operating table" + "\n" +"with several rusty tools" + "\n" +"scattered across the adjacent table." + "\n" +"Desperately, you call out a bold 'hello?'," + "\n" +"but there is no response." + "\n" +"You know what this means." + "\n" +"There is nobody here." + "\n" +"And there hasn't been anybody here" + "\n" +"in a very long time." + "\n" +"You must get out...and fast!" + "\n" +"You are now in" + "\n");
    }

    private static void updateDisplay() {
        System.out.println(locations[currentLocale].getText());
    }

    private static void getCommand() {
    	if(moves != 0){
    		achievement = score/moves;
    		//makes the achievement ratio rounded to the nearest two decimal places for neatness
    		long l = (int)Math.round(achievement * 100);  
    		achievement = l / 100.0;
        	
        }
    
        System.out.print("[" + moves + " moves, score " + score + ", achievement ratio " + achievement + "] " + "\n");
        
        Scanner inputReader = new Scanner(System.in);
        command = inputReader.nextLine();  // command is global.
    }

    private static void navigate() {
        final int INVALID = -1;
        int dir = INVALID;  // This will get set to a value > 0 if a direction command was entered.

        if (        command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n") ) {
            dir = 0;
        } else if ( command.equalsIgnoreCase("south") || command.equalsIgnoreCase("s") ) {
            dir = 1;
        } else if ( command.equalsIgnoreCase("east")  || command.equalsIgnoreCase("e") ) {
            dir = 2;
        } else if ( command.equalsIgnoreCase("west")  || command.equalsIgnoreCase("w") ) {
            dir = 3;

        } else if ( command.equalsIgnoreCase("quit")  || command.equalsIgnoreCase("q")) {
            quit();
        } else if ( command.equalsIgnoreCase("help")  || command.equalsIgnoreCase("h")) {
            help();
        } else if ( command.equalsIgnoreCase("shop")  || command.equalsIgnoreCase("os")) {
        	//this is where the magick shoppe is. the first time you open the shop there is a dialogue.
        	//every other time you open the shop there is the same dialogue each time thats different from the first time.
        	if (currentLocale == 5){
        		janitor++;
        		if (janitor == 1){
        			System.out.println("Strange voice: Well howdy! I can hardly believe my eyes." + "\n" +"Strange voice: Not every day you see another critter" + "\n" +"Strange voice: strolling around this here place! " + "\n" +"Strange voice: My name's Old Craig." + "\n" +"You: How long have you been here?!" + "\n" +"You: How are you still alive?" + "\n" +"Old Craig: About 62 years, it's been! " + "\n" +"Old Craig: Got everything I need to survive in this here closet." + "\n" +"Old Craig: I got some stuff here if y'all need anythang!" + "\n" +"You: Well thanks for that I'll keep it in mind" + "\n");
        		}else if (janitor >= 2){
        			System.out.println("Old Craig: Welcome back! Looking for something?" + "\n");
        		}
        		createMagicItems();
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
        	if (currentLocale == 2 && bag[4].itemFound()){
        		System.out.println("You thrust open the door to finally taste the fresh,");
        		System.out.println("outdoor air. Looking around, you search to find someone");
        		System.out.println("to explain the strange, empty hospital.");
        		System.out.println("However, all you can see are empty cars strewn throughout the road.");
                System.out.println("Not a soul is nearby. There is no sign of human life.");	
                System.out.println("You are still alone!");
                System.out.println("TO BE CONTINUED...");
        		System.exit(0);
        	}else{
        		System.out.println("*Shadowy voice*: YOU CANNOT LEAVE PUNY HUMAN!!"+"\n");
        	}
    	} else if ( command.equalsIgnoreCase("shit")) {
        	System.out.println("This kind of language is not doing to get you anywhere!" + "\n");
        } else if ( command.equalsIgnoreCase("fuck")) {
        	System.out.println("You can't do that here." + "\n");
        } else if ( command.equalsIgnoreCase("poop")) {
        	System.out.println("You stop what you are doing, drop your pants and take a duce." + "\n");
        }else {//if you input an invalid text command you get this error message.
        	System.out.println("Error: Invalid text command");
        	System.out.println("The valid commands are as follows:");
            System.out.println("Help = h/help");
            System.out.println("North = n/north");
            System.out.println("South = s/south");
            System.out.println("East = e/east");
            System.out.println("West = w/west");
            System.out.println("Take Item = t/take");
            System.out.println("Check Inventory = i/inventory");
            System.out.println("Open Map = m/map");
            System.out.println("Open Shop = os/shop");
            System.out.println("Leave building = l/leave");
            System.out.println("Quit = q/quit");
        }

        
        
        
        if (dir > -1) {   // This means a dir was set.
            int newLocation = nav[currentLocale][dir];
            if (newLocation == INVALID) {
                System.out.println("You cannot go that way."+ "\n");
            } else {
                currentLocale = newLocation;
                moves = moves + 1;
                if (locations[currentLocale].getHasVisited()==true){
                	
                }else{
                	locations[currentLocale].setHasVisited(true);
                	score = score + 5;
                }
                
            }
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
        System.out.println("Open Map = m/map");
        System.out.println("Open Shop = os/shop");
        System.out.println("Quit = q/quit"+"\n");
    }

    private static void quit() {
        stillPlaying = false;
    }

    private static void createMagicItems() {
        // Create the list manager for our magic items.
        List0 magicItems  = new List0();
        magicItems.setName("Closet Items");
        magicItems.setDesc("These are the miscellaneouse items.");
        magicItems.setHead(null);

        // Create some magic items and put them in the list.
        ListItem i1 = new ListItem();
        i1.setName(" Duck tape");
        i1.setDesc(" $1");

        ListItem i2 = new ListItem();
        i2.setName(" Broom");
        i2.setDesc(" $0.50");

        ListItem i3 = new ListItem();
        i3.setName(" Mop");
        i3.setDesc(" $0.50");

        // Link it all up.
        magicItems.setHead(i1);
        i1.setNext(i2);
        i2.setNext(i3);
        i3.setNext(null);

        System.out.println(magicItems.toString());
    }
    private static void Take(){
    	switch(currentLocale){
    	case 0:
    		bag[0].setFound(true);
    		locations[currentLocale].setDesc("This is where you woke up");
    	break;
    	case 1:
    		bag[1].setFound(true);
    		locations[currentLocale].setDesc("Where are all the doctors?");
    	break;
    	case 4:
    		bag[2].setFound(true);
    		locations[currentLocale].setDesc("Smells horrific");
    	break;
    	case 5:
    		bag[3].setFound(true);
    		locations[currentLocale].setDesc("This place has a bizarre ambiance");
    	break;
    	case 9:
    		if (bag[3].itemFound()){
    			bag[4].setFound(true);
    			locations[currentLocale].setDesc("What can you find in here?");
    		} else{
    			System.out.println("The door is locked");
    		}
    		
    	break;
    	default: 
    		System.out.println("There is no item here to take."+"\n");
    	break;
    	}
   
    	
    }
    private static void bag(){//inventory
    	String inventory = "";
    	if(bag[0].itemFound()){
    		inventory = inventory + bag[0].toString()+"\n";
    	}
    	if(bag[1].itemFound()){
    		inventory = inventory + bag[1].toString()+"\n";
    	}
    	if(bag[2].itemFound()){
    		inventory = inventory + bag[2].toString()+"\n";
    	}
    	if(bag[3].itemFound()){
    		inventory = inventory + bag[3].toString()+"\n";
    	}
    	if(bag[4].itemFound()){
    		inventory = inventory + bag[4].toString()+"\n";
    	}
    	System.out.println(inventory);
    }

}