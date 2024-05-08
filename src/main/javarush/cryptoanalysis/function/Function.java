package main.javarush.cryptoanalysis.function;

import main.javarush.cryptoanalysis.entity.Result;

public interface Function {
    public Result execute(String[] parameters);
}
