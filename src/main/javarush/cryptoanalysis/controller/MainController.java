package main.javarush.cryptoanalysis.controller;

import main.javarush.cryptoanalysis.view.View;

public class MainController {
    private View view;
    public MainController(View view){
        this.view = view;
    }

    public View getView(){
        return this.view;
    }
}
