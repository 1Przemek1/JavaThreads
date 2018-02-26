package com.company;

public class Main {

    private static final int MAX_SHIPS = 14;

    public static void main(String[] args) {
        Port p = new Port();

        Ship[] ships = new Ship[MAX_SHIPS];
        for (int i = 0; i < MAX_SHIPS; i++) {
            ships[i] = new Ship(p, i);
            ships[i].start();
        }
    }
}
