package Core.Words;

public class NumberWord implements IWord  {

    private int value;
    private String type;

    public NumberWord(int value, String type) {
       this.value = value;
       this.type = type;
    }

    /**
     * Creates a new NumberWord with the default type of "number"
     * @param value the value of the NumberWord
     */
    public NumberWord(int value) {
        this.value = value;
        this.type = "number";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return NumberFormatter.formatInt(value);
    }
}
