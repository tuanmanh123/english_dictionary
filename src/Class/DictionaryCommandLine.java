package Class;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryCommandLine {
    public void showAllWords(Dictionary dictionary) {
        ArrayList<Word> words = dictionary.getWords();
        if (words.isEmpty()) {
            System.out.println("Tu dien trong.");
            return;
        }

        // Sort the words alphabetically
        Collections.sort(words, Comparator.comparing(Word::getWord_target));

        // Display the words
        System.out.println("No | English | Vietnamese");
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            System.out.printf("%d | %s | %s%n", i + 1, word.getWord_target(), word.getWord_explain());
        }
    }



    public void dictionarySearcher(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu can tra: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        ArrayList<Word> searchResults = new ArrayList<>();
        for (Word w : dictionary.getWords()) {
            if (w.getWord_target().toLowerCase().startsWith(keyword)) {
                searchResults.add(w);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("Khong co tu phu hop!");
        } else {
            System.out.println("Ket qua:");
            for (Word w : searchResults) {
                System.out.println(w.getWord_target());
            }
        }
    }
}
