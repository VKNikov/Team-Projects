import java.util.ArrayList;

public class Dealer {
	public static void dealerHandPrint(ArrayList<String> dealerHand, ArrayList<Integer> dealerValue, 
			ArrayList<Integer> playerValue,int hand, boolean playerPass) {
		
		if (hand == 2 || playerPass == false) {
			if (Suit.sum(playerValue) > Suit.sum(dealerValue)) {
				System.out.print("Dealer has ");
				// Display dealer cards
				System.out.println(Suit.sum(dealerValue) + " points.");
			} else {
				System.out.print("Dealer has ");
				// Display dealer cards with second card hidden
				System.out.print("\n" + dealerHand.get(0) + "  ???"); 
			}
			System.out.print("Dealer has ");
			// Display dealer cards with second card hidden
			System.out.print("\n" + dealerHand.get(0) + "  ???"); 

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
