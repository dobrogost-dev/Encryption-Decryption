package encryptdecrypt;

public abstract class Algorithm {
    protected String mode;
    public Algorithm(String mode) {
        this.mode = mode;
    }
    public abstract void process(StringBuilder input, int value);
}

class Shift extends Algorithm {
    public Shift(String mode) {
        super(mode);
    }
    public void process(StringBuilder input, int value) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            //System.out.println("Processing: " + c);
            int charValue = c;
            if (c >= 65 && c <= 90) {
                if (mode.equals("enc"))
                    charValue = c + value;
                if (mode.equals("dec"))
                    charValue = c - value;
                if (charValue < 65) {
                    charValue += 26;
                } else if (charValue > 90) {
                    charValue -= 26;
                }
                //System.out.println("Char: " + c);
                //System.out.println("Char value: " + charValue);
            } else if (c >= 97 && c <= 122) {

                if (mode.equals("enc"))
                    charValue = c + value;
                if (mode.equals("dec"))
                    charValue = c - value;
                if (charValue < 97) {
                    charValue += 26;
                } else if (charValue > 122) {
                    charValue -= 26;
                }
                //System.out.println("Char: " + c);
                //System.out.println("Char value: " + charValue);
            }
            //System.out.println((char) charValue);
            input.setCharAt(i, (char) (charValue));
        }
    }
}
class Unicode extends Algorithm {

    public Unicode(String mode) {
        super(mode);
    }
    public void process(StringBuilder input, int value) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int charValue = 0;
            if (mode.equals("enc"))
                charValue = c + value;
            if (mode.equals("dec"))
                charValue = c - value;
            input.setCharAt(i, (char) (charValue));
        }
    }
}
