package main.javarush.cryptoanalysis.view;

import main.javarush.cryptoanalysis.entity.Result;

public interface View {
    public String[] collectingInformation();
    public Result printResult(Result result);
}
