package com.pazukdev;

import java.util.*;

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

    // #6
    // translate text
    // e.g:
    // dictionary: a : 0; c c : 2 2; d d d : 3 3 3
    // text:   a a b b c c d d d e
    // result: 0 0 b b 2 2 3 3 3 e
    public String translateTextUsingDictionary(String text, final Map<String, String> dictionary) {
        final Map<String, String> map = new HashMap<>();
        int i = 0;
        final String s = "#";
        for (final List<String> subList : getAllSubListsSortedBySize(splitIntoWords(text))) {
            final String toTranslate = wordsIntoText(subList);
            final String translated = translateWord(toTranslate, dictionary);
            if (translated != null) {
                final String key = s + i++;
                final String value = translated;
                text = text.replace(toTranslate, key);
                map.put(key, value);
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }

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

    // #6
    private List<List<String>> getAllSubListsSortedBySize(final List<String> words) {
        final List<String> subArraysAsStrings = new ArrayList<>();
        for ( int i = 0; i < words.size(); i++) {
            String s = "";
            for (int j = i; j < words.size(); j++) {
                s += words.get(j) + " ";
                subArraysAsStrings.add(s.trim());
            }
        }
        final List<List<String>> subArrays = new ArrayList<>();
        for (final String subArrayAsString : subArraysAsStrings) {
            subArrays.add(Arrays.asList(subArrayAsString.split(SEPARATOR)));
        }
        subArrays.sort(Comparator.comparing(List::size, Comparator.reverseOrder()));
        return subArrays;
    }

    // #6
    private String translateWord(final String word, final Map<String, String> dictionary) {
        return dictionary.get(word);
    }

}
