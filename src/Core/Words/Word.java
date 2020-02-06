package Core.Words;

public class Word implements IWord {
    private String value, type;

    public Word(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return value;
    }

}
