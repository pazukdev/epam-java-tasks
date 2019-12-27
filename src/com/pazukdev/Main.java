package com.pazukdev;

public class Main {

    public static void main(String[] args) {
        final String text =  "Bite my shiny metal ass";
        final char replacer = 'X';
        final int indexOfCharToReplace = 2;

        TextHandler textHandler = new TextHandler();
        final String handledText = textHandler.handle(text, replacer, indexOfCharToReplace);

        System.out.println(text);
        System.out.println(handledText);
    }

}
