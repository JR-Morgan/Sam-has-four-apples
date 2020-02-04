package Core;

import Core.Words.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Game {
    private HashMap<String, ArrayList<Word>> words;
    private HashMap<String, Word> currentWords;
    private Random random;
    private int numberAdd, numberSub;
    private int roundNumber;

    protected Sentence sentence;


    public Game() {
        random = new Random();
        words = new HashMap<>();
        try {
            String type;
            words.put(type = "name", parseFile("src/core/Words/Names-list.txt", type));
            words.put(type = "verb", parseFile("src/Core/Words/Verbs-list.txt", type));
            words.put(type = "fruit", parseFile("src/Core/Words/Fruits-list.txt", type));
        } catch (IOException e) {
            e.printStackTrace();
        }

        resetGame();
    }

    protected void nextRound() {
        roundNumber++;

        if(roundNumber > 1) {
            numberAdd = random.nextInt(10);
            if(numberAdd > 0) {
                numberSub = random.nextInt(numberAdd);
            } else {
                numberSub = 0;
            }

            if(random.nextBoolean()) {
                newRule();
            }
        }

        sentence = new Sentence(
                currentWords.get("name").getValue(),
                currentWords.get("verbAdd").getValue(),
                numberAdd,
                currentWords.get("fruit").getValue(),
                currentWords.get("verbSub").getValue(),
                numberSub
        );
        displaySentence(sentence);
    }

    private void newRule() {
        String old = "err", _new = "err";

        int counter = 0, target = random.nextInt(currentWords.keySet().size());
        for(String type : currentWords.keySet()) {
            if(counter == target) {
                old = currentWords.get(type).getValue();
                ArrayList<Word> pool = words.get(currentWords.get(type).getType());
                Word newWord = pool.get(random.nextInt(pool.size()));
                currentWords.replace(type, newWord);
                _new = newWord.getValue();

                break;
            }
            counter++;
        }

        displayRule(old + " is now " + _new);
    }

    protected void resetGame() {
        roundNumber = 0;

        currentWords = new HashMap<String, Word>();

        String type;
        currentWords.put(type = "name",     words.get(type).get(0));
        currentWords.put(type = "verbAdd",  words.get("verb").get(0));
        currentWords.put(type = "verbSub",  words.get("verb").get(1));
        currentWords.put(type = "fruit",    words.get(type).get(0));

        numberAdd = 4;
        numberSub = numberAdd / 2;
    }


    protected abstract void displaySentence(Sentence sentence);

    protected abstract void displayRule(String text);

    protected abstract void displayIncorrect(String text);

    private static ArrayList<Word> parseFile(String filePath, String type) throws IOException {
        ArrayList<Word> set = new ArrayList<Word>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = reader.readLine()) != null ) {
            set.add(new Word(line.trim(), type));
        }

        reader.close();
        return set;
    }

}
