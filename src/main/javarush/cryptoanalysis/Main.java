package main.javarush.cryptoanalysis;

import main.javarush.cryptoanalysis.app.Application;
import main.javarush.cryptoanalysis.controller.MainController;
import main.javarush.cryptoanalysis.entity.Result;
import main.javarush.cryptoanalysis.view.ConsoleView;
import main.javarush.cryptoanalysis.view.SwingView;
import main.javarush.cryptoanalysis.view.View;


public class Main {
    public static void main(String[] args) {
        View view = new SwingView();
        MainController mainController = new MainController(view);
        Application application = new Application(mainController);

        Result resultApp = application.run();
        application.viewResult(resultApp);
    }
}