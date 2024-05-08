package main.javarush.cryptoanalysis.constants;

public class Regex {
    private static final String validatePath = "^[a-zA-Zа-яА-Я/\\\\:]+\\.txt$";
    public static String getValidatePath(){
        return validatePath;
    }
}
