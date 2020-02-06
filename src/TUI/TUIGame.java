package TUI;

import Core.Game;
import Core.Sentence;
import Core.Words.IWord;

import java.util.Scanner;

public class TUIGame extends Game {

    Scanner in;

    public TUIGame() {
        super();
        in = new Scanner(System.in);
        nextRound();
    }
    @Override
    protected void startNewRound(Sentence sentence) {
        System.out.println(sentence);
        System.out.println("How many Apples does Sam Have?");

        if (checkInput(sentence, Integer.parseInt(in.nextLine()))) {
            nextRound();
        } else {
            displayIncorrect();
        }
    }

    @Override
    protected void displayWordRule(IWord oldWord, IWord newWord) {
        System.out.println(oldWord.toString() + " is now " + newWord.toString());
    }

    @Override
    protected void displayOrderRule(IWord word1, IWord word2) {
        System.out.println(word1.toString() + " has swapped with " + word2.toString());
    }

    @Override
    protected void displayIncorrect() {
        System.out.println("Incorrect!");
        reset();
        nextRound();
    }
}
