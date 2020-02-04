package Core;

import java.util.List;

public class Sentence {

    private String name, verbAdd, fruit, verbSub;
    private int numberAdd, numberSub;

    public Sentence(String name, String verbAdd, int numberAdd, String fruit, String verbSub, int numberSub) {
        this.name       = name;
        this.verbAdd    = verbAdd;
        this.numberAdd  = numberAdd;
        this.fruit      = fruit;
        this.verbSub    = verbSub;
        this.numberSub  = numberSub;
    }

    @Override
    public String toString() {
        String fruit = this.fruit;
        if (numberAdd > 1) fruit += "s";
        return String.format("%s %s %s %s, he %s %s", name, verbAdd, numberAdd, fruit, verbSub, numberSub);
    }

    public int getNumberAdd() { return numberAdd; }
    public int getNumberSub() { return numberSub; }
}
