package encryptdecrypt;

public class AlgorithmFactory {
    public Algorithm selectAlgorithm(String mode, String alg) {
        switch (alg) {
            case "shift": return new Shift(mode);
            case "unicode": return new Unicode(mode);
        }
        return null;
    }
}
