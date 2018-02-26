package com.company;

public class Tugboat implements Resource {
    private boolean isTowing;

    public void lock() {
        isTowing = true;
    }

    public void free() {
        isTowing = false;
    }

    public boolean isLocked() {
        return isTowing;
    }
}
