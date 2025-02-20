import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("words.txt"));
        Scanner keyboard = new Scanner(System.in);

        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));

        System.out.println(word);
        List<Character> guesses = new ArrayList<>();

        int wrongCount=0;
        while (true) {

            man(wrongCount);

            if(wrongCount >= 6) {
                System.out.println("You lost!");
                break;
            }


            prStateOfW(word, guesses);
            if(!getPlayerGuess(keyboard, guesses, word)) {
                wrongCount++;
            }

            if(prStateOfW(word, guesses)) {
                break;
            }
            System.out.println("Please enter your guess");
            if(keyboard.nextLine().equals(word)) {
                System.out.println("You won!");
                break;
            }
            else {
                System.out.println("Try again.");
            }

        }

    }

    private static void man(int wrongCount) {
        System.out.println(" -------");
        System.out.println(" |     |");
        if (wrongCount >=1 ) {
            System.out.println(" O");
        }
        if (wrongCount >=2 ) {
            System.out.print("\\ ");
            if (wrongCount >=3 ) {
                System.out.println("/");
            } else {
                System.out.println("");
            }
        }
        if (wrongCount >= 4 ) {
            System.out.println(" |");
        }
        if (wrongCount >=5 ) {
            System.out.print("/ ");
            if (wrongCount >=6 ) {
                System.out.println("\\");
            } else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    private static boolean getPlayerGuess(Scanner keyboard, List<Character> guesses, String word) {
        System.out.println("Enter a letter:");
        String letterGuess = keyboard.nextLine();
        guesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    private static boolean prStateOfW(String word, List<Character> guesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if(guesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("_");
            }
        }
        System.out.println();

        return (word.length() == correctCount);
    }
}
