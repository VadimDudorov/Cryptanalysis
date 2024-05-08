package main.javarush.cryptoanalysis.app;

import main.javarush.cryptoanalysis.controller.MainController;
import main.javarush.cryptoanalysis.entity.Result;
import main.javarush.cryptoanalysis.function.Function;
import main.javarush.cryptoanalysis.function.SelectionMode;


public class Application {
    private final MainController mainController;

    public Application(MainController mainController){
        this.mainController = mainController;
    }

    public Result run(){
        String[] parameters = mainController.getView().collectingInformation();
        String mode = parameters[0];
        Function function = new SelectionMode(mode).executeMode();
        return function.execute(parameters);
    }

    public void viewResult (Result result){
        mainController.getView().printResult(result);
    }
}
