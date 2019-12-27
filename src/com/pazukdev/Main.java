package com.pazukdev;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        final String text =  "Bite my shiny metal axe! And don't forget about my Lunapark!";
        final char replacer = 'X';
        final int indexOfCharToReplace = 2;
        final int wordLengthToReplaceWithSubstring = 3;
        final String substring = "ass";

        System.out.println(text + " (original text)");

        TextHandler textHandler = new TextHandler();

        // #1
        List<String> words = textHandler.splitIntoWords(text);
        textHandler.replaceChar(replacer, indexOfCharToReplace, words);
        String handledText = textHandler.wordsIntoText(words);
        System.out.println(handledText + " (replace char)");

        // #1
        words = textHandler.splitIntoWords(text);
        textHandler.replaceCharAWithOAfterP(words);
        handledText = textHandler.wordsIntoText(words);
        System.out.println(handledText + " (replace 'a' with 'o' after 'p')");

        // #1
        words = textHandler.splitIntoWords(text);
        textHandler.replaceWordsWithSubstring(wordLengthToReplaceWithSubstring, substring, words);
        handledText = textHandler.wordsIntoText(words);
        System.out.println(handledText + " (replace specified length words with substring)");

        // #4

        // #5

        // all
        handledText = textHandler.applyAllFeatures(
                text,
                replacer,
                indexOfCharToReplace,
                wordLengthToReplaceWithSubstring,
                substring
        );

        System.out.println(handledText + " (all)");
    }

}
