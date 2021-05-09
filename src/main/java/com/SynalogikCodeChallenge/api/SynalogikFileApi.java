package com.SynalogikCodeChallenge.api;

import java.io.*;
import java.util.*;

/**
 * Created by glennhealy on 09/05/2021.
 */

public class SynalogikFileApi {
    public static String filePath;
    public static File inputFile;
    public static String currentLine;
    public static int totalWordCount;
    public static int totalCharCount;

    public void analyzeFile(String filePath) throws IOException {
        this.filePath = filePath;
        System.out.println("total characters is: " + getCharCount(filePath));
        System.out.println("Average Word Length is: "  + getAverageWordLength(filePath));
        System.out.println("Total Words: " + getWordCount(filePath));
        printWordFrequencies(filePath);
        getMostFrequent(filePath);
    }

    public static File loadFile(String filePath) {
        return inputFile = new File(filePath);
    }

    public static BufferedReader setUpFileReader(File file) throws IOException {
        FileInputStream fileStream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(fileStream);
        BufferedReader inputReader = new BufferedReader(input);
        return inputReader;
    }

    public static int getCharCount(String filePath) throws IOException {
        File file = loadFile(filePath);
        BufferedReader reader = setUpFileReader(file);
        String newLine = null;
        while((currentLine = reader.readLine()) != null) {
            currentLine.replaceAll("\\p{Punct}", "");
            newLine = currentLine.replaceAll("\\s+", "");
            if(!currentLine.equals("\\s+")) {
                totalCharCount += newLine.length();
            }
        }
        return totalCharCount;
    }

    public static int getWordCount(String filePath) throws IOException{
        File file = loadFile(filePath);
        BufferedReader reader = setUpFileReader(file);
        String[] wordList;
        String newLine = null;
        while((currentLine = reader.readLine()) != null) {
            if(!currentLine.equals(" ")) {
                wordList = currentLine.replaceAll("\\p{Punct}", "").split("\\s+");
                totalWordCount = totalWordCount + wordList.length;
            }
        }
        return totalWordCount;
    }

    public static long getAverageWordLength(String filePath) throws IOException {
        File file = loadFile(filePath);
        BufferedReader reader = setUpFileReader(file);
        String[] words = null;

        long countedlength = 0;
        while ((currentLine = reader.readLine()) != null) {
            if(!currentLine.equals(" ")) {
                words = currentLine.replaceAll("\\p{Punct}", "").split("\\s+");
                countedlength = (long) Arrays.stream(words).mapToLong(String::length).average().getAsDouble();
            }
        }
        return countedlength;
    }

    public static HashMap<Integer, Integer> getWordLengthFrequencies(String filePath) throws IOException {
        HashMap<Integer, Integer> countOfLengths = new HashMap<Integer, Integer>();
        File file = loadFile(filePath);
        BufferedReader reader = setUpFileReader(file);

        while ((currentLine = reader.readLine()) != null) {
            currentLine.replaceAll("\\p{Punct}", "");
            String[] words = currentLine.split("\\s+");

            for(String word : words) {
                int currentNumber = 0;
                if(countOfLengths.get(word.length()) != null) {
                    currentNumber = countOfLengths.get(word.length());
                }
                countOfLengths.put(word.length(), currentNumber+1);
            }
        }
        return countOfLengths;
    }

    public static void printWordFrequencies(String filePath) throws IOException {
        HashMap<Integer, Integer> countOfLengths = getWordLengthFrequencies(filePath);
        int totalNumWords = 0;
        int length = 0;

        for (Map.Entry<Integer, Integer> currentEntry : countOfLengths.entrySet()) {
            totalNumWords = currentEntry.getValue();
            length = currentEntry.getKey();
            System.out.println("There are: " + totalNumWords + " words of Length " + length);
        }

    }

    public static void getMostFrequent(String filePath) throws IOException {
        HashMap<Integer, Integer> countOfLengths = getWordLengthFrequencies(filePath);
        int mostFrequentWord = 0;
        int noOfOccurences = 0;
        for (Map.Entry<Integer, Integer> entry : countOfLengths.entrySet()) {
            if(entry.getValue() >= noOfOccurences) {
                noOfOccurences = entry.getValue();
                mostFrequentWord = entry.getKey();
            }
        }
        System.out.println("The most frequently used word Length is: " + mostFrequentWord + " with " + noOfOccurences + " words of that length");
    }
}
