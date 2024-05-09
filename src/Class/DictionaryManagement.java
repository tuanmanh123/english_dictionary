package Class;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {


    //ham insertFromCommandLine
    public void insertFromCommandline(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap so tu: ");
        int numWords = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < numWords; i++) {
            System.out.println("Nhap tu" + (i + 1) + ":");
            System.out.print("English: ");
            String wordTarget = scanner.nextLine();
            System.out.print("Vietnamese: ");
            String wordExplain = scanner.nextLine();

            Word word = new Word(wordTarget, wordExplain);
            dictionary.addWord(word);
        }
    }


    //ham insertFromFile
    public void insertFromFile(Dictionary dictionary, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    String wordTarget = parts[0];
                    String wordExplain = parts[1];
                    Word word = new Word(wordTarget, wordExplain);
                    dictionary.addWord(word);
                }
            }
        } catch (IOException e) {
            System.err.println("Khong the doc file: " + e.getMessage());
        }
    }


    public void dictionaryExportToFile(Dictionary dictionary,String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Word word : dictionary.getWords()) {
                writer.write(word.getWord_target() + "\t" + word.getWord_explain() + "\n");
            }
            System.out.println("Dictionary exported successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error exporting dictionary: " + e.getMessage());
        }
    }



    //Ham   dictionaryLookup
    public void dictionaryLookup(Dictionary dictionary, String word) {
        boolean found = false;
        for (Word w : dictionary.getWords()) {
            if (w.getWord_target().equalsIgnoreCase(word)) {
                System.out.println("English: " + w.getWord_target());
                System.out.println("Vietnamese: " + w.getWord_explain());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay!");
        }
    }


    // them tu
    public void addWord(Dictionary dictionary, String wordTarget, String wordExplain) {
        Word word = new Word(wordTarget, wordExplain);
        dictionary.addWord(word);
        System.out.println("Them tu thanh cong!");
    }

    // sua tu
    public void editWord(Dictionary dictionary, String wordTarget, String newWordExplain) {
        boolean found = false;
        for (Word w : dictionary.getWords()) {
            if (w.getWord_target().equalsIgnoreCase(wordTarget)) {
                w.setWord_explain(newWordExplain);
                found = true;
                System.out.println("Sua thanh cong!");
                break;
            }
        }
        if (!found) {
            System.out.println("Tu khong tim thay!");
        }
    }

    // xoa tu
    public void deleteWord(Dictionary dictionary, String wordTarget) {
        boolean found = false;
        for (int i = 0; i < dictionary.getWords().size(); i++) {
            Word w = dictionary.getWords().get(i);
            if (w.getWord_target().equalsIgnoreCase(wordTarget)) {
                dictionary.getWords().remove(i);
                found = true;
                System.out.println("Xoa thanh cong!");
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay!");
        }
    }



}
