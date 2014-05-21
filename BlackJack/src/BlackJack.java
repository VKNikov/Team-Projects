import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {
		System.getProperty("file.encoding", "Unicode");

		System.out.println("This is BlackJack card game.");
		System.out.println("Please select a difficulty.");
		System.out
				.println("Press 'E' for easy(cash 150), 'N' for normal (cash 100) and 'H' for hard (cash 50)");

		Scanner input = new Scanner(System.in);

		boolean noWinner = true;
		boolean playerPass = false;
		boolean surrender = false;

		char[] str = new char[0];
		int cash = 0;
		int bet = 0;
		int wincash = 0;

		str = input.nextLine().toLowerCase().toCharArray();

		while (str[0] != 'n' && str[0] != 'h' && str[0] != 'e') {
			System.out
					.println("You have entered incorrect input. Please try again:");
			System.out
					.println("Press 'E' for easy(cash 150), 'N' for normal (cash 100) and 'H' for hard (cash 50):");
			str = input.nextLine().toLowerCase().toCharArray();
		}

		// Setting the starting amount of cash.
		if (str[0] == 'e') {
			cash = 150;
		} else if (str[0] == 'n') {
			cash = 100;
		} else if (str[0] == 'h') {
			cash = 50;
		}

		// Main loop
		while (cash > 0) {
			int hand = 1;
			noWinner = true;
			playerPass = false;
			surrender = false;
			ArrayList<String> dealerHand = new ArrayList<String>();
			ArrayList<Integer> dealerValue = new ArrayList<Integer>();
			ArrayList<String> playerHand = new ArrayList<String>();
			ArrayList<Integer> playerValue = new ArrayList<Integer>();

			for (int i = 0; i < 2; i++) {
				
				Random randomR = new Random();
				int dealerR = randomR.nextInt(13);
				dealerHand.add(Suit.cards(dealerR));
				dealerValue.add(Suit.cardValues(dealerR));
			}

			for (int i = 0; i < 2; i++) {

				Random randomR = new Random();
				int playerR = randomR.nextInt(13);
				playerHand.add(Suit.cards(playerR));
				playerValue.add(Suit.cardValues(playerR));
			}
			// This is for clearing the terminal under Linux.
			// System.out.println("\033[2J");

			System.out.println("Your current cash is: " + cash);
			System.out.printf("\nEnter your bet: ");
			bet = Integer.parseInt(input.nextLine());

			while (bet > cash) {
				System.out.println("Your current cash is: " + cash);
				System.out
						.printf("You do not have enough money to bet %d. Please enter again your bet:",
								bet);
				bet = Integer.parseInt(input.nextLine());
			}
			cash -= bet;

			// This loop is valid until the final result of the hands is known.
			while (noWinner) {
				wincash = 0;
				hand++;
				
				if (surrender == true) {
					cash += bet / 2;
					break;
				}
				
				//Checking Dealer's hand
				if (hand > 2) {
					if (Suit.sum(dealerValue) <= 16 && playerPass == true) {

						System.out.println("\nDealer got a new card.");
						Random randomR = new Random();
						int dealerR = randomR.nextInt(13);
						dealerHand.add(Suit.cards(dealerR));
						dealerValue.add(Suit.cardValues(dealerR));

					} else if (Suit.sum(dealerValue) > 21) {
						wincash = bet * 2;
						bet = 0;
						cash += wincash;
						System.out.println("\nDealer Busted!");
						System.out.println("You win " + wincash + " cash");
					}
				}

				if (hand > 2 && playerPass == true) {
					Dealer.dealerHandPrint(dealerHand, dealerValue,
							playerValue, hand, playerPass);
					// Print Player Hand
					Player.playerHandPrint(playerHand, playerValue, hand);

					// This loop plays when the player draws no more hands and
					// while the dealer has over 17 points.
					while (Suit.sum(dealerValue) <= 16) {
						hand++;
						System.out.println("\nDealer got a new card.");
						Random randomR = new Random();
						int dealerR = randomR.nextInt(13);
						dealerHand.add(Suit.cards(dealerR));
						dealerValue.add(Suit.cardValues(dealerR));

						// Print Dealer Hand
						Dealer.dealerHandPrint(dealerHand, dealerValue,
								playerValue, hand, playerPass);
						// Print Player Hand
						Player.playerHandPrint(playerHand, playerValue, hand);
					}

					if (Suit.sum(dealerValue) > 21) {
						System.out.println("\nDealer busted!");
						wincash = bet * 2;
						bet = 0;
						cash += wincash;
						System.out.println("You win " + wincash + " cash.");

					} else if (Suit.sum(playerValue) > Suit.sum(dealerValue)
							&& Suit.sum(playerValue) <= 21) {
						System.out.println();
						System.out.println("You win!");
						wincash = bet * 2;
						bet = 0;
						cash += wincash;
						System.out.println("You win " + wincash + " cash!");
					} else if (Suit.sum(playerValue) == Suit.sum(dealerValue)) {

						System.out.println("\nPush"); // The game is a draw
						// (i.e. No Winner)
						wincash = bet;
						bet = 0;
						cash += wincash;
						System.out
								.println("You get " + wincash + " cash back.");

					} else {
						System.out.println("\nYou lost!");
						wincash = -bet;
						bet = 0;
						System.out.println("You loose: " + wincash + " cash.");
					}
					break;
				}

				Dealer.dealerHandPrint(dealerHand, dealerValue, playerValue,
						hand, playerPass);
				// Print Player Hand
				Player.playerHandPrint(playerHand, playerValue, hand);

				if ((Suit.sum(dealerValue) == 21)
						&& (Suit.sum(playerValue) == 21) && hand > 2) {
					System.out.println("\nPush"); // The game is a draw (i.e. No
					// Winner)
					wincash = bet;
					bet = 0;
					cash += wincash;
					System.out.println("You get " + wincash + " cash back.");
					break;
				} else if ((Suit.sum(dealerValue) == 21) && (hand > 2)) {
					System.out.println("\nYou lost!"); // CASE 7 -dealer's
					wincash -= bet;
					bet = 0;
					System.out.println("You loose: " + wincash + " cash.");
					break;
				} else if ((Suit.sum(playerValue) == 21) && hand > 2) {
					System.out.println("\nYou win!");
					wincash = (bet * 2);
					bet = 0;
					cash += wincash;
					System.out.println("You win " + wincash + " cash.");
					break;
				} else if (Suit.sum(playerValue) > 21) {
					System.out.println("\nBusted!");

					wincash -= bet;
					bet = 0;
					System.out.println("You loose: " + wincash + " cash.");
					break;
				}

				if ((cash - bet) < 0) {
					System.out.println("Hit(H) or Stand(S) or Surrender(U)");
					str = input.nextLine().toLowerCase().toCharArray();

					// Making sure there is no incorrect input.
					while (str[0] != 's' && str[0] != 'h' && str[0] != 'u') {
						System.out
								.println("You have entered incorrect input. Please try again:");
						System.out
								.println("Hit(H) or Stand(S) or Surrender(U)");
						str = input.nextLine().toLowerCase().toCharArray();
					}
				} else {
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
				}

				// Checking the different player's inputs.
				if (str[0] == 'h') {

					System.out.println("You've got a new card.");
					Random randomR = new Random();
					int playerR = randomR.nextInt(13);
					playerHand.add(Suit.cards(playerR));
					playerValue.add(Suit.cardValues(playerR));

				} else if (str[0] == 'u') {

					System.out
							.println("You surrender\nYou take back half of your bet.");
					surrender = true;

				} else if (str[0] == 'd') {
					System.out.println("You double your bet!");
					System.out.println("You've got a new card.");
					Random randomR = new Random();
					int playerR = randomR.nextInt(13);
					playerHand.add(Suit.cards(playerR));
					playerValue.add(Suit.cardValues(playerR));
					cash -= bet;
					bet = bet * 2;
					playerPass = true;

				} else if (str[0] == 's') {
					playerPass = true;
				}
			}
		}
		// End of game, you have lost!
		System.out.println("You have " + cash + " cash left.");
		System.out.println("You have lost! :(");
	}
}