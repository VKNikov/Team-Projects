package blackJack;

import java.util.ArrayList;
import java.util.Random;

public class Suit {
	
	//Method for generating a random card.
	public static String cards(int rowIndex) {

		String[] rows = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J",
				"Q", "K", "A" };

		String[] suit = { "\u2665", "\u2666", "\u2660", "\u2663" };

		Random randomS = new Random();
		int suitIndex = randomS.nextInt(4);
		String card = new String(rows[rowIndex] + " " + suit[suitIndex]);

		return card;
	}

	//Method for generating the card's value.
	public static int cardValues(int cardIndex) {
		int cardValue;

		switch (cardIndex) {
		case 0:
			cardValue = 2;
			break;
		case 1:
			cardValue = 3;
			break;
		case 2:
			cardValue = 4;
			break;
		case 3:
			cardValue = 5;
			break;
		case 4:
			cardValue = 6;
			break;
		case 5:
			cardValue = 7;
			break;
		case 6:
			cardValue = 8;
			break;
		case 7:
			cardValue = 9;
			break;
		case 8:
		case 9:
		case 10:
		case 11:
			cardValue = 10;
			break;
		default:
			cardValue = 11;
			break;
		}
		return cardValue;
	}
	
	//method for calculating the hand's sum.
	public static Integer sum(ArrayList<Integer> list) {
		Integer sum = 0;
		for (Integer i : list) {
			sum = sum + i;
		}

		if (sum > 21) {
			for (Integer i : list) {
				if (i == 11) {
					sum -= 10;
				}
			}
		}
		return sum;
	}
	
	//Draw Dealer hand.
		public static void drawDealerHand(ArrayList<String> dealerHand,
				ArrayList<Integer> dealerValue) {
			Random randomR = new Random();
			int dealerR = randomR.nextInt(13);
			dealerHand.add(Suit.cards(dealerR));
			dealerValue.add(Suit.cardValues(dealerR));
		}
		
		//Draw Player hand.
		public static void drawePlayerHand(ArrayList<String> playerHand,
				ArrayList<Integer> playerValue) {
			Random randomR = new Random();
			int playerR = randomR.nextInt(13);
			playerHand.add(Suit.cards(playerR));
			playerValue.add(Suit.cardValues(playerR));
		}
}
