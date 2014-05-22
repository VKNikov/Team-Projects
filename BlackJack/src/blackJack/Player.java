package blackJack;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	
	//Validating the player's input for difficulty.
	public static char[] checkCorrectDifficulty(Scanner input) {
		char[] str;
		str = input.nextLine().toLowerCase().toCharArray();

		while (str[0] != 'n' && str[0] != 'h' && str[0] != 'e') {
			System.out
					.println("You have entered incorrect input. Please try again:");
			System.out
					.println("Press 'E' for easy(cash 150), 'N' for normal (cash 100) and 'H' for hard (cash 50):");
			str = input.nextLine().toLowerCase().toCharArray();
		}
		return str;
	}
	
	// Setting the starting amount of cash.
	public static int startWith(char[] str, int cash) {
		
		if (str[0] == 'e') {
			cash = 150;
		} else if (str[0] == 'n') {
			cash = 100;
		} else if (str[0] == 'h') {
			cash = 50;
		}
		return cash;
	}

	//Check player's bet.
	public static int setBet(Scanner input, int cash, int bet, boolean correctBet) {
		
		while (!correctBet) {
			try {
				bet = Integer.parseInt(input.nextLine());
				while (bet > cash) {
					System.out.println("Your current cash is: " + cash);
					System.out
							.printf("You do not have enough money to bet %d. Please enter again your bet\n:",
									bet);
					bet = Integer.parseInt(input.nextLine());
				}
				correctBet = true;
			} 
			catch (NumberFormatException e) {
				System.out.println("Incorrect input!");
				System.out.println("Please enter again your bet:");
				continue;
			}
		}
		return bet;
	}
	
	//Player input with Double choice.
	public static char[] inputWithDouble(Scanner input) {
		char[] str;
		System.out
				.println("\nHit(H) or Stand(S) or Double(D) or Surrender(U)");
		str = input.nextLine().toLowerCase().toCharArray();

		// Making sure there is no incorrect input.
		while (str[0] != 's' && str[0] != 'h' && str[0] != 'u'
				&& str[0] != 'd') {
			System.out
					.println("You have entered incorrect input. Please try again:");
			System.out
					.println("\nHit(H) or Stand(S) or Double(D) or Surrender(U)");
			str = input.nextLine().toLowerCase().toCharArray();
		}
		return str;
	}
	
	//Player input without Double choice.
	public static char[] inputWithoutDouble(Scanner input) {
		char[] str;
		System.out.println("\nHit(H) or Stand(S) or Surrender(U)");
		str = input.nextLine().toLowerCase().toCharArray();

		// Making sure there is no incorrect input.
		while (str[0] != 's' && str[0] != 'h' && str[0] != 'u') {
			System.out
					.println("You have entered incorrect input. Please try again:");
			System.out
					.println("Hit(H) or Stand(S) or Surrender(U)");
			str = input.nextLine().toLowerCase().toCharArray();
		}
		return str;
	}
	
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
