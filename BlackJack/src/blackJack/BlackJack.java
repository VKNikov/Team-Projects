package blackJack;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {
		System.getProperty("file.encoding", "Unicode");

		System.out.println("This is BlackJack card game.");
		System.out.println("Please select a difficulty.");
		System.out
				.println("Press 'E' for easy(cash 150), 'N' for normal (cash 100) and 'H' for hard (cash 50).");

		Scanner input = new Scanner(System.in);

		// Declaring some initial variables.
		boolean noWinner = true;
		boolean playerPass = false;
		boolean surrender = false;
		boolean correctBet = false;
		char[] str = new char[0];
		int cash = 0;
		int bet = 0;
		int wincash = 0;
		int round = 1;

		str = Player.checkCorrectDifficulty(input);
		cash = Player.startWith(str, cash);

		// Main loop
		while (cash > 0) {
			System.out.println("Your current cash is: " + cash);
			System.out.printf("\nRound %d:\n", round);
			round++;
			int hand = 1;
			noWinner = true;
			playerPass = false;
			surrender = false;
			ArrayList<String> dealerHand = new ArrayList<String>();
			ArrayList<Integer> dealerValue = new ArrayList<Integer>();
			ArrayList<String> playerHand = new ArrayList<String>();
			ArrayList<Integer> playerValue = new ArrayList<Integer>();

			// Draw the first two hands of the Dealer.
			for (int i = 0; i < 2; i++) {

				Suit.drawDealerHand(dealerHand, dealerValue);
			}

			// Draw the first two hands of the Player.
			for (int i = 0; i < 2; i++) {

				Suit.drawePlayerHand(playerHand, playerValue);
			}

			// This is for clearing the terminal under Linux.
			// System.out.println("\033[2J");

			System.out.printf("\nEnter your bet: ");

			bet = Player.setBet(input, cash, bet, correctBet);
			cash -= bet;

			// This loop is valid until the final result of the hands is known.
			while (noWinner) {
				wincash = 0;
				hand++;

				if (surrender == true) {
					cash += bet / 2;
					break;
				}

				// Checking Dealer's hand
				if (hand > 2) {
					if (Suit.sum(dealerValue) <= 16 && playerPass == true
							&& Suit.sum(playerValue) < 21) {

						Suit.drawDealerHand(dealerHand, dealerValue);

					} else if (Suit.sum(dealerValue) > 21) {
						wincash = bet * 2;
						bet = 0;
						cash += wincash;
						System.out.println("\nDealer Busted!");
						System.out.println("You win " + wincash + " cash");
					}
				}

				if (hand > 2 && playerPass == true) {
					printHands(playerPass, hand, dealerHand, dealerValue,
							playerHand, playerValue);

					// Draw and print dealer's hand.
					Dealer.dealerDrawCard(playerPass, hand, dealerHand,
							dealerValue, playerHand, playerValue);

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

						System.out.println("\nIt's a Draw!"); // The game is a
																// draw
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

				printHands(playerPass, hand, dealerHand, dealerValue,
						playerHand, playerValue);

				if ((Suit.sum(dealerValue) == Suit.sum(playerValue) && playerPass == true)) {
					System.out.println("\nIt's a Draw!"); // The game is a draw
															// (i.e. No
					// Winner)
					wincash = bet;
					bet = 0;
					cash += wincash;
					System.out.println("You get " + wincash + " cash back.");
					break;
				} else if ((Suit.sum(dealerValue) == 21) && playerPass == true) {
					System.out.println("\nYou lost!"); // CASE 7 -dealer's
					wincash -= bet;
					bet = 0;
					System.out.println("You loose: " + wincash + " cash.");
					break;
				} else if ((Suit.sum(playerValue) == 21) && playerPass == true) {
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
					str = Player.inputWithoutDouble(input);
				} else {
					str = Player.inputWithDouble(input);
				}

				// Checking the different player's inputs.
				if (str[0] == 'h') {

					System.out.println("You've got a new card.");
					Suit.drawePlayerHand(playerHand, playerValue);

				} else if (str[0] == 'u') {

					System.out
							.println("You surrender\nYou take back half of your bet.");
					surrender = true;

				} else if (str[0] == 'd') {
					System.out.println("You double your bet!");
					System.out.println("You've got a new card.");
					Suit.drawePlayerHand(playerHand, playerValue);
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

	// Printing the hands
	public static void printHands(boolean playerPass, int hand,
			ArrayList<String> dealerHand, ArrayList<Integer> dealerValue,
			ArrayList<String> playerHand, ArrayList<Integer> playerValue) {
		// Print Dealer Hand
		Dealer.dealerHandPrint(dealerHand, dealerValue, playerValue, hand,
				playerPass);
		// Print Player Hand
		Player.playerHandPrint(playerHand, playerValue, hand);
	}
}