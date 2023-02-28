import java.util.Scanner;

public class driver {

  public static void main(String[] args) {
    Scanner src = new Scanner(System.in);
    Blackjack game = new Blackjack();
    String input = "";
    boolean flag = true;
    
    do {
      game.play();
      System.out.println("Do you want to play again? (yes/no)");
      
      input = "";
      while (!input.equals("yes") && !input.equals("no")) {
        input = src.next().toLowerCase();
        
        if (input.equals("yes"))
          game.reset();
        else if (input.equals("no"))
          flag = false;
        else
          System.out.println("input not recognized. Want to play again? (yes/no)");
      }
      
    } while(flag);

    System.out.print("thanks for playing! See you later!");
    src.close();
  }

}
