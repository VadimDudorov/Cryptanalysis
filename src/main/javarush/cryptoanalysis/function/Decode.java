package main.javarush.cryptoanalysis.function;

import main.javarush.cryptoanalysis.entity.Result;
import main.javarush.cryptoanalysis.exception.CryptanalysisException;
import main.javarush.cryptoanalysis.repository.ResultCode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static main.javarush.cryptoanalysis.constants.CryptanalysisConstants.*;

public class Decode implements Function {
    @Override
    public Result execute(String[] parameters) {
        String inputPath = parameters[1];
        String outPath = parameters[2];
        int offset = Integer.parseInt(parameters[3]);

        List<Character> textDecode = new ArrayList<>();
        List<Character> text;
        try {
            text = inputFile(inputPath);
        } catch (IOException e){
            throw new CryptanalysisException(e.getMessage());
        }

        for (Character iterator : text) {
            int index = -1;
            for (int i = 0; i < ALPHABET.length; i++) {
                if (iterator.equals(ALPHABET[i])) {
                    index = Math.abs((i - offset + ALPHABET.length) % ALPHABET.length);
                    textDecode.add(ALPHABET[index]);
                    break;
                }
            }
            if (index == -1){
                throw new CryptanalysisException("Переданного символа нет в алфавите " + iterator);
            }
        }
       try {
           outputFile(textDecode, outPath);
       } catch (IOException e){
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
}
