package Class;

import java.util.ArrayList;

public class Dictionary {


        private ArrayList<Word> words;

        public Dictionary() {
            words = new ArrayList<>();
        }

        public void addWord(Word word) {
            words.add(word);
        }

        public ArrayList<Word> getWords() {
            return words;
        }

        public int getLength(){
            int i=words.size();
            return i;
        }
    }


