package com.company;

import java.util.Random;

public class Ship extends Thread {
    private static final Random random = new Random();

    private static int tugboatsNeeded = 2;
    private int id;
    private Port port;

    Ship(Port p, int i) {
        id = i;
        port = p;
    }

    @Override
    public void run() {
        try {
            System.out.println("Ship is docking " + id);
            Tugboat[] tugboatsForDocking = port.getTugboats(tugboatsNeeded);
            sleep(random.nextInt(10000));

            System.out.println("Ship at port " + id);
            port.freeTugboats(tugboatsForDocking);
            Dock dockUsed = port.getDock();
            sleep(random.nextInt(10000));

            System.out.println("Ship is leaving " + id);
            port.freeDock(dockUsed);
            Tugboat[] tugboatsForLeaving = port.getTugboats(tugboatsNeeded);
            sleep(random.nextInt(10000));

            System.out.println("Ship is at sea " + id);
            port.freeTugboats(tugboatsForLeaving);
        } catch (InterruptedException err) {
            System.out.println(err);
        }
    }
}
