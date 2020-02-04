package TUI;

import Core.Game;
import Core.Sentence;

import java.util.Scanner;

public class TUIGame extends Game {


    Scanner in;

    public TUIGame() {
        super();
        in = new Scanner(System.in);
        nextRound();
    }
    @Override
    protected void displaySentence(Sentence sentence) {
        System.out.println(sentence);
        System.out.println("How many Apples does Sam Have?");

        String input = in.nextLine();

        if (Integer.parseInt(input) == sentence.getNumberAdd() - sentence.getNumberSub()) {
            nextRound();
        } else {
            System.out.println("Incorrect!");
            resetGame();
            nextRound();
        }
    }

    @Override
    protected void displayRule(String text) {
        System.out.println(text);
    }

    @Override
    protected void displayIncorrect(String text) {
        System.out.println(text);
    }
}
