package com.pazukdev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Siarhei Sviarkaltsau
 */
public class TextHandler {

    private static final String SEPARATOR = " ";

    public String handle(final String text, final char replacer, final int indexOfCharToReplace) {
        final List<String> words = splitIntoWords(text);
        replaceChar(replacer, indexOfCharToReplace, words);

        final String handledText = wordsIntoText(words);
        return handledText;
    }

    public void replaceChar(final char replacer, final int indexOfCharToReplace, final List<String> words) {
        final List<String> changedWords = new ArrayList<>();
        for (final String word : words) {
            final StringBuilder changedWord = new StringBuilder(word);
            try {
                changedWord.setCharAt(indexOfCharToReplace, replacer); // try to replace character
                changedWords.add(changedWord.toString());
            } catch (final StringIndexOutOfBoundsException e) { // but if the word is shorter than specified index
                changedWords.add(word); // leave the word as is
            }
        }
        words.clear();
        words.addAll(changedWords);
    }

    public List<String> splitIntoWords(final String text) {
        final List<String> words = new ArrayList<>(Arrays.asList(text.split(SEPARATOR)));
        return words;
    }

    public String wordsIntoText(final List<String> words) {
        String text = "";
        for (final String word : words) {
            text += word + SEPARATOR;
        }
        text = text.trim(); // to remove space at the end
        return text;
    }

}
