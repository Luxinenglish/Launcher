package com.github.luxinenglish;

import javafx.application.Application;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        try {
            Class.forName("javafx.application.Application");
            Application.launch(Launcher.class, args);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erreur\n" + e.getMessage() + " not Found",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
