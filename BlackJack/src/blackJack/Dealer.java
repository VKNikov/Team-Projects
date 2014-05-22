package blackJack;
import java.util.ArrayList;

public class Dealer {
	
	public static void dealerDrawCard(boolean playerPass, int hand,
			ArrayList<String> dealerHand, ArrayList<Integer> dealerValue,
			ArrayList<String> playerHand, ArrayList<Integer> playerValue) {
		
		// This loop plays when the player draws no more hands and
		// until the dealer has over 16 points.
		while (Suit.sum(dealerValue) <= 16) {
			hand++;
			System.out.println("\nDealer got a new card.");
			Suit.drawDealerHand(dealerHand, dealerValue);

			BlackJack.printHands(playerPass, hand, dealerHand, dealerValue,
					playerHand, playerValue);
		}
	}
	
	//Method for printing the Dealer's hand
	public static void dealerHandPrint(ArrayList<String> dealerHand, ArrayList<Integer> dealerValue, 
			ArrayList<Integer> playerValue,int hand, boolean playerPass) {
		
		if (hand == 2 || playerPass == false) {
			if ((Suit.sum(playerValue) > Suit.sum(dealerValue)) && Suit.sum(playerValue) <= 21) {
				System.out.print("Dealer has ");
				// Display dealer cards with second card hidden
				System.out.print("\n" + dealerHand.get(0) + "  ???"); 
			} else if (Suit.sum(playerValue) > 21) {
				System.out.print("Dealer has ");
				// Display dealer cards
				System.out.println(Suit.sum(dealerValue) + " points.");

				for (String item : dealerHand) {
					System.out.print(item + "  ");
				}
			} else {
				System.out.print("Dealer has ");
				// Display dealer cards with second card hidden
				System.out.print("\n" + dealerHand.get(0) + "  ???"); 
			}

		} else {
			System.out.print("Dealer has ");
			// Display dealer cards
			System.out.println(Suit.sum(dealerValue) + " points.");

			for (String item : dealerHand) {
				System.out.print(item + "  ");
			}
		}
	}
}
