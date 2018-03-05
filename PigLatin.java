import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Program: Week3, Programming Exercise 4
 * File: PigLatin.java
 * Summary: Converts a string provided by a text file to it's pig latin equivalent.
 * Author: Chase Hausman
 * Date: March 04, 2018
 */
public class PigLatin {
    public static void main(String args[]) {
        // Try to open the text file
        File txtFile = new File("src/piglatin.txt");
        Scanner input = null;

        // Output if we can't find a file
        try {
            input = new Scanner(txtFile);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            System.exit(0);
        }

        // Loop through the file and print the conversion of each word, determined by whitespace
        while(input.hasNext()) {
            System.out.println(wordToPigLatin(input.next()));
        }
    }

    private static String wordToPigLatin(String word) {
        // Make the string upper case, since it doesn't matter in this application and makes comparisons easier
        word = word.toUpperCase();

        // Determine if the word starts with a vowel
        if(word.startsWith("A") || word.startsWith("E") || word.startsWith("I") || word.startsWith("O") || word.startsWith("U")) {
            return vowelRules(word);
        }

        // If it's not a vowel, it's a consonant in our case.
        return consonantRules(word);
    }

    private static String vowelRules(String word) {
        // Just add "WAY"
        return word.concat("WAY");
    }

    private static String consonantRules(String word) {
        // Find first vowel
        String[] vowels = {"A", "E", "I", "O", "U"}; // The vowels we care about

        // Loop through the vowels to find the index of the one that shows up first.
        int indexOfFirst = word.length();
        for (int i = 0; i < 5; i++) {
            if(word.indexOf(vowels[i]) < indexOfFirst && word.indexOf(vowels[i]) > 0) {
                indexOfFirst = word.indexOf(vowels[i]);
            }
        }

        // Get the letters between the beginning of the word and the first vowel
        String firstChunk = word.substring(0, indexOfFirst);
        // And the letters from the first vowel to the end
        String lastChunk = word.substring(indexOfFirst);

        // Return the pieces in the proper pig latin order
        return lastChunk + firstChunk + "AY";
    }
}