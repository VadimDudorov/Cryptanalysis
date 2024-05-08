package main.javarush.cryptoanalysis.view;

import main.javarush.cryptoanalysis.entity.Result;

public interface View {
    String[] collectingInformation();

    void printResult(Result result);
}
