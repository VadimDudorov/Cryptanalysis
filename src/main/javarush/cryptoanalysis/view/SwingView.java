package main.javarush.cryptoanalysis.view;

import main.javarush.cryptoanalysis.entity.Result;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.javarush.cryptoanalysis.constants.Regex.*;

public class SwingView extends JFrame implements View {
    private JButton buttonStart;
    private JLabel inputText;
    private JLabel outputText;
    private JLabel offsetText;
    private JLabel functionText;
    private JTextField inputFile;
    private JTextField outputFile;
    private JTextField offset;
    private JCheckBox encode;
    private JCheckBox decode;
    private JCheckBox bruteForce;
    private JCheckBox analysis;
    private static final String[] parameters = new String[4];

    public SwingView() {
        initializeWindow();
        initializeListener();
        initializeFrame();
    }

    private void initializeWindow() {
        this.setTitle("Шифр Цезаря");

        buttonStart = new JButton("Start");
        buttonStart.setBounds(50, 260, 100, 40);

        inputText = new JLabel("Введите адрес до файла:");
        inputText.setBounds(25, 10, 250, 50);
        inputFile = new JTextField("/Users/vadimdudorov/Downloads/inDecode.txt");
        inputFile.setBounds(250, 23, 325, 25);

        outputText = new JLabel("Введите адрес куда сохранить:");
        outputText.setBounds(25, 40, 250, 50);
        outputFile = new JTextField("/Users/vadimdudorov/Downloads/out.txt");
        outputFile.setBounds(250, 53, 325, 25);

        functionText = new JLabel("Выберите функцию:");
        functionText.setBounds(25, 100, 250, 50);

        offsetText = new JLabel("Введите шаг:");
        offsetText.setBounds(25, 70, 250, 50);
        offsetText.setVisible(false);
        offset = new JTextField();
        offset.setBounds(250, 83, 325, 25);
        offset.setVisible(false);

        encode = new JCheckBox("encode");
        encode.setBounds(25, 130, 80, 50);

        decode = new JCheckBox("decode");
        decode.setBounds(25, 155, 80, 50);

        bruteForce = new JCheckBox("bruteForce");
        bruteForce.setBounds(25, 180, 100, 50);

        analysis = new JCheckBox("analysis");
        analysis.setBounds(25, 205, 90, 50);

        this.add(buttonStart);
        this.add(inputText);
        this.add(outputText);
        this.add(offsetText);
        this.add(functionText);
        this.add(inputFile);
        this.add(outputFile);
        this.add(offset);
        this.add(encode);
        this.add(decode);
        this.add(bruteForce);
        this.add(analysis);
    }

    private void initializeListener() {
        buttonStart.addActionListener(e -> {
                if (validateStart()) {
                    JOptionPane.showMessageDialog(this, "Программа успешно отработала!",
                            null, JOptionPane.INFORMATION_MESSAGE);
                }

        });

        encode.addActionListener(e -> {
            offsetText.setVisible(true);
            offset.setVisible(true);

            parameters[3] = "1";
            decode.setSelected(false);
            bruteForce.setSelected(false);
            analysis.setSelected(false);
        });
        decode.addActionListener(e -> {
            offsetText.setVisible(true);
            offset.setVisible(true);

            parameters[3] = "2";
            encode.setSelected(false);
            bruteForce.setSelected(false);
            analysis.setSelected(false);
        });
        bruteForce.addActionListener(e -> {
            offsetText.setVisible(false);
            offset.setVisible(false);

            parameters[3] = "3";
            decode.setSelected(false);
            encode.setSelected(false);
            analysis.setSelected(false);
        });
        analysis.addActionListener(e -> {
            offsetText.setVisible(false);
            offset.setVisible(false);

            parameters[3] = "4";
            decode.setSelected(false);
            bruteForce.setSelected(false);
            encode.setSelected(false);
        });
    }

    private void initializeFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 350);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public String[] collectingInformation() {
        return parameters;
    }

    @Override
    public void printResult(Result result) {

    }

    private boolean validatePath(String text) {
        Pattern pattern = Pattern.compile(VALIDATE_PATH);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    private boolean offsetValidate(String offset) {
        Pattern pattern = Pattern.compile(OFFSET_VALIDATE);
        Matcher matcher = pattern.matcher(offset);
        return matcher.matches();
    }

    private boolean validateStart() {
        if (!encode.isSelected() && !decode.isSelected()
                && !bruteForce.isSelected() && !analysis.isSelected()) {
            JOptionPane.showMessageDialog(null, "Укажите функцию",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        parameters[0] = inputFile.getText();
        parameters[1] = outputFile.getText();
        parameters[2] = offset.getText();

        if (!validatePath(parameters[0])) {
            JOptionPane.showMessageDialog(null, "Путь вхоядщего файла не верно указан",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!validatePath(parameters[1])) {
            JOptionPane.showMessageDialog(null, "Путь исходящего файла не верно указан",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if ((parameters[3].equals("1") || parameters[3].equals("2")) && (!offsetValidate(parameters[2]))) {
            JOptionPane.showMessageDialog(null, "Не верно указан шаг",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

}
