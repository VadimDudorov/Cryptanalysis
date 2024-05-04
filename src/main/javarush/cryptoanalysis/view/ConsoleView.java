package main.javarush.cryptoanalysis.view;

import main.javarush.cryptoanalysis.entity.Result;
import main.javarush.cryptoanalysis.exception.CryptanalysisException;
import main.javarush.cryptoanalysis.repository.ResultCode;

import java.util.Scanner;

import static main.javarush.cryptoanalysis.constants.CryptanalysisConstants.*;
import static main.javarush.cryptoanalysis.constants.ErrorMessage.*;

public class ConsoleView implements View {

    @Override
    public String[] collectingInformation() {
        String[] parameters = new String[4];
        parameters[0] = selectMode();
        parameters[1] = inputValidatePath();
        parameters[2] = outputValidatePath();
        if (parameters[0].equals("1") || parameters[0].equals("2")) {
            parameters[3] = selectOffset();
        }
        return parameters;
    }

    @Override
    public void printResult(Result result) {
        System.out.println(result.getResultCode());
        System.out.println(result.getException());
    }

    private String selectMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер режима программы: 1 - encode, 2 - decode, 3 - brute force");
        Integer mode = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                mode = scanner.nextInt();
                if (mode == 1 || mode == 2 || mode == 3) {
                    return mode.toString();
                } else {
                    System.out.println("Введитие номер режима из предложенного списка");
                }
            } else {
                System.out.println("Введите целое число");
            }
        }
    }

    private String selectOffset() {
        Scanner scanner = new Scanner(System.in);
        Integer offset;
        while (true) {
            System.out.println("Введите сдвиг");
            if (scanner.hasNextInt()) {
                offset = scanner.nextInt();
                if (offset > 0 && offset < ALPHABET.length) {
                    return offset.toString();
                } else {
                    System.out.println("Введите число больше 0 и меньше " + ALPHABET.length);
                }
            } else {
                System.out.println("Введите целое число");
            }
        }
    }

    private String inputValidatePath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_FILE_SOUTH);
        String text = scanner.nextLine();
        if (text.isEmpty()) {
            return INPUT_FILE;
        } else {
            char[] charsText = text.toCharArray();
            if (charsText.length > 4) {
                int indexTxt = text.lastIndexOf(".");
                String substring;
                if (indexTxt != -1) {
                    substring = text.substring(indexTxt);
                } else {
                    throw new CryptanalysisException(ERROR_TXT);
                }
                if (substring.equals(".txt")) {
                    return text;
                } else {
                    throw new CryptanalysisException(ERROR_TXT);
                }
            } else {
                throw new CryptanalysisException(ERROR_TXT);
            }
        }
    }

    private String outputValidatePath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(OUTPUT_FILE_SOUTH);
        String text = scanner.nextLine();
        if (text.isEmpty()) {
            return OUTPUT_FILE;
        } else {
            char[] charsText = text.toCharArray();
            if (charsText.length > 4) {
                int indexTxt = text.lastIndexOf(".");
                String substring;
                if (indexTxt != -1) {
                    substring = text.substring(indexTxt);
                } else {
                    throw new CryptanalysisException(ERROR_TXT);
                }
                if (substring.equals(".txt")) {
                    return text;
                } else {
                    throw new CryptanalysisException(ERROR_TXT);
                }
            } else {
                throw new CryptanalysisException(ERROR_TXT);
            }
        }
    }
}
