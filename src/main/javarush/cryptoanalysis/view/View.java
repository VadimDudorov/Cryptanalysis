package main.javarush.cryptoanalysis.view;

import main.javarush.cryptoanalysis.entity.Result;
import main.javarush.cryptoanalysis.repository.ResultCode;

public interface View {
    public String[] collectingInformation();
    public void printResult(Result result);
}
