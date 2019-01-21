// Name: Akshay Deepak Hegde
// USC NetID: hegdeaks
// CSCI455 PA2
// Fall 2018

import java.util.ArrayList;
import java.util.Scanner;
/**
   class BulgarianSolitaireSimulator
   
   Simulates the Bulgarian Soiltaire game.
   Shows the output after every round.
   
   User can provide initial configuration or it will generate a random one.
   User can provide single step mode to check every round of the game.
   -u for user configuration, -s for single step mode.
*/

public class BulgarianSolitaireSimulator {
   
   public static void main(String[] args) {
     
      boolean singleStep = false;
      boolean userConfig = false;

      Scanner in = new Scanner(System.in);
      
      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }
  
     SolitaireBoard solitaireBoard = generateSolitaireBoard(userConfig, in);
     simulateSolitaireBoard(solitaireBoard, singleStep, in);      
   }
   
   /**
     Generates the Solitaire board.
     User can provide the initial configuration
     or it will take a random configuration.
   
     Input is taken from the scanner as a string line and
     the string is passed to another scanner to fetch the integers.
     try-catch block is used to handle errors.
     
     @userConfig - true if user wants to input initial configuration.
   */
   private static SolitaireBoard generateSolitaireBoard(Boolean userConfig, Scanner in) {
	   
	   if(userConfig) {
		   boolean repeatInput = true;
		   int sum = 0;
		   ArrayList<Integer> userList = new ArrayList<Integer>();
		   System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
		   System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
	       printInputLine();
		   while(repeatInput == true){
			   sum = 0;
			   userList.clear();
			   repeatInput = false;
			   String userString = in.nextLine();
			   Scanner userListScanner = new Scanner(userString);
			   repeatInput = false;
			   while(userListScanner.hasNext()){
				   int temp = 0;
				   try {
				   temp = userListScanner.nextInt();
				   }
				   catch(Exception e){
					   printError();
		        		repeatInput = true;
		        		break;  
				   }
				   sum += temp;
				   if(temp<=0) {
		        		printError();
		        		repeatInput = true;
		        		break;
				   }
				   userList.add(temp);
		       }
		       if(sum!=SolitaireBoard.CARD_TOTAL && !repeatInput) { 	 
		           repeatInput = true;
		           printError();
		       }
		   }	   
	   return new SolitaireBoard((ArrayList<Integer>) userList);
	   }
	   else {
		   return new SolitaireBoard();
	   }
   }
   
   /**
     Tell the user to input the initial configuration.
   */
   private static void printInputLine() {
		System.out.println("Please enter a space-separated list of positive integers followed by newline:");
   }
   
   /**
     Tell the user that he has not entered the correct initial configuration
   */
   private static void printError() {
		System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " + SolitaireBoard.CARD_TOTAL);
		printInputLine();
   }
   
   /**
     Simulates the Solitaire game.
     Displays the initial configuration.
     Asks user to enter if single step is turned on.
     Displays the output after every round and "done" when done.
     
     @solitaireBoard - Solitaire board with the initial configuration.
     @singleStep - true if single step mode is turned on
   */
   private static void simulateSolitaireBoard(SolitaireBoard solitaireBoard, boolean singleStep, Scanner in) {
	    int i = 0;
		System.out.println("Initial configuration: " + solitaireBoard.configString());
		while(!solitaireBoard.isDone()) {
			solitaireBoard.playRound();
			i++;   
			if(singleStep) {
				System.out.print("<Type return to continue>");
				in.nextLine();
			}
			System.out.println("[" + i + "] Current configuration: " + solitaireBoard.configString());
		}
		System.out.println("Done!");
   }

  
}
