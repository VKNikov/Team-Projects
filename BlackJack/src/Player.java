import java.util.ArrayList;

public class Player {
	//Method for printing the Players's hand.
	public static void playerHandPrint(ArrayList<String> playerHand,
			ArrayList<Integer> playerValue, int hand) {
		System.out.print("\nYou have ");
		System.out.println(Suit.sum(playerValue) + " points."); // display player
		// points
		for (String item : playerHand) { // Display player cards
			System.out.print(item + "  ");

		}
		System.out.println();
	}
}
