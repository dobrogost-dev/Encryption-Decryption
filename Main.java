package encryptdecrypt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String mode = "enc";
        String alg = "shift";
        int key = 0;
        StringBuilder input = new StringBuilder("");
        boolean out = false;
        boolean data = false;
        String outPath = "";
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    if (i < args.length - 1)
                        if (args[i + 1].equals("dec"))
                            mode = "dec";
                    break;
                case "-key":
                    if (i < args.length - 1)
                        if (args[i + 1].matches("\\d+"))
                            key = Integer.valueOf(args[i + 1]);
                        else
                            System.out.println("Error -key");
                    break;
                case "-data":
                    if (i < args.length - 1) {
                        data = true;
                        input = new StringBuilder(args[i + 1]);
                    } else {
                        System.out.println("Error -data");
                    }
                    break;
                case "-in":
                    if (i < args.length - 1) {
                        if (!data) {
                            String path = args[i + 1];
                            try {
                                input = new StringBuilder(new String(Files.readAllBytes(Paths.get(path))));
                            } catch (IOException e) {
                                System.out.println("Cannot read file: " + e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("Error -in");
                    }
                    break;
                case "-out":
                    if (i < args.length - 1) {
                        out = true;
                        outPath= args[i + 1];
                    } else {
                        System.out.println("Error -out");
                    }
                    break;
                case "-alg":
                    if (i < args.length - 1)
                        if (args[i + 1].equals("unicode"))
                            alg = "unicode";
                    break;
                default:
                    break;
            }
        }
        AlgorithmFactory factory = new AlgorithmFactory();
        Algorithm algorithm = factory.selectAlgorithm(mode, alg);
        //System.out.println(input);
        algorithm.process(input, key);
        //System.out.println(input);
        if (out) {
            File file = new File(outPath);
            try (PrintWriter printWriter = new PrintWriter(file)) {
                printWriter.print(input);
            } catch (IOException e) {
                System.out.printf("Error: An exception occurred %s", e.getMessage());
            }
        } else {
            System.out.println(input);
        }
    }
}
