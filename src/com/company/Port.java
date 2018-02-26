package com.company;

import java.util.concurrent.Semaphore;

public class Port {

    private static final int MAX_TUGBOATS = 10;
    private static final int MAX_DOCKS = 5;
    private final Semaphore ava_tugboats = new Semaphore(MAX_TUGBOATS, true);
    private final Semaphore ava_docks = new Semaphore(MAX_DOCKS, true);

    private Tugboat[] tugboats;
    private Dock[] docks;

    Port() {
        tugboats = new Tugboat[MAX_TUGBOATS];
        docks = new Dock[MAX_DOCKS];

        for(int i = 0 ; i < MAX_TUGBOATS; i++) {
            tugboats[i] = new Tugboat();
        }

        for(int i = 0 ; i < MAX_DOCKS; i++) {
            docks[i] = new Dock();
        }
    }

    public Dock getDock() throws InterruptedException {
        ava_docks.acquire();
        return getNextAvailableDock();
    }

    public void freeDock(Dock d) {
        if (markDockAsUnused(d))
            ava_docks.release();
    }

    public Tugboat[] getTugboats(int count) throws InterruptedException {
        Tugboat[] t = new Tugboat[count];

        for(int i = 0; i < count; i++) {
            ava_tugboats.acquire();
            t[i] = getNextAvailableTugboat();
        }

        return t;
    }

    public void freeTugboats(Tugboat[] tugboats) {
        for(Tugboat _t : tugboats) {
            if (markTugboatAsUnused(_t))
                ava_tugboats.release();
        }
    }

    protected synchronized Dock getNextAvailableDock() {
        for (int i = 0; i < MAX_DOCKS; ++i) {
            if (!docks[i].isLocked()) {
                docks[i].lock();
                return docks[i];
            }
        }
        return null; // not reached
    }

    protected synchronized boolean markDockAsUnused(Dock item) {
        for (int i = 0; i < MAX_DOCKS; ++i) {
            if (item == docks[i]) {
                if (docks[i].isLocked()) {
                    docks[i].free();
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }

    protected synchronized Tugboat getNextAvailableTugboat() {
        for (int i = 0; i < MAX_TUGBOATS; ++i) {
            if (!tugboats[i].isLocked()) {
                tugboats[i].lock();
                return tugboats[i];
            }
        }
        return null; // not reached
    }

    protected synchronized boolean markTugboatAsUnused(Tugboat item) {
        for (int i = 0; i < MAX_TUGBOATS; ++i) {
            if (item == tugboats[i]) {
                if (tugboats[i].isLocked()) {
                    tugboats[i].free();
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }
}
