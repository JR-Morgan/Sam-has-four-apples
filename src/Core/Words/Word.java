package Core.Words;

public class Word {
    String value, type;

    public Word(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
