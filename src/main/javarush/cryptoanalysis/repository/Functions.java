package main.javarush.cryptoanalysis.repository;

import main.javarush.cryptoanalysis.function.BruteForce;
import main.javarush.cryptoanalysis.function.Decode;
import main.javarush.cryptoanalysis.function.Encode;
import main.javarush.cryptoanalysis.function.Function;

public enum Functions {
    ENCODE(new Encode()),
    DECODE(new Decode()),
    BRUTE_FORCE(new BruteForce());

    private Function function;

    Functions(Function function) {
        this.function = function;
    }

    public Function getFunction(){
        return function;
    }
}
