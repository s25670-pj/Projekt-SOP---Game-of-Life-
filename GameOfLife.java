package com.mycompany.gameoflife;

public class GameOfLife {

    public static void main(String[] args) {
        GUIOfGame board = new GUIOfGame();
        board.setLocationRelativeTo(null); //ustawi plansze na srodku
        board.show();
    }
}
