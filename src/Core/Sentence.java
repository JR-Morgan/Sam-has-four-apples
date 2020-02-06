package Core;

import Core.Words.IWord;
import Core.Words.NumberWord;
import Core.Words.Word;

import java.util.ArrayList;

public class Sentence {

    private ArrayList<IWord> words;
    private NumberWord numberAdd, numberSub;

    public Sentence(ArrayList<IWord> words, NumberWord numberAdd, NumberWord numberSub) {
        this.words = words;
        this.numberAdd  = numberAdd;
        this.numberSub  = numberSub;
    }

    @Override
    public String toString() {
        String temp3 = words.get(3).toString();
        if (numberAdd.getValue() > 1) temp3 += "s";
        return String.format("%s %s %s %s, he %s %s", toUpper(words.get(0).toString()), words.get(1), words.get(2), toUpper(temp3), words.get(4), words.get(5));
    }

    public NumberWord getNumberAdd() { return numberAdd; }
    public NumberWord getNumberSub() { return numberSub; }

    private static String toUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
