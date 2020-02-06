package Core;

import Core.Words.IWord;
import Core.Words.NumberWord;
import Core.Words.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public abstract class Game {
    /** The maximum starting number of apples */
    private final int MAX_APPLES = 10;
    /** The number of add apples at the start of the game */
    private static final int START_ADD = 4;
    /** The number of sub apples at the start of the game */
    private static final int START_SUB = 2;

    /** The chance that a rule change will swap two current words */
    private final float ORDER_RULE_CHANCE = 1f;
    /** The chance that a rule will change a current word for a new one of a random type*/
    private final float WORD_RULE_CHANCE = 0.10f;
    /** The chance that a rule will change a current word for a new one of the same type */
    private final float HOMOGENEOUS_WORD_RULE_CHANCE = 0.10f;

    private HashMap<String, ArrayList<IWord>> words;
    private ArrayList<IWord> currentWords;
    private Random random;
    private NumberWord numberAdd, numberSub;
    private int roundNumber;

    protected Sentence sentence;


    public Game() {
        random = new Random();
        words = parseWordsFromFile();

        reset();
    }


    protected void nextRound() {
        roundNumber++;


        if(roundNumber > 1) {


            if(random.nextFloat() < WORD_RULE_CHANCE) {
                newWordRule(false);
            } else if(random.nextFloat() < HOMOGENEOUS_WORD_RULE_CHANCE) {
                newWordRule(true);
            } else if(random.nextFloat() < ORDER_RULE_CHANCE) {
                newOrderRule();
            }

            numberAdd.setValue(random.nextInt(MAX_APPLES));

            if(numberAdd.getValue() > 0) {
                numberSub.setValue(random.nextInt(numberAdd.getValue()));
            } else {
                numberSub.setValue(0);
            }
        }

        sentence = new Sentence(
                currentWords,
                numberAdd,
                numberSub
        );
        startNewRound(sentence);
    }

    /**
     * Swaps two words
     */
    private void newOrderRule() {
        int position1 = random.nextInt(currentWords.size()),
            position2;

        do {
            position2 = random.nextInt(currentWords.size());
        } while (position2 == position1);

        IWord word1 = currentWords.get(position1),
              word2 = currentWords.get(position2);

        currentWords.set(position1, word2);
        currentWords.set(position2, word1);

        displayOrderRule(word1,word2);
    }

    /**
     * Generates a new rule by swapping words.<br>
     * If {@code isHomogeneous} then a random current word will be replaced by another word of the same type.<br>
     * If {@code !isHomogeneous} then a random current word will be replaced by another word of a random type.<br>
     * <br>
     * This method calls {@link Game#displayWordRule}
     * @param isHomogeneous - if the rule should change a word with another word of the same type
     */
    private void newWordRule(boolean isHomogeneous) {
        int position = random.nextInt(currentWords.size());
        IWord newWord, oldWord = currentWords.get(position);

        String type;
        if(isHomogeneous) {
            type = oldWord.getType();
        } else {
            type = (String) words.keySet().toArray()[random.nextInt(words.size())];
        }

        List<IWord> pool = words.get(type);
        newWord = pool.get(random.nextInt(pool.size()));
        currentWords.set(position,newWord);

        displayWordRule(oldWord, newWord);
    }


    /**
     * Resets the game to a starting state
     */
    protected void reset() {
        roundNumber = 0;

        currentWords = new ArrayList<IWord>(6);

        numberAdd = new NumberWord(4);
        numberSub = new NumberWord(2);

        currentWords.add(words.get("name").get(0));
        currentWords.add(words.get("verb").get(0));
        currentWords.add(numberAdd);
        currentWords.add(words.get("fruit").get(0));
        currentWords.add(words.get("verb").get(1));
        currentWords.add(numberSub);

    }

    /**
     * Checks a user input is correct against a given sentence
     * @param sentence the sentence
     * @param input the user answer
     * @return {@code input == sentence.getNumberAdd() - sentence.getNumberSub(); }
     */
    protected static boolean checkInput(Sentence sentence, int input) {
        return input == sentence.getNumberAdd().getValue() - sentence.getNumberSub().getValue();
    }


    protected abstract void startNewRound(Sentence sentence);

    protected abstract void displayWordRule(IWord oldWord, IWord newWord);

    protected abstract void displayOrderRule(IWord word1, IWord word2);

    protected abstract void displayIncorrect();

    /**
     * Creates words HashMap from text files in src/core/Words/*-list.txt
     * @return HashMap uses Type as the key
     */
    private static HashMap<String, ArrayList<IWord>> parseWordsFromFile() {
        HashMap<String, ArrayList<IWord>> words = new HashMap<>();
        try {
            String type;
            words.put(type = "name", parseFile("src/core/Words/Names-list.txt", type));
            words.put(type = "verb", parseFile("src/Core/Words/Verbs-list.txt", type));
            words.put(type = "fruit", parseFile("src/Core/Words/Fruits-list.txt", type));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return words;
    }

    /**
     * Parses an array list of words from a given file
     * @param filePath the file path of the file that is to be parsed
     * @param type the type of the words that are to be parsed
     * @return Collection of words.
     * @throws IOException throne by FileReader
     */
    private static ArrayList<IWord> parseFile(String filePath, String type) throws IOException {
        ArrayList<IWord> set = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = reader.readLine()) != null ) {
            set.add(new Word(line.trim(), type));
        }

        reader.close();
        return set;
    }

}
