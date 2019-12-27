package com.pazukdev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Siarhei Sviarkaltsau
 *
 * Task 3
 * Программа обработки текста, который может быть получен как с консоли, так и с файла.
 * 1. В каждом слове k-ю букву заменить заданным символом. Если k больше длины слова,
 * корректировку не выполнять.
 * 2. В тексте после буквы Р, если она не последняя в слове, ошибочно напечатана буква А // как определить что ОШИБОЧНО?
 * вместо О. Внести исправления в текст.                                                 // или любую А менять на О?
 * 3. В тексте слова заданной длины заменить указанной подстрокой, длина которой может
 * не совпадать с длиной слова.
 * 4. Из небольшого текста удалить все символы, кроме пробелов, не являющиеся буквами.
 * Между последовательностями подряд идущих букв оставить хотя бы один пробел.
 * 5. Из текста удалить все слова заданной длины, начинающиеся на согласную букву.
 */
public class TextHandler {

    // TODO
    //  1. write implementation for all defined but not implemented methods
    //  2. define methods for p. #4 and #5 and write its implementations
    //  3. add input from console feature

    private static final String SEPARATOR = " ";

    public String applyAllFeatures(final String text,
                                   final char replacer,
                                   final int indexOfCharToReplace,
                                   final int wordLengthToReplaceWithSubstring,
                                   final String substring) { // add params for #4 and #5 methods
        final List<String> words = splitIntoWords(text);

        // #5 here
        // #4 here
        replaceWordsWithSubstring(wordLengthToReplaceWithSubstring, substring, words); // #3
        replaceCharAWithOAfterP(words); // #2
        replaceChar(replacer, indexOfCharToReplace, words); // #1

        final String handledText = wordsIntoText(words);
        return handledText;
    }

    // #1
    public void replaceChar(final char replacer, final int indexOfCharToReplace, final List<String> words) {
        final List<String> changedWords = new ArrayList<>();
        for (final String word : words) {
            try {
                final String changedWord = replaceChar(replacer, indexOfCharToReplace, word); // try to replace char
                changedWords.add(changedWord);
            } catch (final StringIndexOutOfBoundsException e) { // but if the word is shorter than specified index
                changedWords.add(word); // leave the word as is
            }
        }
        words.clear();
        words.addAll(changedWords);
    }

    // #2
    public void replaceCharAWithOAfterP(final List<String> words) {
        final List<String> changedWords = new ArrayList<>();
        for (final String word : words) {
            final String changedWord = word.replaceAll("Pa", "Po").replaceAll("pa", "po");
            changedWords.add(changedWord);
        }
        words.clear();
        words.addAll(changedWords);
    }

    // #3
    public void replaceWordsWithSubstring(final int wordLengthToReplaceWithSubstring,
                                          final String substring,
                                          final List<String> words) {
        // TODO write method implementation
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

    // private methods

    // #1
    private String replaceChar(final char replacer,
                               final int indexOfCharToReplace,
                               final String word) throws StringIndexOutOfBoundsException {
        final StringBuilder changedWord = new StringBuilder(word);
        changedWord.setCharAt(indexOfCharToReplace, replacer);
        return changedWord.toString();
    }

}
