import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Exception.InputCryptanalysisException;

public class Main {
    public static final Character[] ALPHABET = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З',
            'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
            'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', '\\', '\n', ';', ':', '!', '?', ' ', '—'};

    public static void main(String[] args) throws IOException {

        Main main = new Main();

//        String pathInput = main.validatePath();
//        List<Character> textEncript = main.inputFile(pathInput);
//        List<Character> encript = main.encryption(textEncript);
//        main.outputFile(encript);

        String pathOutput = main.validatePath();
        List<Character> textDecoding = main.inputFile(pathOutput);
        List<Character> decoding = main.decoding(textDecoding);
        main.outputFile(decoding);
    }

    public String validatePath() {
        System.out.println("Введите путь к файлу (с расширением.txt) \nили нажмите Enter для дефолтного пути");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        if (text.isEmpty()) {
            return "/Users/vadimdudorov/Downloads/inEncrypt.txt";
        } else {
            char[] charsText = text.toCharArray();
            if (charsText.length > 4) {
                int indexTxt = text.lastIndexOf(".");
                String substring = null;
                if (indexTxt != -1) {
                    substring = text.substring(indexTxt);
                } else {
                    throw new InputCryptanalysisException("Формат файла не равен .txt");
                }
                if (substring.equals(".txt")) {
                    return text;
                } else {
                    throw new InputCryptanalysisException("Формат файла не равен .txt");
                }
            } else {
                throw new InputCryptanalysisException("Не верный формат файла");
            }
        }
    }

    public List<Character> inputFile(String path) throws IOException {
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

    public void outputFile(List<Character> outputText) throws IOException {
        char[] outputTextChars = new char[outputText.size()];
        for (int i = 0; i < outputText.size(); i++) {
            outputTextChars[i] = outputText.get(i);
        }
        try (Writer writer = new FileWriter("/Users/vadimdudorov/Downloads/outputText.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(outputTextChars);
        }
    }

    public int displacement() {
        int displacement = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Введите сдвиг");
                if (scanner.hasNextInt()) {
                    displacement = scanner.nextInt();
                    if (displacement > 0 && displacement < 76) {
                        return displacement;
                    } else {
                        System.out.println("Введите число больше 0 и меньше 76");
                    }
                } else {
                    System.out.println("Введите целое число");
                }
            }
        }
    }

    public List<Character> encryption(List<Character> text) {
        int displacement = displacement();
        List<Character> textEncryption = new ArrayList<>();
        for (Character iteratorText : text) {
            int index = 0;
            for (int i = 0; i < ALPHABET.length; i++) {
                if (iteratorText.equals(ALPHABET[i])) {
                    index = (i + displacement) % ALPHABET.length;
                    break;
                }
            }
            textEncryption.add(ALPHABET[index]);
        }
        return textEncryption;
    }

    public List<Character> decoding(List<Character> text) {
        int displacement = displacement();
        List<Character> decodingText = new ArrayList<>();
        for (Character iterator : text) {
            int index = 0;
            for (int i = 0; i < ALPHABET.length; i++) {
                if (iterator.equals(ALPHABET[i])) {
                    index = Math.abs((i - displacement + ALPHABET.length) % ALPHABET.length);
                    break;
                } else {
                    throw new InputCryptanalysisException("Переданного символа нет в алфавите " + iterator);
                }
            }
            decodingText.add(ALPHABET[index]);
        }
        return decodingText;
    }

}