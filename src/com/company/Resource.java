package com.company;

public interface Resource {
    void lock();
    void free();
    boolean isLocked();
}
