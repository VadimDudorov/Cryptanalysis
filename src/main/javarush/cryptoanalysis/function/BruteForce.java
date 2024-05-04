package main.javarush.cryptoanalysis.function;

import main.javarush.cryptoanalysis.entity.Result;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BruteForce implements Function {
    @Override
    public Result execute(String[] parameters) {
        //TODO написать логику жеского перебора
        return null;
    }

    private List<Character> inputFile(String path) throws IOException {
        List<Character> characterList = new ArrayList<>();
        try (Reader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()) {
                characterList.add((char) bufferedReader.read());
            }
        }
        // Закоментированный код под вопросом стоил его оставлять или нет
//        List<Character> copyCharacterList = new ArrayList<>(characterList);
//        for (Character characterListIterator : copyCharacterList) {
//            int counter = 1;
//            for (Character alphabetIterator : ALPHABET) {
//                if (alphabetIterator.equals(characterListIterator)) {
//                    counter--;
//                    break;
//                }
//            }
//            if (counter == 1) {
//                characterList.remove(characterListIterator);
//            }
//        }
        return characterList;
    }

    private void outputFile(List<Character> outputText) throws IOException {
        char[] outputTextChars = new char[outputText.size()];
        for (int i = 0; i < outputText.size(); i++) {
            outputTextChars[i] = outputText.get(i);
        }
        try (Writer writer = new FileWriter("/Users/vadimdudorov/Downloads/outputText.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(outputTextChars);
        }
    }
}
