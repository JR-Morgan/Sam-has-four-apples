package Core.Words;

import java.util.HashMap;

class NumberFormatter {
    private static String[] numbers = new String[] {
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen",
            "twenty",
    };

    /**
     * Returns the english format of a given int<br>
     * eg. 5 -> twenty
     * @param value the integer value that is to be formatted. (0 <= value <= 20)
     * @return human readable string
     */
    public static String formatInt(int value) {
        return numbers[value];
    }
}
