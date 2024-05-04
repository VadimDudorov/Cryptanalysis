package main.javarush.cryptoanalysis.function;

import main.javarush.cryptoanalysis.entity.Result;
import main.javarush.cryptoanalysis.exception.CryptanalysisException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static main.javarush.cryptoanalysis.constants.CryptanalysisConstants.*;
import static main.javarush.cryptoanalysis.repository.ResultCode.*;

public class Encode implements Function {
    @Override
    public Result execute(String[] parameters) {
        String inputPath = parameters[1];
        String outPath = parameters[2];
        int offset = Integer.parseInt(parameters[3]);

        List<Character> textEncryption = new ArrayList<>();
        List<Character> text;

        try {
            text = inputFile(inputPath);
        } catch (IOException e) {
            return new Result(ERROR, new CryptanalysisException(e.getMessage()));
        }

        for (Character iteratorText : text) {
            int index;
            for (int i = 0; i < ALPHABET.length; i++) {
                if (iteratorText.equals(ALPHABET[i])) {
                    index = (i + offset) % ALPHABET.length;
                    textEncryption.add(ALPHABET[index]);
                    break;
                }
            }
        }

        try {
            outputFile(textEncryption, outPath);
        } catch (IOException e) {
            return new Result(ERROR, new CryptanalysisException(e.getMessage()));
        }

        return new Result(OK);
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
}
