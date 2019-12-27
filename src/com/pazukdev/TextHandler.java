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
 * 2. В тексте после буквы Р, если она не последняя в слове, ошибочно напечатана буква А
 * вместо О. Внести исправления в текст.
 * 3. В тексте слова заданной длины заменить указанной подстрокой, длина которой может
 * не совпадать с длиной слова.
 * 4. Из небольшого текста удалить все символы, кроме пробелов, не являющиеся буквами.
 * Между последовательностями подряд идущих букв оставить хотя бы один пробел.
 * 5. Из текста удалить все слова заданной длины, начинающиеся на согласную букву.
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
