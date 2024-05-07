package main.javarush.cryptoanalysis.function;

import main.javarush.cryptoanalysis.constants.Dictionary;
import main.javarush.cryptoanalysis.entity.Result;
import main.javarush.cryptoanalysis.exception.CryptanalysisException;
import main.javarush.cryptoanalysis.repository.ResultCode;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.javarush.cryptoanalysis.constants.CryptanalysisConstants.ALPHABET;

public class BruteForce implements Function {
    @Override
    public Result execute(String[] parameters) {
        String inputPath = parameters[1];
        String outPath = parameters[2];

        List<List<Character>> arraysTextDecode = new ArrayList<>();
        List<Character> textDecode = new ArrayList<>();
        List<Character> text;

        try {
            text = inputFile(inputPath);

            for (int offset = 0; offset < ALPHABET.length; offset++) {
                for (Character iterator : text) {
                    int index = -1;
                    for (int i = 0; i < ALPHABET.length; i++) {
                        if (iterator.equals(ALPHABET[i])) {
                            index = Math.abs((i - offset + ALPHABET.length) % ALPHABET.length);
                            textDecode.add(ALPHABET[index]);
                            break;
                        }
                    }
                    if (index == -1) {
                        throw new CryptanalysisException("Переданного символа нет в алфавите " + iterator);
                    }
                }

                arraysTextDecode.add(textDecode);

                outputFile(decodeArrays(arraysTextDecode), outPath);

            }
        } catch (IOException e) {
            throw new CryptanalysisException(e.getMessage());
        }
        return new Result(ResultCode.OK);
    }

    private List<Character> inputFile(String path) throws IOException {
        List<Character> characterList = new ArrayList<>();
        try (Reader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()) {
                characterList.add((char) bufferedReader.read());
            }
        }
        return characterList;
    }

    private void outputFile(List<Character> outputText, String path) throws IOException {
        char[] outputTextChars = new char[outputText.size()];
        for (int i = 0; i < outputText.size(); i++) {
            outputTextChars[i] = outputText.get(i);
        }
        try (Writer writer = new FileWriter(path);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(outputTextChars);
        }
    }

    private List<Character> decodeArrays(List<List<Character>> arrays) {
        StringBuilder stringsChar = new StringBuilder();

        int[] countChar = new int[ALPHABET.length];

        int counter = 0;

        for (List<Character> iteratorArrays : arrays) {
            for (Character iteratorChar : iteratorArrays) {
                stringsChar.append(iteratorChar);
            }
            String[] strings = new String[iteratorArrays.size()];
            for (int i = 0; i < strings.length; i += 2) {
                strings[i] = stringsChar.substring(i, i + 3);
            }
            for (String string : strings) {
                for (int j = 0; j < ALPHABET.length; j++) {
                    if (string.equals(Dictionary.DICTIONARY_RUS[j])) {
                        countChar[counter]++;
                    }
                }
            }
            counter++;
        }

        Arrays.sort(countChar);
        return arrays.get(countChar[countChar.length - 1]);
    }
}
