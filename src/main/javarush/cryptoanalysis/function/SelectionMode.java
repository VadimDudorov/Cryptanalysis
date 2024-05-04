package main.javarush.cryptoanalysis.function;

import main.javarush.cryptoanalysis.entity.Result;
import main.javarush.cryptoanalysis.exception.CryptanalysisException;
import main.javarush.cryptoanalysis.repository.Functions;

import static main.javarush.cryptoanalysis.constants.ModeConstants.*;

public class SelectionMode {
    private String mode;
    public SelectionMode(String mode) {
        this.mode = mode;
    }

    public Function executeMode() {
        return switch (mode){
            case "1" -> Functions.valueOf(ENCODE).getFunction();
            case "2" -> Functions.valueOf(DECODE).getFunction();
            case "3" -> Functions.valueOf(BRUTE_FORCE).getFunction();
            default -> throw new CryptanalysisException("Не найден указаный mode");
        };
    }
}
