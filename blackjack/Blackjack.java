import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
  private int ace_high;
  private int ace_high_bot;
  private LinkedList<String> deck;
  private final String[] DECK_CARDS = {"2", "3", "4", "5", "6", "7", "8", "9", "A", "Q", "K", "J"};
  private List<String> playerHand;
  private List<String> botHand;
  private int playerScore;
  private int botScore;
  private Scanner scr;

  // constructor: gets deck ready
  public Blackjack() {
    deck = new LinkedList<String>();
    playerHand = new ArrayList<String>();
    botHand = new ArrayList<String>();

    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 4; j++) {
        this.deck.add(DECK_CARDS[i]);
      }
    }
    Collections.shuffle(deck);

    playerScore = 0;
    botScore = 0;
    ace_high = 0;
    ace_high_bot = 0;
    scr = new Scanner(System.in);
  }
  
  public void reset() {
    playerScore = 0;
    botScore = 0;
    ace_high = 0;
    ace_high_bot = 0;
    
    deck.clear();
    botHand.clear();
    playerHand.clear();
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 4; j++) {
        this.deck.add(DECK_CARDS[i]);
      }
    }
    Collections.shuffle(deck);
  }

  public void play() {
    boolean stand = false;

    // print intro
    System.out.print("Welcome to Blackjack!\n");
    getStartingHand();

    while (playerScore < 21 || ace_high != 0) {
      // check to see if the ace needs to be changed to a 1 from an 11
      if (playerScore > 21) {
        playerScore -= 10;
        ace_high--;
        continue;
      }

      // display the new hand
	System.out.println();
      printPlayerHand();

      // cases for what the player will do
      System.out.println("What would you like to do:\nH-Hit S-Stand");
      switch (scr.next().toUpperCase()) {
        case "H":
          pickCard();
          break;
        case "S":
          stand = true;
          break;
        default:
          System.out.println("Input not recognized!");
      }

      if (stand)
        break;

    }

    System.out.println(this.playerScore);

    // make the bot play now if they didn't bust
    if (this.playerScore > 21) {
      printPlayerHand();
      System.out.println("bust!");
      return;
    }

    botPlay();
    System.out.println("Dealers hand:");
    printBotHand();

    if (this.botScore >= this.playerScore && this.botScore <= 21)
      System.out.println("Better luck next time!");
    else
      System.out.println("Nice job! You win!");

  }

  public void printPlayerHand() {
    String hand = "";

    for (String card : this.playerHand) {
      hand += card + " ";
    }

    System.out.println(hand + "\t[" + getScore() + "]");
  }

  public void printBotHand() {
    String hand = "";

    for (String card : this.botHand) {
      hand += card + " ";
    }

    System.out.println(hand + "\t[" + this.botScore + "]\n");
  }

  public void getStartingHand() {
    pickCard();
    pickCard();
  }

  public int getScore() {
    return this.playerScore;
  }

  public String pickCard() {
    String card = this.deck.pop();
    playerHand.add(card);

    try {
      this.playerScore += Integer.parseInt(card);
    } catch (NumberFormatException e) {
      if (card.equals("A")) {
        this.playerScore += 11;
        this.ace_high++;
      } else
        this.playerScore += 10;
    }

    return card;

  }

  public String pickCardBot() {
    String card = this.deck.pop();
    botHand.add(card);

    try {
      this.botScore += Integer.parseInt(card);
    } catch (NumberFormatException e) {
      if (card.equals("A")) {
        this.botScore += 11;
        this.ace_high_bot++;
      } else
        this.botScore += 10;
    }

    return card;

  }

  public void botPlay() {
    pickCardBot();
    pickCardBot();

    while (this.botScore < 21 || this.ace_high_bot != 0) {

      if (this.botScore > 21) {
        this.botScore -= 10;
        this.ace_high_bot--;
        continue;
      }

      if (this.botScore > this.playerScore)
        break;

      pickCardBot();
    }

  }

}
