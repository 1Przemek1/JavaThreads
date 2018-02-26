package com.company;

public class Dock implements Resource {
    private boolean isOccupy;

    public void lock() {
        isOccupy = true;
    }

    public void free() {
        isOccupy = false;
    }

    public boolean isLocked() {
        return isOccupy;
    }
}
