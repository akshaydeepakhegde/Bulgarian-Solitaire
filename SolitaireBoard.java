// Name: Akshay Deepak Hegde
// USC NetID: hegdeaks
// CSCI455 PA2
// Fall 2018

import java.util.ArrayList;
import java.util.Random;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
*/


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES
   
   
   /**
      Representation invariant:
      piles[] array holds the piles and numberOfPiles says the total number of piles.
      sum of all cards(piles[]) should be CARD_TOTAL
      0<numberOfPiles <=piles.length
      if numberOfPiles>0, location of cards is [0, numberOfpiles-1]
      0<maximum value of each pile<=CARD_TOTAL

   */
   private int piles[] = new int[CARD_TOTAL];
   private int numberOfPiles;
  
 
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {
	   numberOfPiles = piles.size();
      
	   for(int i=0; i<numberOfPiles; i++) {
		   this.piles[i] = piles.get(i);
	   }
       assert isValidSolitaireBoard();   
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
	   Random random = new Random();
	   int i = 0;
	   int sum = 0;
      
	   while (true) {
		   piles[i] = 1+random.nextInt(CARD_TOTAL-sum);
		   sum += piles[i];
		   i++;
		   if(sum == CARD_TOTAL) {
			   break;
		   }
	   }
	   numberOfPiles = i;
	   assert isValidSolitaireBoard();
   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */
   public void playRound() {
	   int currentPointer = 0;
	   int newPileCount = 0;
	   
	   for (int i =0; i<numberOfPiles ; i++) {
		   piles[currentPointer] = piles[i] - 1;
		   newPileCount = newPileCount + 1;
		   if(piles[currentPointer]!=0) {
			   currentPointer++;
           }
	   }
          
	   piles[currentPointer] = newPileCount;
	   numberOfPiles = currentPointer+1;
	   assert isValidSolitaireBoard();
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
   */
   
   public boolean isDone() {
	   int sum=0;
	   int cards=0;
      
	   for (int i=0; i<numberOfPiles; i++) {
		   for (int j=0; j<numberOfPiles; j++) {
			   if(piles[i]==piles[j] && i!=j)
				   return false;
		   }
		   if(piles[i]==0) {
			   return false;
		   }
		   sum += piles[i];
		   cards++;
	   }
	   if(sum != CARD_TOTAL)
		   return false;
	   if(cards != NUM_FINAL_PILES)
		   return false;

	   assert isValidSolitaireBoard();
       return true;
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
   */
   public String configString() {
	   String string = "";
      
	   for (int i=0; i<numberOfPiles-1; i++) {
		   string += piles[i] + " ";
	   }
	   string += piles[numberOfPiles-1]; 
	   assert isValidSolitaireBoard();
       return string;
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard() {
      int sum=0;
      for(int i=0; i<numberOfPiles; i++){
         sum += piles[i];
         if(piles[i] <= 0 || piles[i] > CARD_TOTAL)
             return false;
      }
      if(sum != CARD_TOTAL)
         return false;
      if(numberOfPiles >= piles.length || numberOfPiles <= 0)
         return false;
      
      return true;
   
   }
   
 }
